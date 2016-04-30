package de.rwth.hack.superdsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProminentDefectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prominent_defects);

        ArrayList<String> data = new ArrayList<String>();
        data.add("Inspect: Engine Critical!");
        data.add("Inspect: Brake Critical!");
        data.add("Battery Status: Normal(Minor)");
        data.add("Mirror Status: Automatic(Minor)");

//        SimpleAdapter adapter = new SimpleAdapter(this,android.R.layout.simple_list_item_1, data);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(  this,
                android.R.layout.simple_list_item_1,
                data
        );

        ((ListView) findViewById(R.id.issueslistView)).setAdapter(aa);

    }
}
