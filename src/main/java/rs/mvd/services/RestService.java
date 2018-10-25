package rs.mvd.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.keycloak.OAuth2Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.mvd.domain.UsernamePasswordModel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    private final String devPublicOkUrl = "https://apinew.dev-9mentors.com/9mentors/public/ok";
    private final String devTopicForMSg = "https://apinew.dev-9mentors.com/9mentors/private/chat";



    public CloseableHttpResponse sendPostRequestForm(/*String path,*/ UrlEncodedFormEntity form/*, String realmName*/) throws IOException {
        //http://localhost:8080/auth/realms/MvdRealm/protocol/openid-connect/token
//        HttpPost post = new HttpPost(KeycloakUriBuilder.fromUri("http://localhost:8080/auth").path(path).build(realmName));
        HttpPost post = new HttpPost(keycloakUrl + "/realms/MvdRealm/protocol/openid-connect/token");
        post.setEntity(form);
        return getHttpClient().execute(post);
    }

    private CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    public UrlEncodedFormEntity createFormLogin(UsernamePasswordModel usernamePasswordModel) throws UnsupportedEncodingException {
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("username", usernamePasswordModel.getUsername()));
        formParams.add(new BasicNameValuePair("password", usernamePasswordModel.getPassword()));
        formParams.add(new BasicNameValuePair(OAuth2Constants.GRANT_TYPE, "password"));
        formParams.add(new BasicNameValuePair(OAuth2Constants.CLIENT_ID, "mvd-app"));
        return new UrlEncodedFormEntity(formParams, "UTF-8");
    }

    public void sendRequestPublicOk() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    URL request_url = new URL(devPublicOkUrl);
                    HttpURLConnection http_conn = (HttpURLConnection) request_url.openConnection();
                    System.out.println("Response code: " + http_conn.getResponseCode());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("Timing: " + (System.currentTimeMillis() - startTime));
    }


    public void sendTopicMessage(String token) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    HttpClient httpclient = HttpClients.createDefault();
                    HttpPost httppost = new HttpPost(devTopicForMSg);
                    StringEntity stringEntity = new StringEntity("{\"message\": \"ljudiiiiiii, ala smrdi ovde!\", \"topicId\": 2362, \"files\": []}");
                    httppost.setEntity(stringEntity);
                    httppost.setHeader("x-tenant-name", "9mentors");
                    httppost.setHeader("authorization", token);
                    httppost.setHeader("accept", "application/json, text/plain, */*");
                    httppost.setHeader("Content-Type", "application/json; charset=utf8");

                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    System.out.println("************* " + response.getStatusLine().getStatusCode());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("Timing: " + (System.currentTimeMillis() - startTime));
    }

}
