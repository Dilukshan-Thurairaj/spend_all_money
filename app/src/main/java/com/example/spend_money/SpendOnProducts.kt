package com.example.spend_money

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class SpendOnProducts : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var productList: ArrayList<Product>
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spend_on_products)

        val money = resources.getString(R.string.money_to_spend,"230,000,000,000")
        val tv = findViewById<TextView>(R.id.fullMoney)

        tv.text = money
        productView()
    }


    private fun productView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productList = ArrayList()
        addProductsToList()

        productAdapter = ProductAdapter(productList, object : OnBtnClickListener {
            override fun onBuyBtnClick(product: Product, editText: EditText) {
                val itemCount = editText.text.toString()
                Log.d("MyTag","Buying Item - Position=$product - ItemCount = $itemCount")
            }

            override fun onSellBtnClick(product: Product, editText: EditText) {
                val itemCount = editText.text.toString()
                Log.d("MyTag", "Selling item - Position=$product - ItemCount = $itemCount")
            }
        })
        recyclerView.adapter = productAdapter
    }

    private fun addProductsToList() {

        val jsonProducts = applicationContext.resources.openRawResource(
            R.raw.products
        ).bufferedReader().use {
            it.readText()
        }

        val output = JSONObject(jsonProducts)

        val jsonArray: JSONArray = output.getJSONArray("products")

        for (i in 0 until jsonArray.length()) {
            val product: JSONObject = jsonArray[i] as JSONObject
            val productName = product["productName"] as String
            val productPrice = product["productPrice"] as String
            val productImage = product["image"] as String

            productList.add(
                Product(name = productName, price = productPrice, image = productImage)
            )
        }
    }
}