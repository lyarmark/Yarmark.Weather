package yarmark.yarmarkweather;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    private TextView high;
    private TextView low;
    private ImageView imageView;

    public ForecastViewHolder(View itemView) {
        super(itemView);

        this.imageView = (ImageView) itemView.findViewById(R.id.icon);
        this.high = (TextView) itemView.findViewById(R.id.forecastHigh);
        this.low = (TextView) itemView.findViewById(R.id.forecastLow);
    }

    public void bind(LocationWeather weather, int i) {
        this.high.setText(weather.getTemp(i).getMax());
        this.low.setText(weather.getTemp(i).getMin());

    }

}
