package com.example.myapplication

object Screen{
    const val BREEDS_SCREEN_ROUTE = "dog_breed_screen"
    const val BASE_IMAGE_BY_BREED_ROUTE = "image_by_breed_screen/"
    const val BREED_ROUTE_KEY = "breed"
    const val IMAGE_BY_BREED_ROUTE = "$BASE_IMAGE_BY_BREED_ROUTE{$BREED_ROUTE_KEY}"
    const val RANDOM_DOG_IMAGE_ROUTE = "random_dog_image"
    const val SEND_AUTHOR_TO_WEAR_KEY = "/selected_author"

    const val SEND_AUTHOR_INTENT_PUT_EXTRA_KEY = "selected_author_key"
    const val SEND_AUTHOR_INTENT_ACTION_KEY = "selected_author"

    const val ASK_NEW_RANDOM_AUTHOR_KEY = "ask_send_random_author"



}
