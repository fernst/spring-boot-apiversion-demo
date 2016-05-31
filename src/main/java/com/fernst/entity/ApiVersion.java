package com.fernst.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Api version.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

  /**
   * The constant BASE. The currently lowest supported version of the API. When a version gets
   * deprecated, this number is increased.
   */
  public static int BASE = 1;

  /**
   * The constant ANY_VERSION. This is a catch-all to specify endpoints that support any version of
   * the api.
   */
  public static int ANY_VERSION = -1;

  /**
   * Value int [].
   */
  int[] value() default {BASE};
}
