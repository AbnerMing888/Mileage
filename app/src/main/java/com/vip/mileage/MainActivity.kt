package com.vip.mileage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.vip.mprogress.MileageProgress

class MainActivity : AppCompatActivity() {

    private var mProgress = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mileageProgress = findViewById<MileageProgress>(R.id.mp_view)

        findViewById<Button>(R.id.btn_test).setOnClickListener {
            //测试进度
            if (mProgress == 0) {
                mProgress = 100
            }

            mProgress -= 5//每次减去5个百分比

            mileageProgress.changeProgress(mProgress)
        }
    }
}