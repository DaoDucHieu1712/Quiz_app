package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class IntructionsDialog extends Dialog {
    private int intructionPoints = 0;

    public IntructionsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intructions_dialog_layout);

        final AppCompatButton continueBtn = findViewById(R.id.continueBtn);
        final TextView intructionsTV = findViewById(R.id.intructionsTV);

        setIntructionPoint(intructionsTV, "You will 1 point on every correct answer.");

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setIntructionPoint(TextView intructionsTV, String intructionPoint){
        if(intructionPoints == 0){
            intructionsTV.setText(intructionPoint);
        }else{
            intructionsTV.setText(intructionsTV.getText()+ "\n\n" + intructionPoint);
        }
    }
}