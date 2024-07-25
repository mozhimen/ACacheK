package com.mozhimen.cachek.test

import android.os.Bundle
import com.mozhimen.cachek.room.CacheKRM
import com.mozhimen.cachek.room.temps.CacheKRMVarPropertyString
import com.mozhimen.basick.elemk.androidx.appcompat.bases.databinding.BaseActivityVDB
import com.mozhimen.basick.utilk.android.widget.showToast
import com.mozhimen.cachek.test.databinding.ActivityCachekRmBinding

class CacheKRMActivity : BaseActivityVDB<ActivityCachekRmBinding>() {
    private var _editStr: String by CacheKRMVarPropertyString(CacheKRM, "editStr", "")

    override fun initView(savedInstanceState: Bundle?) {
        vdb.cachekRmEdit.setText(_editStr)
        vdb.cachekRmBtn.setOnClickListener {
            _editStr = vdb.cachekRmEdit.text.toString()
        }
        vdb.cachekRmBtn1.setOnClickListener {
            "${CacheKRM.contains("editStr")}".showToast()
        }
    }
}