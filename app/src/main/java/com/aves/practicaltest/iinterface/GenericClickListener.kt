package com.aves.practicaltest.iinterface

import com.aves.practicaltest.model.UserModel

interface GenericClickListener {
    fun onClick(usermodel: UserModel)
}