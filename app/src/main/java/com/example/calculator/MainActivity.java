package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private double firstVar,secondVar;
    private Boolean isOperationClick;
    String operation;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        next = findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                String text =textView.getText().toString();
                intent.putExtra("key1", text);
                startActivity(intent);
            }
        });
    }

    public void onNumberClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                setTv_Result("1");
                break;
            case R.id.btn_two:
                setTv_Result("2");
                break;
            case R.id.btn_three:
                setTv_Result("3");
                break;
            case R.id.btn_four:
                setTv_Result("4");
                break;
            case R.id.btn_five:
                setTv_Result("5");
                break;
            case R.id.btn_six:
                setTv_Result("6");
                break;
            case R.id.btn_seven:
                setTv_Result("7");
                break;
            case R.id.btn_eight:
                setTv_Result("8");
                break;
            case R.id.btn_nine:
                setTv_Result("9");
                break;
            case R.id.btn_zero:
                setTv_Result("0");
                break;
            case R.id.btn_clear:
                textView.setText("0");
                firstVar = 0.0;
                secondVar = 0.0;
                break;
            case R.id.btn_dot:
                if (!textView.getText().toString().contains(".")) {
                    textView.append(".");
                }
                break;
            case R.id.btn_minus:
                setTv_Result("-");
                break;
        }
        next.setVisibility(View.INVISIBLE);
    }

    public void setTv_Result(String numbers) {
        if (textView.getText().toString().equals("0")) {
            textView.setText(numbers);
        } else if (isOperationClick) {
            textView.setText(numbers);
        } else {
            textView.append(numbers);
        }
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        next.setVisibility(View.INVISIBLE);
        switch (view.getId()) {
            case R.id.btn_percent:
                firstVar = Double.parseDouble(textView.getText().toString());
                Double result = Double.valueOf(0);
                isOperationClick = true;
                operation = "/";
                switch (operation) {
                    case "/":
                        result = firstVar / 100;
                        break;
                }
                textView.setText(new DecimalFormat("##.#######").format(result));
                break;
            case R.id.btn_multiplay:
                firstVar = Double.parseDouble(textView.getText().toString());
                double resultS = Float.valueOf(0);
                isOperationClick = true;
                operation = "+-";
                switch (operation) {
                    case "+-":
                        resultS = firstVar*= -1;
                        break;
                }
                textView.setText(new DecimalFormat("##.#######").format(resultS));
                break;

            case R.id.btn_plus:
                setFirstVar();
                isOperationClick = true;
                operation = "+";
                break;
            case R.id.btn_minus:
                setFirstVar();
                isOperationClick = true;
                operation = "-";
                break;
            case R.id.btn_multiplication:
                setFirstVar();
                isOperationClick = true;
                operation = "X";
                break;
            case R.id.btn_slash:
                setFirstVar();
                isOperationClick = true;
                operation = "/";
                break;

            case R.id.btn_equals:
                setSecondVar();
                Double results = Double.valueOf(0);
                next.setVisibility(View.VISIBLE);
                switch (operation) {
                    case "+":
                        results = firstVar + secondVar;
                        textView.setText(results.toString());
                        break;
                    case "-":
                        results = firstVar - secondVar;
                        textView.setText(results.toString());
                        break;
                    case "X":
                        results = firstVar * secondVar;
                        textView.setText(results.toString());
                        break;
                    case "/":
                        results = firstVar / secondVar;
                        textView.setText(results.toString());
                        break;
                }
                break;
        }
    }

    public void setFirstVar() {
        firstVar = Double.parseDouble(textView.getText().toString());
    }

    public void setSecondVar() {
        secondVar = Double.parseDouble(textView.getText().toString());
    }
}