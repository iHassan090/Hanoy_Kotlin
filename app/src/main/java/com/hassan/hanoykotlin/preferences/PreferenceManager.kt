package com.hassan.hanoykotlin.preferences

import android.content.Context
import android.content.SharedPreferences
import com.hassan.hanoykotlin.preferences.PreferenceKeys.Companion.APP_OPEN_COUNT
import com.hassan.hanoykotlin.preferences.PreferenceKeys.Companion.REMOTE_CONFIG_KEY
import com.hassan.hanoykotlin.preferences.PreferenceKeys.Companion.SHOW_RATE_US

class PreferenceManager private constructor(context: Context) {
    companion object {
        private var instance: PreferenceManager? = null
        fun getInstance(context: Context): PreferenceManager {
            if (instance == null)
                instance = PreferenceManager(context)
            return instance!!
        }
    }

    private var mSharedPrefs: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null


    init {
        mSharedPrefs = context.getSharedPreferences("ApplicationPreferences", 0)
        mEditor = mSharedPrefs?.edit()
    }

    fun saveRemoteConfig(value: String?) {
        mEditor!!.putString(REMOTE_CONFIG_KEY, value)
        mEditor!!.commit()
    }

    fun getRemoteConfig(): String? {
        return mSharedPrefs!!.getString(REMOTE_CONFIG_KEY, null)
    }

    fun saveOpenCount() {
        mEditor!!.putInt(APP_OPEN_COUNT, getOpenCount() + 1)
        mEditor!!.commit()
    }

    fun getOpenCount(): Int {
        return mSharedPrefs!!.getInt(APP_OPEN_COUNT, 0)
    }

    fun saveShowRateUs(isShow: Boolean) {
        mEditor!!.putBoolean(SHOW_RATE_US, isShow)
        mEditor!!.commit()
    }

    fun isShowRateUs(): Boolean {
        return mSharedPrefs!!.getBoolean(SHOW_RATE_US, true)
    }
}