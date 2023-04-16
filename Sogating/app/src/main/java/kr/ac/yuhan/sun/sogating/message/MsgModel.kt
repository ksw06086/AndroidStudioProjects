package kr.ac.yuhan.sun.sogating.message

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class MsgModel(
    val senderInfo: String = "",
    val sendTxt: String = "",
    val sendDate: String = ""
)