package yarmark.yarmarkweather;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationPagerAdapter extends PagerAdapter {

    private Button addButton;
    private EditText chooseZip;

    private TextView city;
    private TextView zipcode;
    private TextView time;
    private ImageView background;

    private ArrayList<String> zips;
    private Context context;
    private Retrofit retrofit;
    private WeatherService weatherService;

    public LocationPagerAdapter(ArrayList<String> zips, Context mainActivityContext) {
        this.zips = zips;
        this.context = mainActivityContext;
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.weatherService = retrofit.create(WeatherService.class);

    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        View weatherPagerView = inflater.inflate(R.layout.weather_pager, null);
        final RecyclerView weatherRecyclerView = (RecyclerView) weatherPagerView.findViewById(R.id.list);
        weatherRecyclerView.setLayoutManager(layoutManager);

        this.background = (ImageView) weatherPagerView.findViewById(R.id.background);
        Picasso.with(this.context).load("http://lorempixel.com/g/610/835/nature/")
                .placeholder(R.drawable.weather)
                .into(this.background);

        this.addButton = (Button) weatherPagerView.findViewById(R.id.plusButton);
        this.addButton.setText("+");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dinflater = LayoutInflater.from(context);
                View dialogueView = dinflater.inflate(R.layout.add_location, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(dialogueView);
                chooseZip = (EditText) dialogueView.findViewById(R.id.chooseZip);

                builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        zips.add(String.valueOf(chooseZip.getText()));
                        notifyDataSetChanged();
                    }
                })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        this.city = (TextView) weatherPagerView.findViewById(R.id.city);
        this.zipcode = (TextView) weatherPagerView.findViewById(R.id.zipcode);
        this.zipcode.setText(zips.get(position));

        this.time = (android.widget.TextClock) weatherPagerView.findViewById(R.id.dateTime);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
        Date date = new Date();
        this.time.setText(format.format(date));

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("zip", zips.get(position));
        map.put("appid", "2de143494c0b295cca9337e1e96b00e0");
        map.put("units", "imperial");

        final List<Object> weathers = new ArrayList<>();

        Call<CurrentWeather> callCurrent = weatherService.getCurrentWeather(map);
        callCurrent.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                weathers.add(0, currentWeather);
                city.setText(currentWeather.getLocation());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        map.put("cnt", "16");
        Call<LocationWeather> forecastCall = weatherService.getForecastWeather(map);
        forecastCall.enqueue(new Callback<LocationWeather>() {
            @Override
            public void onResponse(Response<LocationWeather> response) {
                LocationWeather locationWeather = response.body();

                ListInfo[] listItems = locationWeather.getList();
                for (ListInfo item : listItems) {
                    weathers.add(item);
                }
                WeatherRecyclerAdapter weatherRecyclerAdapter = new WeatherRecyclerAdapter(weathers, context, locationWeather.getLocation());
                weatherRecyclerView.setAdapter(weatherRecyclerAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });


        container.addView(weatherPagerView);
        return weatherPagerView;
    }

    @Override
    public int getCount() {
        return zips.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
