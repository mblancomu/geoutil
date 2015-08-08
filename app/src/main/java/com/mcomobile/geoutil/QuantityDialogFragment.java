package com.mcomobile.geoutil;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class QuantityDialogFragment extends DialogFragment {

    private EditText ladoA, ladoB, ladoC;
    private ImageButton mOK, mCancelar;
    private String type;

    public static QuantityDialogFragment newInstance(String type) {

        QuantityDialogFragment calculateRouteFragment = new QuantityDialogFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        calculateRouteFragment.setArguments(args);

        return calculateRouteFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        type = getArguments().getString("type");

        return super.onCreateDialog(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_file, container, false);

        setRetainInstance(true);
        ladoA = (EditText) view.findViewById(R.id.edit_ladoa);
        ladoB = (EditText) view.findViewById(R.id.edit_ladob);
        ladoC = (EditText) view.findViewById(R.id.edit_ladoc);
        mCancelar = (ImageButton) view.findViewById(R.id.button_cancel);
        mOK = (ImageButton) view.findViewById(R.id.button_ok);

        getDialog().setTitle("Introduzca los lados");

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });

        mCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        return view;
    }

    private void getData() {

        String value1 = ladoA.getText().toString();
        String value2 = ladoB.getText().toString();
        String value3 = ladoC.getText().toString();
        Log.d("", "Quantity: " + value1 + " , " + value2 + " , " + value3);
        MainActivity callingActivity = (MainActivity) getActivity();
        callingActivity.onUserSelectValue(value1, value2, value3, type);
        dismiss();
    }

}
