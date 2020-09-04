package com.sample.thymeleaf.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.sample.thymeleaf.ThymeleafConfiguration;
import com.sample.thymeleaf.ThymeleafRemoteResourceResolver;
import com.sample.thymeleaf.model.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JsonTxtController {

    @Autowired
    private ThymeleafRemoteResourceResolver thymeRemoteConfig;
   
    
    /* Instance variable(s): */
    @Autowired
    @Qualifier("messageTemplateEngine")
    protected SpringTemplateEngine mMessageTemplateEngine;
    
    
    
    @RequestMapping(value = "/text", method =RequestMethod.GET )
    public String  tempText( Model model) {
        /* Retrieve text message with inserted parameter values. */
        final Context theContext = new Context();
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_FNAME_PARAM, "Tim");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_LNAME_PARAM, "Zan");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_TAG_PARAM, "#java #thyme #app");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_PINFO_PARAM, "thymeleaf app with text template");
        thymeRemoteConfig.setTemplateMode("txt");
        final String theTextMessage =
                mMessageTemplateEngine.process("", theContext);
        
        return theTextMessage;
    }
    
    //TEXT file template generation api
    @RequestMapping(value = "/textloop", method =RequestMethod.GET )
    public String  thymeleafTemplateTextProcess( Model model) {
        /* Retrieve text message with inserted parameter values. */
        final Context theContext = new Context();
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tim1", "Zoltan1"));
        personList.add(new Person("Tim2", "Zoltan2"));
        personList.add(new Person("Tim3", "Zoltan3"));
        personList.add(new Person("Tim4", "Zoltan4"));
        personList.add(new Person("Tim5", "Zoltan5"));
        theContext.setVariable("PersonalInfo", personList);
        
        final String theTextMessage =
                mMessageTemplateEngine.process("text/personalDtlsLoop", theContext);
        log.error(theTextMessage);
        return theTextMessage;
    }
    
    
    @RequestMapping(value = "/json", method =RequestMethod.GET )
    public String  thymeleafTemplateJsonProcess( Model model) {
        log.error("templateJson");
        final Context theContext = new Context();
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_FNAME_PARAM, "Tim");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_LNAME_PARAM, "Zan");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_TAG_PARAM, "#java #thyme #app");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_PINFO_PARAM, "thymeleaf app with text template");
        final String theJsonMessage =
                mMessageTemplateEngine.process("json/personalDetails", theContext);
        return theJsonMessage;
    }
    
    
    
}
