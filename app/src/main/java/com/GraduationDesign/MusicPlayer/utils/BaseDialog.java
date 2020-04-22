package com.GraduationDesign.MusicPlayer.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.GraduationDesign.MusicPlayer.R;


public class BaseDialog extends Dialog {

    TextView title,PositionText,NegativeText;
    String positive,content,negative;
    BaseDialogListener dialogListener;
    Activity context;

    public BaseDialog(Activity context){
        super(context,R.style.BeseDialog);
        this.context = context;
    }
    public static BaseDialog getInstance(Activity context){
        BaseDialog baseDialog = new BaseDialog(context);
        return baseDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_dialog);
        title = findViewById(R.id.dialog_title);
        PositionText = findViewById(R.id.tv_dialog_confirm);
        NegativeText = findViewById(R.id.tv_dialog_cancle);

        this.NegativeText.setText(negative);
        this.PositionText.setText(positive);
        this.title.setText(content);

        PositionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListener.onPositionText();
            }
        });
        NegativeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListener.onNegativeText();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.gravity = Gravity.CENTER;
//
        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*4/5;// 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
    }
    public BaseDialog setContent(String content){
        this.content = content;
        return this;
    }
    public BaseDialog setSelectText(String positive,String negative,final BaseDialogListener baseDialogListener){
        this.dialogListener = baseDialogListener;
        this.positive = positive;
        this.negative = negative;
        return this;
    }

}
