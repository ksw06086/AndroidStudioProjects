package kr.ac.yuhan.sun.sogating

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import kr.ac.yuhan.sun.sogating.auth.IntroActivity
import kr.ac.yuhan.sun.sogating.auth.UserDataModel
import kr.ac.yuhan.sun.sogating.setting.SettingActivity
import kr.ac.yuhan.sun.sogating.slider.CardStackAdapter
import kr.ac.yuhan.sun.sogating.utils.FirebaseAuthUtils
import kr.ac.yuhan.sun.sogating.utils.FirebaseRef
import kr.ac.yuhan.sun.sogating.utils.MyInfo

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val uid = FirebaseAuthUtils.getUid()
    // User 데이터들을 모아둘 리스트
    private val usersDataList = mutableListOf<UserDataModel>()
    // 카드를 넘길 때마다 수치 증가 변수
    private var userCount = 0

    lateinit var cardStackAdapter: CardStackAdapter
    // manager : RecycleView 만들 때 레이아웃 넣어준 것과 같은 역할
    lateinit var manager: CardStackLayoutManager
    private lateinit var currentUserGender : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val setting = findViewById<ImageView>(R.id.settingIcon)
        setting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        val cardStackView = findViewById<CardStackView>(R.id.cardStackView)

        manager = CardStackLayoutManager(baseContext, object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {
                if(direction == Direction.Right){
                    userLikeOtherUser(uid, usersDataList[userCount].uid.toString())
                }

                if(direction == Direction.Left){

                }

                userCount = userCount + 1
                if(userCount == usersDataList.count()){
                    getUserDataList(currentUserGender)
                    Toast.makeText(this@MainActivity, "새로운 카드를 받아옵니다.", Toast.LENGTH_LONG).show()
                }
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

        getMyData()
    }

    // 내 데이터를 가져와서 성별이 다른 사용자 리스트 가져오는 부분
    private fun getMyData() {
        val myImage = findViewById<ImageView>(R.id.myImage)

        val myUid = findViewById<TextView>(R.id.myUid)
        val myNickname = findViewById<TextView>(R.id.myNickname)
        val myAge = findViewById<TextView>(R.id.myAge)
        val myCity = findViewById<TextView>(R.id.myCity)
        val myGender = findViewById<TextView>(R.id.myGender)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // val post = dataSnapshot.getValue<Post>() <= 데이터 가져오는 것
                val data = dataSnapshot.getValue(UserDataModel::class.java)
                currentUserGender = data?.gender.toString()
                MyInfo.myNickname = data?.nickname.toString()

                getUserDataList(currentUserGender)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)
    }

    // 사용자 전체 리스트 가져오는 부분
    private fun getUserDataList(currentUserGender : String){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // val post = dataSnapshot.getValue<Post>() <= 데이터 가져오는 것

                for(dataModel in dataSnapshot.children){
                    val user = dataModel.getValue(UserDataModel::class.java)

                    if(!user!!.gender.equals(currentUserGender)){
                        usersDataList.add(user!!)
                    }

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

    // 유저의 좋아요를 표시하는 부분
    // 나의 uid, 좋아요한 상대의 uid
    private fun userLikeOtherUser(myUid : String, otherUid : String){
        FirebaseRef.userLikeRef.child(myUid).child(otherUid).setValue("true")

        getOtherUserLikeList(otherUid)
    }

    // 내가 좋아요한 상대가 좋아요한 유저리스트 가져오는 부분
    private fun getOtherUserLikeList(otherUid : String){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 상대가 좋아요한 리스트에서 내가 있는지 확인
                for(dataModel in dataSnapshot.children){
                    val likeUserKey = dataModel.key.toString()
                    if(likeUserKey.equals(uid)){
                        Toast.makeText(this@MainActivity, "좋아요한 상대가 00님을 좋아요 했습니다.", Toast.LENGTH_SHORT).show()
                        createNotificationChannel()
                        sendNotification()
                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userLikeRef.child(otherUid).addValueEventListener(postListener)
    }

    // notification
    // 채널 생성하는 부분
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "name"
            val descriptionText = "description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("Test_Channel", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // 나에게 알람메시지 보내는 부분
    private fun sendNotification(){
        var builder = NotificationCompat.Builder(this, "Test_Channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("매칭완료")
            .setContentText("매칭이 완료되었습니다. 상대도 나를 좋아합니다.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(123, builder.build())
        }
    }


}