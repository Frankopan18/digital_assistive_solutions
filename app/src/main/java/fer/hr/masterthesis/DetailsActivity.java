package fer.hr.masterthesis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Base64;

public class DetailsActivity extends AppCompatActivity {


    TextView nazivView, proizvodjacView, linkView, jeziciView, vrstaPoteskoceView, ICTUredjajView, asistivnaTehnologijaView, platformaView, uporabljivostView, dostupnostView, vrstaPrimjeneView, mjestoPrimjeneView, cijenaView, recenzijeView, opisView;
    ImageView prikazView;
    ConstraintLayout constraintLayout1;

    private void initializeWidgets() {
        nazivView = findViewById(R.id.realNazivView);
        proizvodjacView = findViewById(R.id.realProizvodjacView);
        linkView = findViewById(R.id.realLinkView);
        jeziciView = findViewById(R.id.realJeziciView);
        vrstaPoteskoceView = findViewById(R.id.realVrstaPoteskoceView);
        ICTUredjajView = findViewById(R.id.realICTUredjajView);
        asistivnaTehnologijaView = findViewById(R.id.realAsistivnaTehnologijaView);
        platformaView = findViewById(R.id.realPlatformaView);
        uporabljivostView = findViewById(R.id.realUporabljivostView);
        dostupnostView = findViewById(R.id.realDostupnostView);
        vrstaPrimjeneView = findViewById(R.id.realVrstaPrimjeneView);
        mjestoPrimjeneView = findViewById(R.id.realMjestoPrimjeneView);
        cijenaView = findViewById(R.id.realCijenaView);
        recenzijeView = findViewById(R.id.realRecenzijeView);
        opisView = findViewById(R.id.realOpisView);
        prikazView = findViewById(R.id.imageView);
        constraintLayout1 = findViewById(R.id.cLayout1);
        getSupportActionBar().setTitle("Pojedino rješenje");
    }

