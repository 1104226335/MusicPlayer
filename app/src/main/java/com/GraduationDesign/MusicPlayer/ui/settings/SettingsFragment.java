package com.GraduationDesign.MusicPlayer.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.ui.base.BaseFragment;
import com.GraduationDesign.MusicPlayer.ui.settings.login.LoginActivity;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/1/16
 * Time: 9:59 PM
 * Desc: SettingsFragment
 */
public class SettingsFragment extends BaseFragment {

    @BindView(R.id.login)
    RelativeLayout login;
    @BindView(R.id.goto_account_setting)
    RelativeLayout accountSetting;
    @BindView(R.id.music_play_setting)
    RelativeLayout musicplay_setting;
    @BindView(R.id.setting_app_about)
    RelativeLayout app_about;
    @BindView(R.id.setting_check_comment)
    RelativeLayout app_check_comment;
    @BindView(R.id.setting_user_name)
    TextView tvUserName;
    Unbinder unbinder;


    SharedPreferences shared;
    String userName;
    int userIdentity;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        shared = getContext().getSharedPreferences("LoginMsg",Context.MODE_PRIVATE);
        userName = shared.getString("UserName","登录");
        userIdentity = shared.getInt("UserIdentity",0);
        tvUserName.setText(userName);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        accountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AccountBindActivitity.class));
            }
        });
        musicplay_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MusicPlaySetting.class);
                startActivity(intent);
            }
        });
        app_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AppAboutActivity.class);
                startActivity(intent);
            }
        });

    }

    @OnClick(R.id.setting_check_comment)
    public void checkComment(){
        Intent intent = new Intent(getActivity(),CheckCommentActivity.class);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        userName = shared.getString("UserName","登录");
        tvUserName.setText(userName);
        if(userIdentity==0){
            app_check_comment.setVisibility(View.GONE);
        }else app_check_comment.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
