package yarmark.yarmarkweather;

public class CurrentWeather {
    private Weather[] weather;
    private Main main;
    private String name;

    public String getDescription(int i) {
        return weather[i].getDescription();
    }

    public String getIcon(int i) {
        return weather[i].getIcon();
    }

    public Main getTemperature() {
        return this.main;
    }

    public String getLocation() {
        return name;
    }

}
