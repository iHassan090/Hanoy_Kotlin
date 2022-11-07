package com.hassan.hanoykotlin.dialogs

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import com.bumptech.glide.Glide
import com.hassan.hanoykotlin.R
import com.hassan.hanoykotlin.databinding.DialogRateUsBinding
import com.hassan.hanoykotlin.preferences.PreferenceManager


class RateUsDialog(context: Context) : Dialog(context), View.OnClickListener {
    private lateinit var binding: DialogRateUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogRateUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(context).load(R.raw.thank_you).into(binding.gifView)
        binding.button.setOnClickListener(this)
        binding.close.setOnClickListener(this)

        val width = (context.resources.displayMetrics.widthPixels * 0.80).toInt()
        val height = (context.resources.displayMetrics.heightPixels * 0.80).toInt()

        window!!.setLayout(width, height)
    }


    override fun onClick(p0: View?) {
        if (p0?.id == binding.button.id) {
            PreferenceManager.getInstance(context).saveShowRateUs(false)
            val appPackageName = context.packageName
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (exception: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
        dismiss()
    }
}