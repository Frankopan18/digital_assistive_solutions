package fer.hr.masterthesis;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.widget.ScrollView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView infoView, versionView, androidStudioVersionView, copyrightsTextView, licenceView,fontInfoView;
    ConstraintLayout infoLayout;
    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initializeViews();
        initializeSpecialOptionsForInfo();
        getSupportActionBar().setTitle("Informacije");
    }

    private void initializeSpecialOptionsForInfo() {
        if (Preferences.FONT_CHANGED_SIZE_INCREASE || Preferences.FONT_CHANGED_SIZE_DECREASE) {
            switch (Preferences.FONT_CHANGED) {
                case -2:
                    infoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    versionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    androidStudioVersionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    copyrightsTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    licenceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    fontInfoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    break;

                case -1:
                    infoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    versionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    androidStudioVersionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    copyrightsTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    licenceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    fontInfoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    break;

                case 1:
                    infoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    versionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    androidStudioVersionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    copyrightsTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    licenceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    fontInfoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    break;
                case 2:
                    infoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    versionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    androidStudioVersionView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    copyrightsTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    licenceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    fontInfoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    break;
            }
        }

        if (Preferences.FONT_FAMILY_CHANGED) {
            Typeface face = ResourcesCompat.getFont(getApplicationContext(), R.font.omo_type1);
            infoView.setTypeface(face);
            versionView.setTypeface(face);
            androidStudioVersionView.setTypeface(face);
            copyrightsTextView.setTypeface(face);
            licenceView.setTypeface(face);
            fontInfoView.setTypeface(face);

        } else {
            Typeface face = ResourcesCompat.getFont(getApplicationContext(), R.font.open_sans);
            infoView.setTypeface(face);
            versionView.setTypeface(face);
            androidStudioVersionView.setTypeface(face);
            copyrightsTextView.setTypeface(face);
            licenceView.setTypeface(face);
            fontInfoView.setTypeface(face);
        }

        if (Preferences.CONTRAST_CHANGED) {
            // do code
            infoLayout.setBackgroundColor(Color.BLACK);
            infoView.setTextColor(Color.YELLOW);
            versionView.setTextColor(Color.YELLOW);
            androidStudioVersionView.setTextColor(Color.YELLOW);
            copyrightsTextView.setTextColor(Color.YELLOW);
            licenceView.setTextColor(Color.YELLOW);
            fontInfoView.setTextColor(Color.YELLOW);
        } else {
            infoLayout.setBackgroundColor(Color.WHITE);
            infoView.setTextColor(Color.BLACK);
            versionView.setTextColor(Color.BLACK);
            androidStudioVersionView.setTextColor(Color.BLACK);
            copyrightsTextView.setTextColor(Color.BLACK);
            licenceView.setTextColor(Color.BLACK);
            fontInfoView.setTextColor(Color.BLACK);
        }
    }

    private void initializeViews() {
        infoView = findViewById(R.id.infoView10);
        versionView = findViewById(R.id.versionView10);
        androidStudioVersionView = findViewById(R.id.androidStudioVersionView10);
        copyrightsTextView = findViewById(R.id.copyrightsTextView10);
        licenceView = findViewById(R.id.licenceView10);
        fontInfoView = findViewById(R.id.fontInfoView10);
        infoLayout = findViewById(R.id.infoLayout);
        infoView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}