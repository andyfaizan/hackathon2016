package de.rwth.hack.superdsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.dsa.hackathon2016.lib.IVehicleStatus;
import de.dsa.hackathon2016.lib.VehicleStatusReceiver;
import de.dsa.hackathon2016.lib.VehicleStatusUtils;

public class HealthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        String vehicleId = getIntent().getExtras().getString("vehicleNum");

        VehicleStatusUtils.getLastVehicleStatus(vehicleId, new VehicleStatusReceiver() {
            @Override
            public void onStatusReceived(IVehicleStatus vehicleStatus) {
                if (vehicleStatus == null) {
                    Toast.makeText(HealthActivity.this, "No vehicle status could be obtained.", Toast.LENGTH_SHORT).show();
                    return;
                }
                ImageView batterylevel = (ImageView) findViewById(R.id.battery);
                TextView batteryText = (TextView) findViewById(R.id.batteryText);
                if(vehicleStatus.getBatteryVoltage() < 8){
                    batterylevel.setImageResource(R.drawable.sweat);
                    batteryText.setText("Battery capacity has reduced. Please replace!!");
                }else {
                    batterylevel.setImageResource(R.drawable.happy);
                    batteryText.setText("Battery is healthy!!");
                }

                ImageView coolingSystem = (ImageView) findViewById(R.id.cooling);
                TextView coolingText = (TextView) findViewById(R.id.coolingText);
                if(vehicleStatus.getCoolantTemp() > 25){
                    coolingSystem.setImageResource(R.drawable.sweat);
                    coolingText.setText("Engine coolant is hot from long time. Needs attention!!");
                }else {
                    coolingSystem.setImageResource(R.drawable.happy);
                    coolingText.setText("Engine Works perfectly!!");
                }

                //Engine status
                ImageView engineStatus = (ImageView) findViewById(R.id.engine);
                TextView engineText = (TextView) findViewById(R.id.engineText);
                // if(vehicleStatus.getCoolantTemp() > 25){
//                    coolingSystem.setImageResource(R.drawable.sad);
//                }else {
                engineStatus.setImageResource(R.drawable.happy);
                engineText.setText("Engine is Perfect!!");
//                }

                ImageView wheelStatus = (ImageView) findViewById(R.id.wheels);
                TextView wheelsText = (TextView) findViewById(R.id.wheelText);
                // if(vehicleStatus.getCoolantTemp() > 25){
//                    coolingSystem.setImageResource(R.drawable.sad);
//                }else {
                wheelStatus.setImageResource(R.drawable.sweat);
                wheelsText.setText("Rear wheels need alignment!!");
                wheelStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HealthActivity.this, HistoryActivity.class);
                        Bundle b = new Bundle();
                        startActivity(intent);
                    }
                });
//                }
            }
        });

    }



}
