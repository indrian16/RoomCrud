package io.indrian16.roomcrud.ui.updatenote

import io.indrian16.roomcrud.data.note.Note
import io.indrian16.roomcrud.data.note.NoteRepo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpdatePresenter(private val updateView: UpdateContract.View, private val repo: NoteRepo) : UpdateContract.Presenter {

    private var compositeDisposable = CompositeDisposable()

    override fun getNoteData(noteId: Long) {

        val disposable = repo.getNoteById(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        {note -> updateView.getNoteViewData(note)},
                        {error -> updateView.showError(error.message.toString())}
                )
        compositeDisposable.add(disposable)
    }

    override fun updateNote(note: Note) {

        if (note.text.length in 3..26) {

            val disposable = Completable.fromAction {

                repo.updateNote(note)
                updateView.onBackToBase()
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            {},
                            {error -> updateView.showError(error.message.toString())}
                    )
            compositeDisposable.add(disposable)
        } else {

            updateView.invalidUpdateText("You must type min 3 to 26 chars")
        }
    }

    override fun unSubscribe() {

        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}