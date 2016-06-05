package com.fernst.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The type Config rest api.
 */
@Configuration
public class ConfigRestApi {
  /**
   * The Base api version.
   */
  @Value("${api.version.base}")
  protected Integer baseApiVersion;

  /**
   * The Latest api version.
   */
  @Value("${api.version.latest}")
  protected Integer latestApiVersion;

  /**
   * Gets base api version.
   *
   * @return the base api version
   */
  public Integer getBaseApiVersion() {
    return baseApiVersion;
  }

  /**
   * Gets latest api version.
   *
   * @return the latest api version
   */
  public Integer getLatestApiVersion() {
    return latestApiVersion;
  }
}
