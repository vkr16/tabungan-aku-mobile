package id.my.akuonline.ravelkit

import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.text.InputType
import android.text.Layout
import android.view.View
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
import androidx.transition.Visibility
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private var loadingView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_view)
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
        loginBtn.setOnClickListener {
            // Inflate the loading layout
            val usernameErr :TextView =  findViewById(R.id.username_err_msg)
            usernameErr.visibility = View.GONE

            if (username.text.toString() == ""){
                usernameErr.visibility = View.VISIBLE
                usernameErr.text = "Please put down your fucking username"
            }else {
                showLoading()
                object : CountDownTimer(5000, 1000) { // Initial delay and interval
                    override fun onTick(millisUntilFinished: Long) {
                        // Code to be executed at each interval
                    }

                    override fun onFinish() {
                        // Code to be executed after the countdown
                        hideLoading()
                    }
                }.start()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        findViewById<Button>(R.id.login_button).text = "Paused"
    }

    override fun onResume() {
        super.onResume()
        findViewById<Button>(R.id.login_button).text = "Login"
    }

    fun showLoading() {
        // Add the loading view to the main content view
        val loginBtn: Button = findViewById(R.id.login_button)
        loginBtn.isClickable = false
        loginBtn.text = "Logging in"
        val mainView = findViewById<ViewGroup>(android.R.id.content)
        loadingView = layoutInflater.inflate(R.layout.loading_screen, null)
        mainView.addView(loadingView)
    }

    fun hideLoading() {
        // Remove the loading view from the main content view
        val loginBtn: Button = findViewById(R.id.login_button)
        loginBtn.isClickable = true
        loginBtn.text = "Login"
        val mainView = findViewById<ViewGroup>(android.R.id.content)
        loadingView?.let { mainView.removeView(it) }
        loadingView = null
    }
}