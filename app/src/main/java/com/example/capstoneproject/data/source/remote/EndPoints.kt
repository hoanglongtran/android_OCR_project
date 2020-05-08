package com.example.capstoneproject.data.source.remote

class EndPoints {
    private val ROOT_URL = "http://192.168.101.1/MyApi/Api.php?apicall="
    val UPLOAD_URL = ROOT_URL + "uploadpic"
    val GET_PICS_URL = ROOT_URL + "getpics"
}