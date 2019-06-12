package com.hant.words.config;

import com.hant.words.handler.LoginHandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　＞　　　＜　┃
 * ┃　　　　　　　┃
 * ┃...　⌒　...　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃
 * ┃　　　┃
 * ┃　　　┃
 * ┃　　　┃  神兽保佑
 * ┃　　　┃  代码无bug
 * ┃　　　┃
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 *
 * @author ：Hant
 * @date ：Created in 2019/6/4 16:03
 * @description：WebMvc 的配置
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        logger.info("加入拦截器");
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/")
                .excludePathPatterns("/static/**");
    }


    /**
     * @param registry
     * 设置跨域请求
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("PUT POST GET DELETE OPTIONS")
                .allowedOrigins("*");
    }
    /**
     * @param registry
     * 添加数据格式化器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/","classpath:/public/","classpath:/templates/");
    }

    /**
     * 配置自己的国际化语言解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}
