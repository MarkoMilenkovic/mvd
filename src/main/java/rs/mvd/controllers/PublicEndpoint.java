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
import rs.mvd.services.RestService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicEndpoint {

    @Autowired
    private RestService restService;

    @GetMapping(value = "ok", produces = "application/json")
    public Responses ok() {
        return ResponseFactory.ok("Public Endpoint ok!");
    }

    @GetMapping(value = "notOk", produces = "application/json")
    public Responses notOk() {
        throw new BadRequestException("radi");
    }

    @GetMapping(value = "ex", produces = "application/json")
    //namerno zelim da dobijem exception da bih video kakav ce biti response
    public Responses ex() {
        List<String> l = new ArrayList<>();
        String s = l.get(0);
        System.out.println(s);
        return ResponseFactory.ok("S: " + s);
    }

    //ZAKOMENTARISANE STVARI JER IMAM SAMO JEDAN REALM, KADA BIH IMAO VISE I SLAO KROZ PATH ONDA KORISTITI ZAKOMENTARISANO
    @PostMapping(value = "login" , produces = "application/json", consumes = "application/json")
    public Responses getTokenForUser(@RequestBody @Valid UsernamePasswordModel usernamePasswordModel/*,
                                          @PathVariable("realmName") String realmName*/) throws IOException {

        try (CloseableHttpResponse response = restService.sendPostRequestForm(/*ServiceUrlConstants.TOKEN_PATH,*/
                restService.createFormLogin(usernamePasswordModel)/*, realmName*/)) {

            if (response.getStatusLine().getStatusCode() == 401) {
                throw new BadRequestException("Bad credentials");
            }
            String json = EntityUtils.toString(response.getEntity(), "UTF-8");
            return ResponseFactory.ok(json);
        } catch (ParseException | IOException ex) {
            return ResponseFactory.badRequest("Exception: " + ex.getMessage());
        }
    }

}
