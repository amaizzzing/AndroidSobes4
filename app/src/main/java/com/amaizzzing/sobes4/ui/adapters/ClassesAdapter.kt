package com.amaizzzing.sobes4.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amaizzzing.sobes4.ClassesApp
import com.amaizzzing.sobes4.data.services.IImageLoader
import com.amaizzzing.sobes4.databinding.ClassesMainItemBinding
import com.amaizzzing.sobes4.setGone
import com.amaizzzing.sobes4.setVisible
import com.amaizzzing.sobes4.ui.models.ClassesModel

class ClassesAdapter(
    private val imageLoader: IImageLoader<ImageView>,
    var classesList: List<ClassesModel>,
    private val skypeClick:(() -> Unit)? = null
): RecyclerView.Adapter<ClassesAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ClassesMainItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun setImage(image: String) {
            val identifer = ClassesApp.instance.resources.getIdentifier(
                image,
                "drawable",
                ClassesApp.instance.packageName
            )
            imageLoader.loadIntoWithRoundCornersFromResource(identifer, binding.imageClass, 24)
        }
        fun setNameClasses(name: String) {
            binding.nameClass.text = name
        }
        fun setTime(time: String) {
            binding.scheduleText.text = time
        }
        fun setSkype(isSkype: Boolean) {
            if (isSkype) {
                binding.skypeLayout.setVisible()
            } else {
                binding.skypeLayout.setGone()
            }
        }
        fun setSkypeClick() {
            binding.skypeLayout.setOnClickListener {
                skypeClick?.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ClassesMainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(classesList[position]) {
            holder.setImage(image)
            holder.setNameClasses(name)
            holder.setTime(time)
            holder.setSkype(canSkype)
            holder.setSkypeClick()
        }
    }

    override fun getItemCount(): Int = classesList.size
}