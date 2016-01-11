package yarmark.yarmarkweather;

public class LocationWeather {
    private City city;
    private ListInfo[] list;

    public String getLocation() {
        return city.getLocation();
    }

    public Weather getWeather(int i) {
        return list[i].getWeather();
    }

    public Temp getTemp(int i) {
        return list[i].getTemp();
    }

    public ListInfo[] getList() {
        return list;
    }
}
