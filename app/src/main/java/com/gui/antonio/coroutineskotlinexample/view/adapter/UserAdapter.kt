package com.gui.antonio.coroutineskotlinexample.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.gui.antonio.coroutineskotlinexample.R
import com.gui.antonio.coroutineskotlinexample.database.entity.User
import com.gui.antonio.coroutineskotlinexample.databinding.ItemNameBinding

class UserAdapter(val userList: List<User>) : BaseAdapter() {

    lateinit var itemNameBinding: ItemNameBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        if (v == null) {
            v = LayoutInflater.from(parent?.context).inflate(R.layout.item_name, null)
            itemNameBinding = DataBindingUtil.bind(v)!!
            v?.tag = itemNameBinding
        } else {
            itemNameBinding = v.tag as ItemNameBinding
        }
        itemNameBinding.user = userList[position]

        return itemNameBinding.root
    }

    override fun getItem(position: Int): Any {
        return userList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return userList.size
    }

}