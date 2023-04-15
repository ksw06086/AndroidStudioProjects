package kr.ac.yuhan.sun.sogating.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kr.ac.yuhan.sun.sogating.R
import kr.ac.yuhan.sun.sogating.utils.FirebaseAuthUtils
import kr.ac.yuhan.sun.sogating.utils.FirebaseRef

// 내가 좋아하고 상대도 나를 좋아하는 상대 리스트
class MyMatchingListActivity : AppCompatActivity() {

    private val TAG = "MyMatchingListActivity"
    private val uid = FirebaseAuthUtils.getUid()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_matching_list)

        // 내가 좋아요한 사람들의 리스트
        getMyLikeList()
    }

    // 내가 좋아요한 상대가 좋아요한 유저리스트 가져오는 부분
    private fun getMyLikeList(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 상대가 좋아요한 리스트에서 내가 있는지 확인
                for(dataModel in dataSnapshot.children){


                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userLikeRef.child(uid).addValueEventListener(postListener)
    }
}