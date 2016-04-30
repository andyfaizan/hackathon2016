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
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dsa.app.R;
import de.dsa.hackathon2016.lib.IVehicleStatus;
import de.dsa.hackathon2016.lib.VehicleStatusReceiver;
import de.dsa.hackathon2016.lib.VehicleStatusUtils;

/**
 * The main activity, to show the data of a single vehicle retrieved from the server.
 */
public class MainActivity extends Activity {
    private static final String TITLE = "title";
    private static final String VALUE = "value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.refreshButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehicleId = ((EditText)findViewById(R.id.vehicleIdEditText)).getText().toString();
                if (StringUtils.isBlank(vehicleId)) {
                    Toast.makeText(MainActivity.this, "Empty vehicle ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, HealthActivity.class);
                Bundle b = new Bundle();
                b.putString("vehicleNum", vehicleId); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);

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
                        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, data, android.R.layout.simple_list_item_2, new String[] { TITLE, VALUE }, new int[] { android.R.id.text1, android.R.id.text2 });
                        ((ListView)findViewById(R.id.listView)).setAdapter(adapter);
                    }
                });
            }
        });
    }

    /**
     * Sets the title and value to the ListView item list.
     * @param data The list of maps that contain the key-value pairs.
     * @param title The title of the value.
     * @param value The value.
     */
    private void setTitleValueToList(List<Map<String, String>> data, String title, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, title);
        map.put(VALUE, value);
        data.add(map);
    }
}
