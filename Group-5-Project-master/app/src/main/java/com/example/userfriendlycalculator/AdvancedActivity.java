package com.example.userfriendlycalculator;
// written by: Shane
// tested by: Shane
// debugged by:Shane, Anthony, Kristijan

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//this file launches the advanced calculator and sends the inputs to the api_call to perform the necessary operations
public class AdvancedActivity extends AppCompatActivity {
    api_call wolframCall = new api_call();
    private EditText output;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) { //sets up the advanced calculator, including the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_ui);
        output = findViewById(R.id.outputBox);
        output.setShowSoftInputOnFocus(false);
        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.answerBox).equals(output.getText().toString())){
                    output.setText("");
                }
            }
        });
    }

    private void updateText(String stringToAdd){
        //adds strings to the input field, the button methods down below call this method to add strings to the input field
        String pastString = output.getText().toString();
        int cursorPosition = output.getSelectionStart();
        String firstHalf = pastString.substring(0, cursorPosition);
        String secondHalf = pastString.substring(cursorPosition);
        output.setText(String.format("%s%s%s", firstHalf, stringToAdd, secondHalf));
        output.setSelection(cursorPosition + 1);

    }
    //buttons for the calculator, adds a string to the input field by calling the updateText method
    public void zerobutton(View view){
        updateText("0");
    }
    public void onebutton(View view){
        updateText("1");
    }
    public void twobutton(View view){
        updateText("2");
    }
    public void threebutton(View view){
        updateText("3");
    }
    public void fourbutton(View view){
        updateText("4");
    }
    public void fivebutton(View view){
        updateText("5");
    }
    public void sixbutton(View view){
        updateText("6");
    }
    public void sevenbutton(View view){
        updateText("7");
    }
    public void eightbutton(View view){
        updateText("8");
    }
    public void ninebutton(View view){
        updateText("9");
    }
    public void plusbutton(View view){
        updateText("+");
    }
    public void minusbutton(View view){
        updateText("-");
    }
    public void multiplybutton(View view){
        updateText("×");
    }
    public void dividebutton(View view){
        updateText("÷");
    }
    public void decimalbutton(View view){
        updateText(".");
    }
    public void exponentbutton(View view){
        updateText("^");
    }
    //complex function buttons
    public void sinbutton(View view){
        updateText("sin");
    }
    public void cosbutton(View view){
        updateText("cos");
    }
    public void tanbutton(View view){
        updateText("tan");
    }
    public void pibutton(View view){
        updateText("pi");
    }
    public void ebutton(View view){
        updateText("e");
    }
    public void logbutton(View view){
        updateText("log");
    }
    public void naturallogbuttton(View view){
        updateText("ln");
    }
    public void rootbutton(View view){ updateText("root"); }

    public void clearbutton(View view){ //clears the input field
        output.setText("");

    }



    public void advancedEqualButton(View view){ //sends the result from the input field into api_call.java
        String result = "";
        String userInput = output.getText().toString();
        userInput = userInput.replaceAll("//+", "plus");
        userInput = userInput.replaceAll("//-", "minus");
        userInput = userInput.replaceAll("÷", "divide");
        userInput = userInput.replaceAll("×", "multiply");
        result = wolframCall.returnResult(userInput.toString());
        output.setText(result);
        output.setSelection(result.length());
    }
    public void ParenthesisButton(View view){ //adds parantheses to the input field

        int cursorPosition = output.getSelectionStart();
        int openParenthesis = 0;
        int closeParenthesis = 0;
        int textLen = output.getText().length();
        for(int i = 0;i < cursorPosition; i++){
            if (output.getText().toString().substring(i, i + 1).equals("(")){
                openParenthesis += 1;
            }
            if (output.getText().toString().substring(i, i + 1).equals("(")){
                closeParenthesis += 1;
            }
            if(openParenthesis == closeParenthesis || output.getText().toString().substring(textLen -1, textLen).equals(openParenthesis)){
                updateText("(");
            }
            if(closeParenthesis < closeParenthesis || output.getText().toString().substring(textLen -1,textLen).equals(openParenthesis)){
                updateText(")");
            }
            output.setSelection(cursorPosition + 1);
        }
    }
}