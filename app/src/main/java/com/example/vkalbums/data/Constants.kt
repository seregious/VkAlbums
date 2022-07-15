package com.example.vkalbums.data

object Constants {
    const val AUTH_URL = "https://oauth.vk.com/authorize?client_id=8215234&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.131"
    const val my_url = "https://oauth.vk.com/blank.html#access_token=vk1.a._T9mePvUb9y-Q3H3ENS4nrJKRtkJu-RrUxtQU4uGw3hfmCM7cic52BXkEyrtI52_0Vvgz4IPDyWl3cfNEFP2m8TcL9I5gKqSsLBwH1dhXqZhn-Ry9x9HBXOFPGlh8M3Um-q7nhv33hKcfyTzpdres35QNM0tCYOl3Zqbqv9CLxU7wEBREenONEPV4eLsgAr9&expires_in=86400&user_id=85552697"
    val token = my_url.subSequence(45,243)
    val id = my_url.subSequence(269,277)
    const val BASE_URL = "https://api.vk.com/method/"
}