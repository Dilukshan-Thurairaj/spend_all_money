package com.example.spend_money

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

    class ProductViewHolder(productView: View, btnClickListener: OnBtnClickListener, productList: ArrayList<Product>): RecyclerView.ViewHolder(productView){
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val buyBtn = itemView.findViewById<Button>(R.id.buyBtn)
        private val editText = itemView.findViewById<EditText>(R.id.setCount)

        init {
            buyBtn.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    btnClickListener.onBuyBtnClick(productList[position], editText)
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

        return ProductViewHolder(view,btnClickListener,productList)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        val dollar = holder.itemView.context.resources.getString(R.string.dollar,product.price)

        //Glide library used to load image from url
        Glide.with(holder.itemView.context)
            .load(product.image)
            .skipMemoryCache(true)
            .placeholder(R.drawable.placeholder)
            .into(holder.productImage)

        holder.productName.text = product.name
        holder.productPrice.text = dollar
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}