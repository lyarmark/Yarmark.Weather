package yarmark.yarmarkweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ListInfo[] items;
    private Context context;

    public WeatherRecyclerAdapter(ListInfo[] items, Context mainActivityContext) {
        this.items = items;
        this.context = mainActivityContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case 0: {
                View currentView = inflater.inflate(R.layout.current_weather, viewGroup, false);
                viewHolder = new CurrentViewHolder(currentView);
                break;
            }
            case 1: {
                View forecastView = inflater.inflate(R.layout.recycler_forecast, viewGroup, false);
                viewHolder = new ForecastViewHolder(forecastView);
                break;
            }
            default: {
                View currentView = inflater.inflate(R.layout.current_weather, viewGroup, false);
                viewHolder = new CurrentViewHolder(currentView);
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0: {
                CurrentViewHolder currentViewHolder = (CurrentViewHolder) viewHolder;
                break;
            }
            case 1: {
                ForecastViewHolder forecastViewHolder = (ForecastViewHolder) viewHolder;
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
