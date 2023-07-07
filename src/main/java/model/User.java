package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class User {
    private Integer uId;
    private String uUsername;
    private String uPassword;
    private String uEmail;
}
