package com.GraduationDesign.MusicPlayer.ui.settings.feedback;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.ui.base.BaseDialogFragment;

public class feedbackDialogFragment extends BaseDialogFragment implements Dialog.OnShowListener {

    private EditText editTextName;
    String title;
    private Callback mCallback;
    public interface Callback {

        void onConfirm(String content);
    }

    public static feedbackDialogFragment newInstance() {
        feedbackDialogFragment fragment = new feedbackDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setView(R.layout.dialog_feedback)
                .setNegativeButton(R.string.mp_cancel, null)
                .setPositiveButton(R.string.mp_Confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editTextName.getText().length() == 0){
                            dialog.dismiss();
                        }else{
                            dialog.dismiss();
                            mCallback.onConfirm(editTextName.getText().toString());
                        }
                    }
                })
                .create();
        dialog.setOnShowListener(this);
        return dialog;
    }

    @Override
    public void onShow(DialogInterface dialog) {
        resizeDialogSize();
        if (editTextName == null) {
            editTextName = (EditText) getDialog().findViewById(R.id.feedback_edit_text);
            editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (editTextName.length() > 0) {
                            mCallback.onConfirm(editTextName.getText().toString());
                        }
                        return true;
                    }
                    return false;
                }
            });
        }
    }
    public feedbackDialogFragment setCallback(feedbackDialogFragment.Callback callback) {
        mCallback = callback;
        return this;
    }
    public feedbackDialogFragment setTitle(String title) {
        this.title = title;
        return this;
    }
}
