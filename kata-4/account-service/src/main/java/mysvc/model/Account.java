package mysvc.model;

import lombok.*;
import mysvc.exception.BlankRoleArgumentException;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jyustiz on 10/31/18 for project mysvc.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA Only
@RequiredArgsConstructor
public class Account {
    @Id
    // strategy = GenerationType.IDENTITY required to prevent org.h2.jdbc.JdbcSQLException:
    // Sequence "HIBERNATE_SEQUENCE" not found; SQL statement:
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;

    private String role;

    public void setRole(String role) {
        if (StringUtils.isBlank(role))
            throw new BlankRoleArgumentException("Role cannot be blank");
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id:").append(id);
        sb.append(", username:'").append(username).append('\'');
        if (StringUtils.isNotBlank(role))
            sb.append(", role:'").append(role).append('\'');
        else
            sb.append(", role:").append(role);
        sb.append('}');
        return sb.toString();
    }
}
