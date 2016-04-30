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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import static de.dsa.app.R.layout.activity_notification;

/**
 * The main activity, to show the data of a single vehicle retrieved from the server.
 */
public class NotificationActivity extends AppCompatActivity {
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
        setContentView(activity_notification);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheetLayout.setPeekOnDismiss(true);


        List<Map<String, String>> data = new ArrayList<>();
        setTitleValueToList(data, "Vehicle ID", "GO HOME");
        setTitleValueToList(data, "Timestamp", "GO HOME");
        setTitleValueToList(data, "Latitude", "GO HOME");
        setTitleValueToList(data, "Longitude", "GO HOME");
        setTitleValueToList(data, "Speed", "GO HOME");
        setTitleValueToList(data, "Engine RPM", "GO HOME");
        setTitleValueToList(data, "Fuel Level", "GO HOME");
        setTitleValueToList(data, "Coolant Temperature", "GO HOME");
        setTitleValueToList(data, "Total Fuel Used", "GO HOME");
        setTitleValueToList(data, "Service Distance", "GO HOME");
        setTitleValueToList(data, "Axle Weight", "GO HOME");
        setTitleValueToList(data, "Odometer", "GO HOME");
        setTitleValueToList(data, "Battery Voltage", "GO HOME");
        SimpleAdapter adapter = new SimpleAdapter(NotificationActivity.this, data, android.R.layout.simple_list_item_2, new String[]{TITLE, VALUE}, new int[]{android.R.id.text1, android.R.id.text2});
        ((ListView) findViewById(R.id.notificationlistView)).setAdapter(adapter);


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
            case R.id.notification:
                Intent intent=new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.current_data:
                Intent intent1=new Intent(this, MainActivity.class);
                startActivity(intent1);
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
                new MenuSheetView(NotificationActivity.this, menuType,"", new MenuSheetView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(NotificationActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
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

}