package yarmark.yarmarkweather;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    private TextView dayOfWeek;
    private TextView high;
    private TextView low;
    private ImageView imageView;
    private Context context;

    public ForecastViewHolder(View itemView, Context mainActivityContext) {
        super(itemView);
        this.context = mainActivityContext;

        this.dayOfWeek = (TextView) itemView.findViewById(R.id.dayOfWeek);
        this.imageView = (ImageView) itemView.findViewById(R.id.icon);
        this.high = (TextView) itemView.findViewById(R.id.forecastHigh);
        this.low = (TextView) itemView.findViewById(R.id.forecastLow);
    }

    public void bind(ListInfo weather) {
        Picasso.with(this.context).load("http://openweathermap.org/img/w/" + weather.getWeather().getIcon() + ".png")
                .placeholder(R.drawable.naweather)
                .into(imageView);
        this.dayOfWeek.setText(weather.getDt());
        this.high.setText(weather.getTemp().getMax() + "°");
        this.low.setText("   " + weather.getTemp().getMin() + "°");
    }
}
