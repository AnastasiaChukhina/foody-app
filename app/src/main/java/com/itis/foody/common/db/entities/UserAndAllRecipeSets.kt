package com.itis.foody.common.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndAllRecipeSets(
    @Embedded
    var user: User,
    @Relation(parentColumn = "id", entityColumn = "userId", entity = RecipeSet::class)
    var recipeSets: List<RecipeSet>
)
