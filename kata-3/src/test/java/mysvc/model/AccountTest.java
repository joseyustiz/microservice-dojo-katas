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
        String joseToString="User{id:null, username:\'Jose\'}";
        assertThat(account.toString()).isEqualTo(joseToString);
    }
}
