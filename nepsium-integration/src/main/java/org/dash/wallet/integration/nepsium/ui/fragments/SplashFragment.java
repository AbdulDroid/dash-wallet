package org.dash.wallet.integration.nepsium.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dash.wallet.integration.nepsium.R;

public class SplashFragment extends Fragment {
    private static SplashFragment INSTANCE = null;
    private OnFragmentInteractionListener listener;

    public SplashFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SplashFragment.
     */
    public static SplashFragment instance() {
        if (INSTANCE == null)
            INSTANCE = new SplashFragment();
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
        View v = inflater.inflate(R.layout.fragment_splash, container, false);
        try {
            ActionBar actionBar = ((AppCompatActivity) this.getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(getString(R.string.link_kurepay_account));

        } catch (Exception e) {
            e.printStackTrace();
        }
        v.findViewById(R.id.kure_pay_link_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onLinkAccountClicked();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
            listener = (OnFragmentInteractionListener)context;
        else
            throw new IllegalStateException(context.toString() +
                    " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onLinkAccountClicked();
    }
}
