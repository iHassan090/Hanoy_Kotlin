package com.hassan.hanoykotlin.activities

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebSettings.LOAD_DEFAULT
import android.webkit.WebViewClient
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.hassan.hanoykotlin.databinding.ActivityWebViewBinding
import com.hassan.hanoykotlin.dialogs.RateUsDialog
import com.hassan.hanoykotlin.preferences.PreferenceManager
import com.hassan.hanoykotlin.utils.Constants.Companion.APP_OPEN_COUNT_FOR_DIALOG
import com.hassan.hanoykotlin.utils.Constants.Companion.URL


class WebViewActivity : BasicActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initValuesInViews() {
        super.initValuesInViews()
        val webSettings: WebSettings = this.binding.webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.builtInZoomControls = false
        webSettings.cacheMode = LOAD_DEFAULT
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.pluginState = WebSettings.PluginState.ON
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.setSupportMultipleWindows(true)

        this.binding.webView.webChromeClient = WebChromeClient()
        this.binding.webView.webViewClient = WebViewClient()

        this.binding.webView.loadUrl(Firebase.remoteConfig.getString(URL))
        if (PreferenceManager.getInstance(this)
                .getOpenCount() > APP_OPEN_COUNT_FOR_DIALOG && PreferenceManager.getInstance(this)
                .isShowRateUs()
        ) {
            val dialog = RateUsDialog(this)
            dialog.show()
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack())
            return binding.webView.goBack()
        return super.onBackPressed()
    }
}