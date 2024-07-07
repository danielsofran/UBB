package bilete.network.transfer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

public class Response implements Serializable {
    @JsonSerialize
    @JsonDeserialize
    private ResponseType type;
    @JsonSerialize @JsonDeserialize
    private Object data;

    private Response(){};

    public ResponseType type(){
        return type;
    }

    public Object data(){
        return data;
    }

    private void type(ResponseType type){
        this.type=type;
    }

    private void data(Object data){
        this.data=data;
    }

    @Override
    public String toString(){
        return "Response{" +
                "type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }


    public static class Builder{
        private Response response=new Response();

        public Builder type(ResponseType type) {
            response.type(type);
            return this;
        }

        public Builder data(Object data) {
            response.data(data);
            return this;
        }

        public Response build() {
            return response;
        }
    }

}
