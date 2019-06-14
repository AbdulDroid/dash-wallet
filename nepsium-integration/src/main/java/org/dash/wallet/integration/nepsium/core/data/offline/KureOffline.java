package org.dash.wallet.integration.nepsium.core.data.offline;

import android.content.Context;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

public class KureOffline {

    private final SharedPreferences prefs;

    private static final String KURE_PAY_PREFS = "kure_pay_prefs.xml";
    private static final String KURE_PAY_ACCESS_TOKEN = "access_token";
    private String accessToken;
    private String encryptionKey;

    public KureOffline(Context context, String prefsEncryptionKey) {
        this.encryptionKey = prefsEncryptionKey;
        this.prefs = new SecurePreferences(context, prefsEncryptionKey, KURE_PAY_PREFS);
        this.accessToken = getStoredAccessToken();
    }

    private String getStoredAccessToken() {
        return accessToken;
    }
}
