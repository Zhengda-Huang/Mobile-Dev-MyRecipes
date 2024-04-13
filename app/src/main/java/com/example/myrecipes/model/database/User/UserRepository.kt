package com.example.myrecipes.model.database.User

import android.content.Context
import com.example.myrecipes.model.database.AppDatabase

class UserRepository(context: Context) {

    private var userDao: UserDao
    private val database = AppDatabase.getInstance(context)

    init {
        userDao = database.UserDao()
    }

    suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    suspend fun getUserByEmail(email: String): User?{
        return userDao.getUserByEmail(email)
    }

    suspend fun getUserById(Id: Long){
        userDao.getUserById(Id)
    }
}