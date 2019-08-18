package jp.ne.naokiur.lenstimer.models.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import jp.ne.naokiur.lenstimer.models.DBContracts
import jp.ne.naokiur.lenstimer.models.Lens

class LensDao(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(SQL_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(SQL_DELETE)
        p0?.execSQL(SQL_CREATE)
    }

    override fun onDowngrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.execSQL(SQL_DELETE)
        p0?.execSQL(SQL_CREATE)
    }

    fun insert(model: Lens): Boolean {
        val db = writableDatabase

        val values = ContentValues()

        values.put(DBContracts.LensEntity.productName, model.productName)
        values.put(DBContracts.LensEntity.lensPower, model.lensPower.toFloat())
        values.put(DBContracts.LensEntity.forEye, model.forEye)
        values.put(DBContracts.LensEntity.createdAt, model.createdAt)

        db.insert(DBContracts.LensEntity.tableName, null, values)

        return true
    }

    fun selectAll(): List<Lens> {
        val db = readableDatabase

        val cursor = db.rawQuery("SELECT * FROM ${DBContracts.LensEntity.tableName}", null)

        val result = mutableListOf<Lens>()
        while (cursor.moveToNext()) {
            val lens = Lens(
                cursor.getString(cursor.getColumnIndex(DBContracts.LensEntity.productName)),
                cursor.getFloat(cursor.getColumnIndex(DBContracts.LensEntity.lensPower)).toBigDecimal(),
                cursor.getString(cursor.getColumnIndex(DBContracts.LensEntity.forEye)),
                cursor.getLong(cursor.getColumnIndex(DBContracts.LensEntity.createdAt))
            )

            result.add(lens)
        }

        return result
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Lens.db"

        private const val SQL_CREATE = """
           CREATE TABLE ${DBContracts.LensEntity.tableName} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DBContracts.LensEntity.productName} TEXT,
                ${DBContracts.LensEntity.lensPower} REAL,
                ${DBContracts.LensEntity.forEye} TEXT,
                ${DBContracts.LensEntity.createdAt} INTEGER
           ) 
        """

        private const val SQL_DELETE = """
            DROP TABLE IF EXISTS ${DBContracts.LensEntity.tableName}
        """
    }
}