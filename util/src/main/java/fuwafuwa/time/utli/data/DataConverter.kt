package fuwafuwa.time.utli.data

import fuwafuwa.time.utli.number.round
import kotlin.math.pow

object DataConverter {

    private const val CONVERSION_RATE = 1_000.0

    fun convertBytesToNearestType(bytes: Long): DataUnit {
        return when {
            bytes < CONVERSION_RATE -> {
                DataUnit(
                    bytes,
                    "bytes"
                )
            }

            bytes < CONVERSION_RATE.pow(2) -> {
                DataUnit(
                    (bytes / CONVERSION_RATE).round(1),
                    "KB"
                )
            }

            bytes < CONVERSION_RATE.pow(3) -> {
                DataUnit(
                    (bytes / CONVERSION_RATE.pow(2)).round(1),
                    "MB"
                )
            }

            else -> DataUnit(
                (bytes / CONVERSION_RATE.pow(3)).round(1),
                "GB"
            )
        }

    }
}
