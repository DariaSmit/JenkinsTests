package lession10Jenkins.demoqa.properties;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class OwnerTest {
    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    @Test
    @Tag("owner")
    void loginTest(){
        String login = config.login();
        String password = config.password();

        System.out.println("login:" + login);
        System.out.println("password:" + password);

        String  message = "I logged in as " + login + "wis password" + password;
        System.out.println(message);
    }
}
