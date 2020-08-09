package com.demo.solutelabstest.ui

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.demo.solutelabstest.R
import com.demo.solutelabstest.utils.Constants
import com.demo.solutelabstest.utils.Utils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            runnable,
            Constants().SPLASH_SCREEN_TIME_OUT
        )
    }

    private val runnable =
        Runnable {
            Utils().launchStartActivity(
                this@SplashActivity,
                MainActivity::class.java
            )
        }

}