package kr.ac.yuhan.sun.sogating.message

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.ac.yuhan.sun.sogating.R
import kr.ac.yuhan.sun.sogating.auth.UserDataModel
import kr.ac.yuhan.sun.sogating.message.fcm.NotiModel
import kr.ac.yuhan.sun.sogating.message.fcm.PushNotification
import kr.ac.yuhan.sun.sogating.message.fcm.RetrofitInstance
import kr.ac.yuhan.sun.sogating.utils.FirebaseAuthUtils
import kr.ac.yuhan.sun.sogating.utils.FirebaseRef
import kr.ac.yuhan.sun.sogating.utils.MyInfo

class MyMsgListActivity : AppCompatActivity() {

    private val TAG = "MyMatchingListActivity"
    private val uid = FirebaseAuthUtils.getUid()
    // 내가 좋아요한 사람들의 uid 리스트
    private val msgUserListUid = mutableListOf<String>()
    private val msgUserList = mutableListOf<UserDataModel>()

    lateinit var listViewAdapter: ListViewAdapter
    lateinit var getterUID : String
    lateinit var getterToken : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_msg_list)

        val userListView = findViewById<ListView>(R.id.userListView)

        listViewAdapter = ListViewAdapter(this, msgUserList)
        userListView.adapter = listViewAdapter

        userListView.setOnItemClickListener { parent, view, position, id ->
            getterUID = msgUserList[position].uid.toString()
            getterToken = msgUserList[position].token.toString()

            // 다음 액티비티에 값 전달
            val intent = Intent(this, MyMsgActivity::class.java)
            intent.putExtra("getterUID", getterUID)
            intent.putExtra("getterToken", getterToken)

            startActivity(intent)
        }

        // 내가 좋아요한 사람들의 리스트
        getMsgUserList()
    }

    // 나에게 메세지 보낸 사람들의 UID 가져오기
    private fun getMsgUserList() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 상대가 좋아요한 리스트에서 내가 있는지 확인
                for (dataModel in dataSnapshot.children) {
                    // 내가 좋아요한 사람들의 uid가 likeUserList에 들어있음
                    msgUserListUid.add(dataModel.key.toString())
                }
                getUserDataList()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userMsgRef.child(uid).addValueEventListener(postListener)
    }


    // 원하는 유저 리스트 가져오기
    private fun getUserDataList(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // val post = dataSnapshot.getValue<Post>() <= 데이터 가져오는 것
                msgUserList.clear()

                for(dataModel in dataSnapshot.children){
                    val user = dataModel.getValue(UserDataModel::class.java)

                    if(msgUserListUid.contains(user?.uid)){
                        // 내가 좋아요한 사람들의 리스트만 뽑아옴
                        msgUserList.add(user!!)
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


}