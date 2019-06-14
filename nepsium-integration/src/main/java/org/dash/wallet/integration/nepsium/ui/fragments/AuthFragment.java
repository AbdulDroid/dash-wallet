package org.dash.wallet.integration.nepsium.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.dash.wallet.integration.nepsium.R;
import org.dash.wallet.integration.nepsium.core.ConstantUtils;


public class AuthFragment extends Fragment {

    private OnFragmentInteractionListener listener;
    private static AuthFragment INSTANCE = null;

    public AuthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AuthFragment.
     */
    public static AuthFragment instance() {
        if (INSTANCE == null)
            INSTANCE = new AuthFragment();
        return INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_auth, container, false);
        try {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle("Login to KurePay");

        } catch (Exception e) {
            e.printStackTrace();
        }

        v.findViewById(R.id.forgot_password_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onForgotPasswordClicked();
            }
        });

        v.findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onRegisterClicked();
            }
        });

        v.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View t) {
                String email = ((EditText)v.findViewById(R.id.email_view)).getText().toString();
                String password = ((EditText)v.findViewById(R.id.password_view)).getText().toString();
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    ((EditText)v.findViewById(R.id.email_view)).setError("Invalid Email Address");
                }
                boolean check = ConstantUtils.passwordCheck(password, (EditText) v.findViewById(R.id.password_view));
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && check) {
                    if (listener != null)
                        listener.onLoginClicked(email, password);
                }
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onLoginClicked(String username, String password);
        void onRegisterClicked();
        void onForgotPasswordClicked();
    }
}
