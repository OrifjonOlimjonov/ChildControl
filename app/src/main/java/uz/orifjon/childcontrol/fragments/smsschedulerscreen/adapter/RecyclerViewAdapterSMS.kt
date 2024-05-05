package uz.orifjon.childcontrol.fragments.smsschedulerscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.orifjon.childcontrol.databinding.ItemSmsBinding
import uz.orifjon.childcontrol.models.SMS

class RecyclerViewAdapterSMS :
    ListAdapter<SMS, RecyclerViewAdapterSMS.MyViewHolder>(MyDiffUtils()) {

    inner class MyViewHolder(val item: ItemSmsBinding) : ViewHolder(item.root) {
        fun onBind(sms: SMS, position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemSmsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    class MyDiffUtils : DiffUtil.ItemCallback<SMS>() {
        override fun areItemsTheSame(oldItem: SMS, newItem: SMS): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SMS, newItem: SMS): Boolean {
            return oldItem == newItem
        }

    }
}