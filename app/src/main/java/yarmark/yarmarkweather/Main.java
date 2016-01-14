package yarmark.yarmarkweather;

public class Main {

    private double temp;
    private double temp_min;
    private double temp_max;

    public String getTemp() {
        return String.valueOf(this.temp);
    }

    public String getMin() {
        return String.valueOf(temp_min);
    }

    public String getMax() {
        return String.valueOf(temp_max);
    }
}
