package jp.ne.naokiur.lenstimer.models

import android.provider.BaseColumns

object DBContracts {
    class LensEntity: BaseColumns {
        companion object {
            const val tableName = "Lens"
            const val productName = "product_name"
            const val lensPower = "lens_power"
            const val forEye = "for_eye"
            const val createdAt = "created_at"
        }
    }
}