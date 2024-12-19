package com.soumen.currencyconveter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private EditText edtAmount;
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private Button btnConvertor;
    private TextView txtConvertedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        edtAmount = findViewById(R.id.edtAmount);
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency);
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency);
        btnConvertor = findViewById(R.id.btnConvertor);
        txtConvertedValue = findViewById(R.id.txtConvertedValue);

        setupCurrencySpinners();

        btnConvertor.setOnClickListener(view -> {
            performConversion();
        });
    }

    private void setupCurrencySpinners() {
        String[] currencyList = getResources().getStringArray(R.array.currency_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencyList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFromCurrency.setAdapter(adapter);
        spinnerToCurrency.setAdapter(adapter);

        spinnerFromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCurrency = parent.getItemAtPosition(position).toString();
                Toast.makeText(HomeActivity.this, "From: " + selectedCurrency, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerToCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCurrency = parent.getItemAtPosition(position).toString();
                Toast.makeText(HomeActivity.this, "To: " + selectedCurrency, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void performConversion() {
        String amountStr = edtAmount.getText().toString().trim();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        String fromCurrency = spinnerFromCurrency.getSelectedItem().toString();
        String toCurrency = spinnerToCurrency.getSelectedItem().toString();

        double conversionRate = getMockConversionRate(fromCurrency, toCurrency);
        double convertedValue = amount * conversionRate;

        txtConvertedValue.setText(String.format("Converted Value: %.2f %s", convertedValue, toCurrency));
    }

    private double getMockConversionRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals("USD") && toCurrency.equals("INR")) {
            return 80.0;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("USD")) {
            return 0.0125;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return 0.96;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            return 0.012;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            return 0.79;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) {
            return 1.25;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("JPY")) {
            return 157;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("USD")) {
            return 0.0063;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("EUR")) {
            return 0.01;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("INR")) {
            return 88.5;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("GBP")) {
            return 0.009;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("INR")) {
            return 107.16;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("JPY")) {
            return 1.84;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("GBP")) {
            return 0.82;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("EUR")) {
            return 1.20;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("JPY")) {
            return 163;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("JPY")) {
            return 197;
        } else if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        }
        return 1.0;
    }
}
