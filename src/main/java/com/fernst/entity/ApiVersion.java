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
   * The constant ANY_VERSION.
   */
  public static int ANY_VERSION = -1;

  /**
   * Value int [ ].
   *
   * @return the int [ ]
   */
  int[] value() default {ANY_VERSION};

  /**
   * Min int.
   *
   * @return the int
   */
  int min() default 0;

  /**
   * Max int.
   *
   * @return the int
   */
  int max() default Integer.MAX_VALUE;
}
