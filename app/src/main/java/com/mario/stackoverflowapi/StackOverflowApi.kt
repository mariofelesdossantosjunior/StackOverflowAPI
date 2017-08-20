package com.mario.stackoverflowapi

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:35
 * @email mario_feles@live.com
 * @project StackOverflowAPI
 */
interface StackOverflowApi {

    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    fun taggedQuestions(@Query("tagged") tags: String): Observable<StackOverflowQuestions>

}