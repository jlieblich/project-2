package com.jonathanlieblich.somesortofcommerceyousay;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by jonlieblich on 11/9/16.
 */

public class EmptyCartAlert extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Nothing to see here");
        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            //Return to main screen onClickListener
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });
        return builder.create();
    }

    //Go back to main screen if user doesn't press positive button on dialog
    @Override
    public void onPause() {
        getActivity().finish();
        super.onPause();
    }
}
