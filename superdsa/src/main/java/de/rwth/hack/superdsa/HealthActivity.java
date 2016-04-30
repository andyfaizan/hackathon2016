package de.rwth.hack.superdsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

import de.dsa.hackathon2016.lib.IVehicleStatus;
import de.dsa.hackathon2016.lib.VehicleStatusReceiver;
import de.dsa.hackathon2016.lib.VehicleStatusUtils;

public class HealthActivity extends AppCompatActivity {
    protected BottomSheetLayout bottomSheetLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        String vehicleId = "0468";
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheetLayout.setPeekOnDismiss(true);

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
                new MenuSheetView(HealthActivity.this, menuType,"", new MenuSheetView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(HealthActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (bottomSheetLayout.isSheetShowing()) {
                            bottomSheetLayout.dismissSheet();
                        }
                        if (item.getItemId() == R.id.history) {
                            Intent intent1=new Intent(HealthActivity.this, HistoryActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.current_data) {
                            Intent intent1=new Intent(HealthActivity.this, CurrentDataActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.health) {
                            Intent intent1=new Intent(HealthActivity.this, HealthActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.calender) {
                            Intent intent1=new Intent(HealthActivity.this, LocationActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.logout) {
                            Intent intent1=new Intent(HealthActivity.this, LoginActivity.class);
                            startActivity(intent1);
                        }
                        return true;

                    }
                });
        menuSheetView.inflateMenu(R.menu.create);
        bottomSheetLayout.showWithSheetView(menuSheetView);
    }



}
