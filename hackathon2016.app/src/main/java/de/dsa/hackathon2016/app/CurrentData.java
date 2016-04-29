package de.dsa.hackathon2016.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.*;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.dsa.app.R;
import de.dsa.hackathon2016.lib.IVehicleStatus;
import de.dsa.hackathon2016.lib.VehicleStatusReceiver;
import de.dsa.hackathon2016.lib.VehicleStatusUtils;


public class CurrentData extends AppCompatActivity {

    private ArcProgress arcSpeed;
    private ArcProgress arcRPM;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_data);


                arcSpeed = (ArcProgress) findViewById(R.id.speed);
                arcRPM = (ArcProgress) findViewById(R.id.rpm);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String vehicleId = "0468";
                        VehicleStatusUtils.getLastVehicleStatus(vehicleId, new VehicleStatusReceiver() {
                            public void onStatusReceived(final IVehicleStatus vehicleStatus) {
                                arcSpeed.setProgress(Math.round(vehicleStatus.getSpeed()));
                                arcRPM.setProgress(Math.round(vehicleStatus.getEngineRpm()));
                            }
                        });

                    }
                });
            }
        }, 1000, 100);

    }


        @Override
        protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
