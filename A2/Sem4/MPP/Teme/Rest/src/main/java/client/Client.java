package client;

import com.example.main.domain.Artist;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Client {
    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String url = "http://localhost:8080";

    private static void getAllArtists() {
        System.err.println("getAllArtists");
        Artist[] artists = restTemplate.getForObject(url + "/artists", Artist[].class);
        if(artists != null)
            for(Artist artist : artists) {
                System.err.print(artist+";");
            }
        System.err.println();
    }

    private static Artist getArtist(Integer id) {
        System.err.println("getArtist");
        Artist artist = restTemplate.getForObject(url + "/artists/" + id, Artist.class);
        System.err.println(artist);
        return artist;
    }

    private static Artist addArtist(Artist artist) {
        System.err.println("addArtist");
        Artist newArtist = restTemplate.postForObject(url + "/artists", artist, Artist.class);
        System.err.println(newArtist);
        return newArtist;
    }

    private static void deleteArtist(Integer id) {
        System.err.println("deleteArtist "+id);
        restTemplate.delete(url + "/artists/" + id);
    }

    private static void updateArtist(Integer id, Artist artist) {
        System.err.println("updateArtist");
        restTemplate.put(url + "/artists/" + id, artist);
    }

    public static void main(String[] args) {
        getAllArtists();
        Artist a1 = addArtist(new Artist(1, "name1"));
        Artist a2 =addArtist(new Artist(2, "name2"));
        getAllArtists();
        deleteArtist(a2.getId());
        getAllArtists();
        updateArtist(a1.getId(), a2);
        getAllArtists();
        getArtist(a1.getId());
        deleteArtist(a1.getId());
    }
}
