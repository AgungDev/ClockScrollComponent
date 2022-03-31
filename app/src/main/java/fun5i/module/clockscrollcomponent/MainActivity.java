package fun5i.module.clockscrollcomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import fun5i.module.clockscroll.NumberPickerScroll;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainACtivity";

    private NumberPickerScroll np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        np = findViewById(R.id.np);

        np.setweight(-10, 15);
        np.setSP(0);
        //np.setweight(new String[]{"AM", "PM"});

        np.Action(new NumberPickerScroll.onClickBtnFunction() {
            @Override
            public void theAction(String mData, boolean btn) {
                Toast.makeText(getApplicationContext(), (btn)?"{up} "+mData:"{down} "+mData, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "theAction: "+np.getPosition());
            }
        });
    }
}