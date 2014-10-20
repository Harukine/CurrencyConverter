package com.harukine.currencyconverter.currencyconverter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {



    @ViewById
    EditText from, to;

    @ViewById
    TextView result;

    @App
    Application app;

    @Click
    public void doConversion() {
        // Get the currencies from the EditTexts
        requestConversion(from.getText().toString(), to.getText().toString());
    }

    @Background
    public void requestConversion(String from, final String to) {
        // Request the conversion on the service
        app.service.convertCurrentCurrency(from,
                new Callback<CurrencyRates>() {

                    @Override
                    public void success(CurrencyRates rates, Response response) {

                        for (CurrencyRates.Rate rate : rates.rates) {
                            if (to.equalsIgnoreCase(rate.to)) {
                                showResult(rate.rate);
                                return;
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("MainActivity", "Failure: " + error.toString());
                    }
                });
    }

    @UiThread
    public void showResult(Float rate) {
        if (rate != null) {
            result.setText("Rate: " + rate.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner fromspin = (Spinner) findViewById(R.id.fromspin);
        Spinner tospin = (Spinner) findViewById(R.id.tospin);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        fromspin.setAdapter(adapter);
        tospin.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
