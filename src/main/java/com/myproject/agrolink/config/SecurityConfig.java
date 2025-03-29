package com.myproject.agrolink.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.*;

@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("https://d38gswnftydh5r.cloudfront.net");
        corsConfig.addAllowedOrigin("http://localhost:3000");
        corsConfig.addAllowedHeader("*"); // Sau specific: Authorization, Content-Type
        corsConfig.addAllowedMethod("*");
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        CorsFilter corsFilter = new CorsFilter(source);

        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(corsFilter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<FirebaseAuthenticationFilter> firebaseAuthenticationFilter() {
        FilterRegistrationBean<FirebaseAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FirebaseAuthenticationFilter());
        registrationBean.addUrlPatterns("/agrolink/farms/*"); // Specify the URL patterns to secure
        registrationBean.addUrlPatterns("/agrolink/clients/*");
        registrationBean.addUrlPatterns("/agrolink/carts/*");
        registrationBean.addUrlPatterns("/agrolink/cartItems/*");
        registrationBean.addUrlPatterns("/agrolink/deliveryCounties/*");
        registrationBean.addUrlPatterns("/agrolink/orders/*");
        registrationBean.addUrlPatterns("/agrolink/orderItems/*");
        registrationBean.addUrlPatterns("/agrolink/payments/*");
        registrationBean.addUrlPatterns("/agrolink/orderResponses/*");
        registrationBean.addUrlPatterns("/agrolink/fundsDistribs/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter() {
        FilterRegistrationBean<ForwardedHeaderFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ForwardedHeaderFilter());
        return filterRegistrationBean;
    }

}
