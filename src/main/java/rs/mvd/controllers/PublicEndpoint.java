package rs.mvd.controllers;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.mvd.EmailGenerator;
import rs.mvd.domain.LayoutProperties;
import rs.mvd.exceptions.BadRequestException;
import rs.mvd.factories.ResponseFactory;
import rs.mvd.domain.UsernamePasswordModel;
import rs.mvd.response.Responses;
import rs.mvd.services.EmailService;
import rs.mvd.services.RestService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Path("/public")
public class PublicEndpoint {

    @Autowired
    private RestService restService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailGenerator emailGenerator;

    @GET
    @Path("ok")
    @Produces(MediaType.APPLICATION_JSON)
    public Responses ok() {
        return ResponseFactory.ok("Public Endpoint ok!");
    }

    @GET
    @Path("email")
    @Produces(MediaType.APPLICATION_JSON)
    public Responses sendMail() throws MessagingException {
        Map<String, String> accountCreateValues = new HashMap<>();
        accountCreateValues.put("first_name", "Marko");
        accountCreateValues.put("tenantName", "9mentors");
        accountCreateValues.put("code", "123456789");

        String templateName = "account-create";
        LayoutProperties layoutProperties = emailGenerator.getLayoutProperties(templateName);
        String accountCreateHtmlCode = emailGenerator.getHtmlTemplateCode("account-create");


        StrSubstitutor substitutor = new StrSubstitutor(accountCreateValues);
        String resolvedTemplate = substitutor.replace(accountCreateHtmlCode);

        String html_code = emailGenerator.getLayout(layoutProperties.getLayout());
        String finalHtmlCode = emailGenerator.getFinalHtmlCodeForLayout(layoutProperties, resolvedTemplate, html_code);
        emailService.sendMail("aleksandar.jocic@htecgroup.com", finalHtmlCode, layoutProperties.getTitle());
        return ResponseFactory.ok("Email sent!");
    }

    @GET
    @Path("notOk")
    @Produces(MediaType.APPLICATION_JSON)
    public Responses notOk() {
        throw new BadRequestException("radi");
    }

    @GET
    @Path("ex")
    @Produces(MediaType.APPLICATION_JSON)
    //namerno zelim da dobijem exception da bih video kakav ce biti response
    public Responses ex() {
        List<String> l = new ArrayList<>();
        String s = l.get(0);
        System.out.println(s);
        return ResponseFactory.ok("S: " + s);
    }

    //ZAKOMENTARISANE STVARI JER IMAM SAMO JEDAN REALM, KADA BIH IMAO VISE I SLAO KROZ PATH ONDA KORISTITI ZAKOMENTARISANO
    @POST
    @Path("login") // "/{realmName}/login"
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Responses getTokenForUser(@Valid UsernamePasswordModel usernamePasswordModel/*,
                                          @PathVariable("realmName") String realmName*/) throws IOException {

        try (CloseableHttpResponse response = restService.sendPostRequestForm(/*ServiceUrlConstants.TOKEN_PATH,*/
                restService.createFormLogin(usernamePasswordModel)/*, realmName*/)) {

            if (response.getStatusLine().getStatusCode() == 401) {
                return ResponseFactory.badRequest("Bad credentials");
            }
            String json = EntityUtils.toString(response.getEntity(), "UTF-8");
            return ResponseFactory.ok(json);
        } catch (ParseException | IOException ex) {
            return ResponseFactory.badRequest("Exception: " + ex.getMessage());
        }
    }

}
