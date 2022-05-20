package com.itis.foody.features.recipe.domain.usecases.recipe

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSimilarRecipesUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {
}
