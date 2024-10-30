package com.example.a12_mavzu_draganddrop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a12_mavzu_draganddrop.databinding.ItemRvBinding
import com.example.a12_mavzu_draganddrop.models.User
import java.util.Collections

class UserAdapter( var list : ArrayList<User>):RecyclerView.Adapter<UserAdapter.Vh>(), ItemTouchAdapterHelper {

    inner class Vh(val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(user: User, position: Int){
            itemRvBinding.tvName.text = user.name
            itemRvBinding.tvNumber.text = user.number

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
   return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }


    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if ( fromPosition<toPosition){
            for (i in fromPosition until toPosition)
                Collections.swap(list, i, +1)
        }else{
            for (i in fromPosition downTo toPosition)
                Collections.swap(list, i, i )
        }
        notifyItemMoved(fromPosition, toPosition)

    }

    override fun onItemDis(positon: Int) {
       list.removeAt(positon)
        notifyItemRemoved(positon)
    }

}