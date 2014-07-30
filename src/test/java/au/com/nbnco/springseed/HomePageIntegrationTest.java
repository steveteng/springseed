package au.com.nbnco.springseed;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class HomePageIntegrationTest {

    private static final Logger log= LoggerFactory.getLogger(HomePageIntegrationTest.class);

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

    @Value("${server.port:8082}")
    private String port;

    @Test
    public void testHome() throws Exception {
        String url = "http://localhost:8082";
        //String url = "http://localhost:"+port;
        log.info("testing url:"+ url);
        ResponseEntity<String> entity = getRestTemplate().getForEntity( url, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertThat(entity.getBody(),containsString("Welcome to Spring Seed Hote"));
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
