package com.my.bielik.task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_REPLY = "reply_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.button_banana).setOnClickListener(this);
        findViewById(R.id.button_apple).setOnClickListener(this);
        findViewById(R.id.button_pear).setOnClickListener(this);
        findViewById(R.id.button_orange).setOnClickListener(this);
        findViewById(R.id.button_pineapple).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.button_banana :
                intent.putExtra(EXTRA_REPLY, ((Button) v).getText().toString());
                break;
            case R.id.button_apple :
                intent.putExtra(EXTRA_REPLY, ((Button) v).getText().toString());
                break;
            case R.id.button_pear :
                intent.putExtra(EXTRA_REPLY, ((Button) v).getText().toString());
                break;
            case R.id.button_orange :
                intent.putExtra(EXTRA_REPLY, ((Button) v).getText().toString());
                break;
            case R.id.button_pineapple :
                intent.putExtra(EXTRA_REPLY, ((Button) v).getText().toString());
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
