package com.example.lvhang.androidstudyproject.StartActivityForResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lvhang.androidstudyproject.R;

/**
 * Created by lv.hang on 2016/5/18.
 */
public class OtherActivity extends Activity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_for_result_other);

        button = (Button) findViewById(R.id.finish_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", "My name is lvhang!");
                setResult(2, intent);
                finish();
            }
        });
    }
}
