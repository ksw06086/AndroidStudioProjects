package kr.ac.yuhan.sun.sogating.message.fcm

data class PushNotification (
    val data : NotiModel,
    val to : String
)
