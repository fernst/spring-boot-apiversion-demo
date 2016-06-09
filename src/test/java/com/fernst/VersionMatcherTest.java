package com.fernst;

import com.fernst.config.ApiVersionRequestCondition;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fernando on 6/5/16.
 */
public class VersionMatcherTest extends ApiVersionApplicationTestSetup {

    @Test
    public void testVersionMatcher() {
        String mimeApplicationJson = "application/json";
        String mimeApplicationJsonVnd = "application/vnd.fernst+json";
        String mimeApplicationJsonV0 = "application/vnd.fernst.v0+json";
        String mimeApplicationJsonV1 = "application/vnd.fernst.v1+json";
        String mimeApplicationJsonV2 = "application/vnd.fernst.v2+json";
        String mimeApplicationJsonV10 = "application/vnd.fernst.v10+json";
        String mimeApplicationJsonV11 = "application/vnd.fernst.v11+json";
        String mimeApplicationJsonV99 = "application/vnd.fernst.v99+json";
        String mimeApplicationJsonV123 = "application/vnd.fernst.v123+json";
        String mimeApplicationJsonV456 = "application/vnd.fernst.v456+json";
        String mimeApplicationJsonV2147483647 = "application/vnd.fernst.v2147483647+json";
        String mimeApplicationJsonV0000000001 = "application/vnd.fernst.v0000000001+json";

        Assert.assertNull(ApiVersionRequestCondition.getVersionNumber(mimeApplicationJson));
        Assert.assertNull(ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonVnd));
        Assert.assertEquals(Integer.valueOf(0), ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonV0));
        Assert.assertEquals(Integer.valueOf(1), ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonV1));
        Assert.assertEquals(Integer.valueOf(2), ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonV2));
        Assert.assertEquals(Integer.valueOf(10), ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonV10));
        Assert.assertEquals(Integer.valueOf(11), ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonV11));
        Assert.assertEquals(Integer.valueOf(99), ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonV99));
        Assert.assertEquals(Integer.valueOf(123), ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonV123));
        Assert.assertEquals(Integer.valueOf(456), ApiVersionRequestCondition.getVersionNumber(mimeApplicationJsonV456));
        Assert.assertEquals(Integer.valueOf(2147483647), ApiVersionRequestCondition.getVersionNumber
                (mimeApplicationJsonV2147483647));
        Assert.assertEquals(Integer.valueOf(1), ApiVersionRequestCondition.getVersionNumber
                (mimeApplicationJsonV0000000001));
    }
}
