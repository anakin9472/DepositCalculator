package com.android.depositlayout19110189;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonResult);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> results = calculationResults();
                if (results.get(0) == "blank error")
                    Toast.makeText(MainActivity.this, "Please fill in all the blanks.", Toast.LENGTH_LONG).show();
                else if (results.get(0) == "non-digit error")
                    Toast.makeText(MainActivity.this, "Please fill only digit characters.", Toast.LENGTH_LONG).show();
                else
                {
                    Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                    intent.putExtra("profitKey",results.get(0));
                    intent.putExtra("totalMoney",results.get(1));
                    startActivity(intent);
                }
            }
        });
    }

    public List<String> calculationResults()
    {
        //Identify text elements and variables
        List<String> results = new ArrayList<String>();
        String profit, totalMoney;
        EditText editText1 = (EditText)findViewById(R.id.editTextMoneySent);
        EditText editText2 = (EditText)findViewById(R.id.editTextNumberRate);
        EditText editText3 = (EditText)findViewById(R.id.editTextNumberPeriod);
        String moneySent = editText1.getText().toString();
        String percentageRate = editText2.getText().toString();
        String period = editText3.getText().toString();

        moneySent = moneySent.replaceAll("[^a-zA-Z0-9]","");
        percentageRate = percentageRate.replaceAll("[^a-zA-Z0-9]","");
        period = period.replaceAll("[^a-zA-Z0-9]","");
        //Catch errors
        if (editText1.length()<1 || editText2.length() < 1 || editText3.length() < 1)
        {
            results.add("blank error");
            return results;
        }
//        if (!TextUtils.isDigitsOnly(moneySent) || !TextUtils.isDigitsOnly(percentageRate) || !TextUtils.isDigitsOnly(percentageRate))
//        {
//            results.add("non-digit error");
//            return results;
//        }
        //Calculation code
        else
        {
            String equation = moneySent + "*" + percentageRate + "/" + "100" + "*" + period + "*" + "30" + "/" + "360";
            Expression exp = new Expression(equation);
            profit = String.valueOf(exp.calculate());
            totalMoney = moneySent + "+" + profit;
            Expression exp2 = new Expression(totalMoney);
            totalMoney = String.valueOf(exp2.calculate());
            profit = currencyFormat(standarlizeResult(profit));
            totalMoney = currencyFormat(standarlizeResult(totalMoney));
            results.add(profit);
            results.add(totalMoney);
            return results;
        }
    }

    private String standarlizeResult(String result)
    {
        if(result.endsWith(".0"))
            result = result.replace(".0", "");
        else if(result.endsWith(".00"))
            result = result.replace(".00", "");
        else if(result.endsWith(".000"))
            result = result.replace(".000", "");
        else if(result.endsWith(".0000"))
            result = result.replace(".0000", "");
        return result;
    }

    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(Double.parseDouble(amount));
    }
}