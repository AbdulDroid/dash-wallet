package org.dash.wallet.integration.nepsium.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dash.wallet.integration.nepsium.R;

public class RegistrationFragment extends Fragment {

    private OnFragmentInteractionListener listener = null;
    private static RegistrationFragment INSTANCE = null;

    public static RegistrationFragment instance() {
        if (INSTANCE == null)
            INSTANCE = new RegistrationFragment();
        return INSTANCE;
    }

    public RegistrationFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
            listener = (OnFragmentInteractionListener) context;
        else
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_registration, container, false);
        try {
            ActionBar actionBar = ((AppCompatActivity) this.getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle("Create your KurePay account");

        } catch (Exception e) {
            e.printStackTrace();
        }
        v.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onLoginClicked();
            }
        });
        v.findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View t) {
                if (checkInput()) {
                    if (listener != null) {
                        //listener.onRegisterClicked();
                    }
                }
            }
        });
        return v;
    }

    private boolean checkInput() {
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onRegisterClicked(String fullName, String email, String username, String password);
        void onLoginClicked();
    }
}
