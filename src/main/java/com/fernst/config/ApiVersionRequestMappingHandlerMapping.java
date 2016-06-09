package com.fernst.config;

import com.fernst.entity.ApiVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * The type Api version request mapping handler mapping.
 */
@Component
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    /**
     * The Config rest api.
     */
    @Autowired
    ConfigRestApi configRestApi;

    public ApiVersionRequestMappingHandlerMapping() {}

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(typeAnnotation);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(methodAnnotation);
    }

    private RequestCondition<?> createCondition(ApiVersion accessMapping) {
        return (accessMapping != null) ? new ApiVersionRequestCondition(accessMapping.value(), accessMapping.min(),
                accessMapping.max(), configRestApi.getBaseApiVersion(), configRestApi.getLatestApiVersion()) : null;
    }

}
