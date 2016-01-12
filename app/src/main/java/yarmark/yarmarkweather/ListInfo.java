package yarmark.yarmarkweather;

public class ListInfo {
	private String dt;
	private Weather[] weather;
	private Temp temp;

//json returns an array with 1 weather object. always need [0]
	public Weather getWeather() {
		return weather[0];
	}

	public Temp getTemp() {
		return temp;
	}
}
