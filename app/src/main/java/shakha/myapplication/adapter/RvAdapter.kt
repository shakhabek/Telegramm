package shakha.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shakha.myapplication.databinding.ItemRvBinding
import shakha.myapplication.models.Profile


class RvAdapter(var list: List<Profile>) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(private val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(srt:Profile){
            itemRvBinding.tex.text = srt.name


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


