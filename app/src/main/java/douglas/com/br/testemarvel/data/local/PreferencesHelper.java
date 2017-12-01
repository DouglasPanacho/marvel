package douglas.com.br.testemarvel.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import douglas.com.br.testemarvel.Constants;

/**
 * Created by douglaspanacho on 30/11/2017.
 * Used to save Md5hash used to authenticated the requests
 */

public class PreferencesHelper {



    private static final String SHARED_PREFERENCES_NAME = Constants.PACKAGE_NAME + ".SHARED_PREFERENCES";
    private static final String PREF_HASH_VALUE = Constants.PACKAGE_NAME + ".PREF_HASH_VALUE";
    private SharedPreferences mSharedPreferences;

    private PreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    private static PreferencesHelper sInstance;

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesHelper(context);
        }
    }

    public static synchronized PreferencesHelper getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesHelper.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void putSessionHash(String md5Hash) {
        mSharedPreferences.edit().putString(PREF_HASH_VALUE, md5Hash);
    }

    public void getSessionHash() {
        mSharedPreferences.getString(PREF_HASH_VALUE, "");
    }

}
