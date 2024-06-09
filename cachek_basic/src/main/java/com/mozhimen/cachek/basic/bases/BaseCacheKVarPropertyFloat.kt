package com.mozhimen.cachek.basic.bases

import com.mozhimen.cachek.basic.commons.ICacheKProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * @ClassName BaseCacheKDelegateFloat
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/3/13 15:17
 * @Version 1.0
 */
open class BaseCacheKVarPropertyFloat<P : ICacheKProvider>(
    private val _cacheKProvider: P,
    private val _key: String,
    private val _default: Float = 0f
) : ReadWriteProperty<Any?, Float> {
    @Volatile
    private var _field = _cacheKProvider.getFloat(_key, _default)

    override fun getValue(thisRef: Any?, property: KProperty<*>): Float {
        return _field
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
        _field = value
        _cacheKProvider.putFloat(_key, value)
    }
}