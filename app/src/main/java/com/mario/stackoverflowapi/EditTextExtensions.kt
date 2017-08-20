package com.mario.stackoverflowapi

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import rx.Observable
import rx.subjects.BehaviorSubject

/**
 * @author Mario Feles dos Santos Junior
 * @date 20/08/17 - 16:44
 * @email mario_feles@live.com
 * @project StackOverflowAPI
 */

fun EditText.onTextChanged(): Observable<String>{
    val subject = BehaviorSubject.create<String>()
    this.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            subject.onNext(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    })
    return subject
}