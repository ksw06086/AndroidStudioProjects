package kr.ac.yuhan.sun.sogating.message

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.ac.yuhan.sun.sogating.R
import kr.ac.yuhan.sun.sogating.message.fcm.NotiModel
import kr.ac.yuhan.sun.sogating.message.fcm.PushNotification
import kr.ac.yuhan.sun.sogating.message.fcm.RetrofitInstance
import kr.ac.yuhan.sun.sogating.utils.FirebaseAuthUtils
import kr.ac.yuhan.sun.sogating.utils.FirebaseRef
import kr.ac.yuhan.sun.sogating.utils.MyInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MyMsgActivity : AppCompatActivity() {

    private val TAG = "MyMsgActivity"
    private val matchingMsgList = mutableListOf<MsgModel>()
    private val myMsgList = mutableListOf<MsgModel>()
    private val uid = FirebaseAuthUtils.getUid()

    lateinit var listViewAdapter: MsgAdapter
    lateinit var getterUid : String
    lateinit var getterToken : String
    private var allMsgList = mutableListOf<MsgModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_msg)

        val listView = findViewById<ListView>(R.id.msgListView)

        listViewAdapter = MsgAdapter(this, allMsgList)
        listView.adapter = listViewAdapter

        val secondIntent = intent
        getterUid = secondIntent.getStringExtra("getterUID").toString()
        getterToken = secondIntent.getStringExtra("getterToken").toString()

        val sendMsgBtn = findViewById<Button>(R.id.sendMsgBtn)
        sendMsgBtn.setOnClickListener {
            showDialog()
        }

        getMatchingUserMsg()
        getMyMsg()
    }

    // 상대가 보낸 메세지 리스트 가져오기
    private fun getMatchingUserMsg(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                matchingMsgList.clear()
                allMsgList.clear()
                for(dataModel in dataSnapshot.children){
                    val msg = dataModel.getValue(MsgModel::class.java)
                    matchingMsgList.add(msg!!)
                }
                allMsgList.addAll(myMsgList)
                allMsgList.addAll(matchingMsgList)
                allMsgList.sortByDescending { it.sendDate }
                listViewAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userMsgRef.child(uid).child(getterUid).addValueEventListener(postListener)
    }

    // 내가 보낸 메세지 리스트 가져오기
    private fun getMyMsg(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                myMsgList.clear()
                allMsgList.clear()
                for(dataModel in dataSnapshot.children){
                    val msg = dataModel.getValue(MsgModel::class.java)
                    myMsgList.add(msg!!)
                }
                allMsgList.addAll(myMsgList)
                allMsgList.addAll(matchingMsgList)
                allMsgList.sortByDescending { it.sendDate }
                listViewAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userMsgRef.child(getterUid).child(uid).addValueEventListener(postListener)
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
        val textArea = mAlertDialog.findViewById<EditText>(R.id.sendTextArea)
        btn?.setOnClickListener {

            val msgText = textArea!!.text.toString()

            val msgModel = MsgModel(
                MyInfo.myNickname, msgText, LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))

            FirebaseRef.userMsgRef.child(getterUid).child(uid).push().setValue(msgModel)

            val notiModel = NotiModel(MyInfo.myNickname, msgText)
            val pushModel = PushNotification(notiModel, getterToken)

            testPush(pushModel)


            mAlertDialog.dismiss()
        }

        // message
        // 받는 사람 uid
        // 받은 message, 누가 보냈는지
    }
}