    private void receiveInfo() {
        Intent intent = this.getIntent();
        Catalog catalog = (Catalog) intent.getSerializableExtra("CatalogItem");

        // setting data which we received from intent
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

        if (catalog.getNaziv() != null) {
            SpannableString spanNaziv = new SpannableString("\n" + nazivView.getText().toString() + catalog.getNaziv().trim() + "\n");
            spanNaziv.setSpan(boldSpan, 0, nazivView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            nazivView.setText(spanNaziv);
        }
        if (catalog.getProizvodjac() != null) {
            SpannableString spanProizvodjac = new SpannableString("\n" + proizvodjacView.getText().toString() + catalog.getProizvodjac().trim() + "\n");
            spanProizvodjac.setSpan(boldSpan, 0, proizvodjacView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            proizvodjacView.setText(spanProizvodjac);

        }
        if (catalog.getLink() != null) {
            SpannableString spanLink = new SpannableString("\n" + "Link: " + catalog.getLink().trim() + "\n");
            spanLink.setSpan(boldSpan, 0, linkView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            linkView.setText(spanLink);

        }
        if (catalog.getJezici() != null) {
            SpannableString spanJezici = new SpannableString("\n" + "Jezici: " + catalog.getJezici().trim() + "\n");
            spanJezici.setSpan(boldSpan, 0, jeziciView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            jeziciView.setText(spanJezici);

        }
        if (catalog.getVrstaPoteskoce() != null) {
            SpannableString spanVrstaPoteskoce = new SpannableString("\n" + "Vrsta poteškoće: " + catalog.getVrstaPoteskoce().trim() + "\n");
            spanVrstaPoteskoce.setSpan(boldSpan, 0, vrstaPoteskoceView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            vrstaPoteskoceView.setText(spanVrstaPoteskoce);
        }
        if (catalog.getICTUredjaj() != null) {
            SpannableString spanICTUredjaj = new SpannableString("\n" + "ICT Uređaj: " + catalog.getICTUredjaj().trim() + "\n");
            spanICTUredjaj.setSpan(boldSpan, 0, ICTUredjajView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ICTUredjajView.setText(spanICTUredjaj);
        }
        if (catalog.getAsistivnaTehnologija() != null) {
            SpannableString spanAsistivnaTehnologija = new SpannableString("\n" + "Asistivna tehnologija:" + catalog.getAsistivnaTehnologija().trim() + "\n");
            spanAsistivnaTehnologija.setSpan(boldSpan, 0, asistivnaTehnologijaView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            asistivnaTehnologijaView.setText(spanAsistivnaTehnologija);
        }
        if (catalog.getPlatforma() != null) {
            SpannableString spanPlatforma = new SpannableString("\n" + "Platforma: " + catalog.getPlatforma().trim() + "\n");
            spanPlatforma.setSpan(boldSpan, 0, platformaView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            platformaView.setText(spanPlatforma);
        }
        if (catalog.getUporabljivost() != null) {
            SpannableString spanUporabljivost = new SpannableString("\n" + "Uporabljivost: " + catalog.getUporabljivost().trim() + "\n");
            spanUporabljivost.setSpan(boldSpan, 0, uporabljivostView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            uporabljivostView.setText(spanUporabljivost);
        }
        if (catalog.getDostupnost() != null) {
            SpannableString spanDostupnost = new SpannableString("\n" + "Dostupnost: " + catalog.getDostupnost().trim() + "\n");
            spanDostupnost.setSpan(boldSpan, 0, dostupnostView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            dostupnostView.setText(spanDostupnost);
        }
        if (catalog.getVrstaPrimjene() != null) {
            SpannableString spanVrstaPrimjene = new SpannableString("\n" + "Vrsta primjene: " + catalog.getVrstaPrimjene().trim() + "\n");
            spanVrstaPrimjene.setSpan(boldSpan, 0, vrstaPrimjeneView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            vrstaPrimjeneView.setText(spanVrstaPrimjene);
        }
        if (catalog.getMjestoPrimjene() != null) {
            SpannableString spanMjestoPrimjene = new SpannableString("\n" + "Mjesto primjene: " + catalog.getMjestoPrimjene().trim() + "\n\n");
            spanMjestoPrimjene.setSpan(boldSpan, 0, mjestoPrimjeneView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mjestoPrimjeneView.setText(spanMjestoPrimjene);
        }
        if (catalog.getOpis() != null) {
            SpannableString spanOpis = new SpannableString("\n" + "Opis: " + catalog.getOpis().trim() + "\n");
            spanOpis.setSpan(boldSpan, 0, opisView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            opisView.setText(spanOpis);
        }
        if (catalog.getRecenzije() != null) {
            SpannableString spanRecenzije = new SpannableString("\n" + "Recenzije: " + catalog.getRecenzije().trim() + "\n");
            spanRecenzije.setSpan(boldSpan, 0, recenzijeView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            recenzijeView.setText(spanRecenzije);
        }
        if (catalog.getCijena() != null) {
            SpannableString spanCijena = new SpannableString("\n" + "Cijena: " + catalog.getCijena().trim() + "\n");
            spanCijena.setSpan(boldSpan, 0, cijenaView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            cijenaView.setText(spanCijena);
        }

        // for the picture below
        if (catalog.getPrikaz() != null) {
            byte[] decodedPicture = Base64.getDecoder().decode(catalog.getPrikaz());
            Bitmap bmp = BitmapFactory.decodeByteArray(decodedPicture, 0, decodedPicture.length);
            prikazView.setImageBitmap(bmp);
            // Picasso.get().load(RetrofitClientInstance.getFULLUrl()+"/images/"+catalog.getPrikaz()).placeholder(R.drawable.placeholder).into(prikazView);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeWidgets();
        receiveInfo();
        initializeSpecialOptions();
    }

    private void initializeSpecialOptions() {

        if (Preferences.FONT_CHANGED_SIZE_INCREASE || Preferences.FONT_CHANGED_SIZE_DECREASE) {
            switch (Preferences.FONT_CHANGED) {
                case -2:
                    nazivView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    proizvodjacView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    linkView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    ICTUredjajView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    asistivnaTehnologijaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    uporabljivostView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    dostupnostView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    vrstaPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    opisView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    recenzijeView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    cijenaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    break;

                case -1:
                    nazivView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    proizvodjacView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    linkView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    ICTUredjajView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    asistivnaTehnologijaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    uporabljivostView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    dostupnostView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    vrstaPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    opisView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    recenzijeView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    cijenaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    break;

                case 1:
                    nazivView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    proizvodjacView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    linkView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    ICTUredjajView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    asistivnaTehnologijaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    uporabljivostView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    dostupnostView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    vrstaPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    opisView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    recenzijeView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    cijenaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    break;
                case 2:
                    nazivView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    proizvodjacView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    linkView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    ICTUredjajView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    asistivnaTehnologijaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    uporabljivostView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    dostupnostView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    vrstaPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    opisView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    recenzijeView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    cijenaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    break;
            }
        }

        if (Preferences.FONT_FAMILY_CHANGED) {
            Typeface face = ResourcesCompat.getFont(getApplicationContext(), R.font.omo_type1);
            nazivView.setTypeface(face);
            proizvodjacView.setTypeface(face);
            linkView.setTypeface(face);
            jeziciView.setTypeface(face);
            vrstaPoteskoceView.setTypeface(face);
            ICTUredjajView.setTypeface(face);
            asistivnaTehnologijaView.setTypeface(face);
            platformaView.setTypeface(face);
            uporabljivostView.setTypeface(face);
            dostupnostView.setTypeface(face);
            vrstaPrimjeneView.setTypeface(face);
            mjestoPrimjeneView.setTypeface(face);
            opisView.setTypeface(face);
            recenzijeView.setTypeface(face);
            cijenaView.setTypeface(face);

        } else {
            Typeface face = ResourcesCompat.getFont(getApplicationContext(), R.font.open_sans);
            nazivView.setTypeface(face);
            proizvodjacView.setTypeface(face);
            linkView.setTypeface(face);
            jeziciView.setTypeface(face);
            vrstaPoteskoceView.setTypeface(face);
            ICTUredjajView.setTypeface(face);
            asistivnaTehnologijaView.setTypeface(face);
            platformaView.setTypeface(face);
            uporabljivostView.setTypeface(face);
            dostupnostView.setTypeface(face);
            vrstaPrimjeneView.setTypeface(face);
            mjestoPrimjeneView.setTypeface(face);
            opisView.setTypeface(face);
            recenzijeView.setTypeface(face);
            cijenaView.setTypeface(face);
        }

        if (Preferences.CONTRAST_CHANGED) {
            // do code
            constraintLayout1.setBackgroundColor(Color.BLACK);
            nazivView.setTextColor(Color.YELLOW);
            proizvodjacView.setTextColor(Color.YELLOW);
            linkView.setTextColor(Color.YELLOW);
            jeziciView.setTextColor(Color.YELLOW);
            vrstaPoteskoceView.setTextColor(Color.YELLOW);
            ICTUredjajView.setTextColor(Color.YELLOW);
            asistivnaTehnologijaView.setTextColor(Color.YELLOW);
            platformaView.setTextColor(Color.YELLOW);
            uporabljivostView.setTextColor(Color.YELLOW);
            dostupnostView.setTextColor(Color.YELLOW);
            vrstaPrimjeneView.setTextColor(Color.YELLOW);
            mjestoPrimjeneView.setTextColor(Color.YELLOW);
            opisView.setTextColor(Color.YELLOW);
            recenzijeView.setTextColor(Color.YELLOW);
            cijenaView.setTextColor(Color.YELLOW);
        } else {
            constraintLayout1.setBackgroundColor(Color.WHITE);
            nazivView.setTextColor(Color.BLACK);
            proizvodjacView.setTextColor(Color.BLACK);
            linkView.setTextColor(Color.BLACK);
            jeziciView.setTextColor(Color.BLACK);
            vrstaPoteskoceView.setTextColor(Color.BLACK);
            ICTUredjajView.setTextColor(Color.BLACK);
            asistivnaTehnologijaView.setTextColor(Color.BLACK);
            platformaView.setTextColor(Color.BLACK);
            uporabljivostView.setTextColor(Color.BLACK);
            dostupnostView.setTextColor(Color.BLACK);
            vrstaPrimjeneView.setTextColor(Color.BLACK);
            mjestoPrimjeneView.setTextColor(Color.BLACK);
            opisView.setTextColor(Color.BLACK);
            recenzijeView.setTextColor(Color.BLACK);
            cijenaView.setTextColor(Color.BLACK);
        }
    }
}