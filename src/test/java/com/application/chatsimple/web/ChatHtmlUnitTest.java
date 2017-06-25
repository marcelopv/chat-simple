package com.application.chatsimple.web;

import com.application.chatsimple.config.ApplicationConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebMvcTest
@Ignore("Not working, need to investigate why is not serving html static file.")
public class ChatHtmlUnitTest {

    private static String URL;

    @Autowired
    private WebClient webClient;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup(){
        URL = "http://localhost/index.html";
        webClient =  MockMvcWebClientBuilder
                .webAppContextSetup(context)
                .build();
        webClient.getOptions().setThrowExceptionOnScriptError(false);
    }

    @Test
    public void givenUserFillsNameField_whenClicksEnterButton_thenMessageFieldShouldBeDisplayed() throws IOException {
        HtmlPage loginPage = webClient.getPage("http://localhost:8080/");
        HtmlTextInput inputFrom = loginPage.getHtmlElementById("txt_from");
        inputFrom.setValueAttribute("Marcelo");

        HtmlForm enterForm = loginPage.getFormByName("enter_form");
        HtmlSubmitInput enterFormSubmit = enterForm.getOneHtmlElementByAttribute("input", "type", "button");
        enterFormSubmit.click();

        HtmlElement inputMessage = loginPage.getHtmlElementById("txt_message");
        assertThat(inputMessage.isDisplayed()).isTrue();
    }

}
