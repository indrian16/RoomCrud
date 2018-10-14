package io.indrian16.roomcrud.ui.newnote

import io.indrian16.roomcrud.data.note.Note
import io.indrian16.roomcrud.data.note.NoteRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewPresenter(private val newView: NewContract.View, private val repository: NoteRepository) : NewContract.Presenter {

    private var disposable: Disposable? = null

    override fun saveText(note: Note) {

        if (note.text.length in 3..26) {

            disposable = Completable.fromAction {

                repository.insertNote(note)
                newView.onBackToBase()
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            {},
                            {error -> newView.showError(error.message.toString())}
                    )
        } else {

            newView.validationSaveText("You must type min 3 to 26 chars")
        }
    }

    override fun unSubscribe() {

        disposable?.dispose()
    }
}