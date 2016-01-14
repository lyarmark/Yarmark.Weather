package yarmark.yarmarkweather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListInfo {
    private Long dt;
    private Weather[] weather;
    private Temp temp;

    //json returns an array with 1 weather object. always need [0]
    public Weather getWeather() {
        return weather[0];
    }

    public Temp getTemp() {
        return temp;
    }

    public String getDt() {
        Date date = new Date(dt * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        return dateFormat.format(date);
    }
}
