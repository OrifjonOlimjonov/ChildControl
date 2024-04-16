package uz.orifjon.childcontrol.models.local.converters

import androidx.room.TypeConverter


class PairConverter {
    @TypeConverter
    fun fromPair(pair: Pair<Double, Double>): String {
        return "${pair.first}:${pair.second}"
    }

    @TypeConverter
    fun toPair(value: String): Pair<Double, Double> {
        val parts = value.split(":")
        return Pair(parts[0] as Double, parts[1] as Double)
    }
}