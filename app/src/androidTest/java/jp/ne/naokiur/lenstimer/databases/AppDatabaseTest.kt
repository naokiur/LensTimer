package jp.ne.naokiur.lenstimer.databases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import jp.ne.naokiur.lenstimer.databases.daos.LensDao
import jp.ne.naokiur.lenstimer.databases.tables.Lens
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

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
        println("result:")
        println(result)
        assertEquals(lens.productName, result.blockingObserve()?.get(0)?.productName)
        assertEquals(lens.lensPower, result.blockingObserve()?.get(0)?.lensPower)
        assertEquals(lens.forEye, result.blockingObserve()?.get(0)?.forEye)
    }

    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        val observer = Observer<T> {t ->
            value = t
            latch.countDown()
        }

        observeForever(observer)

        latch.await(2, TimeUnit.SECONDS)
        return value
    }
}
