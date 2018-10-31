package mysvc;

import mysvc.web.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyustiz on 10/31/18 for project mysvc.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsServiceApplicationKata3Test {
    @Autowired
    HomeController homeController;
    @Test
    public void contextLoads() {
        assertThat(homeController).isNotNull();
    }
}
