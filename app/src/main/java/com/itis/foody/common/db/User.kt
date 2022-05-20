package com.itis.foody.common.db

data class User(
    var username: String,
    var email: String,
    var profileImage: String?,
    var recipeSets: MutableMap<String, Any>?
){
    constructor() : this("", "", null, HashMap())
}
