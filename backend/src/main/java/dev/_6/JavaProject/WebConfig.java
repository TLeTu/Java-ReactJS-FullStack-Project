//package dev._6.JavaProject;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000") // Allow this origin
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these methods
//                .allowedHeaders("*") // Allow all headers
//                .allowCredentials(true); // Allow credentials
//    }
//}