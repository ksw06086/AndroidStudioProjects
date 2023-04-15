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

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        /// <notification 띄우는 방법들>
        // 첫 번째, 앱에서 코드로 notification 띄우기

        // 두 번째, Firebase Console에서 모든 앱에게 push 보내기
        
        // 세 번째, 특정 사용자에게 메세지 보내기(Firebase Console에서)

        // 네 번째, Firebase Console이 아니라, 앱에서 직접 다른 사람에게 푸시메세지 보내기


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

        val logoutBtn = findViewById<Button>(R.id.logoutBtn)
        logoutBtn.setOnClickListener {
            val auth = Firebase.auth
            auth.signOut()

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }
    }
}