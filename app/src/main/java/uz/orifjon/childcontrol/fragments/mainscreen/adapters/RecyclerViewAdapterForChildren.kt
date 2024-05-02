package uz.orifjon.childcontrol.fragments.mainscreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.orifjon.childcontrol.databinding.ItemChildBinding
import uz.orifjon.childcontrol.models.ChildrenForFirebase

class RecyclerViewAdapterForChildren(val onItemClick:(ChildrenForFirebase, Int)->Unit) :
    ListAdapter<ChildrenForFirebase, RecyclerViewAdapterForChildren.MyViewHolder>(MyDiffUtils()) {


    inner class MyViewHolder(var binding: ItemChildBinding) : ViewHolder(binding.root) {
        fun onBind(childrenForFirebase: ChildrenForFirebase,position: Int) {
            binding.tvName.text = childrenForFirebase.name
            binding.tvUserName.text = childrenForFirebase.userName
            itemView.setOnClickListener { onItemClick(childrenForFirebase,position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position),position)
    }

    class MyDiffUtils : DiffUtil.ItemCallback<ChildrenForFirebase>() {
        override fun areItemsTheSame(
            oldItem: ChildrenForFirebase,
            newItem: ChildrenForFirebase
        ): Boolean {
            return oldItem.childId == newItem.childId
        }

        override fun areContentsTheSame(
            oldItem: ChildrenForFirebase,
            newItem: ChildrenForFirebase
        ): Boolean {
            return oldItem == newItem
        }

    }

}