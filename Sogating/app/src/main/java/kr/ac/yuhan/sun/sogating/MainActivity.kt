package kr.ac.yuhan.sun.sogating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import kr.ac.yuhan.sun.sogating.auth.IntroActivity
import kr.ac.yuhan.sun.sogating.auth.UserDataModel
import kr.ac.yuhan.sun.sogating.slider.CardStackAdapter
import kr.ac.yuhan.sun.sogating.utils.FirebaseRef

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    // User 데이터들을 모아둘 리스트
    private val usersDataList = mutableListOf<UserDataModel>()

    lateinit var cardStackAdapter: CardStackAdapter
    // manager : RecycleView 만들 때 레이아웃 넣어준 것과 같은 역할
    lateinit var manager: CardStackLayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val setting = findViewById<ImageView>(R.id.settingIcon)
        setting.setOnClickListener {
            val auth = Firebase.auth
            auth.signOut()

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }

        val cardStackView = findViewById<CardStackView>(R.id.cardStackView)

        manager = CardStackLayoutManager(baseContext, object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {

            }

            override fun onCardRewound() {

            }

            override fun onCardCanceled() {

            }

            override fun onCardAppeared(view: View?, position: Int) {

            }

            override fun onCardDisappeared(view: View?, position: Int) {

            }

        })

        // 현재 함수 여기서 사용 안해서 데이터가 없음
        cardStackAdapter = CardStackAdapter(baseContext, usersDataList)
        cardStackView.layoutManager = manager
        cardStackView.adapter = cardStackAdapter

        getUserDataList()
    }

    private fun getUserDataList(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // val post = dataSnapshot.getValue<Post>() <= 데이터 가져오는 것

                for(dataModel in dataSnapshot.children){
                    val user = dataSnapshot.getValue(UserDataModel::class.java)

                    usersDataList.add(user!!)
                }

                // 데이터 동기화(초기화)
                cardStackAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userInfoRef.addValueEventListener(postListener)

    }
}