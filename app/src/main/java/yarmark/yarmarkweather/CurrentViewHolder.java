package yarmark.yarmarkweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CurrentViewHolder extends RecyclerView.ViewHolder {

    private TextView city;
    private ImageView weatherIcon;
    private TextView temperature;
    private TextView high;
    private TextView low;
    private TextView description;
    private Context context;

    public CurrentViewHolder(View itemView, Context mainActivityContext) {
        super(itemView);
        this.context = mainActivityContext;

        this.city = (TextView) itemView.findViewById(R.id.city);
        this.weatherIcon = (ImageView) itemView.findViewById(R.id.currentIcon);
        this.temperature = (TextView) itemView.findViewById(R.id.temperature);
        this.high = (TextView) itemView.findViewById(R.id.high);
        this.low = (TextView) itemView.findViewById(R.id.low);
        this.description = (TextView) itemView.findViewById(R.id.desc);
    }

    public void bind(ListInfo currentWeather, String city) {
        Picasso.with(this.context).load("http://openweathermap.org/img/w/" + currentWeather.getWeather().getIcon() + ".png")
                .placeholder(R.drawable.naweather)
                .into(weatherIcon);

        this.city.setText(city);
        this.temperature.setText(currentWeather.getTemp().getDay());
        this.high.setText(currentWeather.getTemp().getMax() + "°");
        this.low.setText("   " + currentWeather.getTemp().getMin() + "°");
        this.description.setText(currentWeather.getWeather().getDescription());
    }
}
