package com.donatii.donatiiapi;

import com.donatii.donatiiapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class JsonDataTest {
    @Autowired
    private JacksonTester<User> jacksonTester;

    @Test
    void testSerialize() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("email");
        user.setParola("parola");

        JsonContent<User> jsonContent = jacksonTester.write(user);
        assertThat(jsonContent).extractingJsonPathStringValue("$.email").isEqualTo("email");
        assertThat(jsonContent).extractingJsonPathStringValue("$.parola").isEqualTo("parola");
        String expectedJson = "{\"id\":1,\"email\":\"email\",\"parola\":\"parola\"}";
        User expectedUser = jacksonTester.parseObject(expectedJson);
        assertThat(user).isEqualTo(expectedUser);
    }
}
