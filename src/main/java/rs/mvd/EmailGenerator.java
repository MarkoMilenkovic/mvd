package rs.mvd;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.mvd.domain.LayoutProperties;
import rs.mvd.repository.LayoutRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static rs.mvd.services.EmailPropertiesEnum.*;

@Service
public class EmailGenerator {

    @Autowired
    private LayoutRepository layoutRepository;

    private static String PATH_PREFIX = "/home/marko/Desktop/email/";

    public String getHtmlTemplateCode(String templateName) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(PATH_PREFIX + templateName + "/" + templateName + ".txt"));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No html template code found!");
        }
    }

    public String getLayout(String layout) {
        try {
            if (layout.equals("layout_button")) {
                byte[] encodedLayoutButton = Files.readAllBytes(Paths.get(PATH_PREFIX + "layout_button.txt"));
                return new String(encodedLayoutButton, StandardCharsets.UTF_8);
            }
            if (layout.equals("layout_no_button")) {
                byte[] encodedLayoutButton = Files.readAllBytes(Paths.get(PATH_PREFIX + "layout_no_button.txt"));
                return new String(encodedLayoutButton, StandardCharsets.UTF_8);
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getFinalHtmlCodeForLayout(LayoutProperties layoutProperties, String resolvedTemplate, String html_code) {
        Map<String, String> values = new HashMap<>();
        values.put(PICTURE_URL.getValue(), layoutProperties.getPictureUrl());
        values.put(TITLE.getValue(), layoutProperties.getTitle());
        values.put(BUTTON_TEXT.getValue(), layoutProperties.getButtonText());
        values.put(TENANT_NAME.getValue(), "9mentors");
        values.put(FULL_TENANT_NAME.getValue(), "9mentors");
        values.put(FULL_TENANT_URL.getValue(), "9mentors.com");
        values.put(BODY.getValue(), resolvedTemplate);
        StrSubstitutor substitutor = new StrSubstitutor(values);
        return substitutor.replace(html_code);
    }

    public LayoutProperties getLayoutProperties(String templateName) {
//        try {
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(new FileReader(
//                    PATH_PREFIX + templateName + "/" + templateName + "-properties.txt"));
//            JSONObject jsonObject = (JSONObject) obj;
//
//            String title = (String) jsonObject.get("title");
//            String buttonText = (String) jsonObject.get("button_text");
//            String pictureUrl = (String) jsonObject.get("picture_url");
//            String layout = (String) jsonObject.get("layout");
//            return new EmailProperties(title, buttonText, pictureUrl, layout);
//        } catch (ParseException | IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("No email properties found!");
//        }
        return layoutRepository.getLayoutProperties(templateName);
    }

}
