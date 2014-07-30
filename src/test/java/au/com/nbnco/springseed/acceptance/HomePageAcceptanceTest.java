package au.com.nbnco.springseed.acceptance;

import au.com.nbnco.springseed.Application;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class HomePageAcceptanceTest extends SpringSeedMvcAcceptanceTest{

    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void start() throws Exception {
        Future<ConfigurableApplicationContext> future = Executors
                .newSingleThreadExecutor().submit(
                        new Callable<ConfigurableApplicationContext>() {
                            @Override
                            public ConfigurableApplicationContext call() throws Exception {
                                return SpringApplication
                                        .run(Application.class);
                            }
                        });
        context = future.get(60, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void stop() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    public void title_of_bing_should_contain_search_query_name() {
        goTo("http://www.bing.com");
        fill("#sb_form_q").with("FluentLenium");
        submit("#sb_form_go");
        assert(title()).contains("FluentLenium");
    }

    @Test
    public void title_of_home_should_contain_welcome() throws InterruptedException {
        goTo(getBaseUrl());
        Thread.sleep(1000);
        assert(pageSource()).contains("Welcome to Spring Seed Hote");
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });
        return restTemplate;

    }

}
