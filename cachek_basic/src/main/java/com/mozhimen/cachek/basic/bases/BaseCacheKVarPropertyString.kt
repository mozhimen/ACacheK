package com.mozhimen.cachek.basic.bases

import com.mozhimen.cachek.basic.commons.ICacheKProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * @ClassName BaseCacheKVarPropertyString
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/3/13 15:17
 * @Version 1.0
 */
open class BaseCacheKVarPropertyString<P : ICacheKProvider>(
    private val _cacheKProvider: P,
    private val _key: String,
    private val _default: String = ""
) : ReadWriteProperty<Any?, String> {
    private var _field = _cacheKProvider.getString(_key, _default)

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return _field
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        _field = value
        _cacheKProvider.putString(_key, value)
    }
}