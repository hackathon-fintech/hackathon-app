package cl.tavor.bancointeligente.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cl.tavor.bancointeligente.R;

public class BillPaymentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);

        ImageView imgCash = (ImageView) findViewById(R.id.imageView_registeredAccount);
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_registered_account, imgCash);
        ImageView imgDocument = (ImageView) findViewById(R.id.imageView_newAccount);
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_new_account, imgDocument);

        CardView buttonCashdesk = (CardView) findViewById(R.id.cardview_registeredAccount);
        buttonCashdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cashDeskIntent = new Intent(CashdeskActivity.this, DepositsActivity.class);
                startActivity(cashDeskIntent);*/
            }
        });

        CardView buttonExecutive = (CardView) findViewById(R.id.cardview_newAccount);
        buttonExecutive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cashDeskIntent = new Intent(getActivity(), CashdeskActivity.class);
                startActivity(cashDeskIntent);*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bill_payment, menu);
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
