package de.rwth.hack.superdsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceHistory extends AppCompatActivity implements View.OnTouchListener{


    private static final String TITLE = "title";
    private static final String VALUE = "value";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_history);

        ArrayList<String> data = new ArrayList<String>();
        data.add("01.02.2016 : performed overall diagnostics, No problems found");
        data.add("Last Month: No Service");
//        SimpleAdapter adapter = new SimpleAdapter(this,android.R.layout.simple_list_item_1, data);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(  this,
                android.R.layout.simple_list_item_1,
                data
        );

        ((ListView) findViewById(R.id.serviceHistorylistView)).setAdapter(aa);


    }

    private void setTitleValueToList(List<Map<String, String>> data, String title, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, title);
        map.put(VALUE, value);
        data.add(map);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // check which textview it is and do what you need to do

        // return true if you don't want it handled by any other touch/click events after this
        return true;
    }
}
