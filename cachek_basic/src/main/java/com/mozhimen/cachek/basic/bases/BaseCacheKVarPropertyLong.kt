package com.mozhimen.cachek.basic.bases

import com.mozhimen.cachek.basic.commons.ICacheKProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * @ClassName BaseCacheKDelegateLong
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/3/13 15:17
 * @Version 1.0
 */
open class BaseCacheKVarPropertyLong<P : ICacheKProvider>(
    private val _cacheKProvider: P,
    private val _key: String,
    private val _default: Long
) : ReadWriteProperty<Any?, Long> {
    @Volatile
    private var _field = _cacheKProvider.getLong(_key, _default)

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        return _field
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        _field = value
        _cacheKProvider.putLong(_key, value)
    }
}