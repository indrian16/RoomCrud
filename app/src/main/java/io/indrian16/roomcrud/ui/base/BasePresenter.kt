package io.indrian16.roomcrud.ui.base

import io.indrian16.roomcrud.data.note.NoteRepo
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BasePresenter(private val baseView: BaseContract.View, private val repo: NoteRepo) :  BaseContract.Presenter {

    private var compositeDisposable = CompositeDisposable()

    override fun loadNote() {

        val disposable = repo.getAllNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        {noteList -> baseView.updateNoteList(noteList)},
                        {error -> baseView.showError(error.message.toString())}
                )
        compositeDisposable.add(disposable)
    }

    override fun deleteAll() {

        val disposable = Observable.create(ObservableOnSubscribe<Any> {

            repo.deleteAllNote()
            it.onComplete()
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        {},
                        {error -> baseView.showError(error.message.toString())}
                )
        compositeDisposable.add(disposable)
    }

    override fun unSubscribe() {

        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}