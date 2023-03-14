package angeATT.Tacocloud.sec;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    @Autowired PasswordEncoder passwordEncoder;
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);
    }

}
