package cn.nickdlk.patchspring.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Bean
    public LicenseInterceptor licenseInterceptor() {
        return new LicenseInterceptor();
    }

     @Override
     public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(licenseInterceptor()).addPathPatterns("/**");
     }


}
