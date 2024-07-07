package json.utils;

import bilete.domain.Artist;
import bilete.domain.Show;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        Show show = new Show(1, new Artist(1, "artist"), LocalDate.now(), LocalTime.now(), "location", 100, 0);
        Show show2 = new Show(2, new Artist(2, "artist2"), LocalDate.now(), LocalTime.now(), "location2", 200, 0);
        Show[] shows = new Show[]{show, show2};
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(shows);

        Show[] artisti = objectMapper.readValue(json, Show[].class);
        //List<Show> artisti = objectMapper.readValue(json, new TypeReference<LinkedList<Show>>(){});
        System.out.println(artisti);
    }
}
