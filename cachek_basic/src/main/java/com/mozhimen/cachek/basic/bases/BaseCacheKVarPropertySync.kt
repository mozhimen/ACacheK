package com.mozhimen.cachek.basic.bases

import com.mozhimen.cachek.basic.commons.ICacheKProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * @ClassName BaseCacheKDelegateInt
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/3/13 15:17
 * @Version 1.0
 */
open class BaseCacheKVarPropertySync<P : ICacheKProvider, T> constructor(
    protected val _cacheKProvider: P,
    protected open val _default: T
) : ReadWriteProperty<Any?, T> {
    @Volatile
    protected var _field: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return synchronized(this) {
            _field ?: _cacheKProvider.getObj(property.name, _default).also { _field = it }
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        synchronized(this) {
            _field = value
            _cacheKProvider.putObj(property.name, value)
        }
    }
}