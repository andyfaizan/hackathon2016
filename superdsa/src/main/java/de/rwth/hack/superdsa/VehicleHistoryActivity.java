package de.rwth.hack.superdsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class VehicleHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_history);

        ArrayList<String> data = new ArrayList<String>();
        data.add("Total Distance: 25000 km");
        data.add("Distance since last service: 3000 km");
        data.add("Maximum Engine Running Time: 7 hrs");
        data.add("Fuel Consumption: 45 L");

//        SimpleAdapter adapter = new SimpleAdapter(this,android.R.layout.simple_list_item_1, data);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(  this,
                android.R.layout.simple_list_item_1,
                data
        );

        ((ListView) findViewById(R.id.vehislistView)).setAdapter(aa);

    }
}
