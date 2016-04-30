package de.rwth.hack.superdsa;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainWSActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ws);
        RelativeLayout serviceList = (RelativeLayout) findViewById(R.id.servicelistLayout);
        serviceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        RelativeLayout vehicleHistory = (RelativeLayout) findViewById(R.id.vehiclehistoryLayout);
        vehicleHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        RelativeLayout serviceHistory = (RelativeLayout) findViewById(R.id.servicehistoryLayout);
        serviceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        RelativeLayout customization = (RelativeLayout) findViewById(R.id.customizationLayout);
        customization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
