package com.itis.foody.common.db.dao

import androidx.room.*
import com.itis.foody.common.db.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getTaskById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM user WHERE id=:id")
    suspend fun deleteUserById(id: Int)
}
