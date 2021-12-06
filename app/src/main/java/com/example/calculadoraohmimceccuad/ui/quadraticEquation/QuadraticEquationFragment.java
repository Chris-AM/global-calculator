package com.example.calculadoraohmimceccuad.ui.quadraticEquation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculadoraohmimceccuad.R;
import com.example.calculadoraohmimceccuad.databinding.FragmentQuadraticEquationBinding;

public class QuadraticEquationFragment extends Fragment {

    Double a, b, c, x1, x2, delta, imaginary, real,
            xFinal, roundedX1, roundedX2, roundedImaginary, roundedReal, roundedFinal;
    private FragmentQuadraticEquationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentQuadraticEquationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView a_input = root.findViewById(R.id.txt_a_input);
        final TextView b_input = root.findViewById(R.id.txt_b_input);
        final TextView c_input = root.findViewById(R.id.txt_c_input);
        final Button calculate = root.findViewById(R.id.btn_calc_quadratic_equation);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String convertedA = a_input.getText().toString();
                String convertedB = b_input.getText().toString();
                String convertedC = c_input.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                try {
                    a = Double.parseDouble(convertedA);
                    if (a == 0) {
                        builder.setTitle("Error");
                        builder.setMessage("<<a>> debe ser distinto de 0");
                        builder.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                    } else {
                        b = Double.parseDouble(convertedB);
                        c = Double.parseDouble(convertedC);
                        delta = (b * b) - (4 * a * c);
                        if (delta > 0) {
                            x1 = (-b + Math.sqrt(delta)) / (2 * a);
                            x2 = (-b - Math.sqrt(delta)) / (2 * a);
                            roundedX1 = Math.round(x1 * 100.0) / 100.0;
                            roundedX2 = Math.round(x2 * 100.0) / 100.0;
                            builder.setTitle("Resultado");
                            builder.setMessage("La equación tiene dos soluciones:\ndelta ===> "
                                    + delta + "\n x1 ===> " + roundedX1 + "\nx2 ===> " + roundedX2);
                            builder.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        } else if (delta == 0) {
                            xFinal = -b / (2 * a);
                            roundedFinal = Math.round(xFinal * 100.0) / 100.0;
                            builder.setTitle("Resultado");
                            builder.setMessage("La equación tiene solo una solución:\ndelta ===> "
                                    + delta + "\n x ===> " + roundedFinal);
                            builder.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        } else if (delta < 0) {
                            imaginary = Math.sqrt(-delta) / (2 * a);
                            real = -b / (2 * a);
                            roundedImaginary = Math.round(imaginary * 100.0) / 100.0;
                            roundedReal = Math.round(real * 100.0) / 100.0;
                            builder.setTitle("Resultado");
                            builder.setMessage("La equación tiene una solución compleja y otra real:\ndelta ===> "
                                    + delta + "\nImaginaria ===> " + roundedImaginary + "i\nReal ===> " + roundedReal);
                            builder.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        }
                    }
                } catch (Exception e) {
                    AlertDialog.Builder error = new AlertDialog.Builder(getContext());
                    error.setTitle("Error");
                    error.setMessage("Por favor, ingresar todos los valores");
                    error.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    error.show();
                }

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}