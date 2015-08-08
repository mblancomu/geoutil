package com.mcomobile.geoutil;

import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView mListado;
    private ArrayList<String> mTriangulos;
    private double mLadoA, mLadoB, mLadoC, mAreaTriangulo, mAreaTotal;
    private ListAdapter mAdapter;
    private TextView mEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mListado = (ListView) findViewById(R.id.listado);
        mEmpty = (TextView) findViewById(R.id.empty);
        mTriangulos = new ArrayList<>();

        if (mTriangulos.size() == 0) {

            mEmpty.setVisibility(View.VISIBLE);
        }

        mListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onUserSelectValue(String ladoA, String ladoB, String ladoC, String type) {

        mLadoA = Double.parseDouble(ladoA);
        mLadoB = Double.parseDouble(ladoB);

        if (type.equals("Cuadrado/Rectángulo")){

        }else {

            mLadoC = Double.parseDouble(ladoC);
        }
        String area = "";

        if (type.equals("Triángulo Irregular")) {

            mAreaTriangulo = areaTriangulo(mLadoA, mLadoB, mLadoC);

        } else if (type.equals("Triángulo Regular")) {

            mAreaTriangulo = areaTrianguloRegular(mLadoA, mLadoB, mLadoC);

        } else if (type.equals("Cuadrado/Rectángulo")) {

            mAreaTriangulo = areaCuadRect(mLadoA, mLadoB);
        }

        area = String.valueOf(new DecimalFormat("##.####").format(mAreaTriangulo));
        mTriangulos.add(area);

        if (mTriangulos.size() > 0 && mAdapter != null) {

            mEmpty.setVisibility(View.GONE);
            mAdapter.updateResults(mTriangulos);

        } else {

            mEmpty.setVisibility(View.GONE);
            mAdapter = new ListAdapter(this, mTriangulos);
        }

        mListado.setAdapter(mAdapter);
        Log.e("", "El área del triangulo es: " + mAreaTriangulo);
    }

    public void onSelectedSurface(String type) {

        Log.e("", "Recibido: " + type);
        if (type.equals("Triángulo Irregular")) {

            QuantityDialogFragment dialog = QuantityDialogFragment.newInstance(type);
            dialog.show(getSupportFragmentManager(), "Dialog");

        } else if (type.equals("Triángulo Regular")) {

            QuantityDialogFragment dialog = QuantityDialogFragment.newInstance(type);
            dialog.show(getSupportFragmentManager(), "Dialog");

        } else if (type.equals("Cuadrado/Rectángulo")) {

            RectCuadFragment dialog = RectCuadFragment.newInstance(type);
            dialog.show(getSupportFragmentManager(), "RectCuad");

        } else if (type.equals("Círculo")) {


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_add) {
/*            QuantityDialogFragment dialog = new QuantityDialogFragment();
            dialog.show(getSupportFragmentManager(), "Dialog");*/

            SpinnerFragment spinnerFragment = new SpinnerFragment();
            spinnerFragment.show(getSupportFragmentManager(), "Spinner");
            Log.e("", "Añadir");
            return true;
        } else if (id == R.id.action_sumar) {
            Log.e("", "Sumar");
            areaTotal(mTriangulos);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private double areaTriangulo(double a, double b, double c) {

        double area;
        double semiperimetro = (a + b + c) / 2;

        Log.e("", "Semiperimetro: " + semiperimetro);

        double semiA = semiperimetro - a;
        double semiB = semiperimetro - b;
        double semiC = semiperimetro - c;

        Log.e("", "Semis: " + semiA + ", " + semiB + " ," + semiC);

        double areaCuadrado = semiperimetro * semiA * semiB * semiC;

        Log.e("", "areaCuadrado: " + areaCuadrado);
        double area2abs = Math.abs(areaCuadrado);

        Log.e("", "areaCuadrado abs: " + area2abs);
        area = Math.sqrt(area2abs);

        Log.e("", "Area: " + area);

        return area;
    }

    private double areaTrianguloRegular(double a, double b, double c) {

        double area = 0;
        area = (Math.sqrt(3) / 4) * (a * b);

        return area;

    }

    private double areaCuadRect(double a, double b) {

        double area = 0;
        area = (a * b);

        return area;

    }

    private double areaTotal(ArrayList<String> areas) {

        double sumaAreas = 0;
        if (areas.size() > 0) {
            double[] areasDouble = new double[areas.size()]; //create an array with the size of the failList

            for (int i = 0; i < areas.size(); ++i) { //iterate over the elements of the list
                String areas1 = areas.get(i);
                String newDouble = areas1.replace(",", ".");
                areasDouble[i] = Double.parseDouble(newDouble); //store each element as a double in the array
                sumaAreas = sumaAreas + areasDouble[i];
            }

        } else {

            Toast.makeText(this, "Debe introducir más datos para calcular el área total", Toast.LENGTH_LONG).show();
        }

        Log.e("", "Total áreas " + sumaAreas);

        return sumaAreas;
    }
}
