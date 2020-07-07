package fer.hr.masterthesis;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class FilterActivity extends AppCompatActivity {

    private CheckBox checkBoxMotoric, checkBoxCognitive, checkBoxMultiple, checkBoxHearing, checkBoxSight,
            checkBoxEducation, checkBoxCommunication, checkBoxRehabillitation, checkBoxOtherUsages,
            checkBoxWindows, checkBoxLinux, checkBoxTizen, checkBoxiOS, checkBoxAndroid, checkBoxSpecificPlatforms,
            checkBoxCroatian, checkBoxEnglish,
            checkBoxFree, checkBoxNotFree;
    private TextView nekiView1,nekiView2,nekiView3,nekiView4,nekiView5;
    private ConstraintLayout constraintLayout;
    private Button submitButton,clearAllButton;

    private void initializeCheckboxes() {
        submitButton = findViewById(R.id.filterButton);
        clearAllButton = findViewById(R.id.uncheckAllButton);

        checkBoxMotoric = findViewById(R.id.checkbox_motoric);
        checkBoxCognitive = findViewById(R.id.checkbox_cognitive);
        checkBoxMultiple = findViewById(R.id.checkbox_multiple);
        checkBoxHearing = findViewById(R.id.checkbox_hearing);
        checkBoxSight = findViewById(R.id.checkbox_sight);

        checkBoxEducation = findViewById(R.id.checkbox_education);
        checkBoxCommunication = findViewById(R.id.checkbox_communication);
        checkBoxRehabillitation = findViewById(R.id.checkbox_rehabilitation);
        checkBoxOtherUsages = findViewById(R.id.checkbox_otherUsages);

        checkBoxWindows = findViewById(R.id.checkbox_windows3);
        checkBoxiOS = findViewById(R.id.checkbox_ios2);
        checkBoxAndroid = findViewById(R.id.checkbox_android);
        checkBoxLinux = findViewById(R.id.checkbox_linux);
        checkBoxTizen = findViewById(R.id.checkbox_tizen);
        checkBoxSpecificPlatforms = findViewById(R.id.checkbox_specificPlatforms);

        checkBoxCroatian = findViewById(R.id.checkbox_croatian);
        checkBoxEnglish = findViewById(R.id.checkbox_english);

        checkBoxFree = findViewById(R.id.checkbox_free);
        checkBoxNotFree = findViewById(R.id.checkbox_notFree);

        nekiView1 = findViewById(R.id.typeOfDisabilityView);
        nekiView2 = findViewById(R.id.typeOfApplianceView);
        nekiView3 = findViewById(R.id.platformView);
        nekiView4 = findViewById(R.id.languageView);
        nekiView5 = findViewById(R.id.priceView);

        constraintLayout = findViewById(R.id.cLayout);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getSupportActionBar().setTitle("Filtriranje po kategorijama");
        initializeCheckboxes();
        initializeSpecialOptions();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                StringBuilder sb = new StringBuilder();
                String filterString = getFilterIntentExtra(intent, sb);
                intent.putExtra("filterString",filterString);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxMotoric.setChecked(false);
                checkBoxFree.setChecked(false);
                checkBoxNotFree.setChecked(false);
                checkBoxEnglish.setChecked(false);
                checkBoxCroatian.setChecked(false);
                checkBoxSpecificPlatforms.setChecked(false);
                checkBoxTizen.setChecked(false);
                checkBoxiOS.setChecked(false);
                checkBoxLinux.setChecked(false);
                checkBoxAndroid.setChecked(false);
                checkBoxWindows.setChecked(false);
                checkBoxOtherUsages.setChecked(false);
                checkBoxRehabillitation.setChecked(false);
                checkBoxCommunication.setChecked(false);
                checkBoxEducation.setChecked(false);
                checkBoxSight.setChecked(false);
                checkBoxMultiple.setChecked(false);
                checkBoxHearing.setChecked(false);
                checkBoxCognitive.setChecked(false);
            }
        });
    }

    private void initializeSpecialOptions() {
        if (Preferences.FONT_CHANGED_SIZE_INCREASE || Preferences.FONT_CHANGED_SIZE_DECREASE) {
            switch (Preferences.FONT_CHANGED) {
                case -2:

                    // text views
                    nekiView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    nekiView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    nekiView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    nekiView4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    nekiView5.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);

                    // languages
                    checkBoxEnglish.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxCroatian.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);

                    //type of disability
                    checkBoxCognitive.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxHearing.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxMotoric.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxMultiple.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxSight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);

                    // type of appliance
                    checkBoxOtherUsages.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxRehabillitation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxCommunication.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxEducation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);


                    // price
                    checkBoxFree.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxNotFree.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);



                    // platforms
                    checkBoxSpecificPlatforms.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxAndroid.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxTizen.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxWindows.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxiOS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    checkBoxLinux.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    break;

                case -1:

                    //text views
                    nekiView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    nekiView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    nekiView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    nekiView4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    nekiView5.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);

                    // languages
                    checkBoxEnglish.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxCroatian.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);

                    //type of disability
                    checkBoxCognitive.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxHearing.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxMotoric.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxMultiple.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxSight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);

                    // type of appliance
                    checkBoxOtherUsages.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxRehabillitation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxCommunication.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxEducation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);


                    // price
                    checkBoxFree.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxNotFree.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);



                    // platforms
                    checkBoxSpecificPlatforms.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxAndroid.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxTizen.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxWindows.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxiOS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    checkBoxLinux.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    break;

                case 1:

                    // text views
                    nekiView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    nekiView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    nekiView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    nekiView4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    nekiView5.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);

                    // languages
                    checkBoxEnglish.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxCroatian.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);

                    //type of disability
                    checkBoxCognitive.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxHearing.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxMotoric.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxMultiple.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxSight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);

                    // type of appliance
                    checkBoxOtherUsages.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxRehabillitation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxCommunication.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxEducation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);


                    // price
                    checkBoxFree.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxNotFree.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);

                    // platforms
                    checkBoxSpecificPlatforms.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxAndroid.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxTizen.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxWindows.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxiOS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    checkBoxLinux.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    break;
                case 2:

                    // text views
                    nekiView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    nekiView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    nekiView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    nekiView4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    nekiView5.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);

                    // languages
                    checkBoxEnglish.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxCroatian.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);

                    //type of disability
                    checkBoxCognitive.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxHearing.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxMotoric.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxMultiple.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxSight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);

                    // type of appliance
                    checkBoxOtherUsages.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxRehabillitation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxCommunication.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxEducation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);


                    // price
                    checkBoxFree.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxNotFree.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);

                    // platforms
                    checkBoxSpecificPlatforms.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxAndroid.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxTizen.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxWindows.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxiOS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    checkBoxLinux.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    break;
            }
        }


        // tu drugi if
        if (Preferences.FONT_FAMILY_CHANGED) {
            Typeface face = ResourcesCompat.getFont(getApplicationContext(), R.font.omo_type1);
            nekiView1.setTypeface(face);
            nekiView2.setTypeface(face);
            nekiView3.setTypeface(face);
            nekiView4.setTypeface(face);
            nekiView5.setTypeface(face);

            // languages
            checkBoxEnglish.setTypeface(face);
            checkBoxCroatian.setTypeface(face);

            //type of disability
            checkBoxCognitive.setTypeface(face);
            checkBoxHearing.setTypeface(face);
            checkBoxMotoric.setTypeface(face);
            checkBoxMultiple.setTypeface(face);
            checkBoxSight.setTypeface(face);

            // type of appliance
            checkBoxOtherUsages.setTypeface(face);
            checkBoxRehabillitation.setTypeface(face);
            checkBoxCommunication.setTypeface(face);
            checkBoxEducation.setTypeface(face);


            // price
            checkBoxFree.setTypeface(face);
            checkBoxNotFree.setTypeface(face);

            // platforms
            checkBoxSpecificPlatforms.setTypeface(face);
            checkBoxAndroid.setTypeface(face);
            checkBoxTizen.setTypeface(face);
            checkBoxWindows.setTypeface(face);
            checkBoxiOS.setTypeface(face);
            checkBoxLinux.setTypeface(face);

        } else {
            Typeface face = ResourcesCompat.getFont(getApplicationContext(), R.font.open_sans);
            nekiView1.setTypeface(face);
            nekiView2.setTypeface(face);
            nekiView3.setTypeface(face);
            nekiView4.setTypeface(face);
            nekiView5.setTypeface(face);

            // languages
            checkBoxEnglish.setTypeface(face);
            checkBoxCroatian.setTypeface(face);

            //type of disability
            checkBoxCognitive.setTypeface(face);
            checkBoxHearing.setTypeface(face);
            checkBoxMotoric.setTypeface(face);
            checkBoxMultiple.setTypeface(face);
            checkBoxSight.setTypeface(face);

            // type of appliance
            checkBoxOtherUsages.setTypeface(face);
            checkBoxRehabillitation.setTypeface(face);
            checkBoxCommunication.setTypeface(face);
            checkBoxEducation.setTypeface(face);


            // price
            checkBoxFree.setTypeface(face);
            checkBoxNotFree.setTypeface(face);

            // platforms
            checkBoxSpecificPlatforms.setTypeface(face);
            checkBoxAndroid.setTypeface(face);
            checkBoxTizen.setTypeface(face);
            checkBoxWindows.setTypeface(face);
            checkBoxiOS.setTypeface(face);
            checkBoxLinux.setTypeface(face);
        }

        if (Preferences.CONTRAST_CHANGED) {
            // do code
            constraintLayout.setBackgroundColor(Color.BLACK);
            nekiView1.setTextColor(Color.YELLOW);
            nekiView2.setTextColor(Color.YELLOW);
            nekiView3.setTextColor(Color.YELLOW);
            nekiView4.setTextColor(Color.YELLOW);
            nekiView5.setTextColor(Color.YELLOW);

            // languages
            checkBoxEnglish.setTextColor(Color.YELLOW);
            checkBoxCroatian.setTextColor(Color.YELLOW);

            //type of disability
            checkBoxCognitive.setTextColor(Color.YELLOW);
            checkBoxHearing.setTextColor(Color.YELLOW);
            checkBoxMotoric.setTextColor(Color.YELLOW);
            checkBoxMultiple.setTextColor(Color.YELLOW);
            checkBoxSight.setTextColor(Color.YELLOW);

            // type of appliance
            checkBoxOtherUsages.setTextColor(Color.YELLOW);
            checkBoxRehabillitation.setTextColor(Color.YELLOW);
            checkBoxCommunication.setTextColor(Color.YELLOW);
            checkBoxEducation.setTextColor(Color.YELLOW);


            // price
            checkBoxFree.setTextColor(Color.YELLOW);
            checkBoxNotFree.setTextColor(Color.YELLOW);

            // platforms
            checkBoxSpecificPlatforms.setTextColor(Color.YELLOW);
            checkBoxAndroid.setTextColor(Color.YELLOW);
            checkBoxTizen.setTextColor(Color.YELLOW);
            checkBoxWindows.setTextColor(Color.YELLOW);
            checkBoxiOS.setTextColor(Color.YELLOW);
            checkBoxLinux.setTextColor(Color.YELLOW);
        } else {
            constraintLayout.setBackgroundColor(Color.WHITE);
            nekiView1.setTextColor(Color.BLACK);
            nekiView2.setTextColor(Color.BLACK);
            nekiView3.setTextColor(Color.BLACK);
            nekiView4.setTextColor(Color.BLACK);
            nekiView5.setTextColor(Color.BLACK);

            // languages
            checkBoxEnglish.setTextColor(Color.BLACK);
            checkBoxCroatian.setTextColor(Color.BLACK);

            //type of disability
            checkBoxCognitive.setTextColor(Color.BLACK);
            checkBoxHearing.setTextColor(Color.BLACK);
            checkBoxMotoric.setTextColor(Color.BLACK);
            checkBoxMultiple.setTextColor(Color.BLACK);
            checkBoxSight.setTextColor(Color.BLACK);

            // type of appliance
            checkBoxOtherUsages.setTextColor(Color.BLACK);
            checkBoxRehabillitation.setTextColor(Color.BLACK);
            checkBoxCommunication.setTextColor(Color.BLACK);
            checkBoxEducation.setTextColor(Color.BLACK);


            // price
            checkBoxFree.setTextColor(Color.BLACK);
            checkBoxNotFree.setTextColor(Color.BLACK);

            // platforms
            checkBoxSpecificPlatforms.setTextColor(Color.BLACK);
            checkBoxAndroid.setTextColor(Color.BLACK);
            checkBoxTizen.setTextColor(Color.BLACK);
            checkBoxWindows.setTextColor(Color.BLACK);
            checkBoxiOS.setTextColor(Color.BLACK);
            checkBoxLinux.setTextColor(Color.BLACK);
        }
    }


    private String getFilterIntentExtra(Intent intent, StringBuilder sb) {
        if (checkBoxMotoric.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Motoričke");
            } else {
                sb.append(",Motoričke");
            }
        }
        if (checkBoxCognitive.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Kognitivne");
            } else {
                sb.append(",Kognitivne");
            }
        }
        if (checkBoxHearing.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Teškoće sluha");
            } else {
                sb.append(",Teškoće sluha");
            }
        }
        if (checkBoxMultiple.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Višestruke");
            } else {
                sb.append(",Višestruke");
            }
        }
        if (checkBoxSight.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Teškoće vida");
            } else {
                sb.append(",Teškoće vida");
            }
        }

        if (checkBoxEducation.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Edukacija");
            } else {
                sb.append(",Edukacija");
            }
        }
        if (checkBoxCommunication.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Komunikacija");
            } else{
                sb.append(",Komunikacija");
            }
        }
        if (checkBoxRehabillitation.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Rehabilitacija");
            } else{
                sb.append(",Rehabilitacija");
            }
        }
        if (checkBoxOtherUsages.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Druge primjene");
            } else{
                sb.append(",Druge primjene");
            }
        }
        if (checkBoxWindows.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Windows");
            } else {
                sb.append(",Windows");
            }
        }
        if (checkBoxAndroid.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Android");
            } else{
                sb.append(",Android");
            }
        }
        if (checkBoxLinux.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Linux");
            } else {
                sb.append(",Linux");
            }
        }
        if (checkBoxiOS.isChecked()) {
            if (sb.length() == 0) {
                sb.append("iOS");
            } else {
                sb.append(",iOS");
            }
        }
        if (checkBoxTizen.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Tizen");
            } else {
                sb.append(",Tizen");
            }
        }
        if (checkBoxSpecificPlatforms.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Specifične platforme");
            } else {
                sb.append(",Specifične platforme");
            }
        }

        if (checkBoxCroatian.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Hrvatski");
            } else {
                sb.append(",Hrvatski");
            }
        }
        if (checkBoxEnglish.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Engleski");
            } else {
                sb.append(",Engleski");
            }
        }

        if (checkBoxFree.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Besplatno");
            } else {
                sb.append(",Besplatno");
            }
        }
        if (checkBoxNotFree.isChecked()) {
            if (sb.length() == 0) {
                sb.append("Nije besplatno");
            } else {
                sb.append(",Nije besplatno");
            }
        }
        return sb.toString();
    }
}
