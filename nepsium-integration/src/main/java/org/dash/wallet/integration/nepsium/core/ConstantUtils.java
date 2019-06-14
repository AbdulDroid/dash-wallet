package org.dash.wallet.integration.nepsium.core;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Slide;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;

public class ConstantUtils {
    public static final String WALLET_RECEIVING_ADDRESS_EXTRA = "kure_pay_receiving_address_extra";
    public static final String FRAGMENT = "NepsiumIntegration";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Slide enterTransition() {
        Slide slide = new Slide();
        slide.setDuration(200);
        slide.setInterpolator(new DecelerateInterpolator());
        return slide;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Explode exitTransition() {
        Explode explode = new Explode();
        explode.setDuration(200);
        explode.setMode(Explode.MODE_OUT);
        explode.setInterpolator(new AccelerateInterpolator());
        return explode;
    }

    public static boolean passwordCheck(String password, EditText view) {
        if (TextUtils.isDigitsOnly(password)) {
            view.setError("Password cannot be all digits");
            return false;
        }else if (password.length() < 8) {
            view.setError("Password length too short");
            return false;
        }
        view.setError(null);
        return true;
    }
}
