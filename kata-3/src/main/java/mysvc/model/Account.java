package mysvc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jyustiz on 10/31/18 for project mysvc.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA Only
class Account {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String username;

    public Account(String username){
        this.username=username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id:").append(id);
        sb.append(", username:'").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
