package kr.ac.yuhan.sun.sogating.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kr.ac.yuhan.sun.sogating.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}