package jp.ne.naokiur.lenstimer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
//import jp.ne.naokiur.lenstimer.models.Lens
//import jp.ne.naokiur.lenstimer.models.dao.LensDao

import kotlinx.android.synthetic.main.activity_initial_setting.*
import java.util.*

class InitialSettingActivity : AppCompatActivity() {
    private lateinit var rightProductName: EditText
    private lateinit var rightLensPower: EditText
    private lateinit var leftProductName: EditText
    private lateinit var leftLensPower: EditText

    private lateinit var settingExecute: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_setting)

        settingExecute = findViewById(R.id.settingExecute)

//        settingExecute.setOnClickListener {
//            val dao = LensDao(this)
//
//            rightProductName = findViewById(R.id.right_product_name)
//            rightLensPower = findViewById(R.id.right_lens_power)
//
//            leftProductName = findViewById(R.id.left_product_name)
//            leftLensPower = findViewById(R.id.left_lens_power)
//
//            val now = Calendar.getInstance().timeInMillis
//
//            val right = Lens(
//                rightProductName.text.toString(),
//                rightLensPower.text.toString().toBigDecimal(),
//                "R",
//                now
//            )
//
//            val left = Lens(
//                leftProductName.text.toString(),
//                leftLensPower.text.toString().toBigDecimal(),
//                "L",
//                now
//            )
//
//            dao.insert(right)
//            dao.insert(left)
//
//            finish()
//        }
    }

    override fun onBackPressed() {
        Toast.makeText(this, "初期情報を入力して下さい。", Toast.LENGTH_SHORT).show()
    }
}
