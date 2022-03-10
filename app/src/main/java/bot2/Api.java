package bot2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {


    public static void main(String[] args) throws IOException, InterruptedException{
        HttpClient jokeApi = HttpClient.newHttpClient();
        HttpRequest jokeRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://v2.jokeapi.dev/joke/programming?type=double"))
                .build();

        HttpResponse<String> jokes = jokeApi.send(jokeRequest, HttpResponse.BodyHandlers.ofString());

        // TODO: 2022-03-04 remove the line below once you understand how parsing works, this is just to visualize the httpresponse
       // System.out.println(jokes.body());

        ObjectMapper jokMapper = new ObjectMapper();
        Joke myJoke = jokMapper.readValue(jokes.body(),Joke.class);

        System.out.println(myJoke);



        // TODO: 2022-03-04  the folwing so i rembering how JsonNode works
        //JsonNode jokeNode = jokMapper.readTree(jokes.body());
        //System.out.println(String.format(jokeNode.get("joke").toString()));
        //System.out.println(jokeNode.get("joke").toString().replace("\n","").replace("\"",""));
    }
}
