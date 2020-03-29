package jp.ne.naokiur.lenstimer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.ne.naokiur.lenstimer.databases.daos.LensDao

class LensViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LensViewModel::class.java)) {
            return LensViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}