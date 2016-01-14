package yarmark.yarmarkweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> weather;
    private Context context;
    private String city;

    public WeatherRecyclerAdapter(List<Object> weather, Context mainActivityContext, String city) {
        this.weather = weather;
        this.context = mainActivityContext;
        this.city = city;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case 0: {
                View current = inflater.inflate(R.layout.current_weather, viewGroup, false);
                viewHolder = new CurrentViewHolder(current, viewGroup.getContext());
                break;
            }
            case 1: {
                View forecast = inflater.inflate(R.layout.forecast, viewGroup, false);
                viewHolder = new ForecastViewHolder(forecast, viewGroup.getContext());
                break;
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (position) {
            case 0: {
                CurrentViewHolder currentViewHolder = (CurrentViewHolder) viewHolder;
                currentViewHolder.bind((CurrentWeather) weather.get(position), this.city);
                break;
            }
            case 1: {
                ForecastViewHolder forecastViewHolder = (ForecastViewHolder) viewHolder;
                forecastViewHolder.bind((ListInfo) weather.get(position));
                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return weather.size();
    }
}
