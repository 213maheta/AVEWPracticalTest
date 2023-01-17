package com.aves.practicaltest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aves.practicaltest.R
import com.aves.practicaltest.databinding.ActivityFirstBinding
import com.aves.practicaltest.databinding.ActivityThirdBinding
import com.aves.practicaltest.model.UserModel
import com.bumptech.glide.Glide
import com.google.gson.Gson

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_third)

        val usermodel = Gson().fromJson(intent?.getStringExtra("usermodel"), UserModel::class.java)
        setAllData(usermodel)
    }

    private fun setAllData(usermodel: UserModel?) {
        Glide.with(binding.mainImage).load(usermodel?.urls?.raw).into(binding.mainImage)
        binding.description.text = usermodel?.altDescription
    }
}