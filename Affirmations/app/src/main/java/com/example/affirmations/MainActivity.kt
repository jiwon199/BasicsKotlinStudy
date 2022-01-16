package com.example.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //데이터는 Datasource 에 있는 loadAffirmation이라는 손수 만든 함수 호출해서 가져오자.
        val myDataset = Datasource().loadAffirmations()
        //recyclerView라는 변수를 만들고 findViewById()를 사용하여
        // 레이아웃 내에서 RecyclerView 참조를 찾는다.
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        //아까 만든 어댑터의 인스턴스를 만든다. ItemAdapter(this, myDataset)
        //이 어댑터를 선언한 recyclerView의 어댑터로 설정한다.
        recyclerView.adapter = ItemAdapter(this, myDataset)

        //recyclerView의 레이아웃 크기가 고정되어 있을때
        // 즉 컨텐츠가 변경되도 레이아웃 크기가 안바뀔때이 설정을 트루로 놓을 수 있다.
        recyclerView.setHasFixedSize(true)
    }
}