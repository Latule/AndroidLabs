package com.example.android;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import static android.content.Context.MODE_PRIVATE;


public class SettingsFragment extends PreferenceFragmentCompat  {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_pref);
        SharedPreferences preferences = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        SwitchPreference switchPreference = getPreferenceScreen().findPreference("store_data");
        final ListPreference listPreference = getPreferenceScreen().findPreference("font_size");

        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.parseBoolean(newValue.toString());
                editor.putBoolean("StoreData", checked);
                return true;
            }
        });

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int index = listPreference.findIndexOfValue(newValue.toString());
                editor.putString("font size", (String) listPreference.getEntries()[index]);
                editor.apply();
                return true;
            }
        });

    }



}