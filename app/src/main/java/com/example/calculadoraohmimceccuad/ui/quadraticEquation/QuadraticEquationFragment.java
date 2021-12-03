package com.example.calculadoraohmimceccuad.ui.quadraticEquation;

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

    private FragmentQuadraticEquationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentQuadraticEquationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView a_input = root.findViewById(R.id.txt_a_input);
        final TextView b_input = root.findViewById(R.id.txt_b_input);
        final TextView c_input = root.findViewById(R.id.txt_c_input);
        final Button calculate = root.findViewById(R.id.btn_calc_quadratic_equation);
        
        
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}