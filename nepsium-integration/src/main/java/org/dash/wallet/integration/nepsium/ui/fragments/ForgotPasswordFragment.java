package org.dash.wallet.integration.nepsium.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.dash.wallet.integration.nepsium.R;

public class ForgotPasswordFragment extends Fragment {

    private static ForgotPasswordFragment INSTANCE = null;
    private OnFragmentInteractionListener listener = null;

    public static ForgotPasswordFragment instance() {
        if (INSTANCE == null)
            INSTANCE = new ForgotPasswordFragment();
        return INSTANCE;
    }

    public ForgotPasswordFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
            listener = (OnFragmentInteractionListener) context;
        else
            throw new IllegalArgumentException(context.toString() +
                    "must implement OnFragmentInteractionListener");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        try {
            ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (bar != null) {
                bar.setTitle("Reset your KurePay Password");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        v.findViewById(R.id.reset_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View t) {
                String email = ((EditText)v.findViewById(R.id.email_view)).getText().toString();
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (listener != null)
                        listener.onForgotPasswordClicked(email);
                } else {
                    ((EditText)v.findViewById(R.id.email_view)).setError("Invalid Email Address");
                }
            }
        });
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onForgotPasswordClicked(String email);
    }
}
