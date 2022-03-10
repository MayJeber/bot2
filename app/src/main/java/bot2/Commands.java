package bot2;

//krav till boten
//1. när jag skriva !joke det kommer upp ett joke
//2. värje gång jag skriva !joke dycker upp ett nytt joke inte samma från föregåande rad
//3. jag kan dölja mitt joke och trcyka på den att den ses när jag vill


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Commands extends ListenerAdapter {
    public String prefix = "!";

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");


        if (args[0].equalsIgnoreCase(prefix + "joke")) {
            HttpClient jokeApi = HttpClient.newHttpClient();
            HttpRequest jokeRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://v2.jokeapi.dev/joke/programming?type=double"))
                    .build();

            HttpResponse<String> jokes = null;
            try {
                jokes = jokeApi.send(jokeRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // TODO: 2022-03-04 remove the line below once you understand how parsing works, this is just to visualize the httpresponse
            // System.out.println(jokes.body());

            ObjectMapper jokMapper = new ObjectMapper();
            Joke myJoke = null;
            try {
                myJoke = jokMapper.readValue(jokes.body(), Joke.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println(myJoke);
            event.getChannel().sendTyping().timeout(2, TimeUnit.SECONDS).queue();
            event.getChannel().sendMessage("||" +myJoke.getJoke()+ "||").queueAfter(3,TimeUnit.SECONDS);


        }
    }


    public void myJoke() {
    }
}





       /* private final OkHttpClient client = new OkHttpClient();

        public void showOneJoke() throws Exception{
            HttpClient jokeApi = HttpClient.newHttpClient();
            HttpRequest jokeRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://v2.jokeapi.dev/joke/programming?type=single"))
                    .build();

            HttpResponse<String> jokes = jokeApi.send(jokeRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(jokes.body());
            }
        }



/*try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
*/






