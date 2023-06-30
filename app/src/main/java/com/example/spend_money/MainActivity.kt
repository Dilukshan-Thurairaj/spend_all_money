package com.example.spend_money

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.mainBtn)

        btn.setOnClickListener {
            val gridIntent = Intent(this,SpendOnProducts::class.java)
            startActivity(gridIntent)
        }
    }
}

//        val tv: TextView = findViewById<TextView>(R.id.JsonText)
//        val productStringBuilder = StringBuilder()
//
//        val jsonProducts = applicationContext.resources.openRawResource(
//            R.raw.products
//        ).bufferedReader()
//
//        var eachLine: String? = jsonProducts.readLine()
//
//        while (eachLine != null) {
//            productStringBuilder.append(eachLine + "\n")
//            eachLine = jsonProducts.readLine()
//        }
//
//        val returnBuilder = parseJson(productStringBuilder)
//
//        tv.text = returnBuilder
//    }
//
//    private fun parseJson(productBuilder:StringBuilder): String {
//
//        val productNameBuilder = StringBuilder()
//        val json = JSONObject(productBuilder.toString())
//
//        val jsonArray: JSONArray = json.getJSONArray("products")
//
//        for(i in 0 until jsonArray.length()){
//            val product: JSONObject = jsonArray[i] as JSONObject
//
//            val productName = product["productName"] as String
//            val productPrice = product["productPrice"] as String
//            val productImageUrl = product["image"]
//
//            productNameBuilder.append(productName+"\n")
//            productNameBuilder.append(productPrice+"\n")
//            productNameBuilder.append(productImageUrl)
//            productNameBuilder.append("\n\n")
//        }
//        return productNameBuilder.toString()
//    }

//    val jsonProducts = applicationContext.resources.openRawResource(
//        R.raw.products
//    ).bufferedReader().use {
//        it.readText()
//    }
//
//    val output = JSONObject(jsonProducts)
//
//    val jsonArray: JSONArray = output.getJSONArray("products")
//
//    for(i in 0 until jsonArray.length()){
//
//        val product: JSONObject = jsonArray[i] as JSONObject
//
//        val productName = product["productName"] as String
//
//        tv.text = productName
//    }
// }