package jp.ne.naokiur.lenstimer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.ne.naokiur.lenstimer.databases.AppDatabase
import jp.ne.naokiur.lenstimer.databases.daos.LensDao
import jp.ne.naokiur.lenstimer.databases.tables.Lens
import jp.ne.naokiur.lenstimer.repositories.LensRepository

class LensViewModel(
    // dataSourceを取得するようにしていたが、
    // 呼び出し元のActivity, Fragment側で意識する必要がなくなるので、
    // ViewModel側でInitする、という理解
    // https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#8
    application: Application
) : AndroidViewModel(application) {
    private val repository: LensRepository
    val allLenses: LiveData<List<Lens>>

    init {
        val lensDao = AppDatabase.getInstance(application).lensDao
        repository = LensRepository(lensDao)
        allLenses = repository.allLenses
    }
}