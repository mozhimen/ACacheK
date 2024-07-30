package com.mozhimen.cachek.room.temps

import com.mozhimen.cachek.basic.bases.BaseCacheKVarPropertyFloat
import com.mozhimen.cachek.basic.bases.BaseCacheKVarPropertySyncFloat
import com.mozhimen.cachek.room.CacheKRM


/**
 * @ClassName CacheKRMDelegateFloat
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/3/13 15:17
 * @Version 1.0
 */
class CacheKRMVarPropertyFloat(
    cacheKRMProvider: CacheKRM, key: String, default: Float = 0f
) : BaseCacheKVarPropertySyncFloat<CacheKRM>(cacheKRMProvider, key, default)