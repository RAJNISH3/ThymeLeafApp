package com.sample.thymeleaf.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.sample.thymeleaf.ThymeleafConfiguration;
import com.sample.thymeleaf.model.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JsonTxtController {

    public static final String CURRENCY_SEK = "USD";
    public static final String CURRENCY_NTD = "INR";
    
    
   
    
    /* Instance variable(s): */
    @Autowired
    @Qualifier("messageTemplateEngine")
    protected SpringTemplateEngine mMessageTemplateEngine;
    
    @RequestMapping(value = "/text", method =RequestMethod.GET )
    public String  tempText( Model model) {
        /* Retrieve text message with inserted parameter values. */
        final Context theContext = new Context();
//        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_FROMCURRENCY_PARAM, CURRENCY_SEK);
//        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_TOCURRENCY_PARAM, CURRENCY_NTD);
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_FNAME_PARAM, "Tim");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_LNAME_PARAM, "Zan");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_TAG_PARAM, "#java #thyme #app");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_PINFO_PARAM, "thymeleaf app with text template");
        final String theTextMessage =
//                mMessageTemplateEngine.process("text/currency_conversion_request", theContext);
                mMessageTemplateEngine.process("text/personalDetails", theContext);
       // model.addAttribute("text", theTextMessage);
        log.error("Hello 2nd Controller");
        return theTextMessage;
    }
    
    //TEXT LOOP
    @RequestMapping(value = "/textloop", method =RequestMethod.GET )
    public String  tempTextLoop( Model model) {
        /* Retrieve text message with inserted parameter values. */
        final Context theContext = new Context();
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tim1", "Zoltan1"));
        personList.add(new Person("Tim2", "Zoltan2"));
        personList.add(new Person("Tim3", "Zoltan3"));
        personList.add(new Person("Tim4", "Zoltan4"));
        personList.add(new Person("Tim5", "Zoltan5"));
//        Map<String,Object> variables = new HashMap<>();
//        variables.put(ThymeleafConfiguration.TEMPLATE_FNAME_PARAM, "Tim");
//        variables.put(ThymeleafConfiguration.TEMPLATE_FNAME_PARAM, "Tim2");
//        variables.put(ThymeleafConfiguration.TEMPLATE_FNAME_PARAM, "tim3");
//        variables.put(ThymeleafConfiguration.TEMPLATE_LNAME_PARAM, "Zan1");
//        variables.put(ThymeleafConfiguration.TEMPLATE_LNAME_PARAM, "Zan2");
//        variables.put(ThymeleafConfiguration.TEMPLATE_LNAME_PARAM, "Zan3");
//        variables.put(ThymeleafConfiguration.TEMPLATE_TAG_PARAM, "#java #thyme #app");
//        variables.put(ThymeleafConfiguration.TEMPLATE_PINFO_PARAM, "thymeleaf app with text template");
        theContext.setVariable("PersonalInfo", personList);
        
        final String theTextMessage =
                mMessageTemplateEngine.process("text/personalDtlsLoop", theContext);
       // model.addAttribute("text", theTextMessage);
        log.error("Hello 2nd Controller");
        return theTextMessage;
    }
    
    
    @RequestMapping(value = "/json", method =RequestMethod.GET )
    public String  templateJosn( Model model) {
        /* Retrieve JSON message with inserted parameter values. */
        final Context theContext = new Context();
        //theContext.setVariable(ThymeleafConfiguration.TEMPLATE_FROMCURRENCY_PARAM, CURRENCY_SEK);
        //theContext.setVariable(ThymeleafConfiguration.TEMPLATE_TOCURRENCY_PARAM, CURRENCY_NTD);
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_FNAME_PARAM, "Tim");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_LNAME_PARAM, "Zan");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_TAG_PARAM, "#java #thyme #app");
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_PINFO_PARAM, "thymeleaf app with text template");
        final String theJsonMessage =
//            mMessageTemplateEngine.process("json/currency_conversion_request", theContext);
                mMessageTemplateEngine.process("json/personalDetails", theContext);
       // model.addAttribute("text", theTextMessage);
        log.error("Hello 2nd Controller");
        return theJsonMessage;
    }
    
    
    
}
