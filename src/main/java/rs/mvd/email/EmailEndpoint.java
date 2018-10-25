package rs.mvd.email;


import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import rs.mvd.domain.LayoutProperties;
import rs.mvd.factories.ResponseFactory;
import rs.mvd.response.Responses;

import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

import static rs.mvd.email.LayoutVariables.*;
import static rs.mvd.email.LayoutVariables.BODY;
import static rs.mvd.email.LayoutVariables.FULL_TENANT_URL;
import static rs.mvd.email.LayoutVariables.LINK;
import static rs.mvd.email.LayoutVariables.TENANT_NAME;
import static rs.mvd.email.TemplateVariables.*;
import static rs.mvd.email.TemplateVariables.FULL_TENANT_NAME;

@RestController
@Path("/email")
public class EmailEndpoint {

    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailGenerator emailGenerator;

    @GET
    @Path("{template}")
    @Produces(MediaType.APPLICATION_JSON)
    public Responses sendMail(@PathParam("template") String templateName) throws MessagingException {
        Map<String, String> propertiesMap = populateTemplateVariablesMap();

        LayoutProperties layoutProperties = emailGenerator.getLayoutProperties(templateName);
        String templateHtmlCode = emailGenerator.getHtmlTemplateCode(templateName);

        StrSubstitutor substitutor = new StrSubstitutor(propertiesMap);
        String resolvedTemplate = substitutor.replace(templateHtmlCode);

        String html_code = emailGenerator.getLayout(layoutProperties.getLayout());
        Map<String, String> layoutPropertiesMap = populateLayoutVariablesMap(layoutProperties, resolvedTemplate);
        String finalHtmlCode = emailGenerator.getFinalHtmlCodeForLayout(layoutPropertiesMap, html_code);

        emailService.sendMail("marko.milenkovic@htecgroup.com", finalHtmlCode, layoutProperties.getTitle());
        return ResponseFactory.ok("Email sent!");
    }


    private Map<String, String> populateTemplateVariablesMap(){
        Map<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put(FIRST_NAME.getValue(), "Marko");
        propertiesMap.put(GROUP_NAME.getValue(), "Grupaaaaaa");
        propertiesMap.put(TOPIC_TITLE.getValue(), "Jocin kutak");
        propertiesMap.put(LINK.getValue(), "https://htecgroup.9mcollab.com/conclusion-view");
        propertiesMap.put(FULL_TENANT_URL.getValue(), "9mentors.com");
        propertiesMap.put(FULL_TENANT_NAME.getValue(), "9mentors");
        propertiesMap.put(CODE.getValue(), "123456789");
        propertiesMap.put(TENANT_NAME.getValue(), "9mentors");
        propertiesMap.put(TENANT.getValue(), "9mentors");
        propertiesMap.put(URL.getValue(), "9mentors.com");
        propertiesMap.put(GROUP_TITLE.getValue(), "JOCIN KUTAK");
        propertiesMap.put(LINK_CONCLUSION.getValue(), "9mentors.com");
        propertiesMap.put(EXPIRY_DATE.getValue(), "12.09.1993.");
        propertiesMap.put(LINK_GROUP.getValue(), "JOCIN KUTAK");
        propertiesMap.put(GROUP.getValue(), "JOCIN KUTAK");
        propertiesMap.put(URL_EXPLORE.getValue(), "9mentors.com");
        propertiesMap.put(LINK_DIRECT_MESSAGES.getValue(), "9mentors.com");
        propertiesMap.put(COUNT_OF_DM_NOT_READ.getValue(), "14");
        propertiesMap.put(LINK_PREFERENCES.getValue(), "9mentors.com");
        propertiesMap.put("weekly_email_body", "9mentors");
        return propertiesMap;
    }

    private Map<String, String> populateLayoutVariablesMap(LayoutProperties layoutProperties, String resolvedTemplate){
        Map<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put(PICTURE_URL.getValue(), layoutProperties.getPictureUrl());
        propertiesMap.put(TITLE.getValue(), layoutProperties.getTitle());
        propertiesMap.put(BUTTON_TEXT.getValue(), layoutProperties.getButtonText());
        propertiesMap.put(TENANT_NAME.getValue(), "9mentors");
        propertiesMap.put(FULL_TENANT_NAME.getValue(), "9mentors");
        propertiesMap.put(FULL_TENANT_URL.getValue(), "9mentors.com");
        propertiesMap.put(BODY.getValue(), resolvedTemplate);
        propertiesMap.put(LINK.getValue(), "9mentors.com");
        propertiesMap.put(DISPLAY_LINK.getValue(), "display link");
        return propertiesMap;
    }

}

