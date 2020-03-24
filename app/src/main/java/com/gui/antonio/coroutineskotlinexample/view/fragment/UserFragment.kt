package com.gui.antonio.coroutineskotlinexample.view.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.gui.antonio.coroutineskotlinexample.R
import com.gui.antonio.coroutineskotlinexample.database.entity.User
import com.gui.antonio.coroutineskotlinexample.databinding.FragmentUserBinding
import com.gui.antonio.coroutineskotlinexample.view.adapter.UserAdapter
import com.gui.antonio.coroutineskotlinexample.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment(), Observer<List<User>> {

    private lateinit var userViewModel: UserViewModel
    private var list = ArrayList<User>()
    private lateinit var arrayAdapter: UserAdapter
    private lateinit var userBinding: FragmentUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        constructViewModel()
        setObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        userBinding.viewModel = userViewModel
        userBinding.userFragment = this
        return userBinding.root
    }

    private fun constructViewModel() {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    }

    private fun setObserver() {
        userViewModel.getAllUsers.observe(this, this)
    }

    override fun onChanged(userList: List<User>?) {
        userList?.forEach { list.add(it) }
        setAdapter()
    }

    private fun setAdapter() {
        arrayAdapter = UserAdapter(userList = list)
        listView.adapter = arrayAdapter
        arrayAdapter.notifyDataSetChanged()
        showLastItemInList()
    }

    fun onClickSaveButton(view: View) {
        if (verifyForm() as Boolean) {
            closeKeyboard(view)
            val user = generateUser()
            insertUser(user)
            updateListView(user)
        } else {
            nameEditText.error = getString(R.string.error_name)
        }
    }

    private fun verifyForm(): Boolean? {
        return nameEditText.text?.isNotEmpty()
    }

    private fun closeKeyboard(view: View) {
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun insertUser(user: User) {
        userViewModel.insertUser(user)
    }

    private fun updateListView(user: User) {
        list.add(user)
        arrayAdapter.notifyDataSetChanged()
        showLastItemInList()
    }

    private fun showLastItemInList() {
        listView.smoothScrollToPosition(list.lastIndex)
    }

    private fun generateUser(): User {
        return User(name = nameEditText.text.toString())
    }


}
