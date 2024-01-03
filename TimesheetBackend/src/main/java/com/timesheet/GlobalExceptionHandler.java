package com.timesheet;

import io.sentry.Sentry;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(ex.getMessage())
                .withLevel(io.sentry.event.Event.Level.ERROR)
                .withLogger(GlobalExceptionHandler.class.getName())
                .withSentryInterface(new ExceptionInterface(ex))
                .withEnvironment(activeProfile)
                .withExtra("path", getRequestPath(request))
                .withExtra("request-header", getRequestHeader(request))
                .withExtra("response-header", getResponseHeader(response))
                .withExtra("parameters", getRequestParameters(request))
                .withExtra("method", getRequestMethod(request))
                .withExtra("remote-address", getRequestRemoteAddress(request))
                .withExtra("remote-host", getRequestRemoteHost(request))
                .withExtra("remote-port", getRequestRemotePort(request))
                .withExtra("remote-user", getRequestRemoteUser(request))
                .withExtra("servlet-path", getRequestServletPath(request))
                .withExtra("protocol", getRequestProtocol(request));
        Sentry.capture(eventBuilder);
        throw ex;
    }

    private Object getRequestRemotePort(HttpServletRequest request) {
        return request.getRemotePort();
    }


    private Object getRequestProtocol(HttpServletRequest request) {
        return request.getProtocol();
    }


    private Object getRequestServletPath(HttpServletRequest request) {
        return request.getServletPath();
    }


    private Object getRequestRemoteUser(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    private Object getRequestRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }

    private Object getRequestRemoteAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    private Object getRequestMethod(HttpServletRequest request) {
        return request.getMethod();
    }

    private Map<String, String> getResponseHeader(HttpServletResponse response) {
        try {
            Map<String, String> headerMap = new HashMap<>();
            response.getHeaderNames().forEach(key -> headerMap.put(key, response.getHeader(key)));
            return headerMap;
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getRequestHeader(HttpServletRequest request) {
        try {
            Enumeration<String> requestHeaderNames = request.getHeaderNames();
            Map<String, String> headerMap = new HashMap<>();
            while (requestHeaderNames.hasMoreElements()) {
                String key = requestHeaderNames.nextElement();
                headerMap.put(key, request.getHeader(key));
            }
            return headerMap;
        } catch (Exception e) {
            return null;
        }
    }

    private String getRequestPath(HttpServletRequest request) {
        return request.getRequestURI();
    }

    private Map<String, String[]> getRequestParameters(HttpServletRequest request) {
        return request.getParameterMap();
    }


}
