package com.example.roomdatabase.fragment.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.model.User
import com.example.roomdatabase.databinding.CustomRowBinding

class ItemUserAdapter(val context: Context): RecyclerView.Adapter<ItemUserAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CustomRowBinding.inflate(layoutInflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.bind(currentItem)

        holder.binding.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.binding.rowLayout.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    inner class MyViewHolder(val binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root)  {
        fun bind(user: User) {
            binding.tvId.text = user.id.toString()
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvAge.text = user.age.toString()

        }

    }
    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}
