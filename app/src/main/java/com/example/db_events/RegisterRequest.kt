package com.example.db_events

data class RegisterRequest(val email: String, val password: String, val matchingPassword: String) {

}