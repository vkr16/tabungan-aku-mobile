package id.my.akuonline.ravelkit

import android.os.Bundle
import android.text.InputType
import android.text.Layout
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_view)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val username : EditText = findViewById(R.id.input_username)
        val password : EditText = findViewById(R.id.input_password)
        val show : CheckBox = findViewById(R.id.show_password)

        show.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else{
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            password.setSelection(password.text.length)
            password.typeface = ResourcesCompat.getFont(this, R.font.ubuntusans)
        }

        val showPassword : TextView = findViewById(R.id.sp_label)

        showPassword.setOnClickListener{
            findViewById<CheckBox>(R.id.show_password).performClick()
        }
        val loginBtn : Button = findViewById(R.id.login_button)

        loginBtn.setOnClickListener{
            // Inflate the loading layout
            showLoading()
        }
    }


    fun showLoading() {
        // Add the loading view to the main content view
        val mainView = findViewById<ViewGroup>(android.R.id.content)
        mainView.addView(layoutInflater.inflate(R.layout.loading_screen, null))


    }
}