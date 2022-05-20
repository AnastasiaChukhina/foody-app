package com.itis.foody.features.recipe.domain.mappers

interface ModelMapper<S, D> {
    fun map(source: S): D
}
