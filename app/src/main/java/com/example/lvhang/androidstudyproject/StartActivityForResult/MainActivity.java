package com.example.lvhang.androidstudyproject.StartActivityForResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lvhang.androidstudyproject.R;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_for_result_main);
        button = (Button) findViewById(R.id.main_button);
        textView = (TextView) findViewById(R.id.result_textview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到新打开Activity关闭后返回的数据
                //第二个参数为请求码，可以根据业务需求自己编号
                startActivityForResult(new Intent(MainActivity.this, OtherActivity.class), 1);
            }
        });
    }

    /**
     * 为了得到传回的数据，必须在前面的Activity中（指MainActivity类）重写onActivityResult方法
     *
     * requestCode 请求码，即调用startActivityForResult()传递过去的值，如果MainActivity有两个button，这个requestCode可以判断哪个button传过去的。
     * resultCode 结果码，结果码用于标识返回数据来自哪个新Activity,resultCode可以在另一个activity里面的setResult()来设。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
        textView.setText(result + " requestCode: " + requestCode + ", resultCode: " +resultCode);
    }
}
