package kr.ac.yuhan.sun.sogating.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kr.ac.yuhan.sun.sogating.R
import kr.ac.yuhan.sun.sogating.auth.UserDataModel

class ListViewAdapter(val context: Context, val item: MutableList<UserDataModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return item.size
    }

    override fun getItem(position: Int): Any {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView
        if(convertView == null){

            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.list_view_item, parent, false)

        }

        val nickname = convertView!!.findViewById<TextView>(R.id.listViewItemNickname)
        nickname.text = item[position].nickname

        return convertView!!
    }

}