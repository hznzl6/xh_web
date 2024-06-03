package com.example.mass.inteceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyCodeInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        String ipAddress = request.getRemoteAddr();

        // Swagger UI页面路径
        if (requestURI.contains("/swagger-ui.html")) {
            return true;
        } else if(requestURI.contains("/swagger-resources/configuration/ui")) {
            return true;
        } else if(requestURI.contains("/swagger-resources/configuration/security")) {
            return true;
        } else if(requestURI.contains("/swagger-resources")) {
            return true;
        } else if(requestURI.contains("/csrf")) {
            return true;
        } else if(requestURI.contains("/error")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-600.woff")) {
            return true;
        } else if(requestURI.contains("/user-account/login?phone=guest&pwd=guest")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/favicon-16x16.png")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/favicon-32x32.png")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/springfox.css")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/swagger-ui.css")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/swagger-ui-bundle.js")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/springfox.js")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-700.woff2")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-regular.woff2")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-700.woff")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-regular.woff")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-700.woff2")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/source-code-pro-v7-latin-300.woff2")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-700.woff")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/source-code-pro-v7-latin-300.woff")) {
            return true;
        } else if(requestURI.contains("/files")) {
            return true;
        } else if(requestURI.contains("/favicon.ico")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/source-code-pro-v7-latin-600.woff")) {
            return true;
        } else if(requestURI.contains("/webjars/springfox-swagger-ui/fonts/source-code-pro-v7-latin-600.woff2")) {
            return true;
        }

        String verifyCode = request.getHeader("verifyCode");
        System.out.println("verifyCode:"+verifyCode);
        if (!"2024".equals(verifyCode)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 返回403拒绝访问
            response.getWriter().write("Invalid verifyCode. Access Denied.");

//            logger.warn("\nAccess Denied for request: {}\nIP Address: {}\nforwardedFor: {}\n clientIP: {}", requestURI + ipAddress, forwardedFor, clientIP);
            logger.warn("\nAccess Denied for request: {}\nIP Address: {}", requestURI, ipAddress);

            return false;
        } else {
            // 如果请求成功，打印成功信息
//            logger.info("\nAccess Allowed for request: {}\nIP Address: {}\n forwardedFor: {}\n clientIP: {}", requestURI, ipAddress, forwardedFor, clientIP);
            logger.info("\nAccess Denied for request: {}\nIP Address: {}", requestURI, ipAddress);
            return true;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Do nothing here
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Do nothing here
    }
}

