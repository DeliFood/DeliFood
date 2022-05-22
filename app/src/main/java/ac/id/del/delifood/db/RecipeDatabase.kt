package ac.id.del.delifood.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
}