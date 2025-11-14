package com.example.foodigo.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodigo.DetailsActivity
import com.example.foodigo.MainActivity
import com.example.foodigo.databinding.MenuItemBinding


class MenuAdapter (private val menuItemsName: MutableList<String>, private val menuItemPrice: MutableList<String>, private val menuImages: MutableList<Int>,private val requireContext: Context): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private val itemClickListener: OnClickListener?=null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuViewHolder {

        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MenuViewHolder,
        position: Int
    ) {

        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItemsName.size

    inner class MenuViewHolder(private val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position=adapterPosition
                if (position!= RecyclerView.NO_POSITION){
                    itemClickListener?.onItemClick(position)
                }
                val intent = Intent(requireContext, DetailsActivity::class.java)
                intent.putExtra("menuItemName",menuItemsName.get(position))
                intent.putExtra("menuItemImage",menuImages.get(position))
                requireContext.startActivity(intent)
            }
        }
        fun bind(position: Int) {
            binding.apply{
                menuFoodName.text= menuItemsName[position]
                menuPrice.text=menuItemPrice[position]
                menuImage.setImageResource(menuImages[position])


            }

        }

    }
    interface  OnClickListener{
        fun onItemClick(position: Int)

    }

}

