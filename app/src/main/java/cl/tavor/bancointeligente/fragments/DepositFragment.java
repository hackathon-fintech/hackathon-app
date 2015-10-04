package cl.tavor.bancointeligente.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cl.tavor.bancointeligente.App;
import cl.tavor.bancointeligente.R;
import cl.tavor.bancointeligente.activities.CustomerServiceActivity;
import cl.tavor.bancointeligente.activities.DepositActivity;
import cl.tavor.bancointeligente.activities.DepositsActivity;
import cl.tavor.bancointeligente.activities.MainActivity;
import io.swagger.client.ApiException;
import io.swagger.client.api.BranchApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.DepositSlip;
import io.swagger.client.model.DepositSlipDetail;
import io.swagger.client.model.UserToken;

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
    private CheckBox cashCheckbox;
    private CheckBox docCheckbox;
    private Double amount = 0.0;
    private EditText cash20000;
    private EditText cash10000;
    private EditText cash5000;
    private EditText cash1000;
    private EditText cashChange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_deposit, container, false);
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

        cash20000 = (EditText) view.findViewById(R.id.editText_cash20000);
        cash10000 = (EditText) view.findViewById(R.id.editText_cash10000);
        cash5000 = (EditText) view.findViewById(R.id.editText_cash5000);
        cash1000 = (EditText) view.findViewById(R.id.editText_cash1000);
        cashChange = (EditText) view.findViewById(R.id.editText_cash500);

        Switch myAccountSwitch = (Switch) view.findViewById(R.id.switch_myAccount);
        myAccountSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    numberAccountEdit.setVisibility(View.GONE);
                    accountHolderEdit.setVisibility(View.GONE);
                    depositerNameEdit.setVisibility(View.GONE);
                    depositerPhoneEdit.setVisibility(View.GONE);
                    numberAccountEdit.setText(App.userAccount.getAccount());
                    accountHolderEdit.setText(App.userAccount.getFirstName());
                    depositerNameEdit.setText(App.userAccount.getFirstName());
                    depositerPhoneEdit.setText("");
                    Gson gson = new Gson();
                    Log.i(this.getClass().getSimpleName(), "Setting user data to deposit : " + gson.toJson(App.userAccount));
                } else {
                    numberAccountEdit.setVisibility(View.VISIBLE);
                    accountHolderEdit.setVisibility(View.VISIBLE);
                    depositerNameEdit.setVisibility(View.VISIBLE);
                    depositerPhoneEdit.setVisibility(View.VISIBLE);
                    numberAccountEdit.setText("");
                    accountHolderEdit.setText("");
                    depositerNameEdit.setText("");
                    depositerPhoneEdit.setText("");
                }
            }
        });

        cashLayout = (LinearLayout) view.findViewById(R.id.layout_cashDeposit);
        docLayout = (LinearLayout) view.findViewById(R.id.layout_documentDeposit);

        cashCheckbox = (CheckBox) view.findViewById(R.id.checkbox_depositCash);
        docCheckbox = (CheckBox) view.findViewById(R.id.checkbox_depositDocument);
        cashCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    cashLayout.setVisibility(View.VISIBLE);
                    docCheckbox.setChecked(false);
                }
                else {
                    cashLayout.setVisibility(View.GONE);
                }
            }
        });


        docCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    docLayout.setVisibility(View.VISIBLE);
                    cashCheckbox.setChecked(false);
                } else {
                    docLayout.setVisibility(View.GONE);
                }
            }
        });


        Button sendButton = (Button) view.findViewById(R.id.button_saveDeposit);
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cashCheckbox.isChecked()){
                    try {
                        amount = (!cash20000.getText().toString().isEmpty() ? Double.valueOf(cash20000.getText().toString()) : 0.0) +
                                (!cash10000.getText().toString().isEmpty() ? Double.valueOf(cash10000.getText().toString()) : 0.0) +
                                (!cash5000.getText().toString().isEmpty() ? Double.valueOf(cash5000.getText().toString()) : 0.0) +
                                (!cash1000.getText().toString().isEmpty() ? Double.valueOf(cash1000.getText().toString()) : 0.0) +
                                (!cashChange.getText().toString().isEmpty() ? Double.valueOf(cashChange.getText().toString()) : 0.0);
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getActivity(), "Falta monto.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                else if (docCheckbox.isChecked()){
                    EditText docAmount = (EditText) view.findViewById(R.id.editText_documentAumont);
                    try {
                        amount = Double.valueOf(docAmount.getText().toString());
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getActivity(), "Falta monto.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (!cashCheckbox.isChecked() && !docCheckbox.isChecked() && accountHolderEdit.getText().toString().isEmpty()
                        && numberAccountEdit.getText().toString().isEmpty()
                        && accountHolderEdit.getText().toString().isEmpty()
                        && depositerNameEdit.getText().toString().isEmpty()
                        && depositerPhoneEdit.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Faltan datos.", Toast.LENGTH_SHORT).show();
                }
                else if (amount == 0.0){
                    Toast.makeText(getActivity(), "Falta monto.", Toast.LENGTH_SHORT).show();
                }
                else {
                    new DoDeposit().execute();
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

    private class DoDeposit extends AsyncTask<Object, String, Integer> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage(getString(R.string.dialog_loading));
            pDialog.setIndeterminate(true);
            pDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Object... objects) {
            BranchApi branch = new BranchApi();
            DepositSlip deposit = new DepositSlip();
            deposit.setToAccount(numberAccountEdit.getText().toString());
            deposit.setToName(accountHolderEdit.getText().toString());
            deposit.setFromName(depositerNameEdit.getText().toString());
            deposit.setFromPhone(depositerPhoneEdit.getText().toString());

            List<DepositSlipDetail> detailList = new ArrayList<>();
            if (cashCheckbox.isChecked()){
                DepositSlipDetail detail20k = new DepositSlipDetail();
                detail20k.setType("20000");
                detail20k.setAmount(!cash20000.getText().toString().isEmpty() ? Double.valueOf(cash20000.getText().toString()) : 0.0);
                DepositSlipDetail detail10k = new DepositSlipDetail();
                detail10k.setType("10000");
                detail10k.setAmount(!cash10000.getText().toString().isEmpty() ? Double.valueOf(cash10000.getText().toString()) : 0.0);
                DepositSlipDetail detail5k = new DepositSlipDetail();
                detail5k.setType("5000");
                detail5k.setAmount(!cash5000.getText().toString().isEmpty() ? Double.valueOf(cash5000.getText().toString()) : 0.0);
                DepositSlipDetail detail1k = new DepositSlipDetail();
                detail1k.setType("1000");
                detail1k.setAmount(!cash1000.getText().toString().isEmpty() ? Double.valueOf(cash1000.getText().toString()) : 0.0);
                DepositSlipDetail detailCoins = new DepositSlipDetail();
                detailCoins.setType("CHANGE");
                detailCoins.setAmount(!cashChange.getText().toString().isEmpty() ? Double.valueOf(cashChange.getText().toString()) : 0.0);
                detailList.add(detail20k);
                detailList.add(detail10k);
                detailList.add(detail5k);
                detailList.add(detail1k);
                detailList.add(detailCoins);
            }
            else if (docCheckbox.isChecked()){
                DepositSlipDetail detail = new DepositSlipDetail();
                detail.setType("CHECK");
                detailList.add(detail);
                detail.setAmount(amount);
            }
            deposit.setDetail(detailList);
            Gson gson = new Gson();
            Log.i(this.getClass().getSimpleName(), "Deposit: " + gson.toJson(deposit));
            DepositSlip result;
            try {
                result = branch.putJson(App.userAccount.getToken(), App.userAccount.getRut(), deposit);
                App.depositId = result.getDepositId();
                Log.i(this.getClass().getSimpleName(), "Result: " + gson.toJson(result));
            } catch (ApiException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            pDialog.dismiss();
            if (!App.isInside){
                Toast.makeText(getActivity(), "Su papeleta ha quedado en cola. Dirígase a la sucursal.", Toast.LENGTH_LONG).show();
                App.depositRequested = true;
            }
            else {
                Toast.makeText(getActivity(), "Se ha enviado su papeleta. Su número de atención es: " + App.depositId, Toast.LENGTH_LONG).show();
            }
            getActivity().finish();

        }
    }

}
