package yarmark.yarmarkweather;

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

    private LocationWeather currentWeather;

    public CurrentViewHolder(View itemView) {
        super(itemView);

        this.location = (TextView) itemView.findViewById(R.id.location);
        this.temperature = (TextView) itemView.findViewById(R.id.temperature);
        this.high = (TextView) itemView.findViewById(R.id.high);
        this.low = (TextView) itemView.findViewById(R.id.low);
        this.description = (TextView) itemView.findViewById(R.id.desc);
    }

    public void bind(LocationWeather currentWeather) {
        this.location.setText(this.currentWeather.getLocation());
        this.temperature.setText(this.currentWeather.getTemp(0).getDay());
        this.high.setText(this.currentWeather.getTemp(0).getMax());
        this.low.setText(this.currentWeather.getTemp(0).getMin());
        this.description.setText(this.currentWeather.getWeather(0).getDescription());

    }
}
