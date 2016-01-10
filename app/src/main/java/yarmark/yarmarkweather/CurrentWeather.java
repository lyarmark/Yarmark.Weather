package yarmark.yarmarkweather;

public class CurrentWeather {
    private Weather[] weather;
    private Main main;

    public String getDescription(int i) {
        return weather[i].getDescription();
    }

    public String getIcon(int i) {
        return weather[i].getIcon();
    }

    public String getTemperature() {
        return main.getTemperature();
    }

}
