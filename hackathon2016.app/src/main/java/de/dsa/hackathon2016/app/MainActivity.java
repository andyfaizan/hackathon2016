/************************************************************************
 *                                                                      *
 *  DDDD     SSSS    AAA        Daten- und Systemtechnik Aachen GmbH    *
 *  D   D   SS      A   A       Pascalstrasse 28                        *
 *  D   D    SSS    AAAAA       52076 Aachen-Oberforstbach, Germany     *
 *  D   D      SS   A   A       Telefon: +49 (0)2408 / 9492-0           *
 *  DDDD    SSSS    A   A       Telefax: +49 (0)2408 / 9492-92          *
 *                                                                      *
 *                                                                      *
 *  (c) Copyright by DSA - all rights reserved                          *
 *                                                                      *
 ************************************************************************
 *
 * Initial Creation:
 *    Author      pb
 *    Created on  29/03/2016
 *
 ************************************************************************/
package de.dsa.hackathon2016.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dsa.app.R;
import de.dsa.hackathon2016.lib.IVehicleStatus;
import de.dsa.hackathon2016.lib.VehicleStatusReceiver;
import de.dsa.hackathon2016.lib.VehicleStatusUtils;

import static de.dsa.app.R.layout.activity_main;

/**
 * The main activity, to show the data of a single vehicle retrieved from the server.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TITLE = "title";
    private static final String VALUE = "value";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    protected BottomSheetLayout bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheetLayout.setPeekOnDismiss(true);


        findViewById(R.id.refreshButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehicleId = ((EditText) findViewById(R.id.vehicleIdEditText)).getText().toString();
                if (StringUtils.isBlank(vehicleId)) {
                    Toast.makeText(MainActivity.this, "Empty vehicle ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                VehicleStatusUtils.getLastVehicleStatus(vehicleId, new VehicleStatusReceiver() {
                    @Override
                    public void onStatusReceived(IVehicleStatus vehicleStatus) {
                        if (vehicleStatus == null) {
                            Toast.makeText(MainActivity.this, "No vehicle status could be obtained.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<Map<String, String>> data = new ArrayList<>();
                        setTitleValueToList(data, "Vehicle ID", vehicleStatus.getVehicleId());
                        setTitleValueToList(data, "Timestamp", vehicleStatus.getTimestamp());
                        setTitleValueToList(data, "Latitude", Float.toString(vehicleStatus.getGpsLatitude()));
                        setTitleValueToList(data, "Longitude", Float.toString(vehicleStatus.getGpsLongitude()));
                        setTitleValueToList(data, "Speed", Float.toString(vehicleStatus.getSpeed()));
                        setTitleValueToList(data, "Engine RPM", Integer.toString(vehicleStatus.getEngineRpm()));
                        setTitleValueToList(data, "Fuel Level", Float.toString(vehicleStatus.getFuelLevel()));
                        setTitleValueToList(data, "Coolant Temperature", Float.toString(vehicleStatus.getCoolantTemp()));
                        setTitleValueToList(data, "Total Fuel Used", Integer.toString(vehicleStatus.getTotalFuelUsed()));
                        setTitleValueToList(data, "Service Distance", Integer.toString(vehicleStatus.getServiceDistance()));
                        setTitleValueToList(data, "Axle Weight", Float.toString(vehicleStatus.getAxleWeight()));
                        setTitleValueToList(data, "Odometer", Float.toString(vehicleStatus.getOdometer()));
                        setTitleValueToList(data, "Battery Voltage", Float.toString(vehicleStatus.getBatteryVoltage()));
                        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, data, android.R.layout.simple_list_item_2, new String[]{TITLE, VALUE}, new int[]{android.R.id.text1, android.R.id.text2});
                        ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
                    }
                });
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Sets the title and value to the ListView item list.
     *
     * @param data  The list of maps that contain the key-value pairs.
     * @param title The title of the value.
     * @param value The value.
     */
    private void setTitleValueToList(List<Map<String, String>> data, String title, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, title);
        map.put(VALUE, value);
        data.add(map);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.grid_menu:
                showMenuSheet(MenuSheetView.MenuType.GRID);
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
                new MenuSheetView(MainActivity.this, menuType,"", new MenuSheetView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (bottomSheetLayout.isSheetShowing()) {
                            bottomSheetLayout.dismissSheet();
                        }
                        if (item.getItemId() == R.id.history) {
                            showMenuSheet(MenuSheetView.MenuType.GRID);
                        }
                        return true;
                    }
                });
        menuSheetView.inflateMenu(R.menu.create);
        bottomSheetLayout.showWithSheetView(menuSheetView);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://de.dsa.hackathon2016.app/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }



    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://de.dsa.hackathon2016.app/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
