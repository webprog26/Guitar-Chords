package com.example.webprog26.guitarchords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_go)
    Button mBtnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        mBtnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
        finish();
    }
}
