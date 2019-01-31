package com.likithvarma.cryptostring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    String id;
    EditText editText;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        id=getIntent().getStringExtra("Id");// getting id from 1st activity weather encryption or decryption choosed
        editText=findViewById(R.id.text);
        textView=findViewById(R.id.result);
        button=findViewById(R.id.submit);
        button.setEnabled(false);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().trim().length()==0){//checking whether edittext is empty or not
                    button.setEnabled(false);// if empty disabling the submit button
                    textView.setText(R.string.Enter_text);//asking to enter the text
                } else {
                    button.setEnabled(true);//if not enable the button
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView.setText(null); // when changes in edit text making the textview to empty
                if(s.toString().trim().length()==0){//checking whether edittext is empty or not
                    button.setEnabled(false);// if empty disabling the submit button
                    textView.setText(R.string.Enter_text);//asking to enter the text
                } else {
                    button.setEnabled(true);
                    textView.setText(R.string.click_submit);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
// setting Activity title according to the id from "MainActivity"
        if(id.equals("enc")){
            getSupportActionBar().setTitle(R.string.encryption);
        }else if(id.equals("dec")){
            getSupportActionBar().setTitle(R.string.decryption);
        }
    }


    public void submit(View view) {
        String s=editText.getText().toString();
        if (id.equals("enc")){
            Toast.makeText(this, R.string.encrypting_text, Toast.LENGTH_SHORT).show();
            textView.setText(encryption(s));//calling method for encryption and setting it to textView

        }
        else if(id.equals("dec")){
            Toast.makeText(this, R.string.decrypt_text, Toast.LENGTH_SHORT).show();
            textView.setText(decryption(s));//calling method for decryption and setting it to textView
        }
    }

    String encryption(String s){//method for encryption
        String result="";
        for(int i=0;i<s.length();i++){
            int count=1;
            while(i<s.length()-1 && s.charAt(i)!='\0' && (s.charAt(i)==s.charAt(i+1)))// checking next char is same or else end of text
            { count+=1;
                i+=1;
            }
            result=result+s.charAt(i)+String.valueOf(count);
        }
        return result;
    }


    String decryption(String s){//method for encryption
        String result="";
        for(int i=1;i<s.length();i+=2) {//reading numbers at the odd positions
            if (Character.isDigit(s.charAt(i))) {
                int j = Character.getNumericValue(s.charAt(i));
                while (j > 0) {
                    result = result + s.charAt(i - 1);// adding characters according to the count
                    j--;
                }
            }
            else{
                return getString(R.string.hint_dec);
            }
        }
        return result;
    }

}

