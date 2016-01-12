package yarmark.yarmarkweather;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    private TextView high;
    private TextView low;
    private ImageView imageView;
    private Context context;

    public ForecastViewHolder(View itemView, Context mainActivityContext) {
        super(itemView);
        this.context = mainActivityContext;

        this.imageView = (ImageView) itemView.findViewById(R.id.icon);
        this.high = (TextView) itemView.findViewById(R.id.forecastHigh);
        this.low = (TextView) itemView.findViewById(R.id.forecastLow);
    }

    public void bind(LocationWeather weather) {
        this.high.setText(weather.getTemp(0).getMax());
        this.low.setText(weather.getTemp(0).getMin());

    }

}
