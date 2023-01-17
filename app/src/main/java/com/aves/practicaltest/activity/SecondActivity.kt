package com.aves.practicaltest.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aves.practicaltest.R
import com.aves.practicaltest.databinding.ActivitySecondBinding
import com.aves.practicaltest.model.UserModel
import com.bumptech.glide.Glide
import com.google.gson.Gson

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        val usermodel = Gson().fromJson(intent?.getStringExtra("usermodel"), UserModel::class.java)

        setAllData(usermodel)
    }

    private fun setAllData(usermodel: UserModel?) {
        Glide.with(binding.profileImage).load(usermodel?.user?.profileImage?.large).into(binding.profileImage)
        binding.username.text = usermodel?.user?.username
        binding.location.text = usermodel?.user?.location
        binding.bio.text = usermodel?.user?.bio

        binding.username.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("usermodel", Gson().toJson(usermodel))
            startActivity(intent)
        }

        binding.profileImage.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("usermodel", Gson().toJson(usermodel))
            startActivity(intent)
        }
    }
}