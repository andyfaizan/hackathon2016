package de.rwth.hack.superdsa;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

public class HistoryActivity extends AppCompatActivity {

    protected BottomSheetLayout bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histoy);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheetLayout.setPeekOnDismiss(true);
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
                new MenuSheetView(HistoryActivity.this, menuType,"", new MenuSheetView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(HistoryActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (bottomSheetLayout.isSheetShowing()) {
                            bottomSheetLayout.dismissSheet();
                        }
                        if (item.getItemId() == R.id.history) {
                            Intent intent1=new Intent(HistoryActivity.this, HistoryActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.current_data) {
                            Intent intent1=new Intent(HistoryActivity.this, CurrentDataActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.health) {
                            Intent intent1=new Intent(HistoryActivity.this, HealthActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.calender) {
                            Intent intent1=new Intent(HistoryActivity.this, LocationActivity.class);
                            startActivity(intent1);
                        }
                        if (item.getItemId() == R.id.logout) {
                            Intent intent1=new Intent(HistoryActivity.this, LoginActivity.class);
                            startActivity(intent1);
                        }
                        return true;

                    }
                });
        menuSheetView.inflateMenu(R.menu.create);
        bottomSheetLayout.showWithSheetView(menuSheetView);
    }
}
