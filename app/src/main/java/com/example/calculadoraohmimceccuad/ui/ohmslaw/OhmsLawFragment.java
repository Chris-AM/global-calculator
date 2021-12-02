package com.example.calculadoraohmimceccuad.ui.ohmslaw;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculadoraohmimceccuad.R;
import com.example.calculadoraohmimceccuad.databinding.FragmentOhmslawBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OhmsLawFragment extends Fragment {

    private FragmentOhmslawBinding binding;
    Double voltage, current, resistance, power, result, convertedResult;
    String option;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentOhmslawBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView voltageInput = root.findViewById(R.id.txt_voltage_input);
        final TextView currentInput = root.findViewById(R.id.txt_current_input);
        final TextView resistanceInput = root.findViewById(R.id.txt_resistance_input);
        final Button calculateButton = root.findViewById(R.id.btn_calc_ohmslaw);
        // get reference to spinner from layout
        final Spinner spinner = root.findViewById(R.id.spinner_ohmslaw);
        //initialize a String Array
        String[] ohmsVariables = new String[]{
                "Seleccione una opción",
                "Voltaje",
                "Resistencia",
                "Corriente"
        };
        final List<String> ohmsVariablesList = new ArrayList<>(Arrays.asList(ohmsVariables));
        //create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.layout_ohms_spinner, ohmsVariablesList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.rgb(128, 24, 248));
                }

                return view;
            }
        };
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(R.layout.layout_ohms_spinner);
        //set the ArrayAdapter to the spinner
        spinner.setAdapter(spinnerAdapter);
        //set the onItemSelected listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if (position > 0) {
                    Toast.makeText(
                            getContext(), "Haz Elegido: " + selectedItemText, Toast.LENGTH_SHORT
                    ).show();
                }
                option = selectedItemText;

                if (option.equals("Seleccione una opción")) {
                    voltageInput.setInputType(InputType.TYPE_NULL);
                    resistanceInput.setInputType(InputType.TYPE_NULL);
                    currentInput.setInputType(InputType.TYPE_NULL);
                } else if (option.equals("Voltaje")) {
                    voltageInput.setVisibility(View.GONE);
                    resistanceInput.setVisibility(View.VISIBLE);
                    resistanceInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                    currentInput.setVisibility(View.VISIBLE);
                    currentInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                } else if (option.equals("Resistencia")) {
                    voltageInput.setVisibility(View.VISIBLE);
                    voltageInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                    resistanceInput.setVisibility(View.GONE);
                    currentInput.setVisibility(View.VISIBLE);
                    currentInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                } else if (option.equals("Corriente")) {
                    voltageInput.setVisibility(View.VISIBLE);
                    voltageInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                    resistanceInput.setVisibility(View.VISIBLE);
                    resistanceInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                    currentInput.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String convertedR = resistanceInput.getText().toString();
                String convertedI = currentInput.getText().toString();
                String convertedV = voltageInput.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                switch (option) {
                    case "Voltaje":
                        try {
                            resistance = Double.parseDouble(convertedR);
                            current = Double.parseDouble(convertedI);
                            result = voltageCalc(current, resistance);
                            convertedResult = Math.round(result * 100.0) / 100.0;
                            builder.setTitle("Resultado");
                            builder.setMessage("El voltaje es: " + convertedResult + " volt");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        } catch (Exception e) {
                            catchError();
                        }
                        break;
                    case "Resistencia":
                        try {
                            voltage = Double.parseDouble(convertedV);
                            current = Double.parseDouble(convertedI);
                            resistance = resistanceCalc(voltage, current);
                            Double convertedResistance = Math.round(resistance * 100.0) / 100.0;
                            builder.setTitle("Resultado");
                            builder.setMessage("La resistencia es: " + convertedResistance + " ohm");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        } catch (Exception e) {
                            catchError();
                        }
                        break;
                    case "Corriente":
                        try {
                            voltage = Double.parseDouble(convertedV);
                            resistance = Double.parseDouble(convertedR);
                            current = currentCalc(voltage, resistance);
                            Double convertedCurrent = Math.round(current * 100.0) / 100.0;
                            builder.setTitle("Resultado");
                            builder.setMessage("La corriente es: " + convertedCurrent + " ampere");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show(); 
                        } catch (Exception e) {
                            catchError();
                        }
                        break;
                }// end switch
            }
        });

        return root;
    } //end onCreateView


    private Double voltageCalc(Double i, Double r) {
        this.current = i;
        this.resistance = r;
        voltage = i * r;
        return voltage;
    }

    private Double currentCalc(Double v, Double r) {
        this.voltage = v;
        this.resistance = r;
        current = v / r;
        return current;
    }

    private Double resistanceCalc(Double v, Double i) {
        this.voltage = v;
        this.current = i;
        resistance = v / i;
        return resistance;
    }

    private Double powerCalc(int comboIndex) {
        switch (comboIndex) {
            case 0:
                power = current * current * resistance;
                break;
            case 1:
                power = (voltage * voltage) / resistance;
                break;
            case 2:
                power = voltage * current;
                break;
        }
        return power;
    }

    private void catchError() {
        AlertDialog.Builder error = new AlertDialog.Builder(getContext());
        error.setTitle("Error");
        error.setMessage("Todos los valores deben ser ingresados según se explican en la Ley de Ohm");
        error.setNegativeButton("Entendido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        error.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}