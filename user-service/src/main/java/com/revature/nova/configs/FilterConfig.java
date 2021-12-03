//package com.revature.nova.configs;
//
//import com.revature.nova.filters.AuthenticationFilter;
//import com.revature.nova.filters.CORSFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Collections;
//
///**
// * Register beans for every filter used in the service
// *
// * @author James Brown, Kollier Martin
// * @date 11/22/2021
// */
//@Configuration
//public class FilterConfig {
//    @Bean
//    @SuppressWarnings({"rawtypes, unchecked"})
//    public FilterRegistrationBean authFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new AuthenticationFilter());
//        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    @SuppressWarnings({"rawtypes, unchecked"})
//    public FilterRegistrationBean corsRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new CORSFilter());
//        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
//        return filterRegistrationBean;
//    }
//}
