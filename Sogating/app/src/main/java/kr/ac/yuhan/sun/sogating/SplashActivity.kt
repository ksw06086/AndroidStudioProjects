package kr.ac.yuhan.sun.sogating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kr.ac.yuhan.sun.sogating.auth.IntroActivity
import kr.ac.yuhan.sun.sogating.utils.FirebaseAuthUtils

class SplashActivity : AppCompatActivity() {

//    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        val uid = auth.currentUser?.uid.toString()
        val uid = FirebaseAuthUtils.getUid()

        val handler = Handler()
        if(uid == "null"){
            handler.postDelayed(Runnable {
                val intent = Intent(applicationContext, IntroActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000) // 3초 있다 메인액티비티로
        } else {
            handler.postDelayed(Runnable {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000) // 3초 있다 메인액티비티로
        }



    }
}