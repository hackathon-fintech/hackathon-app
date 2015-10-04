package cl.tavor.bancointeligente.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Locale;

import cl.tavor.bancointeligente.App;
import cl.tavor.bancointeligente.activities.CashdeskActivity;
import cl.tavor.bancointeligente.activities.CustomerServiceActivity;
import cl.tavor.bancointeligente.activities.DepositActivity;
import cl.tavor.bancointeligente.activities.MainActivity;
import cl.tavor.bancointeligente.R;

/**
 * Created by gustavo on 10/3/15.
 */
public class MainFragment extends Fragment {

    private static View view;
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    /** Called when the activity is first created. */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imgCashdesk = (ImageView) view.findViewById(R.id.imageView_cashdesk);
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_cashdesk, imgCashdesk);
        ImageView imgExecutive = (ImageView) view.findViewById(R.id.imageView_executive);
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_executive, imgExecutive);
        ImageView imgProcedures = (ImageView) view.findViewById(R.id.imageView_procedures);
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_service, imgProcedures);
        ImageView imgEmergencies = (ImageView) view.findViewById(R.id.imageView_emergencies);
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.button_emergencies, imgEmergencies);

        CardView buttonCashdesk = (CardView) view.findViewById(R.id.cardview_cashdesk);
        buttonCashdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DepositActivity.class);
                startActivity(intent);
            }
        });

        CardView buttonExecutive = (CardView) view.findViewById(R.id.cardview_executive);
        buttonExecutive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (App.isInside){
                    Toast.makeText(getActivity(), "Se le notificará a un ejecutivo que ud. está en la sucursal.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "Se le notificará a un ejecutivo de su llegada una vez que llegue al banco.", Toast.LENGTH_LONG).show();
                    App.sacRequested = true;
                }

            }
        });

        CardView buttonSAC = (CardView) view.findViewById(R.id.cardview_sac);
        buttonSAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent intent = new Intent(getActivity(), CustomerServiceActivity.class);
                startActivity(intent);*/
            }
        });

        CardView buttonEmergencies = (CardView) view.findViewById(R.id.cardview_emergencies);
        buttonEmergencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }



}
