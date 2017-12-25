package com.mathius.dd.milestokm;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private AdView mAdView;
    private TextView mTvFirstState;
    private ImageView mIvChangeButtonspinner;
    private TextView mTvSecondState;
    private ImageView mIvDelete;
    private EditText mEtUpEditText;
    private ImageView mIvInsert;
    private TextView mTvDown;
    private ImageView mIvCopyAll;
    private ImageView mIvShare;
    private ScrollView mScrollViewEditText;
    String ml_to_km;
    Double result;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //реклама баннер
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mEtUpEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                // конвертирует единицы
                convertText();
                //firebase analytics проверяем нажатие данной кнопки
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "converted1");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            }
        });

    }

    private void initView() {

        mAdView = findViewById(R.id.adView);
        mTvFirstState = findViewById(R.id.tvFirstState);
        mIvChangeButtonspinner = findViewById(R.id.ivChangeButtonspinner);
        mIvChangeButtonspinner.setOnClickListener(this);
        mTvSecondState = findViewById(R.id.tvSecondState);
        mIvDelete = findViewById(R.id.ivDelete);
        mIvDelete.setOnClickListener(this);
        mEtUpEditText = findViewById(R.id.etInput);
        mEtUpEditText.setOnClickListener(this);
        mIvInsert = findViewById(R.id.ivInsert);
        mIvInsert.setOnClickListener(this);
        mTvDown = findViewById(R.id.etResult);
        mTvDown.setOnClickListener(this);
        mIvCopyAll = findViewById(R.id.ivCopyAll);
        mIvCopyAll.setOnClickListener(this);
        mIvShare = findViewById(R.id.ivShare);
        mIvShare.setOnClickListener(this);
        mScrollViewEditText = findViewById(R.id.scrollViewEditText);
        mScrollViewEditText.setOnClickListener(this);
    }


    void convertText() {
        if (String.valueOf(mEtUpEditText.getText()) == null || String.valueOf(mEtUpEditText.getText()).isEmpty()) {
            return;
        }
        ml_to_km = mEtUpEditText.getText().toString();
        if (mTvFirstState.getText().equals(getString(R.string.miles))) {
            result = Double.valueOf(ml_to_km) * 1.609344;
        } else {
            result = Double.valueOf(ml_to_km) * 0.621371192237334;
        }

//                ml_to_km = String.valueOf(result);

        mTvDown.setText(String.valueOf(result));
    }

    void makeAnimationOnView(int resourceId, Techniques techniques, int duration, int repeat) {
        YoYo.with(techniques)
                .duration(duration)
                .repeat(repeat)
                .playOn(findViewById(resourceId));

    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (v.getId()) {


            case R.id.ivDelete:// TODO 17/12/24
                //эффект нажатия на кнопку
                makeAnimationOnView(R.id.ivDelete, Techniques.FadeOut, 150, 0);
                makeAnimationOnView(R.id.ivDelete, Techniques.FadeIn, 350, 0);

                //edittext равно Пустота
                mEtUpEditText.setText("");
                mTvDown.setText("");
                break;
            case R.id.ivInsert:// TODO 17/12/24

                mEtUpEditText.setText("");

                //эффект нажатия на кнопку
                makeAnimationOnView(R.id.ivInsert, Techniques.FadeOut, 150, 0);
                makeAnimationOnView(R.id.ivInsert, Techniques.FadeIn, 350, 0);

                //Вставляем в edittext - текст с буфера обмена
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                //check for null
                String oldText;
                if (mEtUpEditText.getText().toString() == null) {
                    oldText = "";
                } else {

                    oldText = mEtUpEditText.getText().toString();
                }

                //check for null
                String newText;
                if (clipboard == null) {
                    newText = "";
                } else {

                    newText = clipboard.getPrimaryClip().getItemAt(0).getText().toString();
                }

                mEtUpEditText.setText(oldText + newText);


                break;
            case R.id.ivCopyAll:// TODO 17/12/24
                //эффект нажатия на кнопку
                makeAnimationOnView(R.id.ivCopyAll, Techniques.FadeOut, 150, 0);
                makeAnimationOnView(R.id.ivCopyAll, Techniques.FadeIn, 350, 0);

                // Gets a handle to the clipboard service.
                ClipboardManager clipboard2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                // Creates a new text clip to put on the clipboard
                ClipData clip = ClipData.newPlainText("simple text", mTvDown.getText().toString());

                // Set the clipboard's primary clip.
                clipboard2.setPrimaryClip(clip);
                Toast.makeText(this, R.string.TextCopied, Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivShare:// TODO 17/12/24
                //эффект нажатия на кнопку
                makeAnimationOnView(R.id.ivShare, Techniques.FadeOut, 150, 0);
                makeAnimationOnView(R.id.ivShare, Techniques.FadeIn, 350, 0);

                String shareBody = mTvDown.getText().toString();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_using)));
                break;
            case R.id.tvFirstState:// TODO 17/12/24
                break;
            case R.id.tvSecondState:// TODO 17/12/24
                break;
            case R.id.etInput:// TODO 17/12/24
                break;
            case R.id.etResult:// TODO 17/12/24
                break;
            case R.id.scrollViewEditText:// TODO 17/12/24
                break;
            case R.id.ivChangeButtonspinner:// TODO 17/12/24
                //получаем название kilometres и app_name из strings.xml
//                String kilometres = getString(R.string.myAlphabet);
//                String miles = getString(R.string.app_name);
                String kilometres = getString(R.string.kilometres);
                String miles = getString(R.string.miles);

                //получаем текущий item в спиннере 2 (mTvSecondState)
                String resultSecondState = mTvSecondState.getText().toString();

//                if (!resultSecondState.equals(kilometres) && !resultSecondState.equals(miles)) {
//
//                    //эффект нажатия на кнопку ivChangeButton
//                    makeAnimationOnView(R.id.ivChangeButtonspinner, Techniques.RotateOut, 750, 0);
                makeAnimationOnView(R.id.ivChangeButtonspinner, Techniques.RotateIn, 700, 0);
//
//
//                    //эффект нажатия на textview mTvFirstState
//                makeAnimationOnView(R.id.tvFirstState, Techniques.FadeOut, 350, 0);
                makeAnimationOnView(R.id.tvFirstState, Techniques.FadeIn, 700, 0);
//
//
//                    //эффект нажатия на textview mTvSecondState
//                makeAnimationOnView(R.id.tvSecondState, Techniques.FadeOut, 350, 0);
                makeAnimationOnView(R.id.tvSecondState, Techniques.FadeIn, 700, 0);
//
//                } else {
                //эффект нажатия на если выбраны kilometres и miles
//                makeAnimationOnView(R.id.spinnerSecondState, Techniques.Shake, 700, 0);

//                }
                //Меняем местами название First and Second textviews
                changePlacesSpinnerCyrillicLatin();

                //проверяем пустой ли текст в mEtUpEditText
                if (mEtUpEditText == null || mEtUpEditText.equals(null) || mEtUpEditText.equals("")) {
                    break;
                }

                //преобразования текста при нажатии этой кнопки
                convertText();

                break;
        }
    }

    //Меняем местами название First and Second textviews
    void changePlacesSpinnerCyrillicLatin() {

        String s1;
        s1 = mTvFirstState.getText().toString();
        mTvFirstState.setText(mTvSecondState.getText().toString());
        mTvSecondState.setText(s1);

    }


}
