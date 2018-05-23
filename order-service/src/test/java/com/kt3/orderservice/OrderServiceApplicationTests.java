package com.kt3.orderservice;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceApplicationTests {

    private static final Logger logger = Logger.getLogger(OrderServiceApplicationTests.class.getName());

    @Test
    public void contextLoads() {
        String body = "{\"to\": \"01258333926\", \"content\": \"Your verification code is: {pin_code}\", \"app_id\": \"NgPqmyj5i09rJtay-Ukr1wfQDCvYX2eb\"}";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.exchange
                ("https://api.speedsms.vn/index.php/pin/create", HttpMethod.POST,
                        new HttpEntity<>(body, createHeaders("WUUgFjhCyTclFRLUnEA_rxGGvnfe4LNH", "x")), String.class);
        System.out.println(res.getBody().toString());
        logger.info(res.getBody().toString());

    }

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }
}
