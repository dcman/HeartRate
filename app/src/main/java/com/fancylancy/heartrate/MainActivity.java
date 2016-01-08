package com.fancylancy.heartrate;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String TAG = MainActivity.class.getSimpleName();
    private BluetoothAdapter mBluetoothAdapter;
    private boolean searchBt = true;
    private View.OnClickListener mOnClickListener;
    List<BluetoothDevice> pairedDevices = new ArrayList<BluetoothDevice>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i(TAG, "Started");
        bluetoothOn();
        listBTDevices();
    }

    private void listBTDevices() {
        final List<String> list = new ArrayList<String>();
        pairedDevices.addAll(mBluetoothAdapter.getBondedDevices());
        for (BluetoothDevice device : pairedDevices) {
            // Add the name and address to an array adapter to show in a ListView
            list.add(device.getName() + " " + device.getAddress());

        }
        Log.i(TAG,list.toString());
     }

    private void bluetoothOn() {
        if (!mBluetoothAdapter.isEnabled()) {
            Log.i(TAG, "Turning on BT");
            Snackbar.make(findViewById(android.R.id.content), R.string.bluetoothOn, Snackbar.LENGTH_LONG)
                    .setAction(R.string.undo, mOnClickListener)
                    .setActionTextColor(Color.RED)
                    .show();
            mBluetoothAdapter.enable();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
