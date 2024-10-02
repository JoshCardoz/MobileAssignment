package com.example.mobileassignment;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        TextView monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView);
        TextView principalTextView = findViewById(R.id.principalTextView);
        TextView interestRateTextView = findViewById(R.id.interestRateTextView);
        TextView termTextView = findViewById(R.id.termTextView);
        Button backButton = findViewById(R.id.backButton);

        double monthlyPayment = getIntent().getDoubleExtra("MONTHLY_PAYMENT", 0);
        double principal = getIntent().getDoubleExtra("PRINCIPAL", 0);
        String interestRate = getIntent().getStringExtra("INTEREST_RATE");
        String term = getIntent().getStringExtra("TERM");

        monthlyPaymentTextView.setText(String.format("$%.2f", monthlyPayment));
        principalTextView.setText(String.format("Principal: $%.2f", principal));
        interestRateTextView.setText(String.format("Interest Rate: %s", interestRate));
        termTextView.setText(String.format("Term: %s", term));

        backButton.setOnClickListener(v -> finish());
    }
}