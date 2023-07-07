package com.example.spend_money

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.*
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
        //Creating object of Money bank class
        val moneyBank = MoneyBank(230000000000)
        val tv = findViewById<TextView>(R.id.fullMoney)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productList = ArrayList()
        addProductsToList()

        productAdapter = ProductAdapter(productList, object : OnBtnClickListener {
            override fun onBuyBtnClick(product: Product, editText: EditText, editTextMap: HashMap<Int, String>) {

                val itemCount = editText.text.toString()

                if(itemCount.isNotEmpty()) {
                    Log.d("MyTag", "Buying Item - Position= - ItemCount = $editText")
                    Log.d("Money", moneyBank.getMoney().toString())

                    moneyBank.buyProduct(product, itemCount)
                    if (moneyBank.getMoney() == 0L){
                        val layout = layoutInflater.inflate(R.layout.victory_popup,findViewById(R.id.spendMoneyLayout),false)
                        val width = LinearLayout.LayoutParams.MATCH_PARENT
                        val height = LinearLayout.LayoutParams.WRAP_CONTENT

                        val victoryPopUp = PopupWindow(layout,width,height)

                        victoryPopUp.showAtLocation(layout, Gravity.CENTER,0,100)
                    }
                    if(moneyBank.getMoney() < 0){
                        moneyBank.resetRound(editTextMap)
                    }

                    tv.text = resources.getString(R.string.money_to_spend, String.format("%,d",moneyBank.getMoney()))
                    Log.d("Round", moneyBank.getRound().toString())
                    Log.d("Money", moneyBank.getMoney().toString())
                }
                else{
                    Toast.makeText(this@SpendOnProducts,"PLEASE SET NUMBER OF ITEMS",Toast.LENGTH_SHORT).show()
                }
            }

//            override fun onSellBtnClick(product: Product, editText: EditText) {
//                Log.d("MyTag", "Selling item - Position=$product - ItemCount = ")
//
//                moneyBank.sellProduct(product, editText)
//                tv.text = resources.getString(R.string.money_to_spend,moneyBank.getMoney())
//                editText.setTextColor(resources.getColor(R.color.danger_red,resources.newTheme()))
//            }
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
                Product(name = productName,
                    price = productPrice,
                    image = productImage)
            )
        }
    }
}