package dev.cristopher.customrules

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hardcodedColor: Int = Color.parseColor("#80000000")
        findViewById<ConstraintLayout>(R.id.layoutMain).setBackgroundColor(hardcodedColor)
    }
}
