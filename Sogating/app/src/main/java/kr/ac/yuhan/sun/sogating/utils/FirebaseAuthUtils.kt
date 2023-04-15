package kr.ac.yuhan.sun.sogating.utils

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthUtils {
    companion object {
        private lateinit var auth : FirebaseAuth

        // 로그인된 현재 나의 uid를 가져오는 함수
        fun getUid() : String {
            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()
        }
    }
}