package controller.dto;

import lombok.Data;
import model.User;

import java.io.Serializable;

@Data
public class LoginSuccessfulDto implements Serializable {
    private String token;
    private User.UserType userType;

    public LoginSuccessfulDto(String token, User.UserType userType) {
        this.token = token;
        this.userType = userType;
    }
}
