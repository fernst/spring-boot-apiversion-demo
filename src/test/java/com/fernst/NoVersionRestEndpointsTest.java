package com.fernst;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fernst.entity.ApiResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Created by fernando on 6/5/16.
 */
public class NoVersionRestEndpointsTest extends ApiVersionApplicationTestSetup {

    static String URL = "/noversion";
    static String MESSAGE = "No Version annotation response";

    @Test
    public void testNoVersion() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE));
    }

    @Test
    public void testNoVersionVendorHeader() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_VENDOR))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE));
    }

    @Test
    public void testNoVersionVendorVersion0Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V0))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE));
    }

    @Test
    public void testNoVersionVendorVersion1Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V1))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE));
    }

    @Test
    public void testNoVersionVendorVersion2147483647Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V2147483647))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE));
    }
}
