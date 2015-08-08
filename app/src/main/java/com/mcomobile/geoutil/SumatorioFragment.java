package com.mcomobile.geoutil;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SumatorioFragment  extends DialogFragment {

    private TextView mAreaTotal;
    private ImageButton mOK, mCancelar;
    private String type;

    public static SumatorioFragment newInstance(String type) {

        SumatorioFragment calculateRouteFragment = new SumatorioFragment();
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
        View view = inflater.inflate(R.layout.sumatorio_file, container, false);

        setRetainInstance(true);
        mAreaTotal = (TextView)view.findViewById(R.id.sumatorio);
        mCancelar = (ImageButton) view.findViewById(R.id.button_cancel);
        mOK = (ImageButton) view.findViewById(R.id.button_ok);

        getDialog().setTitle("Introduzca los lados");
        mAreaTotal.setText(type + " m2");

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
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

}

