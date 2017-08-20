package com.mario.stackoverflowapi

import com.google.gson.annotations.SerializedName

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:35
 * @email mario_feles@live.com
 * @project StackOverflowAPI
 */

data class StackOverflowQuestion(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("view_count") val viewCount: Int,
    @SerializedName("answer_count") val answerCount: Int,
    @SerializedName("score") val score: Int,
    @SerializedName("creation_date") val creationDate: Int
) {

  override fun toString() = title
}
