package mysvc.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyustiz on 10/29/18 for project mysvc.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HomeControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private TestRestTemplate template;

    @Before
    public void setUp() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
    }

    @Test
    public void getHello() {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody()).isEqualTo("Hello World from Kata-3!");
    }
}