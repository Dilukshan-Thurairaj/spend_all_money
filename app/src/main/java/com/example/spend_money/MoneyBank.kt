package com.example.spend_money

import android.util.Log


class MoneyBank(var totalMoney: Long) {

    private val productMap = hashMapOf<String,String>()
    //private val fixedAmount = 230000000000

    //Set Money inside the bank
//    fun setMoney(money: Long){
//        totalMoney = money
//    }

    //Get Money from the bank
    fun getMoney(): String {
        return String.format("%,d",totalMoney)
    }

    fun buyProduct(product: Product, itemCount: String) {
        val cost = product.price.toLong() * itemCount.toLong()
        //Product map is a hashmap that has the product price has key and the editText input as the value
        if (productMap.keys.contains(product.price)){
            Log.d("Inside Money Bank",productMap[product.price].toString())
            val change = product.price.toLong() * productMap[product.price]?.toLong()!!
            totalMoney += change
        }
        productMap[product.price] = itemCount
        totalMoney -= cost
    }

//    fun sellProduct(product: Product, editText: EditText) {
//        val setCount = editText.text.toString()
//        val addition = product.price.toLong() * setCount.toLong()
//
//        totalMoney +=
//            if( (totalMoney+addition) > fixedAmount){
//                val balanceProducts = (fixedAmount - totalMoney) / product.price.toLong()
//                editText.setText(balanceProducts.toInt().toString())
//                (balanceProducts * product.price.toLong())
//            }
//            else{
//                addition
//            }
//
//
//    }

}