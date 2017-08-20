package com.mario.stackoverflowapi

import com.google.gson.annotations.SerializedName
import com.mario.stackoverflowapi.StackOverflowQuestion

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:35
 * @email mario_feles@live.com
 * @project StackOverflowAPI
 */

data class StackOverflowQuestions(
    @SerializedName("items") val items: List<StackOverflowQuestion> = emptyList()
)
