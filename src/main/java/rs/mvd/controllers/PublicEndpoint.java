package rs.mvd.controllers;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
import java.util.List;

@RestController
@Path("/public")
public class PublicEndpoint {

    @Autowired
    private RestService restService;
    @Autowired
    private EmailService emailService;

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
        emailService.sendMail("marko.milenkovic@htecgroup.com");
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
