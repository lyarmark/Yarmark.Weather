package yarmark.yarmarkweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CurrentViewHolder extends RecyclerView.ViewHolder {

    private TextView location;
    private TextView temperature;
    private TextView high;
    private TextView low;
    private TextView description;
    private Context context;

    public CurrentViewHolder(View itemView, Context mainActivityContext) {
        super(itemView);
        this.context = mainActivityContext;

        this.temperature = (TextView) itemView.findViewById(R.id.temperature);
        this.high = (TextView) itemView.findViewById(R.id.high);
        this.low = (TextView) itemView.findViewById(R.id.low);
        this.description = (TextView) itemView.findViewById(R.id.desc);
    }

    public void bind(ListInfo currentWeather) {
        this.temperature.setText(currentWeather.getTemp().getDay());
        this.high.setText(currentWeather.getTemp().getMax());
        this.low.setText(currentWeather.getTemp().getMin());
        this.description.setText(currentWeather.getWeather().getDescription());

    }
}
