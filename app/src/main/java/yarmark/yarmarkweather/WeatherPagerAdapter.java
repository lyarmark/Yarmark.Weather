package yarmark.yarmarkweather;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LeahYarmark on 1/9/2016.
 */
public class WeatherPagerAdapter extends PagerAdapter {

    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.temperature)
    TextView temperature;
    @Bind(R.id.high)
    TextView high;
    @Bind(R.id.low)
    TextView low;
    @Bind(R.id.desc)
    TextView description;

    private CurrentWeather weather;


    public WeatherPagerAdapter(CurrentWeather weather) {
        this.weather = weather;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.current_weather, null);

        temperature.setText(this.weather.getTemperature());

        ButterKnife.bind(view);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
