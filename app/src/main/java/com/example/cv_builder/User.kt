package com.example.cv_builder

class User {
    var firstName: String
    var lastName: String
    var emailAddress: String
    var passWord: String
    var stayLoggedIn: Boolean

    constructor(firstName: String, lastName: String,emailAddress: String, passWord: String,  stayLoggedIn: Boolean) {
        this.firstName = firstName
        this.lastName = lastName
        this.emailAddress = emailAddress
        this.passWord = passWord
        this.stayLoggedIn = stayLoggedIn
    }
}