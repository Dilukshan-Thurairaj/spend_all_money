package com.example.spend_money

import android.widget.EditText

interface OnBtnClickListener {
    fun onBuyBtnClick(product: Product, editText: EditText)
    //fun onSellBtnClick(product: Product, editText: EditText)
}