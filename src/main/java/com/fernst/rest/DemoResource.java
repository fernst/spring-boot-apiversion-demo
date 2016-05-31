package com.fernst.rest;

import com.fernst.entity.ApiVersion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fernando on 5/31/16.
 */
@RestController
public class DemoResource {

  //No filter gets applied here
  @RequestMapping("/noversion")
  public ResponseEntity<String> getNoVersionEndpoint() {
    return new ResponseEntity<>("No Version response", HttpStatus.OK);
  }

  //Supports any version (even when no version is specified)
  @RequestMapping("/any")
  @ApiVersion(ApiVersion.ANY_VERSION)
  public ResponseEntity<String> getAnyVersionEndpoint() {
    return new ResponseEntity<>("Any Version response", HttpStatus.OK);
  }

  //Works with the base version of the api
  @RequestMapping("/base")
  @ApiVersion(ApiVersion.BASE)
  public ResponseEntity<String> getBaseEndpoint() {
    return new ResponseEntity<>("Base response", HttpStatus.OK);
  }

  //Only the filter for version 1 is applied
  @RequestMapping("/v1")
  @ApiVersion(1)
  public ResponseEntity<String> getV1Endpoint() {
    return new ResponseEntity<>("V1 response", HttpStatus.OK);
  }

  //Only the filter for version 2 is applied
  @RequestMapping("/v2")
  @ApiVersion(2)
  public ResponseEntity<String> getV2Endpoint() {
    return new ResponseEntity<>("V2 response", HttpStatus.OK);
  }
}
