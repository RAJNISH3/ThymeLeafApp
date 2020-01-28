package com.sample.thymeleaf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

@Service
public class ThymeleafRemoteResourceResolver extends StringTemplateResolver {

    private final static String PREFIX = "";

    public ThymeleafRemoteResourceResolver() {
        setResolvablePatterns(Collections.singleton("*"));
    }
    
    
    
    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate,
            String template, Map<String, Object> templateResolutionAttributes) {

        // ThymeleafTemplate is our internal object that contains the content.
        // You should change this to match you're set up.
        InputStream inp = Thread.currentThread().getContextClassLoader().getResourceAsStream("/templates/text/personalDetails.txt");
        String s1 = null;
        try {
            s1 = convertInputStreamToString(inp);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if (inp != null) {
            return super.computeTemplateResource(configuration, "txt", s1,
                templateResolutionAttributes);
        }
        return null;
    }
    private static String convertInputStreamToString(InputStream inputStream) 
            throws IOException {

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            return result.toString(StandardCharsets.UTF_8.name());

        }
}
