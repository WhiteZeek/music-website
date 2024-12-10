package com.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.pattern.PathPattern;
//用于跨域
@Configuration//开始启动
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**")//允许跨域请求的路径
                .allowedOrigins("*")//域名
                .allowedMethods("*")//允许的请求方法
                .allowCredentials(true);//是否允许证书
    }
    @Override// 重写以进行静态资源映射
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        String dir=System.getProperty("user.dir");
        registry.addResourceHandler("static/img/**")
                .addResourceLocations("classpath:/img/");
        registry.addResourceHandler("static/picImages/**")
                .addResourceLocations("classpath:/picImages/")
                .addResourceLocations("file://"+ dir.replaceAll("\\\\", "/")+"/src/main/resources/static/picImages/");
                
    }
}
