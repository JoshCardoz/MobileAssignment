package com.example.mobileassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText principalEditText;
    private Spinner interestRateSpinner;
    private Spinner amortizationPeriodSpinner;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principalEditText = findViewById(R.id.editTextPrincipal);
        interestRateSpinner = findViewById(R.id.spinnerInterestRate);
        amortizationPeriodSpinner = findViewById(R.id.spinnerAmortizationPeriod);
        calculateButton = findViewById(R.id.calculate);
        resultTextView = findViewById(R.id.results);

        // Find the spinners in your layout
        interestRateSpinner = findViewById(R.id.spinnerInterestRate);
        amortizationPeriodSpinner = findViewById(R.id.spinnerAmortizationPeriod);

        // Populate Interest Rate Spinner
        String[] interestRates = {
                "3 Year Fixed Rate 4.84%",
                "5 Year Fixed Rate 4.74%",
                "0.5 Year closed 7.74%",
                "1 Year Closed 7.74%",
                "1 Year Open 8%",
                "2 Year Closed 7.34%",
                "3 Year Closed 6.94%",
                "4 Year Closed 6.74%",
                "5 Year Closed 6.79%",
                "6 Year Closed 6.99%",
                "7 Year Closed 7.1%",
                "10 Year Closed 7.25%"
        };
        ArrayAdapter<String> interestAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, interestRates);
        interestRateSpinner.setAdapter(interestAdapter);

        // Populate Amortization Period Spinner
        String[] years = new String[30];
        years[0] = "0.5 years";
        for (int i = 1; i < years.length; i++) {
            years[i] = (i + 1) + " years";
        }
        ArrayAdapter<String> amortizationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
        amortizationPeriodSpinner.setAdapter(amortizationAdapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateMortgageAndShowResult();
            }
        });
    }


    private void calculateMortgageAndShowResult() {
        try {
            double principal = Double.parseDouble(principalEditText.getText().toString());
            String interestRateString = interestRateSpinner.getSelectedItem().toString();
            String termString = amortizationPeriodSpinner.getSelectedItem().toString();

            double monthlyPayment = MortgageCalc.calculateMonthlyPayment(principal, interestRateString, termString);

            // Create Intent to start ResultActivity
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("MONTHLY_PAYMENT", monthlyPayment);
            intent.putExtra("PRINCIPAL", principal);
            intent.putExtra("INTEREST_RATE", interestRateString);
            intent.putExtra("TERM", termString);
            startActivity(intent);
        } catch (NumberFormatException e) {
            // Handle error (e.g., show a toast message)
        } catch (Exception e) {
            // Handle error (e.g., show a toast message)
        }
    }


}