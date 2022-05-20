package com.itis.foody.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itis.foody.common.db.converters.ImageConverter
import com.itis.foody.common.db.converters.IngredientConverter
import com.itis.foody.common.db.converters.NutrientsInfoConverter
import com.itis.foody.common.db.converters.RecipeStepConverter
import com.itis.foody.common.db.dao.RecipeDao
import com.itis.foody.common.db.dao.RecipeSetDao
import com.itis.foody.common.db.dao.UserDao
import com.itis.foody.common.db.entities.Recipe
import com.itis.foody.common.db.entities.RecipeSet
import com.itis.foody.common.db.entities.RecipeSetRecipeCrossRef
import com.itis.foody.common.db.entities.User

@Database(
    entities = [User::class, RecipeSet::class, Recipe::class, RecipeSetRecipeCrossRef::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(
    IngredientConverter::class,
    NutrientsInfoConverter::class,
    ImageConverter::class,
    RecipeStepConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun recipeSetDao(): RecipeSetDao
    abstract fun recipeDao(): RecipeDao

    companion object {

        private const val DB_NAME = "app.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context?) = instance ?: synchronized(LOCK) {
            context?.let {
                buildDatabase(it).apply {
                    instance = this
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
