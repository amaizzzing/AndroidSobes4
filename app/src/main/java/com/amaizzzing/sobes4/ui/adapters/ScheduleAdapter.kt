package com.amaizzzing.sobes4.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amaizzzing.sobes4.ClassesApp
import com.amaizzzing.sobes4.R
import com.amaizzzing.sobes4.data.services.IImageLoader
import com.amaizzzing.sobes4.databinding.ScheduleItemBinding
import com.amaizzzing.sobes4.setGone
import com.amaizzzing.sobes4.setVisible
import com.amaizzzing.sobes4.ui.models.ClassesModel

class ScheduleAdapter(
    private val imageLoader: IImageLoader<ImageView>,
    var scheduleList: List<ClassesModel>,
    private val skypeClick:(() -> Unit)? = null
): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ScheduleItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun setImage(image: String) {
            val identifer = ClassesApp.instance.resources.getIdentifier(
                image,
                "drawable",
                ClassesApp.instance.packageName
            )
            imageLoader.loadIntoWithRoundCornersFromResource(identifer, binding.imageClassSchedule, 24)
        }
        fun setNameClasses(name: String) {
            binding.nameClassSchedule.text = name
        }
        fun setTime(time: String) {
            binding.timeScheduleItem.text = time
        }
        fun setSkype(isSkype: Boolean) {
            if (isSkype) {
                binding.skypeLayoutSchedule.setVisible()
            } else {
                binding.skypeLayoutSchedule.setGone()
            }
        }

        fun setTeacher(teacher: String) {
            binding.teacherNameSchedule.text = teacher
        }

        fun setDescription(description: String) {
            binding.descriptionSchedule.text = description
        }

        fun setBackgroundColor(isExtra: Boolean) {
            if (isExtra) {
                binding.mainLayoutSchedule.background =
                    binding.mainLayoutSchedule.context.resources.getDrawable(R.drawable.round_corners_sport)
                binding.skypeLayoutSchedule.setGone()
            }
        }

        fun setCircle(big: Boolean) {
            imageLoader.loadIntoWithRoundCornersFromResource(
                ClassesApp.instance.resources.getIdentifier(
                    if (big)"circle_big" else "circle_small",
                    "drawable",
                    ClassesApp.instance.packageName
                ),
                binding.scheduleCircleImage,
                24
            )
        }

        fun setSkypeClick() {
            binding.skypeLayoutSchedule.setOnClickListener {
                skypeClick?.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder =
        ViewHolder(
            ScheduleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        with(scheduleList[position]) {
            holder.apply {
                setImage(image)
                setNameClasses(name)
                setTime(time)
                setSkype(canSkype)
                setTeacher(teacher)
                setDescription(description)
                setBackgroundColor(isExtra)
                setCircle(position == 0)
                setSkypeClick()
            }
        }
    }

    override fun getItemCount(): Int = scheduleList.size
}