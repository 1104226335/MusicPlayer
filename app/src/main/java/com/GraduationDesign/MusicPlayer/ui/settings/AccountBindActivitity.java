package com.GraduationDesign.MusicPlayer.ui.settings;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.GraduationDesign.MusicPlayer.R;

public class AccountBindActivitity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_bind_activitity);

        RelativeLayout set_account_bind =(RelativeLayout) findViewById(R.id.change_email);
        set_account_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotochangeEmail();
            }
        });
        RelativeLayout changePassword=(RelativeLayout) findViewById(R.id.change_password);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goto_changePassword();
            }
        });
        toolbar = findViewById(R.id.app_accout_bind_toolbar);
        setToolbar();
    }

    private void gotochangeEmail(){
        Intent goto_changeEmail=new Intent(this,ChangeEmail.class);
        startActivity(goto_changeEmail);
    }
    private void goto_changePassword(){
        Intent goto_changePassword=new Intent(this,change_password.class);
        startActivity(goto_changePassword);
    }

    public void setToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("账号和绑定");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}