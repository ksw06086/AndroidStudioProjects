package kr.ac.yuhan.sun.sogating.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.ac.yuhan.sun.sogating.R
import kr.ac.yuhan.sun.sogating.auth.UserDataModel
import kr.ac.yuhan.sun.sogating.message.fcm.PushNotification
import kr.ac.yuhan.sun.sogating.message.fcm.RetrofitInstance
import kr.ac.yuhan.sun.sogating.utils.FirebaseAuthUtils
import kr.ac.yuhan.sun.sogating.utils.FirebaseRef

// 내가 좋아하고 상대도 나를 좋아하는 상대 리스트
class MyMatchingListActivity : AppCompatActivity() {

    private val TAG = "MyMatchingListActivity"
    private val uid = FirebaseAuthUtils.getUid()
    // 내가 좋아요한 사람들의 uid 리스트
    private val likeUserListUid = mutableListOf<String>()
    private val likeUserList = mutableListOf<UserDataModel>()

    lateinit var listViewAdapter: ListViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_matching_list)

        val userListView = findViewById<ListView>(R.id.userListView)

        listViewAdapter = ListViewAdapter(this, likeUserList)
        userListView.adapter = listViewAdapter
        
//        userListView.setOnItemClickListener { parent, view, position, id ->
//            checkMatching(likeUserList[position].uid.toString())
//
//            val notiModel = NotiModel("a", "b")
//            val pushModel = PushNotification(notiModel, likeUserList[position].token.toString())
//
//            testPush(pushModel)
//        }

        userListView.setOnItemLongClickListener { parent, view, position, id ->
            checkMatching(likeUserList[position].uid.toString())

            return@setOnItemLongClickListener(true)
        }

        // 내가 좋아요한 유저를 클릭하면(Long 클릭 시)
        // 메세지 보내기 창이 떠서 메세지를 보낼 수 있게 하고,
        // 메세지를 보내고 상대방에게 Push 알람 띄워주고
        // 만약 서로 좋아요하지 않은 상대라면 메세지 못보내게 함

        // 전체 사람들 리스트
        getUserDataList()

        // 내가 좋아요한 사람들의 리스트
        getMyLikeList()
    }
    
    // 클릭한 상대가 나와 매칭되었는지 확인하는 부분
    private fun checkMatching(otherUid : String){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 상대가 좋아요한 리스트에서 내가 있는지 확인
                for(dataModel in dataSnapshot.children){
                    val likeUserKey = dataModel.key.toString()
                    if(likeUserKey.equals(uid)){
                        Toast.makeText(this@MyMatchingListActivity, "매칭이 된 상대입니다.", Toast.LENGTH_SHORT).show()
                        showDialog()
                    }
                }
                // Toast.makeText(this@MyMatchingListActivity, "매칭이 안 된 상대입니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userLikeRef.child(otherUid).addValueEventListener(postListener)
    }

    // 내가 좋아요한 상대가 좋아요한 유저리스트 가져오는 부분
    private fun getMyLikeList(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 상대가 좋아요한 리스트에서 내가 있는지 확인
                for(dataModel in dataSnapshot.children){
                    // 내가 좋아요한 사람들의 uid가 likeUserList에 들어있음
                    likeUserListUid.add(dataModel.key.toString())
                }
                getUserDataList()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userLikeRef.child(uid).addValueEventListener(postListener)
    }

    // 사용자 전체 리스트 가져오는 부분
    private fun getUserDataList(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // val post = dataSnapshot.getValue<Post>() <= 데이터 가져오는 것

                for(dataModel in dataSnapshot.children){
                    val user = dataModel.getValue(UserDataModel::class.java)

                    if(likeUserListUid.contains(user?.uid)){
                        // 내가 좋아요한 사람들의 리스트만 뽑아옴
                        likeUserList.add(user!!)
                    }

                }
                listViewAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userInfoRef.addValueEventListener(postListener)

    }

    // PUSH 메세지 보내기 기능
    private fun testPush(notification : PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        RetrofitInstance.api.postNotification(notification)
    }

    // Dialog 띄우는 부분
    private fun showDialog(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("메세지 보내기")

        val mAlertDialog = mBuilder.show()
        val btn = mAlertDialog.findViewById<Button>(R.id.sendBtnArea)
        btn?.setOnClickListener {
            mAlertDialog.dismiss()
        }

        // message
            // 받는 사람 uid
                // 받은 message, 누가 보냈는지
    }
}