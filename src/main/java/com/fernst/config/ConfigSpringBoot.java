package com.fernst.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * The type Config spring boot.
 */
@Configuration
public class ConfigSpringBoot extends WebMvcConfigurationSupport {

  @Override
  @Bean
  public RequestMappingHandlerMapping requestMappingHandlerMapping() {
    RequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandlerMapping();

    //Configuration copied from the parent class
    handlerMapping.setOrder(0);
    handlerMapping.setInterceptors(this.getInterceptors());
    handlerMapping.setContentNegotiationManager(this.mvcContentNegotiationManager());
    handlerMapping.setCorsConfigurations(this.getCorsConfigurations());
    PathMatchConfigurer configurer = this.getPathMatchConfigurer();
    if (configurer.isUseSuffixPatternMatch() != null) {
      handlerMapping.setUseSuffixPatternMatch(configurer.isUseSuffixPatternMatch().booleanValue());
    }

    if (configurer.isUseRegisteredSuffixPatternMatch() != null) {
      handlerMapping.setUseRegisteredSuffixPatternMatch(
          configurer.isUseRegisteredSuffixPatternMatch().booleanValue());
    }

    if (configurer.isUseTrailingSlashMatch() != null) {
      handlerMapping.setUseTrailingSlashMatch(configurer.isUseTrailingSlashMatch().booleanValue());
    }

    if (configurer.getPathMatcher() != null) {
      handlerMapping.setPathMatcher(configurer.getPathMatcher());
    }

    return handlerMapping;
  }
}
