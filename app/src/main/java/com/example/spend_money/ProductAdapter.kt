package com.example.spend_money

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class ProductAdapter(
    private val productList:ArrayList<Product>,
    private val btnClickListener: OnBtnClickListener
    ): RecyclerView.Adapter<ProductAdapter.ProductViewHolder> (){

    private val editTextMap = HashMap<Int, String>()

    class ProductViewHolder(productView: View): RecyclerView.ViewHolder(productView){
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val buyBtn = itemView.findViewById<Button>(R.id.buyBtn)
        private val editText = itemView.findViewById<EditText>(R.id.setCount)

        fun bind(product: Product, editTextMap: HashMap<Int,String>, btnClickListener: OnBtnClickListener) {

            val dollar = itemView.context.resources.getString(R.string.dollar,product.price)

            //Glide library used to load image from url
            Glide.with(itemView.context)
                .load(product.image)
                .skipMemoryCache(true)
                .placeholder(R.drawable.placeholder)
                .into(productImage)

            productName.text = product.name
            productPrice.text = dollar

            val savedValue = editTextMap[adapterPosition]
            Log.d("Saved",savedValue.toString())

            //Setting back the value from hashmap
            if (savedValue != null){
                editText.setText(savedValue)
                Log.d("Please", "Work")
            }
            else {
                editText.setText("0")
                editTextMap[adapterPosition] = "0"
            }

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString() == ""){
                        editTextMap[adapterPosition] = "0"
                    }
                    else {
                        editTextMap[adapterPosition] = s.toString()
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            buyBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    btnClickListener.onBuyBtnClick(product, editText,editTextMap)
                }
            }


//            sellBtn.setOnClickListener {
//                val position = adapterPosition
//                if(position != RecyclerView.NO_POSITION){
//                    btnClickListener.onSellBtnClick(productList[position], editText)
//                }
//            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        Log.d("Position", productList[position].toString())
        holder.bind(product,editTextMap,btnClickListener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}