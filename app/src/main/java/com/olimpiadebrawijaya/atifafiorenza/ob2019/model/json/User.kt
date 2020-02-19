package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json

object User {
    data class LoginResponse(var status : String = "", var nim : String = "",
                             var nama : String = "", var fak : String = "",
                             var foto : String = "")
    data class UserLogin(var nim : String, var password : String)
}
