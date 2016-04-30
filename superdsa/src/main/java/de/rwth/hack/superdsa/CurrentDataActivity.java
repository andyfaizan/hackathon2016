package de.rwth.hack.superdsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.*;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.dsa.hackathon2016.lib.IVehicleStatus;
import de.dsa.hackathon2016.lib.VehicleStatusReceiver;
import de.dsa.hackathon2016.lib.VehicleStatusUtils;


public class CurrentDataActivity extends AppCompatActivity {

    private ArcProgress arcSpeed;
    private ArcProgress arcRPM;
    private ArcProgress arcFuel;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_data);

        arcSpeed = (ArcProgress) findViewById(R.id.speed);
        arcRPM = (ArcProgress) findViewById(R.id.rpm);
        arcFuel = (ArcProgress) findViewById(R.id.fuel);
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
                                int iEngineRpm = Math.round(vehicleStatus.getEngineRpm());
                                int iSpeed = Math.round(vehicleStatus.getSpeed());
                                int iFuel = Math.round(vehicleStatus.getFuelLevel());
                                arcSpeed.setProgress(iSpeed);
                                arcRPM.setProgress(iEngineRpm);
                                arcFuel.setProgress(iFuel);
                                if(iEngineRpm > 5000)
                                {
                                    arcRPM.setFinishedStrokeColor( -65536);
                                }
                                else
                                {
                                    arcRPM.setFinishedStrokeColor( -16711936);
                                }
                                if(iSpeed > 100)
                                {
                                    arcSpeed.setFinishedStrokeColor(-65536);
                                }
                                else
                                {
                                    arcSpeed.setFinishedStrokeColor( -16711936);
                                }
                                if(iFuel < 50)
                                {
                                    arcFuel.setFinishedStrokeColor(-65536);
                                }
                                else
                                {
                                    arcFuel.setFinishedStrokeColor( -16711936);
                                }
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
