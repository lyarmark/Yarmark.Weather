package yarmark.yarmarkweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ListInfo[] weatherList;
    private Context context;
    private String city;

    public WeatherRecyclerAdapter(ListInfo[] weatherList, Context mainActivityContext, String city) {
        this.weatherList = weatherList;
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
        switch (viewHolder.getItemViewType()) {
            case 0: {
                CurrentViewHolder currentViewHolder = (CurrentViewHolder) viewHolder;
                currentViewHolder.bind(this.weatherList[0], this.city);
                break;
            }
            case 1: {
                ForecastViewHolder forecastViewHolder = (ForecastViewHolder) viewHolder;
                forecastViewHolder.bind(this.weatherList[position]);
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
        return weatherList.length;
    }
}
