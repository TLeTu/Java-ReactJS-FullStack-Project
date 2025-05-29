package dev._6.JavaProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private String roles;

    public UserInfo(UserInfo userInfo) {
        this.name = userInfo.name;
        this.email = userInfo.email;
        this.password = userInfo.password;
        this.roles = userInfo.roles;
    }

}