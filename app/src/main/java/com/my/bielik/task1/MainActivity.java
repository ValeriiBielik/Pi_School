package com.my.bielik.task1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.my.bielik.task1.SecondActivity.EXTRA_REPLY;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    public static final String APP_PREFERENCES = "app_preferences";
    public static final String LAUNCH_COUNTER = "launch_counter";

    private TextView mReplyTextView;
    private TextView mReplyHeadTextView;
    private TextView mLaunchCounterTextView;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReplyTextView = findViewById(R.id.tv_chosen_text);
        mReplyHeadTextView = findViewById(R.id.tv_received_text);
        mLaunchCounterTextView = findViewById(R.id.tv_launch_counter);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            mReplyHeadTextView.setText(uri.toString());
            mReplyHeadTextView.setVisibility(View.VISIBLE);
        }

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (preferences.contains(LAUNCH_COUNTER)) {
            int counter = preferences.getInt(LAUNCH_COUNTER, 0) + 1;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(LAUNCH_COUNTER, counter);
            editor.apply();

            mLaunchCounterTextView.setText(String.valueOf(counter));
        }
        else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(LAUNCH_COUNTER, 1);
            editor.apply();

            mLaunchCounterTextView.setText("1");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null)
                    mReplyTextView.setText(data.getStringExtra(EXTRA_REPLY));
            }
        }
    }

    public void choose(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void share(View view) {
        String message = mReplyTextView.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(message)
                .startChooser();
    }
}
