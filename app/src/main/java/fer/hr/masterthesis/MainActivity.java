package fer.hr.masterthesis;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CatalogListViewAdapter catalogAdapter;
    private ListView mListView;
    private SearchView filterTxt;
    private boolean isCategoricalFilterOn = false;
    private SwipeRefreshLayout refreshLayout;
    private MenuItem fontFamilyItem = null;
    private MyAPIService myAPIService;
    private  ProgressBar myProgressBar;


    private void populateListViewCatalog(final List<Catalog> catalogList,boolean isRefreshed) {
        mListView = findViewById(R.id.mListView);
        mListView.setTextFilterEnabled(true);

        filterTxt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!isCategoricalFilterOn) {
                    catalogAdapter.getFilter().filter(newText + Preferences.NORMAL_SEARCH);
                    if (newText.equals("")) {
                        catalogAdapter.getFilter().filter(newText + Preferences.NORMAL_SEARCH);
                        filterTxt.clearFocus();
                    }
                } else {
                    catalogAdapter.getFilter().filter(newText + Preferences.NORMAL_SEARCH + Preferences.CATEGORICAL_SEARCH);
                    if (newText.equals("")) {
                        catalogAdapter.getFilter().filter(newText + Preferences.NORMAL_SEARCH + Preferences.CATEGORICAL_SEARCH);
                        filterTxt.clearFocus();
                    }
                }
                return false;
            }
        });
        if (isRefreshed){
            Call<List<Catalog>> call = myAPIService.getCatalogs();

            call.enqueue(new Callback<List<Catalog>>() {

                @Override
                public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                    myProgressBar.setVisibility(View.GONE);
                    if (!isSameLikeOriginal(catalogList,response.body())){
                        catalogAdapter = new CatalogListViewAdapter(getApplicationContext(), response.body());
                        mListView.setAdapter(catalogAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Catalog>> call, Throwable throwable) {
                    myProgressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });



        }else{
            catalogAdapter = new CatalogListViewAdapter(this, catalogList);
            mListView.setAdapter(catalogAdapter);
        }
    }

    // test list must be fully contained in list from API
    private boolean isSameLikeOriginal(List<Catalog> testList, List<Catalog> listFromAPI) {
        if (testList.size() == listFromAPI.size()){
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Katalog rješenja");

        myProgressBar = findViewById(R.id.myProgressBar);
        filterTxt = findViewById(R.id.filterTxtView);
        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);
        refreshLayout = findViewById(R.id.refreshLayout);

        myAPIService = RetrofitClientInstance.getRetrofitInstance().create(MyAPIService.class);

        populateEntireList(myProgressBar, myAPIService);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                if (!Preferences.isCategorical && filterTxt.getQuery().toString().length() == 0){
                    populateListViewCatalog(catalogAdapter.getCatalogs(),true);
                }
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);

                    }
                },500);
            }
        });
    }



    private void populateEntireList(final ProgressBar myProgressBar, MyAPIService myAPIService) {
        Call<List<Catalog>> call = myAPIService.getCatalogs();


        call.enqueue(new Callback<List<Catalog>>() {

            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                myProgressBar.setVisibility(View.GONE);
                populateListViewCatalog(response.body(),false);
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable throwable) {
                myProgressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.overflowMenu2 || item.getItemId() == R.id.overflowMenu1 || item.getItemId() == R.id.overflowMenu3){
            return false;
        }

        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        item.setActionView(new View(getApplicationContext()));
        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return false;
            }
        });

        switch (item.getItemId()) {
            case R.id.categoricalFilterView:
                Preferences.isCategorical = true;
                Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.resetFilter:
                if (isCategoricalFilterOn) {
                    catalogAdapter.getFilter().filter("" + Preferences.CATEGORICAL_SEARCH);
                    isCategoricalFilterOn = false;
                } else {
                    catalogAdapter.getFilter().filter("" + Preferences.NORMAL_SEARCH);
                }
                filterTxt.setQuery("", false);
                filterTxt.clearFocus();
                Preferences.isCategorical = false;
                return false;

            case R.id.increaseFontSize:
                increaseFont();
                Preferences.FONT_CHANGED++;
                return false;

            case R.id.decreaseFontSize:
                decreaseFont();
                Preferences.FONT_CHANGED--;
                return false;

            case R.id.originalFontSize:
                originalFont();
                Preferences.FONT_CHANGED = 0;
                return false;

            case R.id.changeFontFamily:
                // XOR to switch between true and false, where false is originate font family, and true is changed one
                fontFamilyItem = item;
                Preferences.FONT_FAMILY_CHANGED ^= true;
                if (Preferences.FONT_FAMILY_CHANGED){
                    item.setTitle("Open Sans");
                }else{
                    item.setTitle("Omo Type Std");
                }
                changeFontFamily();
                return false;
            case R.id.changeContrast:
                changeContrast();
                return false;
            case R.id.defaultSpecialSettings:
                changeBackToNormalSettings();
                Preferences.isCategorical = false;
                return false;

            case R.id.informationView:
                // start activity here
                Intent infoIntent = new Intent(getApplicationContext().getApplicationContext(), InfoActivity.class);
                startActivity(infoIntent);
                return true;
            case R.id.closeSpecialSettings:
                return true;
            case R.id.closeSpecialSettings1:
                return true;
            case R.id.closeSpecialSettings2:
                return true;
            default:
                return false;
        }
    }


    private void changeBackToNormalSettings() {
        if (fontFamilyItem != null){
            fontFamilyItem.setTitle("Omo Type Std");
        }
        Preferences.CONTRAST_CHANGED = false;
        Preferences.FONT_CHANGED_SIZE_DECREASE = false;
        Preferences.FONT_CHANGED_SIZE_INCREASE = false;
        Preferences.FONT_FAMILY_CHANGED = false;
        Preferences.FONT_CHANGED = 0;
        // promijeni i tekst u item sa font family,to si zaboravio --> Napravio si gore
        Typeface face = ResourcesCompat.getFont(getApplicationContext(), R.font.open_sans);
        for (int i = 0; i < mListView.getCount(); i++) {
            if (mListView.getChildAt(i) != null) {

                TextView a = mListView.getChildAt(i).findViewById(R.id.nazivView15);
                TextView b = mListView.getChildAt(i).findViewById(R.id.proizvodjacView15);
                TextView c = mListView.getChildAt(i).findViewById(R.id.cijenaView15);
                TextView d = mListView.getChildAt(i).findViewById(R.id.mjestoPrimjeneView15);
                TextView e = mListView.getChildAt(i).findViewById(R.id.platformaView15);
                TextView f = mListView.getChildAt(i).findViewById(R.id.vrstaPoteskoceView15);
                TextView g = mListView.getChildAt(i).findViewById(R.id.jezikView15);
                Button oneButton = mListView.getChildAt(i).findViewById(R.id.detailButton1);

                a.setTextColor(Color.BLACK);
                b.setTextColor(Color.BLACK);
                c.setTextColor(Color.BLACK);
                d.setTextColor(Color.BLACK);
                e.setTextColor(Color.BLACK);
                f.setTextColor(Color.BLACK);
                g.setTextColor(Color.BLACK);
                oneButton.setTextColor(Color.BLACK);
                mListView.getChildAt(i).setBackgroundColor(Color.WHITE);
                a.setTypeface(face);
                b.setTypeface(face);
                c.setTypeface(face);
                d.setTypeface(face);
                e.setTypeface(face);
                f.setTypeface(face);
                g.setTypeface(face);
                oneButton.setTypeface(face);
                a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
            } else {
                break;
            }
        }
    }

    private void changeContrast() {
        Preferences.CONTRAST_CHANGED = true;
        for (int i = 0; i < mListView.getCount(); i++) {
            if (mListView.getChildAt(i) != null) {

                TextView a = mListView.getChildAt(i).findViewById(R.id.nazivView15);
                TextView b = mListView.getChildAt(i).findViewById(R.id.proizvodjacView15);
                TextView c = mListView.getChildAt(i).findViewById(R.id.cijenaView15);
                TextView d = mListView.getChildAt(i).findViewById(R.id.mjestoPrimjeneView15);
                TextView e = mListView.getChildAt(i).findViewById(R.id.platformaView15);
                TextView f = mListView.getChildAt(i).findViewById(R.id.vrstaPoteskoceView15);
                TextView g = mListView.getChildAt(i).findViewById(R.id.jezikView15);
                Button oneButton = mListView.getChildAt(i).findViewById(R.id.detailButton1);

                a.setTextColor(Color.YELLOW);
                b.setTextColor(Color.YELLOW);
                c.setTextColor(Color.YELLOW);
                d.setTextColor(Color.YELLOW);
                e.setTextColor(Color.YELLOW);
                f.setTextColor(Color.YELLOW);
                g.setTextColor(Color.YELLOW);
              //  oneButton.setTextColor(Color.BLACK); --> it should always be black
                mListView.getChildAt(i).setBackgroundColor(Color.BLACK);
            } else {
                break;
            }
        }
    }

    private void changeFontFamily() {
        // RADI, SAD SAMO NAPRAVI ZA SVE TEXT VIEWOVE,I DOWNLODADAJ NEKE LIJEPE FONTOVE
        Typeface face = null;
        if (Preferences.FONT_FAMILY_CHANGED) {
            face = ResourcesCompat.getFont(getApplicationContext(), R.font.omo_type1);
        } else {
            // here is supposed to be another font than pacifico,so something else and up also change it to something else
            face = ResourcesCompat.getFont(getApplicationContext(), R.font.open_sans);
        }

        for (int i = 0; i < mListView.getCount(); i++) {
            if (mListView.getChildAt(i) != null) {
                TextView a = mListView.getChildAt(i).findViewById(R.id.nazivView15);
                TextView b = mListView.getChildAt(i).findViewById(R.id.proizvodjacView15);
                TextView c = mListView.getChildAt(i).findViewById(R.id.cijenaView15);
                TextView d = mListView.getChildAt(i).findViewById(R.id.mjestoPrimjeneView15);
                TextView e = mListView.getChildAt(i).findViewById(R.id.platformaView15);
                TextView f = mListView.getChildAt(i).findViewById(R.id.vrstaPoteskoceView15);
                TextView g = mListView.getChildAt(i).findViewById(R.id.jezikView15);
                Button detailsButton = mListView.getChildAt(i).findViewById(R.id.detailButton1);

                a.setTypeface(face);
                b.setTypeface(face);
                c.setTypeface(face);
                d.setTypeface(face);
                e.setTypeface(face);
                f.setTypeface(face);
                g.setTypeface(face);
                detailsButton.setTypeface(face);

            } else {
                break;
            }
        }


    }

    private void originalFont() {
        Preferences.FONT_CHANGED_SIZE_DECREASE = false;
        Preferences.FONT_CHANGED_SIZE_INCREASE = false;
        for (int i = 0; i < mListView.getCount(); i++) {
            if (mListView.getChildAt(i) != null) {
                TextView a = mListView.getChildAt(i).findViewById(R.id.nazivView15);
                TextView b = mListView.getChildAt(i).findViewById(R.id.proizvodjacView15);
                TextView c = mListView.getChildAt(i).findViewById(R.id.cijenaView15);
                TextView d = mListView.getChildAt(i).findViewById(R.id.mjestoPrimjeneView15);
                TextView e = mListView.getChildAt(i).findViewById(R.id.platformaView15);
                TextView f = mListView.getChildAt(i).findViewById(R.id.vrstaPoteskoceView15);
                TextView g = mListView.getChildAt(i).findViewById(R.id.jezikView15);

                a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
            } else {
                break;
            }
        }
    }


    private void decreaseFont() {
        Preferences.FONT_CHANGED_SIZE_DECREASE = true;

        for (int i = 0; i < mListView.getCount(); i++) {
            if (mListView.getChildAt(i) != null) {
                if (Preferences.FONT_CHANGED > -2) {

                    TextView a = mListView.getChildAt(i).findViewById(R.id.nazivView15);
                    TextView b = mListView.getChildAt(i).findViewById(R.id.proizvodjacView15);
                    TextView c = mListView.getChildAt(i).findViewById(R.id.cijenaView15);
                    TextView d = mListView.getChildAt(i).findViewById(R.id.mjestoPrimjeneView15);
                    TextView e = mListView.getChildAt(i).findViewById(R.id.platformaView15);
                    TextView f = mListView.getChildAt(i).findViewById(R.id.vrstaPoteskoceView15);
                    TextView g = mListView.getChildAt(i).findViewById(R.id.jezikView15);


                    // moze bit 1,0,-1 --> ako je 0,onda ovo dolje, ako je 1 onda na pocetnu velicinu,ako je -1 onda -4f

                    if (Preferences.FONT_CHANGED == 1){
                        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                    }else if(Preferences.FONT_CHANGED == 0){
                        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    }else if(Preferences.FONT_CHANGED == -1){
                        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                        f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                        g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 4f);
                    }else if(Preferences.FONT_CHANGED == 2){
                        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    }

                } else {
                    Preferences.FONT_CHANGED++;
                }
            } else {
                break;
            }
        }
    }


    private void increaseFont() {
        Preferences.FONT_CHANGED_SIZE_INCREASE = true;
        for (int i = 0; i < mListView.getCount(); i++) {
            if (mListView.getChildAt(i) != null) {
                if (Preferences.FONT_CHANGED < 2) {
                    TextView a = mListView.getChildAt(i).findViewById(R.id.nazivView15);
                    TextView b = mListView.getChildAt(i).findViewById(R.id.proizvodjacView15);
                    TextView c = mListView.getChildAt(i).findViewById(R.id.cijenaView15);
                    TextView d = mListView.getChildAt(i).findViewById(R.id.mjestoPrimjeneView15);
                    TextView e = mListView.getChildAt(i).findViewById(R.id.platformaView15);
                    TextView f = mListView.getChildAt(i).findViewById(R.id.vrstaPoteskoceView15);
                    TextView g = mListView.getChildAt(i).findViewById(R.id.jezikView15);


                    // tu moze bit -1,0,1

                    if (Preferences.FONT_CHANGED == -1){
                        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                        g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE);
                    }else if(Preferences.FONT_CHANGED == 0){
                        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                        g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 2f);
                    }else if(Preferences.FONT_CHANGED == 1){
                        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                        f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                        g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE + 4f);
                    }else if(Preferences.FONT_CHANGED == -2){
                        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        f.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                        g.setTextSize(TypedValue.COMPLEX_UNIT_DIP, Preferences.FONT_SIZE - 2f);
                    }

                } else {
                    Preferences.FONT_CHANGED--;
                }
            } else {
                break;
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (!filterTxt.isIconified()) {
            filterTxt.setIconified(true);
        } else {
            super.onBackPressed();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                isCategoricalFilterOn = true;
                String filterString = data.getStringExtra("filterString");
                catalogAdapter.getFilter().filter(filterString + Preferences.CATEGORICAL_SEARCH);
            }
        }
    }
}
