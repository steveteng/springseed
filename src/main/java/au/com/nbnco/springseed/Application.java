package au.com.nbnco.springseed;

import au.com.nbnco.springseed.service.EnvTellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.Arrays;

@Configuration
@ComponentScan("au.com.nbnco.springseed")
@EnableAutoConfiguration
@EnableJpaRepositories("au.com.nbn.springseed.repository")
public class Application {


    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private EnvTellerService envTellerService;

    public void run(String... args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        log.info("Inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info(beanName);
        }

        log.info("===========Env Profile===========");
        log.info(envTellerService.getEnvName());
        log.info("=================================");

    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
