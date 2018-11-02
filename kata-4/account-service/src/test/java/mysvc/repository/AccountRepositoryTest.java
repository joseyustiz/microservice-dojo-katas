package mysvc.repository;

import mysvc.model.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by jyustiz on 10/31/18 for project mysvc.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountRepository accountRepository;
    final String JOSEYUSTIZ = "joseyustiz";
    Account joseyustiz;
    Account username1;
    Account username2;

    @Before
    public void setup() {
        joseyustiz = new Account(JOSEYUSTIZ);
        joseyustiz.setRole("admin");
        username1 = new Account("username1");
        username1.setRole("user");
        username2 = new Account("username2");
        username2.setRole("user");
        this.entityManager.persist(joseyustiz);
        this.entityManager.persist(username1);
        this.entityManager.persist(username2);
    }

    @Test
    public void testFindAccountByRole() {
        Iterable<Account> accounts = accountRepository.findByRole("admin");
        int count = 0;
        for (Account account : accounts) {
            assertThat(account.getUsername()).isEqualTo(JOSEYUSTIZ);
            assertThat(account.getRole()).isEqualTo("admin");
            assertThat(account.getId()).isGreaterThan(0L);
            count++;
        }
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testFindAccountByUsername() {

        Iterable<Account> accounts = accountRepository.findByUsername(JOSEYUSTIZ);
        int count = 0;
        for (Account account : accounts) {
            assertThat(account.getUsername()).isEqualTo(JOSEYUSTIZ);
            assertThat(account.getId()).isGreaterThan(0L);
            count++;
        }
        assertThat(count).isEqualTo(1);
    }

    @After
    public void cleanup() {
        this.entityManager.clear();
    }
}
