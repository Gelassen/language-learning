package com.home.languagelearning.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.home.languagelearning.R;
import com.home.languagelearning.domain.AddNewWordController;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public class AppDialogFragment extends DialogFragment {

    public static final String TAG = AppDialogFragment.class.getName();

    private AddNewWordController addNewWordController = new AddNewWordController();

    public static AppDialogFragment newInstance(Bundle args) {
        AppDialogFragment fragment = new AppDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), getTheme());
        View myView = LayoutInflater.from(getContext())
                .inflate(R.layout.activity_new_word, (ViewGroup) getView(), false);
        alertDialog.setTitle(R.string.new_word_title_origin);
        alertDialog.setView(myView);
        alertDialog.setPositiveButton(R.string.dialog_positive_btn, onPositiveListener);
        alertDialog.setNegativeButton(R.string.dialog_negative_btn, onNegativeListener);
        return alertDialog.show();
    }

    private DialogInterface.OnClickListener onPositiveListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            boolean next = addNewWordController.nextPage();
            if (next) {
                obtainOrigin(getView());
                showNextPage(getView());
            } else {
                processData(getView());
                dismiss();
            }
        }

        private void obtainOrigin(final View root) {
            EditText input = (EditText) root.findViewById(R.id.add_new_word_text);
            String origin = input.getText().toString();
            addNewWordController.addOrigin(origin);
        }

        private void showNextPage(final View root) {
            // replace title
            TextView titleView = (TextView) root.findViewById(R.id.add_new_word_title);
            titleView.setText(R.string.new_word_title_translation);
            // clear text view
            EditText input = (EditText) root.findViewById(R.id.add_new_word_text);
            input.clearComposingText();
        }

        private void processData(final View root) {
            // update model
            EditText input = (EditText) root.findViewById(R.id.add_new_word_text);
            String translation = input.getText().toString();
            addNewWordController.addTranslation(translation);
            // validate and save
            boolean isDataValid = addNewWordController.isDataValid();
            if (isDataValid) {
                addNewWordController.save(getContext());
            } else {
                // TODO show error
            }
        }
    };

    private DialogInterface.OnClickListener onNegativeListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dismiss();
        }
    };


}
