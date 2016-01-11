package yarmark.yarmarkweather;

public class ListInfo {
	private String dt;
	private Weather[] weather;
	private Temp temp;

	public Weather getWeather() {
		return weather[0];
	}

	public Temp getTemp() {
		return temp;
	}
}
