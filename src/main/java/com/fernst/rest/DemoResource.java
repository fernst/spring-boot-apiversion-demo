package com.fernst.rest;

import com.fernst.config.ConfigRestApi;
import com.fernst.entity.ApiVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Demo resource.
 */
@RestController
public class DemoResource {

    /**
     * The Config rest api.
     */
    @Autowired
    ConfigRestApi configRestApi;

    /**
     * Gets no version endpoint.
     *
     * @return the no version endpoint
     */
    //No filter gets applied here
    @RequestMapping("/noversion")
    public ResponseEntity<String> getNoVersionEndpoint() {
        return new ResponseEntity<>("No Version annotation response", HttpStatus.OK);
    }

    /**
     * Gets any version endpoint.
     *
     * @return the any version endpoint
     */
    //Supports any version (even when no version is specified)
    @RequestMapping("/any")
    @ApiVersion(ApiVersion.ANY_VERSION)
    public ResponseEntity<String> getAnyVersionEndpoint() {
        return new ResponseEntity<>("Any Version response", HttpStatus.OK);
    }

    /**
     * Gets v 1 range.
     *
     * @return the v 1 range
     */
    //Works with the base version of the api
    @RequestMapping("/range")
    @ApiVersion(min = 1, max = 1)
    public ResponseEntity<String> getV1Range() {
        return new ResponseEntity<>("V1-V1 range response", HttpStatus.OK);
    }

    /**
     * Gets v 2 v 3 range.
     *
     * @return the v 2 v 3 range
     */
    //Works with the base version of the api
    @RequestMapping("/range")
    @ApiVersion(min = 2, max = 3)
    public ResponseEntity<String> getV2V3Range() {
        return new ResponseEntity<>("V2-V3 range response", HttpStatus.OK);
    }

    /**
     * Gets v 4 v 5 range.
     *
     * @return the v 4 v 5 range
     */
    //Works with the base version of the api
    @RequestMapping("/range")
    @ApiVersion(min = 4, max = 5)
    public ResponseEntity<String> getV4V5Range() {
        return new ResponseEntity<>("V4-V5 range response", HttpStatus.OK);
    }

    /**
     * Gets v 1 specific.
     *
     * @return the v 1 specific
     */
    //Only the filter for version 1 is applied
    @RequestMapping("/specific")
    @ApiVersion(value = {1, 2})
    public ResponseEntity<String> getV1Specific() {
        return new ResponseEntity<>("V1, V2 response specifying version", HttpStatus.OK);
    }

    /**
     * Gets v 2 specific.
     *
     * @return the v 2 specific
     */
    //Specific filter for versions 3,v4 and 5
    @RequestMapping("/specific")
    @ApiVersion(value = {3, 4, 5})
    public ResponseEntity<String> getV2Specific() {
        return new ResponseEntity<>("V3, V4, V5 response specifying version", HttpStatus.OK);
    }
}
