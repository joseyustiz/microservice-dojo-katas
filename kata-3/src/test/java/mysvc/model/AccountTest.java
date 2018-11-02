package mysvc.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyustiz on 10/31/18 for project mysvc.
 */
public class AccountTest {

    @Test
    public void toStringReturnProperJsonFormat(){
        Account account = new Account("Jose");
        String accountToString="User{id:null, username:\'Jose\', role:null}";
        assertThat(account.toString()).isEqualTo(accountToString);

        account = new Account("Jhon");
        account.setRole("admin");
        accountToString = "User{id:null, username:\'Jhon\', role:'admin'}";
        assertThat(account.toString()).isEqualTo(accountToString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRoleDoesNotAllowEmptyRole(){
        Account account = new Account("Jhon");
        account.setRole("");
    }
}
