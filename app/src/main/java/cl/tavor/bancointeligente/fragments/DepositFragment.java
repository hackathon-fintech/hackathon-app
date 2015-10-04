package cl.tavor.bancointeligente.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cl.tavor.bancointeligente.R;
import cl.tavor.bancointeligente.activities.CustomerServiceActivity;
import cl.tavor.bancointeligente.activities.DepositActivity;
import cl.tavor.bancointeligente.activities.DepositsActivity;
import cl.tavor.bancointeligente.activities.MainActivity;

/**
 * Created by gustavo on 10/3/15.
 */
public class DepositFragment extends Fragment {

    private static View view;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private EditText numberAccountEdit;
    private EditText accountHolderEdit;
    private EditText depositerNameEdit;
    private EditText depositerPhoneEdit;
    private LinearLayout cashLayout;
    private LinearLayout docLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_deposit, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    /** Called when the activity is first created. */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numberAccountEdit = (EditText) view.findViewById(R.id.editText_accountNumber);
        accountHolderEdit = (EditText) view.findViewById(R.id.editText_holderName);
        depositerNameEdit = (EditText) view.findViewById(R.id.editText_depositerName);
        depositerPhoneEdit = (EditText) view.findViewById(R.id.editText_depositerPhone);

        Switch myAccountSwitch = (Switch) view.findViewById(R.id.switch_myAccount);
        myAccountSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    numberAccountEdit.setVisibility(View.GONE);
                    accountHolderEdit.setVisibility(View.GONE);
                    depositerNameEdit.setVisibility(View.GONE);
                    depositerPhoneEdit.setVisibility(View.GONE);
                } else {
                    numberAccountEdit.setVisibility(View.VISIBLE);
                    accountHolderEdit.setVisibility(View.VISIBLE);
                    depositerNameEdit.setVisibility(View.VISIBLE);
                    depositerPhoneEdit.setVisibility(View.VISIBLE);
                }
            }
        });

        cashLayout = (LinearLayout) view.findViewById(R.id.layout_cashDeposit);
        docLayout = (LinearLayout) view.findViewById(R.id.layout_documentDeposit);

        CheckBox cashCheckbox = (CheckBox) view.findViewById(R.id.checkbox_depositCash);
        cashCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    cashLayout.setVisibility(View.VISIBLE);
                }
                else {
                    cashLayout.setVisibility(View.GONE);
                }
            }
        });

        CheckBox docCheckbox = (CheckBox) view.findViewById(R.id.checkbox_depositDocument);
        docCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    docLayout.setVisibility(View.VISIBLE);
                } else {
                    docLayout.setVisibility(View.GONE);
                }
            }
        });



    }

    public static DepositFragment newInstance(int sectionNumber) {
        DepositFragment fragment = new DepositFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((DepositActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }*/



}
