package bot2;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.Async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MayBot extends ListenerAdapter {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private TextChannel textChannel = null;
    private Map<String, User> users = new HashMap<>();

    public MayBot(){
        executor.scheduleAtFixedRate(responseRunnable, 0,1, TimeUnit.SECONDS);
    }

    Runnable responseRunnable = new Runnable() {
        @Override
        public void run() {
            if(textChannel == null) return;
            for(User user : users.values()){
                if(user.isResponseTime()){
                    textChannel.sendMessage(user.getHelloMessage()).queue();
                }
                if(user.isWantsWeatherInfo()){
                    textChannel.sendMessage(user.getWeatherMessage()).queue();
                }
            }
        }
    };

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        textChannel = event.getChannel();
        String userName = event.getAuthor().getName();
        if("TestBot2022".equalsIgnoreCase(userName)) return;

        User user = users.get(userName);
        if(user == null){
            user = new User(userName);
            users.put(userName, user);
        }
        user.add(event.getMessage().getContentRaw());
    }
}
