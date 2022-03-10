package bot2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {
    private String setup;
    private String delivery;
    private String joke;
    private String type;

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        String result = "";
        if("twopart".equalsIgnoreCase(type)){
            result+= this.getSetup();
            result+= "->";
            result+= this.getDelivery();
        }else{
            result+= this.getJoke();
        }
        return result;
    }
}
