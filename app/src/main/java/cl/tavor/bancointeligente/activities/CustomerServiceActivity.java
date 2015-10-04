package cl.tavor.bancointeligente.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cl.tavor.bancointeligente.R;

public class CustomerServiceActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);

        ImageView imgCheck = (ImageView) findViewById(R.id.imageView_requestCheckBook);
        //ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_registered_account, imgCheck);
        ImageView imgDocument = (ImageView) findViewById(R.id.imageView_requestPromissory);
        //ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_new_account, imgDocument);

        CardView buttonCheck = (CardView) findViewById(R.id.cardview_requestCheckBook);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        CardView buttonPromissory = (CardView) findViewById(R.id.cardview_requestPromissory);
        buttonPromissory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer, menu);
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
