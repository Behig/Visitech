package com.example.visitech;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {
    private Button loginbtn;
    private TextView forgot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container,false);

        loginbtn = (Button) v.findViewById(R.id.loginButton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        forgot = (TextView) v.findViewById(R.id.forgotText);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });
        return v;
    }

    public void showToast(){
        Toast toastl = Toast.makeText(getActivity(), "Check out the next update for login :)", Toast.LENGTH_SHORT);
        toastl.setGravity(Gravity.CENTER,0,0);
        toastl.show();
    }
}
