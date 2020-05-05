package com.GraduationDesign.MusicPlayer.ui.feedback;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;

public class SubmitFeedbackActivity extends AppCompatActivity {
    Toolbar toolbar;
    String feedback;
    EditText EditText_feedback;
    Button submit_feedback_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_feedback);
        toolbar = findViewById(R.id.app_submit_feedback_setting_toolbar);
        setToolbar();
        EditText_feedback = findViewById(R.id.edit_submit_feedback);
        submit_feedback_button = findViewById(R.id.button_submit_feedback);
        submit_feedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback = EditText_feedback.getText().toString();
                if(TextUtils.isEmpty(feedback)){
                    TextHelper.showLongText("反馈为空");
                    //EditText_feedback.setError("反馈为空");

                }else sendfeedback();
            }
        });
    }
    public void sendfeedback(){
        CommonApi.sendMyFeedback("", "", "", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void setToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("帮助与反馈");
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
