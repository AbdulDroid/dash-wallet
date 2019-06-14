package org.dash.wallet.integration.nepsium.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.dash.wallet.integration.nepsium.R;
import org.dash.wallet.integration.nepsium.core.ConstantUtils;
import org.dash.wallet.integration.nepsium.ui.fragments.AuthFragment;
import org.dash.wallet.integration.nepsium.ui.fragments.ForgotPasswordFragment;
import org.dash.wallet.integration.nepsium.ui.fragments.MainKureFragment;
import org.dash.wallet.integration.nepsium.ui.fragments.RegistrationFragment;
import org.dash.wallet.integration.nepsium.ui.fragments.SplashFragment;

public class KurePayActivity extends AppCompatActivity implements
        SplashFragment.OnFragmentInteractionListener,
        AuthFragment.OnFragmentInteractionListener,
        RegistrationFragment.OnFragmentInteractionListener,
        ForgotPasswordFragment.OnFragmentInteractionListener,
        MainKureFragment.OnFragmentInteractionListener {

    private String receiver = "";
    private SplashFragment splashFragment = null;
    private AuthFragment authFragment = null;
    private RegistrationFragment registrationFragment = null;
    private ForgotPasswordFragment forgotPasswordFragment = null;
    private MainKureFragment mainKureFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kure_pay);
        receiver = getIntent().getStringExtra(ConstantUtils.WALLET_RECEIVING_ADDRESS_EXTRA);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        try {
            String start = getIntent().getStringExtra("start");
            showStartFragment(start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFragment(Fragment fr) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        fr.setEnterTransition(ConstantUtils.enterTransition());
        fr.setExitTransition(ConstantUtils.exitTransition());
        tr.replace(R.id.fragment_container, fr, fr.getClass().getSimpleName());
        tr.commit();
    }

    private void showStartFragment(String start) {
        if (start.equalsIgnoreCase("splash"))
            showSplashFragment(true);
        else if (start.equalsIgnoreCase("main"))
            showMainFragment(true);
    }

    private void showSplashFragment(boolean start) {
        if (splashFragment == null)
            splashFragment = SplashFragment.instance();
        if (start) {
            startFromScratch(splashFragment);
        } else {
            loadFragment(splashFragment);
        }
    }

    private void showAuthFragment() {
        if (authFragment == null)
            authFragment = AuthFragment.instance();
        loadFragment(authFragment);
    }

    private void showRegisterFragment() {
        if (registrationFragment == null)
            registrationFragment = RegistrationFragment.instance();
        loadFragment(registrationFragment);
    }

    private void showForgotFragment() {
        if (forgotPasswordFragment == null)
            forgotPasswordFragment = ForgotPasswordFragment.instance();
        loadFragment(forgotPasswordFragment);
    }

    private void showMainFragment(boolean start) {
        if (mainKureFragment == null)
            mainKureFragment = MainKureFragment.newInstance();
        if (start) {
            startFromScratch(mainKureFragment);
        } else {
            loadFragment(mainKureFragment);
        }
    }

    private void startFromScratch(Fragment fr) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        fr.setEnterTransition(ConstantUtils.exitTransition());
        fr.setExitTransition(ConstantUtils.exitTransition());
        tr.add(
                R.id.fragment_container,
                fr,
                fr.getClass().getSimpleName()
        );
        tr.commitAllowingStateLoss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if ((forgotPasswordFragment != null && forgotPasswordFragment.isAdded()) ||
                (registrationFragment != null && registrationFragment.isAdded()))
            showAuthFragment();
        else if ((splashFragment != null && splashFragment.isAdded()) ||
                (authFragment != null && authFragment.isAdded()) ||
                (mainKureFragment != null && mainKureFragment.isAdded()))
            super.onBackPressed();
    }

    @Override
    public void onLoginClicked(String username, String password) {

    }

    @Override
    public void onRegisterClicked() {
        showRegisterFragment();
    }

    @Override
    public void onForgotPasswordClicked() {
        showForgotFragment();
    }

    @Override
    public void onRegisterClicked(String fullName, String email, String username, String password) {

    }

    @Override
    public void onLoginClicked() {
        showAuthFragment();
    }

    @Override
    public void onForgotPasswordClicked(String email) {

    }

    @Override
    public void onLinkAccountClicked() {
        showAuthFragment();
    }
}