package com.amaizzzing.sobes4.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amaizzzing.sobes4.ClassesApp
import com.amaizzzing.sobes4.data.services.IImageLoader
import com.amaizzzing.sobes4.databinding.HomeworkItemBinding
import com.amaizzzing.sobes4.ui.models.HomeworkModel

class HomeworkAdapter(
    private val imageLoader: IImageLoader<ImageView>,
    var homeworkList: List<HomeworkModel>
): RecyclerView.Adapter<HomeworkAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: HomeworkItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun setImage(image: String) {
            val identifer = ClassesApp.instance.resources.getIdentifier(
                image,
                "drawable",
                ClassesApp.instance.packageName
            )
            imageLoader.loadIntoWithRoundCornersFromResource(identifer, binding.classesImage, 24)
        }
        fun setNameClasses(name: String) {
            binding.homeworkClassesName.text = name
        }
        fun setTime(time: String, textColor: Int) {
            binding.homeworkClassesExpireDate.text = time
            binding.homeworkClassesExpireDate.setTextColor(textColor)
        }
        fun setDescription(description: String) {
            binding.homeworkClassesDescription.text = description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkAdapter.ViewHolder =
        ViewHolder(
            HomeworkItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HomeworkAdapter.ViewHolder, position: Int) {
        with(homeworkList[position]) {
            holder.setImage(image)
            holder.setNameClasses(classesName)
            holder.setTime(expires, expiresTextColor)
            holder.setDescription(description)
        }
    }

    override fun getItemCount(): Int = homeworkList.size
}