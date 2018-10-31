package mysvc.repository;

import mysvc.model.Account;
import org.junit.After;
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

    @Test
    public void testFindAccountByUsername() {
        final String JOSEYUSTIZ="joseyustiz";
        // given
        Account joseyustiz = new Account(JOSEYUSTIZ);
        Account username1 = new Account("username1");
        Account username2 = new Account("username2");
        this.entityManager.persist(joseyustiz);
        this.entityManager.persist(username1);
        this.entityManager.persist(username2);

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
