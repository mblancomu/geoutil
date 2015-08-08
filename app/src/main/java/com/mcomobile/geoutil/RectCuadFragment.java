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

public class RectCuadFragment extends DialogFragment {

    private EditText ladoA, ladoB;
    private ImageButton mOK, mCancelar;
    private String type;

    public static RectCuadFragment newInstance(String type) {

        RectCuadFragment calculateRouteFragment = new RectCuadFragment();
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
        View view = inflater.inflate(R.layout.data_cuadrado, container, false);

        setRetainInstance(true);
        ladoA = (EditText) view.findViewById(R.id.edit_ladoa);
        ladoB = (EditText) view.findViewById(R.id.edit_ladob);
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
        MainActivity callingActivity = (MainActivity) getActivity();
        callingActivity.onUserSelectValue(value1, value2, null, type);
        dismiss();
    }

}

