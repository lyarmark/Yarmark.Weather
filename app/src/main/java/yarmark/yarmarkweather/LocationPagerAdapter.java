package yarmark.yarmarkweather;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationPagerAdapter extends PagerAdapter {

    private EditText zipcode;
    private TextView time;
    private ArrayList<String> zips;
    private Context context;
    private Retrofit retrofit;
    private WeatherService service;

    public LocationPagerAdapter(ArrayList<String> zips, Context mainActivityContext) {
        this.zips = zips;
        this.context = mainActivityContext;
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(WeatherService.class);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        View view = inflater.inflate(R.layout.weather_pager, null);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(layoutManager);

        this.zipcode = (EditText) view.findViewById(R.id.zipcode);
        this.zipcode.setText(zips.get(position));

        this.time = (android.widget.TextClock) view.findViewById(R.id.dateTime);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
        Date date = new Date();
        this.time.setText(format.format(date));

        Call<JsonObject> call = service.getLocationWeather(zips.get(position));

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response) {
                JsonObject weatherJson = response.body();

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                LocationWeather locationWeather = gson.fromJson(weatherJson, LocationWeather.class);

                ListInfo[] locations = locationWeather.getList();
                WeatherRecyclerAdapter recyclerAdapter = new WeatherRecyclerAdapter(locations, context);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return zips.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
