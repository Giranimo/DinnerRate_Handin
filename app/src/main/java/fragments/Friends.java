package fragments;

/**
 * Created by Administrator on 15-10-2014.
 */


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s.dinnerrate.R;


public class Friends extends Fragment implements View.OnClickListener {
    private EditText password;
    private EditText email;
    private Button login;
    private TextView Error;
    private Button ForgotPW;
    private Button register;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login, container, false);

        return v;
    }

    public static Friends newInstance(String text) {

        Friends f = new Friends();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.pword);
        login = (Button) view.findViewById(R.id.login);
        register = (Button) view.findViewById(R.id.registerbtn);
        ForgotPW = (Button) view.findViewById(R.id.passres);
        Error = (TextView) view.findViewById(R.id.loginErrorMsg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmailValid(email.getText())) {
                    if (String.valueOf(email.getText()).equals("kasperwind@gmail.com") && String.valueOf(password.getText()).equals("Abc1234")) {
                        Toast t = Toast.makeText(getActivity().getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        Error.setText("Wrong Email or Password");
                        Log.d("friends", String.valueOf(email.getText()));
                    }
                } else {
                    Error.setText("Please Insert Correct Email");
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        ForgotPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailValid(email.getText())) {
                    Error.setText("Reset Password Mail has been sent to: " + email.getText());
                } else {
                    Error.setText("Please Insert Correct Email");
                }
            }
        });

    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onClick(View v) {

    }
}
