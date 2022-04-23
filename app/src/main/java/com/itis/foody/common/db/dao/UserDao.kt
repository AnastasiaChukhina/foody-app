package com.itis.foody.common.db.dao

import androidx.room.*
import com.itis.foody.common.db.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email=:email")
    suspend fun getUserByEmail(email: String): User

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM user WHERE id=:id")
    suspend fun deleteUserById(id: Int)
}
