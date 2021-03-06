package cc.before30.metricex.demo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * DemoAppConfig
 *
 * @author before30
 * @since 2020/03/04
 */
@Configuration
public class DemoAppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        builder.interceptors(new CustomClientHttpRequestInterceptor());
        return builder.build();
    }

}
