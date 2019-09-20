package com.atitto.nearbyplaces.common

import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun CompositeDisposable.makeAction(action: Completable, errorLiveData: MutableLiveData<String>, postExecute: () -> Unit) {
    add(
        action
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postExecute.invoke()
            },{
                errorLiveData.postValue(it.message)
            })
    )
}

fun <T> CompositeDisposable.makeAction(action: Flowable<T>, errorLiveData: MutableLiveData<String>, postExecute: (T) -> Unit) {
    add(
        action
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postExecute.invoke(it)
            }, {
                errorLiveData.postValue(it.message)
            })
    )
}

fun <T> CompositeDisposable.makeAction(action: Observable<T>, errorExecute: (Throwable) -> Unit, postExecute: (T) -> Unit) {
    add(
        action
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postExecute.invoke(it)
            }, {
                errorExecute.invoke(it)
            })
    )
}

fun <T> CompositeDisposable.makeAction(action: Single<T>, errorLiveData: MutableLiveData<String>, postExecute: (T) -> Unit) {
    add(
        action
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postExecute.invoke(it)
            }, {
                errorLiveData.postValue(it.message)
            })
    )
}

fun <T> CompositeDisposable.makeActionAndConsume(action: Single<T>, errorLiveData: MutableLiveData<String>, valueLiveData: MutableLiveData<T>) {
    makeAction(action, errorLiveData) { valueLiveData.postValue(it) }
}

fun <T> CompositeDisposable.makeAction(action: Single<T>, errorExecute: (Throwable) -> Unit, postExecute: (T) -> Unit) {
    add(
        action
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postExecute.invoke(it)
            }, {
                errorExecute.invoke(it)
            })
    )
}