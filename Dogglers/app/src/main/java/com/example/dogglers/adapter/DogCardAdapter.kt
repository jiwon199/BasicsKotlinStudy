/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int,
    private val dataset: List<Dog>
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {


    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // TODO: Declare and initialize all of the list item UI components
        val dog_name: TextView = view!!.findViewById(R.id.dog_name)
        val dog_age: TextView = view!!.findViewById(R.id.dog_age)
        val dog_hobby: TextView = view!!.findViewById(R.id.dog_hobby)
        val dog_image: ImageView = view!!.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {

        var adapterLayout= LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_list_item, parent, false)
        if (layout==1 || layout==2){
            adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }
        else {
            adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_list_item, parent, false)
        }



        return DogCardViewHolder(adapterLayout)
    }

    //데이터셋의 사이즈 반환
    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {

        val resources = context?.resources

        val dog = dataset[position] //position 변수가 현재 항목을 나타냄.
        holder.dog_hobby.text =  resources?.getString(R.string.dog_hobbies, dog.hobbies)
        holder.dog_image.setImageResource(dog.imageResourceId)
        holder.dog_name.text=dog.name
        holder.dog_age.text=resources?.getString(R.string.dog_age,dog.age)
    }
}
