package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

class ItemAdapter (private val context: Context, private val dataset: List<Affirmation>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()
{

    // 각 data item에 대한 view reference 제공
    // 복잡한 data items 이라면 아이템 하나에 view 여러개가 있을 수도.
    //뷰홀더에서 모든 data item에 대한 접근 제공
    // 각 data item은 여기서 Affirmation 객체이다.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        //item.xml 레이아웃으로 이루어진 형태로 만든다
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    //뷰의 컨텐츠를 대체. 레이아웃 매니저에 의해 호출됨
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position] //position 변수가 현재 항목을 나타냄.
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    //데이터셋의 사이즈 반환
    override fun getItemCount(): Int {
        return dataset.size
    }
}