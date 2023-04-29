package catmoe.fallencrystal.cometcore.utils.database

import java.util.*

class QueryResult(resultList: List<Map<String, Any>>?) {
    val resultList: List<Map<String, Any>>

    init {
        this.resultList = resultList ?: ArrayList()
    }

    fun getResult(row: Int): Map<String, Any>? {
        return try {
            resultList[row]
        } catch (none: Exception) {
            null
        }
    }

    fun getResult(row: Int, key: String): Any? {
        return try { Objects.requireNonNull(getResult(row))?.get(key) } catch (none: Exception) { null }
    }

    companion object {
        fun isTrue(resultValue: Any?): Boolean {
            return if (resultValue != null) {
                if (resultValue is Boolean) { resultValue } else { "1" == resultValue.toString() }
            } else {
                false
            }
        }
    }
}