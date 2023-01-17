package com.aves.practicaltest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.aves.practicaltest.R
import com.aves.practicaltest.adapter.UserAdapter
import com.aves.practicaltest.databinding.ActivityFirstBinding
import com.aves.practicaltest.iinterface.GenericClickListener
import com.aves.practicaltest.model.UserModel
import com.aves.practicaltest.sealed.MainEvent
import com.aves.practicaltest.viewmodel.FirstViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstActivity : AppCompatActivity(), GenericClickListener {

    private val viewModel by viewModel<FirstViewModel>()
    private lateinit var binding: ActivityFirstBinding
    private val adapter by lazy {
        UserAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first)
        binding.rcvUser.adapter = adapter


        viewModel.setObserver()
        lifecycleScope.launchWhenStarted {
            viewModel.event.collect{
                when(it)
                {
                    MainEvent.ShowProgressBar->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is MainEvent.ResponseList<*>->{
                        val list = it.data as List<UserModel>
                        if(list.size>0)
                        {
                            adapter.userList.clear()
                            adapter.userList.addAll(list)
                            adapter.notifyDataSetChanged()
                        }
                        binding.progressBar.visibility = View.GONE
                    }
                    is MainEvent.ShowToast<*>->{
                        binding.progressBar.visibility = View.GONE
                        val message = it.message
                        Toast.makeText(this@FirstActivity, message, Toast.LENGTH_LONG).show()

                    }
                    else -> {}
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllUser()
    }

    override fun onClick(usermodel: UserModel) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("usermodel", Gson().toJson(usermodel))
        startActivity(intent)
    }


}