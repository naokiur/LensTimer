package jp.ne.naokiur.lenstimer.databases

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import jp.ne.naokiur.lenstimer.databases.daos.LensDao
import jp.ne.naokiur.lenstimer.databases.tables.Lens
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var lensDao: LensDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        lensDao = appDatabase.lensDao
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        appDatabase.close()
    }

    @Test
    fun getLensDao() {
        val lens = Lens(productName = "sample", lensPower = "5.5", forEye = "r")
        lensDao.insert(lens)
        val result = lensDao.getAll()

        assertEquals(lens.productName, result[0].productName)
        assertEquals(lens.lensPower, result[0].lensPower)
        assertEquals(lens.forEye, result[0].forEye)
    }
}