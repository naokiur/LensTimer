package jp.ne.naokiur.lenstimer

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import jp.ne.naokiur.lenstimer.models.Lens
import jp.ne.naokiur.lenstimer.models.dao.LensDao
import java.math.BigDecimal
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private lateinit var leftEyeButton: Button
    private lateinit var rightEyeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lensDao = LensDao(this)

//        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        leftEyeButton = findViewById(R.id.button2)
        rightEyeButton = findViewById(R.id.button)

        leftEyeButton.setOnClickListener {
            val result = lensDao.selectAll()

            result.forEach {
                println("${it.productName}, ${it.lensPower}, ${it.forEye}")
            }
        }

        rightEyeButton.setOnClickListener {
            val lens = Lens(
                "test",
                BigDecimal(1.0),
                "R",
                Calendar.getInstance().timeInMillis
            )
            lensDao.insert(lens)
        }

//        textMessage = findViewById(R.id.message)
//        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
