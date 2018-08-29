package rs.mvd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import rs.mvd.filters.ReportingInterceptor;
import rs.mvd.filters.RequestInterceptor;

@Configuration
public class RequestInterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor();
    }

    @Bean
    public ReportingInterceptor reportingInterceptor() {
        return new ReportingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(reportingInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(requestInterceptor()).addPathPatterns("/private/**");

    }

}
