package com.mario.stackoverflowapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.devconference.stackoverflow.returningTrue
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:35
 * @email mario_feles@live.com
 * @project StackOverflowAPI
 */

class SearchActivity : AppCompatActivity() {

    val api: StackOverflowApi by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        retrofit.create(StackOverflowApi::class.java)
    }

    private val toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }
    private val editSearchInput by lazy { findViewById(R.id.edit_search_input) as EditText }
    private val questionListRecyclerVIew by lazy { findViewById(R.id.question_list_recycler_view) as RecyclerView }
    private val adapter by lazy {
        QuestionListAdapter(this) { question ->
            Toast.makeText(this, "Clicked ${question.title}", Toast.LENGTH_SHORT).show()
        }
    }

    private var searchInputSubscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)

        questionListRecyclerVIew.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        questionListRecyclerVIew.adapter = adapter

        searchInputSubscription = editSearchInput.onTextChanged()
                .skip(3)
                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(AndroidSchedulers.mainThread())
                .filter { charSequence -> !charSequence.isNullOrBlank() }
                .map { charSequence -> charSequence.toString() }
                .concatMap { query ->
                    api.taggedQuestions(query)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                }
                .subscribe(
                        { questions -> populateView(questions) },
                        { error -> Log.e("Uhuu", "Error") },
                        { Log.d("Uhuu", "Completed") }
                )
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.question_list_sorting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) return false

        when (item.itemId) {
            R.id.menu_score_ascending -> {
                adapter.scoreAscending(); return true
            }
            R.id.menu_score_descending -> {
                adapter.scoreDescending()
                return true
            }
            R.id.menu_unanswered -> returningTrue {
                adapter.showUnanswered()
            }
        }

        return false
    }

    private fun populateView(questions: StackOverflowQuestions?) {
        adapter.questions = questions?.items ?: emptyList()
    }

    override fun onDestroy() {
        super.onDestroy()
        searchInputSubscription?.unsubscribe()
    }

}
