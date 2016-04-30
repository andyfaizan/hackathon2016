package de.rwth.hack.superdsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.*;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;
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
    protected BottomSheetLayout bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_data);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheetLayout.setPeekOnDismiss(true);


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
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.grid_menu:
                showMenuSheet(MenuSheetView.MenuType.GRID);
                break;
            case R.id.notification:
                Intent intent=new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.actionitems, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showMenuSheet(final MenuSheetView.MenuType menuType) {
        MenuSheetView menuSheetView =
                new MenuSheetView(CurrentDataActivity.this, menuType,"", new MenuSheetView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(CurrentDataActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (bottomSheetLayout.isSheetShowing()) {
                            bottomSheetLayout.dismissSheet();
                        }
                        if (item.getItemId() == R.id.history) {
                            Intent intent1=new Intent(CurrentDataActivity.this, HistoryActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.current_data) {
                            Intent intent1=new Intent(CurrentDataActivity.this, CurrentDataActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.health) {
                            Intent intent1=new Intent(CurrentDataActivity.this, HealthActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.calender) {
                            Intent intent1=new Intent(CurrentDataActivity.this, LocationActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.logout) {
                            Intent intent1=new Intent(CurrentDataActivity.this, LoginActivity.class);
                            startActivity(intent1);
                        }
                        return true;

                    }
                });
        menuSheetView.inflateMenu(R.menu.create);
        bottomSheetLayout.showWithSheetView(menuSheetView);
    }
}
