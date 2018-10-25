package rs.mvd.email;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.mvd.domain.LayoutProperties;
import rs.mvd.repository.LayoutRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

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

    public String getFinalHtmlCodeForLayout(Map<String, String> layoutPropertiesMap, String html_code) {
        StrSubstitutor substitutor = new StrSubstitutor(layoutPropertiesMap);
        return substitutor.replace(html_code);
    }

    public LayoutProperties getLayoutProperties(String templateName) {
        return layoutRepository.getLayoutProperties(templateName);
    }

}
