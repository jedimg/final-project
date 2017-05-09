package com.example.laptop.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.laptop.finalproject.constants.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    Unbinder unbinder;
    boolean language_type;

    @BindView(R.id.etPostcode) EditText etPostcode;
    @BindView(R.id.tvOr) TextView tvOr;
    @BindView(R.id.tvFilters) TextView tvFilters;
    @BindView(R.id.swUseMyLocation) Switch swUseMyLocation;
    @BindView(R.id.tvCuisine) TextView tvCuisine;
    @BindView(R.id.tvCategory) TextView tvCategory;
    @BindView(R.id.tvPrice) TextView tvPrice;
    @BindView(R.id.tvRating) TextView tvRating;
    @BindView(R.id.btnFindNearby) Button btnFindNearby;
    @BindView(R.id.toolbarMain) Toolbar toolbarMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Butterknife to the view
        unbinder = ButterKnife.bind(this);
        language_type = true;
        setupViews();
        setupToolbar();

        /**
         * TODO: Create and implement views that let the user provide input for the API and a way
         * to get to the MapsActivity
         */

        //Set a click listener on the button
        //when pressed, check if all inputs are correct
        //if they are, pass relevant data and start MapsActivity
        btnFindNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Testing the map activity
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.btnEN){
            if (language_type) {

                return true;
            }
            else {
                this.language_type = true;
                setupViews();

                return true;
            }
        }
        else if (item.getItemId() == R.id.btnBG) {
            if (!language_type){
                return true;
            }

            else {
                this.language_type = false;
                setupViews();

                return true;
            }
        }
        else {

            return super.onOptionsItemSelected(item);
        }
    }

    private void setupViews() {

        if (language_type) {

            etPostcode.setHint(Constants.EN_POSTCODE_HINT);
            tvOr.setText(Constants.EN_OR);
            tvFilters.setText(Constants.EN_FILTERS);
            swUseMyLocation.setText(Constants.EN_USE_LOCATION);
            tvCuisine.setText(Constants.EN_CUISINE);
            tvCategory.setText(Constants.EN_CATEGORY);
            tvPrice.setText(Constants.EN_PRICE);
            tvRating.setText(Constants.EN_RATING);
            btnFindNearby.setText(Constants.EN_BUTTON);
            toolbarMain.setTitle(Constants.EN_MAIN_TOOLBAR_TITLE);
        }
        else {

            etPostcode.setHint(Constants.BG_POSTCODE_HINT);
            tvOr.setText(Constants.BG_OR);
            tvFilters.setText(Constants.BG_FILTERS);
            swUseMyLocation.setText(Constants.BG_USE_LOCATION);
            tvCuisine.setText(Constants.BG_CUISINE);
            tvCategory.setText(Constants.BG_CATEGORY);
            tvPrice.setText(Constants.BG_PRICE);
            tvRating.setText(Constants.BG_RATING);
            btnFindNearby.setText(Constants.BG_BUTTON);
            toolbarMain.setTitle(Constants.BG_MAIN_TOOLBAR_TITLE);
        }
    }

    private void setupToolbar() {

        setSupportActionBar(toolbarMain);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }
}