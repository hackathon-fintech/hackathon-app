package cl.tavor.bancointeligente.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.Gson;
import java.util.List;
import cl.tavor.bancointeligente.App;
import cl.tavor.bancointeligente.R;
import cl.tavor.bancointeligente.fragments.MainFragment;
import cl.tavor.bancointeligente.fragments.NavigationDrawerFragment;
import io.swagger.client.ApiException;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.UserAccount;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null){
            if (!bluetoothAdapter.isEnabled()){
                bluetoothAdapter.enable();
            }
        }

        App.beaconManager = new BeaconManager(this);
        App.beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(final Region region, final List<Beacon> beacons) {
                // Note that results are not delivered on UI thread.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Just in case if there are multiple beacons with the same uuid, major, minor.
                        //System.out.println("Region found: " + gson.toJson(region));
                        if (beacons.isEmpty()){
                            System.out.println("No beacons in range ------ ");
                            if (App.isInside){
                                Snackbar.make(findViewById(android.R.id.content), "Gracias por venir!", Snackbar.LENGTH_LONG)
                                        .setAction("Ok", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                            }
                                        })
                                        .setActionTextColor(Color.WHITE)
                                        .show();
                            }
                            App.isInside = false;
                            if (App.userAccount != null){
                                String status = "";
                                new UpdateUserStatus().execute(status);
                            }

                        }
                        else {
                            for (Beacon rangedBeacon : beacons) {
                                System.out.println("Beacon in Range --- Reg:" + region.getIdentifier() + " Meters = " + Utils.computeProximity(rangedBeacon) + " +- " + Utils.computeAccuracy(rangedBeacon));
                                System.out.println("isInside --- " + String.valueOf(App.isInside));
                                if (region.getIdentifier().equals("SAC") &&
                                        (Utils.computeProximity(rangedBeacon) == Utils.Proximity.NEAR || Utils.computeProximity(rangedBeacon) == Utils.Proximity.IMMEDIATE || Utils.computeProximity(rangedBeacon) == Utils.Proximity.FAR) && !App.isInside){
                                    App.isInside = true;
                                    if (App.sacRequested){
                                        Snackbar.make(findViewById(android.R.id.content), "Bienvenido! Su ejecutivo lo está esperando.", Snackbar.LENGTH_LONG)
                                                .setAction("Ok", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                    }
                                                })
                                                .setActionTextColor(Color.WHITE)
                                                .show();
                                        App.sacRequested = false;
                                    }
                                    else if (App.depositRequested){
                                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                        Snackbar.make(findViewById(android.R.id.content), "Bienvenido! Su número de atención es el " + App.depositId + ".", Snackbar.LENGTH_LONG)
                                                .setAction("Ok", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                    }
                                                })
                                                .setActionTextColor(Color.WHITE)
                                                .show();
                                        App.depositRequested = false;
                                    }
                                    else {
                                        Snackbar.make(findViewById(android.R.id.content), "Bienvenido!", Snackbar.LENGTH_LONG)
                                                .setAction("Ok", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                    }
                                                })
                                                .setActionTextColor(Color.WHITE)
                                                .show();
                                    }
                                    if (App.userAccount != null){
                                        String status = "INSIDE";
                                        new UpdateUserStatus().execute(status);
                                    }
                                }

                            }
                        }
                    }
                });
            }
        });

        App.beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    App.beaconManager.startRanging(App.SAC_REGION);
                } catch (RemoteException e) {
                    Toast.makeText(MainActivity.this, "Cannot start ranging, something terrible happened",
                            Toast.LENGTH_LONG).show();
                    Log.e(getClass().getCanonicalName(), "Cannot start ranging", e);
                }
            }
        });

        new Userlogin().execute();

    }


    @Override
    protected void onDestroy() {
        App.isInside = false;
        try {
            App.beaconManager.stopRanging(App.SAC_REGION);
            App.beaconManager.disconnect();
        } catch (RemoteException e) {
            Log.d(this.getClass().getSimpleName(), "Error while stopping ranging", e);
        }
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = MainFragment.newInstance(position + 1);
                break;
            case 1:
                fragment = MainFragment.newInstance(position + 1);
                break;
            case 2:
                fragment = MainFragment.newInstance(position + 1);
                break;
            case 3:
                fragment = MainFragment.newInstance(position + 1);
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    private class Userlogin extends AsyncTask<Object, String, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Integer doInBackground(Object... objects) {
            UserApi usr = new UserApi();
            try {
                Gson gson = new Gson();
                UserAccount account = usr.login("53101275","68641");
                App.userAccount = account;
                Log.i(this.getClass().getSimpleName(), "Login Result = " + gson.toJson(account));
            } catch (ApiException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {

        }
    }

    private class UpdateUserStatus extends AsyncTask<String, String, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... objects) {
            String branchStatus = objects[0];
            UserApi usr = new UserApi();
            App.userAccount.setBranchStatus(branchStatus);
            if (branchStatus.equals("INSIDE")){
                App.userAccount.setBranchCode("MONEDA");
            }
            else {
                App.userAccount.setBranchCode("");
            }
            Log.i(this.getClass().getSimpleName(), "Setting branch status: " + branchStatus);
            try {
                Gson gson = new Gson();
                UserAccount result = usr.putJson(App.userAccount.getToken(),App.userAccount);
                App.userAccount = result;
                Log.i(this.getClass().getSimpleName(), "Result: " + gson.toJson(result));
            } catch (ApiException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {

        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        protected static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
