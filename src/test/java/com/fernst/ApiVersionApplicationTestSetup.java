package com.fernst;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiVersionApplication.class)
@WebAppConfiguration
public class ApiVersionApplicationTestSetup {

    protected static String MIME_APPLICATION_JSON = "application/json";
    protected static String MIME_APPLICATION_JSON_VENDOR = "application/vnd.fernst+json";
    protected static String MIME_APPLICATION_JSON_V0 = "application/vnd.fernst.v0+json";
    protected static String MIME_APPLICATION_JSON_V1 = "application/vnd.fernst.v1+json";
    protected static String MIME_APPLICATION_JSON_V2 = "application/vnd.fernst.v2+json";
    protected static String MIME_APPLICATION_JSON_V3 = "application/vnd.fernst.v3+json";
    protected static String MIME_APPLICATION_JSON_V4 = "application/vnd.fernst.v4+json";
    protected static String MIME_APPLICATION_JSON_V5 = "application/vnd.fernst.v5+json";
    protected static String MIME_APPLICATION_JSON_V6 = "application/vnd.fernst.v6+json";
    protected static String MIME_APPLICATION_JSON_V2147483647 = "application/vnd.fernst.v2147483647+json";

    protected MockMvc _mockMvc;

    protected ObjectMapper _objectMapper = new ObjectMapper();

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        _mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    protected MockHttpServletRequestBuilder _get(String urlTemplate, String acceptHeader) {
        return get(urlTemplate).header("Accept", acceptHeader);
    }
}
