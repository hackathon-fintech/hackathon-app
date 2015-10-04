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

public class CashdeskActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashdesk);

        ImageView imgCash = (ImageView) findViewById(R.id.imageView_deposits);
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_deposits, imgCash);
        ImageView imgDocument = (ImageView) findViewById(R.id.imageView_billPayment);
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_billpayment, imgDocument);

        CardView buttonCashdesk = (CardView) findViewById(R.id.cardview_deposits);
        buttonCashdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cashDeskIntent = new Intent(CashdeskActivity.this, DepositActivity.class);
                startActivity(cashDeskIntent);
            }
        });

        CardView buttonExecutive = (CardView) findViewById(R.id.cardview_billPayment);
        buttonExecutive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cashDeskIntent = new Intent(CashdeskActivity.this, BillPaymentActivity.class);
                startActivity(cashDeskIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cashdesk, menu);
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
