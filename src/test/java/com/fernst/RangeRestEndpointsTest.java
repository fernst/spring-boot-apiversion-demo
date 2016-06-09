package com.fernst;

import com.fernst.entity.ApiResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Created by fernando on 6/5/16.
 */
public class RangeRestEndpointsTest extends ApiVersionApplicationTestSetup {

    static String URL = "/range";
    static String MESSAGE_V1_V1 = "V1-V1 range response";
    static String MESSAGE_V2_V3 = "V2-V3 range response";
    static String MESSAGE_V4_V5 = "V4-V5 range response";

    @Test
    public void testNoVersion() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON))
                .andReturn()
                .getResponse();

        Assert.assertEquals(404, httpResponse.getStatus());
    }

    @Test
    public void testNoVersionVendorHeader() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_VENDOR))
                .andReturn()
                .getResponse();

        Assert.assertEquals(404, httpResponse.getStatus());
    }

    @Test
    public void testNoVersionVendorVersion0Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V0))
                .andReturn()
                .getResponse();

        Assert.assertEquals(404, httpResponse.getStatus());
    }

    @Test
    public void testNoVersionVendorVersion1Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V1))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE_V1_V1));
    }

    @Test
    public void testNoVersionVendorVersion2Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V2))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE_V2_V3));
    }

    @Test
    public void testNoVersionVendorVersion3Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V3))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE_V2_V3));
    }

    @Test
    public void testNoVersionVendorVersion4Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V4))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE_V4_V5));
    }

    @Test
    public void testNoVersionVendorVersion5Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V5))
                .andReturn()
                .getResponse();

        Assert.assertEquals(200, httpResponse.getStatus());

        ApiResponse response = _objectMapper.readValue(httpResponse.getContentAsByteArray(), ApiResponse.class);

        Assert.assertTrue(response.getMessage()
                .contains(MESSAGE_V4_V5));
    }

    @Test
    public void testNoVersionVendorVersion6Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V6))
                .andReturn()
                .getResponse();

        Assert.assertEquals(404, httpResponse.getStatus());
    }

    @Test
    public void testNoVersionVendorVersion2147483647Header() throws Exception {
        MockHttpServletResponse httpResponse = _mockMvc.perform(_get(URL, MIME_APPLICATION_JSON_V2147483647))
                .andReturn()
                .getResponse();

        Assert.assertEquals(404, httpResponse.getStatus());
    }
}
