package com.example.cv_builder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
// Inflate the Layout to set in the RecyclerView and return the ViewHolder object
class MyAdapter(var jlist: ArrayList<Job>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return MyViewHolder(view)
    }
/* Sets the contents of an item at a given position in the RecyclerView.
 Called by RecyclerView to display the data at a specified position over and over.
*/

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.itemView.name.text = jlist[position].title
        holder.itemView.author.text = jlist[position].description
        if(position == 0){
            holder.itemView.name.setBackgroundColor(Color.GRAY)
            holder.itemView.author.setBackgroundColor(Color.GRAY)
        }

    }
    // return Size of the list of data.
    override fun getItemCount(): Int {
        return jlist.size
    }
    /*RecyclerView.Adapter accepts the generic type of your Adapter inner class ViewHolder type.
 In this example Adapter class name is MyAdapter and the MyViewHolder  is the inner class */

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}