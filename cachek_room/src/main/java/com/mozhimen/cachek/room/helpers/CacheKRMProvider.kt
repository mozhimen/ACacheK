package com.mozhimen.cachek.room.helpers

import com.mozhimen.cachek.basic.commons.ICacheKProvider
import com.mozhimen.cachek.room.commons.CacheKRMDatabase
import com.mozhimen.cachek.room.commons.ICacheKRMProvider
import com.mozhimen.cachek.room.mos.MCacheKRM2
import com.mozhimen.kotlin.elemk.kotlin.cons.CSuppress
import com.mozhimen.kotlin.utilk.android.util.e
import com.mozhimen.kotlin.utilk.bases.BaseUtilK
import com.mozhimen.kotlin.utilk.kotlin.UtilKT
import com.mozhimen.kotlin.utilk.kotlin.bytes2obj

/**
 * @ClassName CacheKRMProvider
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/10/9
 * @Version 1.0
 */
class CacheKRMProvider(private val _rmName: String) : ICacheKRMProvider, BaseUtilK() {
    override fun <T> putObj(key: String, obj: T) {
        val cache = MCacheKRM2(_rmName, key, UtilKT.t2bytes(obj) ?: run { "serialize fail!".e(TAG);return })
        CacheKRMDatabase.cacheKDao2().saveCache(cache)
    }

    override fun putString(key: String, value: String) {
        putObj(key, value)
    }

    override fun putBoolean(key: String, value: Boolean) {
        putObj(key, value)
    }

    override fun putInt(key: String, value: Int) {
        putObj(key, value)
    }

    override fun putLong(key: String, value: Long) {
        putObj(key, value)
    }

    override fun putFloat(key: String, value: Float) {
        putObj(key, value)
    }

    override fun putDouble(key: String, value: Double) {
        putObj(key, value)
    }

    override fun putStringSet(key: String, value: Set<String>) {
        putObj(key, value.joinToString { it })
    }

    /////////////////////////////////////////////////////////////////////

    @Suppress(CSuppress.UNCHECKED_CAST)
    override fun <T> getObj(key: String, default: T): T {
        val cache = CacheKRMDatabase.cacheKDao2().getCache(_rmName,key)
        return ((if (cache?.data != null) cache.data.bytes2obj() else null) as? T?) ?: default
    }

    override fun getInt(key: String): Int =
        getInt(key, 0)

    override fun getLong(key: String): Long =
        getLong(key, 0L)

    override fun getString(key: String): String =
        getString(key, "")

    override fun getBoolean(key: String): Boolean =
        getBoolean(key, false)

    override fun getFloat(key: String): Float =
        getFloat(key, 0f)

    override fun getDouble(key: String): Double =
        getDouble(key, 0.0)

    override fun getStringSet(key: String): Set<String> =
        getStringSet(key, emptySet())

    /////////////////////////////////////////////////////////////////////

    override fun getInt(key: String, defaultValue: Int): Int =
        getObj(key, defaultValue)

    override fun getLong(key: String, defaultValue: Long): Long =
        getObj(key, defaultValue)

    override fun getString(key: String, defaultValue: String): String =
        getObj(key, defaultValue)

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        getObj(key, defaultValue)

    override fun getFloat(key: String, defaultValue: Float): Float =
        getObj(key, defaultValue)

    override fun getDouble(key: String, defaultValue: Double): Double =
        getObj(key, defaultValue)

    override fun getStringSet(key: String, defaultValue: Set<String>): Set<String> {
        return try {
            val strSet: String = getObj(key, "")
            if (strSet.isNotEmpty()) {
                strSet.split(",").toSet()
            } else
                defaultValue
        } catch (e: Exception) {
            defaultValue
        }
    }

    /////////////////////////////////////////////////////////////////////

    fun contains(key: String): Boolean =
        CacheKRMDatabase.cacheKDao2().getCache(_rmName,key) != null

    fun remove(key: String) {
        if (contains(key))
            CacheKRMDatabase.cacheKDao2().deleteCacheByKey(_rmName,key)
    }

    override fun clear() {
        CacheKRMDatabase.cacheKDao2().deleteAllCaches(_rmName)
    }
}