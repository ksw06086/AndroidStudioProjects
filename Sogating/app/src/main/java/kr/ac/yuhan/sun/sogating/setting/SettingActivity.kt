package kr.ac.yuhan.sun.sogating.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kr.ac.yuhan.sun.sogating.R
import kr.ac.yuhan.sun.sogating.auth.IntroActivity
import kr.ac.yuhan.sun.sogating.message.MyMatchingListActivity
import kr.ac.yuhan.sun.sogating.message.MyMsgActivity
import kr.ac.yuhan.sun.sogating.message.MyMsgListActivity

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val myPageBtn = findViewById<Button>(R.id.myPageBtn)
        myPageBtn.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        val myMatchingListBtn = findViewById<Button>(R.id.myMatchingListBtn)
        myMatchingListBtn.setOnClickListener {
            val intent = Intent(this, MyMatchingListActivity::class.java)
            startActivity(intent)
        }

        val myMsgBtn = findViewById<Button>(R.id.myMsgBtn)
        myMsgBtn.setOnClickListener {
            val intent = Intent(this, MyMsgListActivity::class.java)
            startActivity(intent)
        }

        val logoutBtn = findViewById<Button>(R.id.logoutBtn)
        logoutBtn.setOnClickListener {
            val auth = Firebase.auth
            auth.signOut()

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }
    }
}