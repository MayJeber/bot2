package bot2;

public class User {
    private String name;
    boolean responseTime = true;
    private boolean wantsWeatherInfo = false;

    public User(String name){
        this.name = name;
    }

    public void add(String message){
        System.out.println(name + ": " + message);
        if(message == null) return;
        wantsWeatherInfo = isCommandOf("weather", message);
        responseTime = !isCommandOf("stop", message);
        responseTime = isCommandOf("start", message);
    }

    private boolean isCommandOf(String command, String message){
        command = command.toLowerCase();
        message = message.toLowerCase();
        if(command.equalsIgnoreCase(message)){
            return true;
        }

        if(message.startsWith(command + " ")){
            return true;
        }
        if(message.indexOf(" " + command + " ")>=0){
            System.out.println("Setting response to true");
            return true;
        }
        return (message.endsWith(" " + command));
    }

    public boolean isResponseTime() {
        return responseTime;
    }

    public CharSequence getHelloMessage() {
        return "Hello " + name;
    }

    public void setResponseTime(boolean responseTime) {
        this.responseTime = responseTime;
    }

    public boolean isWantsWeatherInfo() {
        return wantsWeatherInfo;
    }

    public void setWantsWeatherInfo(boolean wantsWeatherInfo) {
        this.wantsWeatherInfo = wantsWeatherInfo;
    }

    public CharSequence getWeatherMessage() {
        return name  + " wants to know weather!";
    }
}
