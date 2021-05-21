package co.tiagoaguiar.evernotekt.model

import android.bluetooth.le.ScanRecord
import co.tiagoaguiar.evernotekt.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import java.util.*

class RemoteDataSource {

    fun listNotes(): Observable<List<Note>> =
        RetrofitClient.evernoteApi
            .listNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun getNote(noteId: Int): Observable<Note> =
        RetrofitClient.evernoteApi
            .getNote(noteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun createNote(note: Note): Observable<Note> =
        RetrofitClient.evernoteApi
            .createNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


}