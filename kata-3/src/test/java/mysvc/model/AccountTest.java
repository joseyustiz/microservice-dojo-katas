package mysvc.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyustiz on 10/31/18 for project mysvc.
 */
@DisplayName("An account")
public class AccountTest {

    @Test
    @DisplayName("converted to String")
    void toStringReturnProperJsonFormat(){
        Account account = new Account("Jose");
        String joseToString="User{id:null, username:\'Jose\'}";
        assertThat(account.toString()).isEqualTo(joseToString);
    }
}
