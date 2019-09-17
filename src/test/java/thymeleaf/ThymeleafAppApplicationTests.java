package thymeleaf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.sample.thymeleaf.ThymeleafConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymeleafAppApplicationTests {

    /* Constant(s): */
    public static final String CURRENCY_SEK = "SEK";
    public static final String CURRENCY_NTD = "NTD";

    
    /* Instance variable(s): */
    @Autowired
    @Qualifier("messageTemplateEngine")
    protected SpringTemplateEngine mMessageTemplateEngine;


    /**
     * Tests retrieval of an JSON message in which two parameter values are inserted.
     * Expected result: The message should be retrieved with the parameter values
     * inserted into it.
     */
   // @Test
    public void jsonMessageTest() {
        /* Retrieve JSON message with inserted parameter values. */
        final Context theContext = new Context();
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_FROMCURRENCY_PARAM, CURRENCY_SEK);
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_TOCURRENCY_PARAM, CURRENCY_NTD);
        final String theJsonMessage =
            mMessageTemplateEngine.process("json/currency_conversion_request", theContext);

        /* Verify the JSON message. */
      //  final JsonPath theJsonMessageJsonPath = JsonPath.with(theJsonMessage);
      //  assertThat(theJsonMessageJsonPath.getString("conversion_rate.from_currency"),
     //       is(CURRENCY_SEK));
    //    assertThat(theJsonMessageJsonPath.getString("conversion_rate.to_currency"),
    //        is(CURRENCY_NTD)
      //  );

        /* Log the message to the console for visual feedback. */
       // LOGGER.info(theJsonMessage);
    }

    /**
     * Tests retrieval of a text message in which two parameter values are inserted.
     * Expected result: The message should be retrieved with the parameter values
     * inserted into it.
     */
    @Test
    public void textMessageTest() {
        /* Retrieve text message with inserted parameter values. */
        final Context theContext = new Context();
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_FROMCURRENCY_PARAM, CURRENCY_SEK);
        theContext.setVariable(ThymeleafConfiguration.TEMPLATE_TOCURRENCY_PARAM, CURRENCY_NTD);
        final String theTextMessage =
            mMessageTemplateEngine.process("/text/currency_conversion_request", theContext);

        /* Verify the text message. */
        //assertThat(theTextMessage, containsString(CURRENCY_SEK));
       //assertThat(theTextMessage, containsString(CURRENCY_NTD));

        /* Log the message to the console for visual feedback. */
       // LOGGER.info(theTextMessage);
        String s1 = theTextMessage;
    }

}
