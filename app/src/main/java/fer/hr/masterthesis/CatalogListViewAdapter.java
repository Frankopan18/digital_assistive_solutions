package fer.hr.masterthesis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

public class CatalogListViewAdapter extends BaseAdapter implements Filterable {

    private List<Catalog> catalogs;
    private Context context;
    private CustomFilter filter;
    private List<Catalog> filteredCatalogList;
    private List<Catalog> helperFilterCatalogList;


    public CatalogListViewAdapter(Context context, List<Catalog> catalogs) {
        this.context = context;
        this.catalogs = catalogs;
        this.filteredCatalogList = catalogs;
    }

    // promijeni ovu metodu malo, ako stavim if,onda normal_search radi,ali categorical ne
    public List<Catalog> getCatalogs() {
        if (filteredCatalogList != null) {
            if (!filteredCatalogList.equals(catalogs) || Preferences.isCategorical) {
                return filteredCatalogList;
            }
        }
        return catalogs;
    }

    @Override
    public int getCount() {
        if (filteredCatalogList == null) {
            return 0;
        } else {
            return filteredCatalogList.size();
        }
    }

    @Override
    public Object getItem(int pos) {
        return filteredCatalogList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.catalog_item, viewGroup, false);
        }

        final Catalog thisCatalog = filteredCatalogList.get(position);

        TextView nameTxt = view.findViewById(R.id.nazivView15);
        TextView manufacterTxt = view.findViewById(R.id.proizvodjacView15);
        TextView priceView = view.findViewById(R.id.cijenaView15);
        TextView jeziciView = view.findViewById(R.id.jezikView15);
        TextView vrstaPoteskoceView = view.findViewById(R.id.vrstaPoteskoceView15);
        TextView platformaView = view.findViewById(R.id.platformaView15);
        TextView mjestoPrimjeneView = view.findViewById(R.id.mjestoPrimjeneView15);
        ImageView techView = view.findViewById(R.id.prikazView);
        Button detailsButton = view.findViewById(R.id.detailButton1);

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        nameTxt.setText(thisCatalog.getNaziv());
        manufacterTxt.setText(thisCatalog.getProizvodjac());

        String cij = "Cijena: ";
        String jez = "Jezici: ";
        String plat = "Platforma: ";
        String vrstaPot = "Vrsta poteškoće: ";
        String mjPrim = "Mjesto primjene: ";

        if (thisCatalog.getCijena() != null) {
            SpannableString spanCijena = new SpannableString(cij + thisCatalog.getCijena().trim());
            spanCijena.setSpan(boldSpan, 0, cij.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            priceView.setText(spanCijena);
        } else {
            SpannableString spanCijena = new SpannableString(cij);
            spanCijena.setSpan(boldSpan, 0, cij.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            priceView.setText(spanCijena);
        }

        if (thisCatalog.getJezici() != null) {
            SpannableString spanJezik = new SpannableString(jez + thisCatalog.getJezici().trim());
            spanJezik.setSpan(boldSpan, 0, jez.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            jeziciView.setText(spanJezik);
        } else {
            SpannableString spanJezik = new SpannableString(jez);
            spanJezik.setSpan(boldSpan, 0, jez.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            jeziciView.setText(spanJezik);
        }


        if (thisCatalog.getPlatforma() != null) {
            SpannableString spanPlatforma = new SpannableString(plat + thisCatalog.getPlatforma().trim());
            spanPlatforma.setSpan(boldSpan, 0, plat.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            platformaView.setText(spanPlatforma);
        } else {
            SpannableString spanPlatforma = new SpannableString(plat);
            spanPlatforma.setSpan(boldSpan, 0, plat.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            platformaView.setText(spanPlatforma);
        }


        if (thisCatalog.getVrstaPoteskoce() != null) {
            SpannableString spanVrstPot = new SpannableString(vrstaPot + thisCatalog.getVrstaPoteskoce().trim());
            spanVrstPot.setSpan(boldSpan, 0, vrstaPot.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            vrstaPoteskoceView.setText(spanVrstPot);
        } else {
            SpannableString spanVrstPot = new SpannableString(vrstaPot);
            spanVrstPot.setSpan(boldSpan, 0, vrstaPot.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            vrstaPoteskoceView.setText(spanVrstPot);
        }

        if (thisCatalog.getMjestoPrimjene() != null) {
            SpannableString spanMjestoPrim = new SpannableString(mjPrim + thisCatalog.getMjestoPrimjene().trim());
            spanMjestoPrim.setSpan(boldSpan, 0, mjPrim.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mjestoPrimjeneView.setText(spanMjestoPrim);
        } else {
            SpannableString spanMjestoPrim = new SpannableString(mjPrim);
            spanMjestoPrim.setSpan(boldSpan, 0, mjPrim.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mjestoPrimjeneView.setText(spanMjestoPrim);
        }

        detailsButton.setText("Detalji - " + thisCatalog.getNaziv());


        if (thisCatalog.getPrikaz() != null) {

            byte[] decodedPicture = Base64.getDecoder().decode(thisCatalog.getPrikaz());
            Bitmap bmp = BitmapFactory.decodeByteArray(decodedPicture, 0, decodedPicture.length);
            techView.setImageBitmap(bmp);
//          Picasso.get().load(RetrofitClientInstance.getFULLUrl() + "/images/" + thisCatalog.getPrikaz()).placeholder(R.drawable.placeholder).into(techView);
        } else {
            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.placeholder).into(techView);
        }

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(context.getApplicationContext(), DetailsActivity.class);
                intent.putExtra("CatalogItem", thisCatalog);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });


        if (!Preferences.FONT_CHANGED_SIZE_INCREASE && !Preferences.FONT_CHANGED_SIZE_DECREASE) {
            nameTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
            manufacterTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
            priceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
            jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
            vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
            platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
            mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
        }

        // Increasing the font
        // getView se poziva na sljedecem pozivu, ali je namijenjen svim elementima
        if (Preferences.FONT_CHANGED_SIZE_INCREASE || Preferences.FONT_CHANGED_SIZE_DECREASE) {
            switch (Preferences.FONT_CHANGED) {
                case -2:
                    nameTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    manufacterTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    priceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    break;

                case -1:
                    nameTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    manufacterTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    priceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    break;

                case 1:
                    nameTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    manufacterTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    priceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    break;
                case 2:
                    nameTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    manufacterTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    priceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    jeziciView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    vrstaPoteskoceView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    platformaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    mjestoPrimjeneView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    break;
            }
        }

        // it's changed, so it's the other one(not default one)
        if (Preferences.FONT_FAMILY_CHANGED) {
            Typeface face = ResourcesCompat.getFont(context, R.font.omo_type1);
            nameTxt.setTypeface(face);
            manufacterTxt.setTypeface(face);
            priceView.setTypeface(face);
            jeziciView.setTypeface(face);
            vrstaPoteskoceView.setTypeface(face);
            platformaView.setTypeface(face);
            mjestoPrimjeneView.setTypeface(face);
            detailsButton.setTypeface(face);

        } else {
            Typeface face = ResourcesCompat.getFont(context, R.font.open_sans);
            nameTxt.setTypeface(face);
            manufacterTxt.setTypeface(face);
            priceView.setTypeface(face);
            jeziciView.setTypeface(face);
            vrstaPoteskoceView.setTypeface(face);
            platformaView.setTypeface(face);
            mjestoPrimjeneView.setTypeface(face);
            detailsButton.setTypeface(face);
        }

        if (Preferences.CONTRAST_CHANGED) {
            // do code
            view.setBackgroundColor(Color.BLACK);
            nameTxt.setTextColor(Color.YELLOW);
            manufacterTxt.setTextColor(Color.YELLOW);
            priceView.setTextColor(Color.YELLOW);
            jeziciView.setTextColor(Color.YELLOW);
            vrstaPoteskoceView.setTextColor(Color.YELLOW);
            platformaView.setTextColor(Color.YELLOW);
            mjestoPrimjeneView.setTextColor(Color.YELLOW);
            //           detailsButton.setTextColor(Color.BLACK); ---> it should always be black
        } else {
            view.setBackgroundColor(Color.WHITE);
            nameTxt.setTextColor(Color.BLACK);
            manufacterTxt.setTextColor(Color.BLACK);
            priceView.setTextColor(Color.BLACK);
            jeziciView.setTextColor(Color.BLACK);
            vrstaPoteskoceView.setTextColor(Color.BLACK);
            platformaView.setTextColor(Color.BLACK);
            mjestoPrimjeneView.setTextColor(Color.BLACK);
            //  detailsButton.setTextColor(Color.BLACK); --> it should always be black
        }

        return view;
    }

    private String parseTextNicely(String cijena) {
        StringBuilder builderString = new StringBuilder();
        int count = 0;
        for (String singlePiece : cijena.split(" ")) {
            builderString.append(" " + singlePiece);
            if (++count > Preferences.MAX_SPLITS) {
                break;
            }
            if (builderString.length() >= Preferences.OPTIMAL_TEXTVIEW_LENGTH) {
                break;
            }
        }
        builderString.append("...");
        return builderString.toString();
    }


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }


    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String realConstraint = (String) constraint;
            FilterResults results = new FilterResults();
            List<Catalog> filters = new LinkedList<>();

            List<String> typeOfDisability = new LinkedList<>();
            List<String> typeOfAppliance = new LinkedList<>();
            List<String> platform = new LinkedList<>();
            List<String> languages = new LinkedList<>();
            List<String> prices = new LinkedList<>();

            realConstraint = realConstraint.toUpperCase();

            if (realConstraint.contains(Preferences.CATEGORICAL_SEARCH) && realConstraint.contains(Preferences.NORMAL_SEARCH)) {
                realConstraint = realConstraint.replace(Preferences.CATEGORICAL_SEARCH, "");
                realConstraint = realConstraint.replace(Preferences.NORMAL_SEARCH, "");

                for (int i = 0; i < helperFilterCatalogList.size(); i++) {
                    if (helperFilterCatalogList.get(i).getNaziv().toUpperCase().contains(realConstraint)) {
                        filters.add(helperFilterCatalogList.get(i));
                    }else if(helperFilterCatalogList.get(i).getCijena().toUpperCase().contains(realConstraint)){
                        filters.add(helperFilterCatalogList.get(i));
                    }else if(helperFilterCatalogList.get(i).getJezici().toUpperCase().contains(realConstraint)){
                        filters.add(helperFilterCatalogList.get(i));
                    }else if(helperFilterCatalogList.get(i).getPlatforma().toUpperCase().contains(realConstraint)){
                        filters.add(helperFilterCatalogList.get(i));
                    }
                }
                results.count = filters.size();
                results.values = filters;


            } else if (realConstraint.contains(Preferences.CATEGORICAL_SEARCH)) {
                realConstraint = realConstraint.replace(Preferences.CATEGORICAL_SEARCH, "");
                String[] allFilters = realConstraint.split(",");

                for (String singleFilter : allFilters) {
                    if (Preferences.vrstaTeskoce.contains(singleFilter)) {
                        typeOfDisability.add(singleFilter);
                    } else if (Preferences.vrstaPrimjene.contains(singleFilter)) {
                        typeOfAppliance.add(singleFilter);
                    } else if (Preferences.platforma.contains(singleFilter)) {
                        platform.add(singleFilter);
                    } else if (Preferences.jezik.contains(singleFilter)) {
                        languages.add(singleFilter);
                    } else if (Preferences.cijena.contains(singleFilter)) {
                        prices.add(singleFilter);
                    }
                }

                for (int i = 0; i < catalogs.size(); i++) {
                    if (catalogItemFulfillsFilters(catalogs.get(i), typeOfDisability, typeOfAppliance, platform, languages, prices)) {
                        filters.add(catalogs.get(i));
                    }
                }
                helperFilterCatalogList = new LinkedList<>(filters);
                results.count = filters.size();
                results.values = filters;
                if (filters.size() == 0) {

                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Nema rezultata");
                    StringBuilder sb = new StringBuilder();
                    sb.append("Nema rezultata filtriranja za: ");
                    sb.append(realConstraint);
                    sb.append(".");
                    alertDialog.setMessage(sb.toString());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            } else if (realConstraint.contains(Preferences.NORMAL_SEARCH)) {
                realConstraint = realConstraint.replace(Preferences.NORMAL_SEARCH, "");
                for (int i = 0; i < catalogs.size(); i++) {

                    if (catalogs.get(i).getNaziv().toUpperCase().contains(realConstraint)) {
                        filters.add(catalogs.get(i));
                    } else if(catalogs.get(i).getPlatforma().toUpperCase().contains(realConstraint)){
                        filters.add(catalogs.get(i));
                    } else if(catalogs.get(i).getCijena().toUpperCase().contains(realConstraint)){
                        filters.add(catalogs.get(i));
                    }else if(catalogs.get(i).getJezici().toUpperCase().contains(realConstraint)){
                        filters.add(catalogs.get(i));
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredCatalogList = (LinkedList<Catalog>) results.values;
            notifyDataSetChanged();
        }
    }

    private boolean catalogItemFulfillsFilters(Catalog catalog, List<String> typeOfDisability, List<String> typeOfAppliance, List<String> platform, List<String> languages, List<String> prices) {
        Catalog examineCatalog = catalog;
        boolean firstPass = false, secondPass = false, thirdPass = false, fourthPass = false, fifthPass = false;
        if (typeOfDisability.size() > 0) {
            for (String s : typeOfDisability) {
                if (examineCatalog.getVrstaPoteskoce().toUpperCase().contains(s)) {
                    firstPass = true;
                    break;
                }
            }
        } else {
            firstPass = true;
        }
        if (typeOfAppliance.size() > 0) {
            for (String s : typeOfAppliance) {
                if (examineCatalog.getVrstaPrimjene().toUpperCase().contains(s)) {
                    secondPass = true;
                    break;
                }
            }
        } else {
            secondPass = true;
        }
        if (platform.size() > 0) {
            for (String s : platform) {
                if (examineCatalog.getPlatforma().toUpperCase().contains(s)) {
                    thirdPass = true;
                    break;
                }
            }
        } else {
            thirdPass = true;
        }
        if (languages.size() > 0) {
            for (String s : languages) {
                if (examineCatalog.getJezici().toUpperCase().contains(s)) {
                    fourthPass = true;
                    break;
                }
            }
        } else {
            fourthPass = true;
        }
        if (prices.size() > 0) {
            for (String s : prices) {
                if (examineCatalog.getCijena().toUpperCase().contains(s)) {
                    fifthPass = true;
                    break;
                }
            }
        } else {
            fifthPass = true;
        }
        if (firstPass && secondPass && thirdPass && fourthPass && fifthPass) {
            return true;
        }
        return false;
    }

}