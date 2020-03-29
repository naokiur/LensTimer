package jp.ne.naokiur.lenstimer.repositories

import androidx.lifecycle.LiveData
import jp.ne.naokiur.lenstimer.databases.daos.LensDao
import jp.ne.naokiur.lenstimer.databases.tables.Lens

class LensRepository(private val lensDao: LensDao) {
    val allLenses: LiveData<List<Lens>> = lensDao.getAll()
}