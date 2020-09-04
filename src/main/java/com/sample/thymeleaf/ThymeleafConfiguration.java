package com.sample.thymeleaf;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
@EnableAutoConfiguration
public class ThymeleafConfiguration {

    /* Constant(s): */
    /**
     * Path to root package/directory in which the different types of message templates are found.
     */
    public static final String TEMPLATES_BASE = "classpath:/templates/";
    /** Pattern relative to templates base used to match JSON templates. */
    public static final String JSON_TEMPLATES_RESOLVE_PATTERN = "json/*";
    /** Pattern relative to templates base used to match text templates. */
    public static final String TEXT_TEMPLATES_RESOLVE_PATTERN = "text/*";
    /* Parameter keys for New message templates. */
    public static final String TEMPLATE_FNAME_PARAM = "first_name";
    public static final String TEMPLATE_LNAME_PARAM = "last_name";
    public static final String TEMPLATE_TAG_PARAM = "tags";
    public static final String TEMPLATE_PINFO_PARAM = "info";
    /**
     * Creates the template resolver that retrieves text message payloads.
     *
     * @return Template resolver.
     */
    @Autowired
    ThymeleafRemoteResourceResolver thymeRemoteConfig;

    @Bean
    public SpringResourceTemplateResolver textMessageTemplateResolver() {
        SpringResourceTemplateResolver theResourceTemplateResolver = new SpringResourceTemplateResolver();
        theResourceTemplateResolver.setPrefix(TEMPLATES_BASE);
        theResourceTemplateResolver.setResolvablePatterns(
            Collections.singleton(TEXT_TEMPLATES_RESOLVE_PATTERN));
        theResourceTemplateResolver.setSuffix(".txt");
        theResourceTemplateResolver.setTemplateMode("text");
        theResourceTemplateResolver.setCharacterEncoding("UTF-8");
        theResourceTemplateResolver.setCacheable(false);
        theResourceTemplateResolver.setOrder(1);
        return theResourceTemplateResolver;
    }

//    @Primary
//    @Bean
//    public ThymeleafRemoteResourceResolver textCustomResolver() {
//        thymeRemoteConfig.setResolvablePatterns(
//            Collections.singleton(TEXT_TEMPLATES_RESOLVE_PATTERN));
//        thymeRemoteConfig.setTemplateMode("text");
//        thymeRemoteConfig.setCacheable(false);
//        thymeRemoteConfig.setOrder(1);
//        return thymeRemoteConfig;
//    }

    /**
     * Creates the template resolver that retrieves JSON message payloads.
     *
     * @return Template resolver.
     */
    @Bean
    public SpringResourceTemplateResolver jsonMessageTemplateResolver() {
        SpringResourceTemplateResolver theResourceTemplateResolver = new SpringResourceTemplateResolver();
        theResourceTemplateResolver.setPrefix(TEMPLATES_BASE);
        theResourceTemplateResolver.setResolvablePatterns(
            Collections.singleton(JSON_TEMPLATES_RESOLVE_PATTERN));
        theResourceTemplateResolver.setSuffix(".json");
        /*
         * There is no json template mode so the next line has been commented out. Thymeleaf will recognize the ".json"
         * template resource suffix so there is no need to set a template mode.
         */
        // theResourceTemplateResolver.setTemplateMode("json");
        theResourceTemplateResolver.setCharacterEncoding("UTF-8");
        theResourceTemplateResolver.setCacheable(false);
        theResourceTemplateResolver.setOrder(2);
        return theResourceTemplateResolver;
    }

    /**
     * Creates the template engine for all message templates.
     *
     * @param inTemplateResolvers
     *            Template resolver for different types of messages etc. Note that any template resolvers defined
     *            elsewhere will also be included in this collection.
     * @return Template engine.
     */
    @Bean
    public SpringTemplateEngine messageTemplateEngine(
            final Collection<SpringResourceTemplateResolver> inTemplateResolvers) {
        final SpringTemplateEngine theTemplateEngine = new SpringTemplateEngine();
        for (SpringResourceTemplateResolver theTemplateResolver : inTemplateResolvers) {
            theTemplateEngine.addTemplateResolver(theTemplateResolver);
            theTemplateEngine.addTemplateResolver(thymeRemoteConfig);
        }
        return theTemplateEngine;
    }
}
