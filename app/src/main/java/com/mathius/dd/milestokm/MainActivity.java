package com.mathius.dd.milestokm;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvTop;
    private EditText mEtInput;
    private Button mBtnConvert;
    private TextView mTvButtom;
    private TextView mEtResult;
    private String ml_to_km;
    private Double result;
    private String kl_to_ml;
    private Button mBtnClean;
    private Button mBtnSwap;
    private double converter = 1.609344;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mTvTop = (TextView) findViewById(R.id.tvTop);
        mEtInput = (EditText) findViewById(R.id.etInput);
        mBtnConvert = (Button) findViewById(R.id.btnConvert);
        mBtnConvert.setOnClickListener(this);
        mTvButtom = (TextView) findViewById(R.id.tvButtom);
        mEtResult = (TextView) findViewById(R.id.etResult);
        mBtnClean = (Button) findViewById(R.id.btnClean);
        mBtnClean.setOnClickListener(this);
        mBtnSwap = (Button) findViewById(R.id.btnSwap);
        mBtnSwap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (v.getId()) {
            case R.id.btnConvert:


                // hide virtual keyboard
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);

                if (String.valueOf(mEtInput.getText()) == null||String.valueOf(mEtInput.getText()).isEmpty()) {
                    break;
                }
                ml_to_km = String.valueOf(mEtInput.getText());
                if (String.valueOf(mTvTop.getText()) == getString(R.string.miles)) {
                    result = Double.valueOf(ml_to_km) * 1.609344;
                } else {
                    result = Double.valueOf(ml_to_km) * 0.621371192237334;
                }

                ml_to_km = String.valueOf(result);

                mEtResult.setText(ml_to_km);

                break;
            case R.id.btnClean:

                imm.showSoftInput(mEtInput, InputMethodManager.SHOW_IMPLICIT);

                mEtResult.setText("");
                mEtInput.setText("");
                break;
            case R.id.btnSwap:
                if (String.valueOf(mTvTop.getText()) == getString(R.string.miles)) {
                    mTvTop.setText(getString(R.string.kilometres));
                    mTvButtom.setText(getString(R.string.miles));
                    converter = 0.621371192237334;
                } else {
                    mTvTop.setText(getString(R.string.miles));
                    mTvButtom.setText(getString(R.string.kilometres));
                    converter = 1.609344;
                }


                break;
        }
    }
}
