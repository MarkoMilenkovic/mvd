package rs.mvd.services;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.keycloak.OAuth2Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.mvd.domain.UsernamePasswordModel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

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

}
