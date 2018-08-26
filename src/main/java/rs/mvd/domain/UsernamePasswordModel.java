package rs.mvd.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsernamePasswordModel {

    @Size(min = 3, max = 10)
    @NotNull
    private String username;
    private String password;

    public UsernamePasswordModel() {
    }

    public UsernamePasswordModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
