package jp.ne.naokiur.lenstimer.databases.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import jp.ne.naokiur.lenstimer.databases.tables.Lens

@Dao
interface LensDao {
    @Insert
    fun insert(lens: Lens)

    @Query("SELECT * FROM lenses")
    fun getAll(): LiveData<List<Lens>>
}