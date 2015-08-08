package com.mcomobile.geoutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SpinnerFragment extends DialogFragment {

    private Spinner mSpinner;
    private ImageButton mOK, mCancelar;
    private String mSelected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spinner, container, false);

        setRetainInstance(true);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mCancelar = (ImageButton) view.findViewById(R.id.button_cancel);
        mOK = (ImageButton) view.findViewById(R.id.button_ok);

        spinnerOptions();

        getDialog().setTitle("Seleccione superficie");

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData(mSelected);
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

    private void spinnerOptions() {

        List<String> list = new ArrayList<String>();
        list.add("Triángulo Irregular");
        list.add("Triángulo Regular");
        list.add("Cuadrado/Rectángulo");
        list.add("Círculo");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(dataAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mSelected = parent.getItemAtPosition(position).toString();

                Log.e("", "Seleccionado: " + mSelected);
                //getData(mSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getData(String mSelected) {

        MainActivity callingActivity = (MainActivity) getActivity();
        callingActivity.onSelectedSurface(mSelected);
        dismiss();
    }

}
