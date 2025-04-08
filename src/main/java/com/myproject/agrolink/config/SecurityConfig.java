package com.myproject.agrolink.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // ✅ Setați toate origin-urile permise
        corsConfig.setAllowedOrigins(Arrays.asList(
                "https://www.paulayadeagro.online",
                "http://localhost:3000"
        ));

        corsConfig.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "X-Requested-With"
        ));

        corsConfig.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(new CorsFilter(source));
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<FirebaseAuthenticationFilter> firebaseAuthenticationFilter() {
        FilterRegistrationBean<FirebaseAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FirebaseAuthenticationFilter());

        // ✅ Listează rutele care au nevoie de autentificare
        registrationBean.addUrlPatterns("/agrolink/farms/*");
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
