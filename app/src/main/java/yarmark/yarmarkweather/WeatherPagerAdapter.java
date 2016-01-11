package yarmark.yarmarkweather;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherPagerAdapter extends PagerAdapter {

    private TextView time;
    private RecyclerView recyclerView;
    private String[] locations;
    private Context context;

    public WeatherPagerAdapter(String[] locations, Context mainActivityContext) {
        this.locations = locations;
        this.context = mainActivityContext;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<JsonObject> call = service.getLocationWeather(locations[position]);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response) {
                JsonObject weatherJson = response.body();

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                LocationWeather locationWeather = gson.fromJson(weatherJson, LocationWeather.class);
                WeatherRecyclerAdapter recyclerAdapter = new WeatherRecyclerAdapter(locationWeather.getList(), context);
                setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        return container.getContext();
    }

    @Override
    public int getCount() {
        return locations.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setAdapter(WeatherRecyclerAdapter recyclerView) {
        this.recyclerView.setAdapter(recyclerView);
    }
}
