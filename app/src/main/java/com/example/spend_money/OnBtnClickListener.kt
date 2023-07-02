package com.example.spend_money

import android.widget.EditText

interface OnBtnClickListener {
    fun onBuyBtnClick(product: Product, editText: EditText, editTextMap: HashMap<Int, String>)
    //fun onSellBtnClick(product: Product, editText: EditText)
}