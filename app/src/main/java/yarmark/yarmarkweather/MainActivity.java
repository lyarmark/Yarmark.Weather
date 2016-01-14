package yarmark.yarmarkweather;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ViewPager locationsViewPager;
    ArrayList<String> zips;
    private SharedPreferences sharedPreferences;
    private LocationPagerAdapter locationsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationsViewPager = (ViewPager) findViewById(R.id.viewPager);
        zips = new ArrayList<String>();
        sharedPreferences = this.getSharedPreferences("DEFAULT", MODE_PRIVATE);

        locationsPagerAdapter = new LocationPagerAdapter(zips, this);
        locationsViewPager.setAdapter(locationsPagerAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        StringBuilder builder = new StringBuilder();
        for (String l : zips) {
            builder.append(l + " ");
        }
        editor.putString("LOCATIONS", builder.toString());
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        String temp = sharedPreferences.getString("LOCATIONS", "11218");
        String[] temp2 = temp.split(" ");
        for (String s : temp2) {
            zips.add(s);
        }
//        String[] zipsArray = sharedPreferences.getString("ZIPCODES", "11218").split(" ");
        //      ArrayList<String> zips = new ArrayList<String>();
        //    Collections.addAll(zips, zipsArray);
        locationsPagerAdapter.notifyDataSetChanged();
    }
}
