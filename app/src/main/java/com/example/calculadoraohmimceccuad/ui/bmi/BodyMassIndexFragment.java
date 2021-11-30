package com.example.calculadoraohmimceccuad.ui.bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculadoraohmimceccuad.R;
import com.example.calculadoraohmimceccuad.databinding.FragmentBodyMassIndexBinding;
import com.example.calculadoraohmimceccuad.databinding.FragmentHomeBinding;
import com.example.calculadoraohmimceccuad.databinding.FragmentQuadraticEquationBinding;
import com.example.calculadoraohmimceccuad.ui.home.HomeViewModel;

public class BodyMassIndexFragment extends Fragment {

    Double res;
    Double roundedRes;
    private FragmentBodyMassIndexBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_body_mass_index, container, false);

        final TextView weight = root.findViewById(R.id.txt_weight_input);
        final TextView height = root.findViewById(R.id.txt_height_input);
        final Button calculate = root.findViewById(R.id.btn_clc_bmi);
        final TextView result = root.findViewById(R.id.txt_bmi_result);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String w = weight.getText().toString();
                String h = height.getText().toString();

                if (w.length() == 0 || h.length() == 0) {
                    AlertDialog.Builder error = new AlertDialog.Builder(getActivity());
                    error.setCancelable(true);
                    error.setTitle("Error");
                    error.setMessage("Debe ingresar un valor para altura y peso");
                    error.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    error.show();
                } else {
                    Double convertedWeight = Double.parseDouble(w.toString().replace(',', '.'));
                    Double convertedHeight = Double.parseDouble(h.toString().replace(',', '.'));
                    res = convertedWeight / Math.pow(convertedHeight, 2);
                    roundedRes = Math.round(res * 100.0) / 100.0;
                    result.setText(Double.toString(roundedRes));
                    inform();
                }
            }
        });

        return root;
    }


    public void inform() {
        if (roundedRes < 18.5) {
            AlertDialog.Builder inform = new AlertDialog.Builder(getActivity());
            inform.setCancelable(true);
            inform.setTitle("Informe");
            inform.setMessage("Su IMC es: " + roundedRes + " lo cual significa que usted sufre desnutrición severa");
            inform.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            inform.show();
        } else if (roundedRes < 18.7) {
            AlertDialog.Builder inform = new AlertDialog.Builder(getActivity());
            inform.setCancelable(true);
            inform.setTitle("Informe");
            inform.setMessage("Su IMC es: " + roundedRes + " lo cual significa que su peso es normal. Felicidades");
            inform.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            inform.show();
        } else if (roundedRes < 25) {
            AlertDialog.Builder inform = new AlertDialog.Builder(getActivity());
            inform.setCancelable(true);
            inform.setTitle("Informe");
            inform.setMessage("Su IMC es: " + roundedRes + " lo cual significa que usted sufre sobre peso en grado 1");
            inform.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            inform.show();
        } else if (roundedRes < 27) {
            AlertDialog.Builder inform = new AlertDialog.Builder(getActivity());
            inform.setCancelable(true);
            inform.setTitle("Informe");
            inform.setMessage("Su IMC es: " + roundedRes + " lo cual significa que usted sufre sobre peso en grado 2");
            inform.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            inform.show();
        } else if (roundedRes < 30) {
            AlertDialog.Builder inform = new AlertDialog.Builder(getActivity());
            inform.setCancelable(true);
            inform.setTitle("Informe");
            inform.setMessage("Su IMC es: " + roundedRes + " lo cual significa que usted sufre obesidad tipo 1");
            inform.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            inform.show();
        } else if (35 < roundedRes) {
            AlertDialog.Builder inform = new AlertDialog.Builder(getActivity());
            inform.setCancelable(true);
            inform.setTitle("Informe");
            inform.setMessage("Su IMC es: " + roundedRes + " lo cual significa que usted sufre obesidad tipo 2");
            inform.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            inform.show();
        } else if (roundedRes < 40) {
            AlertDialog.Builder inform = new AlertDialog.Builder(getActivity());
            inform.setCancelable(true);
            inform.setTitle("Informe");
            inform.setMessage("Su IMC es: " + roundedRes + " lo cual significa que usted sufre obesidad tipo 3 (mórbida)");
            inform.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            inform.show();
        } else if (roundedRes < 50) {
            AlertDialog.Builder inform = new AlertDialog.Builder(getActivity());
            inform.setCancelable(true);
            inform.setTitle("Informe");
            inform.setMessage("Su IMC es: " + roundedRes + " lo cual significa que usted sufre obesidad tipo 4 (extrema)");
            inform.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            inform.show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}