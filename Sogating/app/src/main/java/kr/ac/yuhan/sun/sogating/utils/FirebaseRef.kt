package kr.ac.yuhan.sun.sogating.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// 실시간 데이터베이스 경로 설정 및 가져오는 클래스
class FirebaseRef {
    companion object {
        val database = Firebase.database

        val userInfoRef = database.getReference("userInfo")
        val userLikeRef = database.getReference("userLike")
        val userMsgRef = database.getReference("userMsg")
    }
}