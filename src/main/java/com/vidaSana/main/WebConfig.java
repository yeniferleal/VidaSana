package com.vidaSana.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/");
        registry.addViewController("/login");
        
        //registry.addViewController("/errores/403").setViewName("/errores/403");
    }
   
    
    
}
