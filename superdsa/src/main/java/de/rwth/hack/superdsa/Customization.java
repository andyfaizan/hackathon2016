package de.rwth.hack.superdsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Customization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);
        ArrayList<String> data = new ArrayList<String>();
        data.add("Drive on Slope      :   100 km");
        data.add("Drive on Urban Roads:   2000 km");
        data.add("Suggestion 1        :   Change Engine Maps");
        data.add("Suggestion 2        :   Change Pedal Maps");

//        SimpleAdapter adapter = new SimpleAdapter(this,android.R.layout.simple_list_item_1, data);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(  this,
                android.R.layout.simple_list_item_1,
                data
        );

        ((ListView) findViewById(R.id.custlistView)).setAdapter(aa);


    }
}
