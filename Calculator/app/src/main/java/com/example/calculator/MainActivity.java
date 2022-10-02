package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    String source1 = "";
    String source2 = "";
    float result;
    int numberOfOperand;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberOfOperand = 0;
    }

    public void onClickNum(View view) {
        EditText editText = findViewById(R.id.editText);
        Button button = (Button) view;
        String num = (String) button.getText();
        if (numberOfOperand == 0){  //첫 번째 입력일 경우
            source1 = source1 + num;
        }
        else{   //두 번째 입력일 경우
            source2 = source2 + num;
        }
        editText.setText(source1 + operator + source2);
        System.out.println("source1 = " + source1);
        System.out.println("source2 = " + source2);
        System.out.println("operator = " + operator);
    }

    public void onClickOperator(View view) {
        EditText editText = findViewById(R.id.editText);
        Button button = (Button) view;
        numberOfOperand = 1;
        operator = (String) button.getText();
        editText.setText(source1 + operator + source2);
    }

    public void onClickResult(View view) {
        EditText editText = findViewById(R.id.editText);
        System.out.println("source1 = " + source1);
        System.out.println("source2 = " + source2);
        if (operator.equals("+")){
            result = (float) Integer.parseInt(source1) + (float) Integer.parseInt(source2);
        }
        else if(operator.equals("-")){
            result = (float) Integer.parseInt(source1) - (float) Integer.parseInt(source2);
        }
        else if(operator.equals("*")){
            result = (float) Integer.parseInt(source1) * (float) Integer.parseInt(source2);

        }
        else{
            if(source2.equals("0")){
                editText.setText("0으로 나눌 수 없습니다.");
            }
            else{
                result = (float) Integer.parseInt(source1) / (float) Integer.parseInt(source2);
            }
        }
        editText.setText(Float.toString(result));
        source1 = "";
        source2 = "";
        numberOfOperand = 0;
        result = 0;
        operator = "";
    }

    public void onClickClear(View view) {
        EditText editText = findViewById(R.id.editText);
        editText.setText("");
        source1 = "";
        source2 = "";
        numberOfOperand = 0;
        result = 0;
        operator = "";
    }
}