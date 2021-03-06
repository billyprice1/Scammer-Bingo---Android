package com.xelitexirish.scammerbingo.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;

import com.xelitexirish.scammerbingo.R;

import java.util.List;

public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSimplePreferencesScreen();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSimplePreferencesScreen() {
        if (isSimplePreferences(this)) {
            addPreferencesFromResource(R.xml.pref_general);

            setupPreferenceClickListeners();
        }
    }

    private void setupPreferenceClickListeners() {

        findPreference("CACHE_ONLINE_LIST").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Snackbar.make(findViewById(android.R.id.content), "Coming soon, I promise", Snackbar.LENGTH_SHORT).show();
                return false;
            }
        });

        findPreference("ENABLE_SOUNDS").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if ((boolean) newValue) {
                    Snackbar.make(findViewById(android.R.id.content), "Sounds enabled", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Sounds disabled", Snackbar.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public boolean isMultiPane() {
        return isXLargeTablet(this);
    }

    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 4;
    }

    private static boolean isSimplePreferences(Context context) {
        return Build.VERSION.SDK_INT < 11 || !isXLargeTablet(context);
    }
}
