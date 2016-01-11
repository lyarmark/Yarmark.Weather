package yarmark.yarmarkweather;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by LeahYarmark on 1/9/2016.
 */
public interface WeatherService {
    @GET("data/2.5/forecast/daily?&mode=json&units=imperial&cnt=16&appid=2de143494c0b295cca9337e1e96b00e0")

    Call<JsonObject> getLocationWeather(@Query("zip") String zip);
}
