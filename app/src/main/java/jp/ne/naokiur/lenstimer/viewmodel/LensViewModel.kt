package jp.ne.naokiur.lenstimer.viewmodel

import android.app.Application
import androidx.lifecycle.*
import jp.ne.naokiur.lenstimer.databases.AppDatabase
import jp.ne.naokiur.lenstimer.databases.daos.LensDao
import jp.ne.naokiur.lenstimer.databases.tables.Lens
import jp.ne.naokiur.lenstimer.repositories.LensRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LensViewModel(
    // dataSourceを取得するようにしていたが、
    // 呼び出し元のActivity, Fragment側で意識する必要がなくなるので、
    // ViewModel側でInitする、という理解
    // https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#8
    application: Application
) : AndroidViewModel(application) {
    private val repository: LensRepository
    val allLenses: LiveData<List<Lens>>

    val leftName = MutableLiveData<String>()
    val leftPower = MutableLiveData<String>()

    val rightName = MutableLiveData<String>()
    val rightPower = MutableLiveData<String>()

    init {
        val lensDao = AppDatabase.getInstance(application).lensDao
        repository = LensRepository(lensDao)
        allLenses = repository.allLenses

        leftName.value = ""
        leftPower.value = ""

        rightName.value = ""
        rightPower.value = ""
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {

        val leftLens = Lens(productName = leftName.value, lensPower = leftPower.value, forEye = "L")
        val rightLens = Lens(productName = rightName.value, lensPower = rightPower.value, forEye = "R")
        repository.insert(leftLens)
        repository.insert(rightLens)
    }
}