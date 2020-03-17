package jp.ne.naokiur.lenstimer.databases.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "lenses", indices = [Index(value = ["product_name", "lens_power", "for_eye"], unique = true)])
data class Lens(
    @PrimaryKey(autoGenerate = true)val id: Long = 0L,
    @ColumnInfo(name = "product_name") val productName: String?,
    @ColumnInfo(name = "lens_power") val lensPower: String?,
    @ColumnInfo(name = "for_eye") val forEye: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)