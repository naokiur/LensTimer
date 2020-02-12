package jp.ne.naokiur.lenstimer.models

import java.math.BigDecimal

class Lens(
    val productName: String,
    val lensPower: BigDecimal,
    val forEye: String,
    val createdAt: Long
)