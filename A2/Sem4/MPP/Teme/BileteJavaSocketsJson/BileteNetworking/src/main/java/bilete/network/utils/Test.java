package bilete.network.utils;

import bilete.domain.User;
import bilete.network.transfer.Request;
import bilete.network.transfer.RequestType;
import bilete.network.transfer.Response;
import bilete.network.transfer.ResponseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        User user = new User(1, "user", "pass");
        Request request = new Request.Builder().type(RequestType.LOGIN).data(user).build();

        Response response = new Response.Builder().type(ResponseType.ERROR).data("linia 2").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);
        System.out.println(json);
    }
}
