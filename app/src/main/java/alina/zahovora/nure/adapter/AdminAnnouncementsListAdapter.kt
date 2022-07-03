package alina.zahovora.nure.adapter

import alina.zahovora.nure.FullInfoAnnouncementActivity
import alina.zahovora.nure.R
import alina.zahovora.nure.data.Announcement
import alina.zahovora.nure.data.Shop
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide

class AdminAnnouncementsListAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Announcement>,
) : BaseAdapter()  {

    @SuppressLint("ServiceCast")
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.announcement_list_item, parent, false)

        //Get fields for list item
        val nameTextView = rowView.findViewById(R.id.name) as TextView
        val categoryTextView = rowView.findViewById(R.id.category) as TextView
        val dateTextView = rowView.findViewById(R.id.date) as TextView
        val rentButton = rowView.findViewById(R.id.rent) as Button
        val alreadyRent = rowView.findViewById(R.id.alreadyRent) as TextView
        val image = rowView.findViewById(R.id.image) as ImageView

        //Set up values for list item
        val announcement = getItem(position) as Announcement

        nameTextView.text = announcement.name

        categoryTextView.text = announcement.category.name
        dateTextView.text = announcement.created_at.toString()

        Glide.with(rowView.context)
            .load("http://15.188.224.152/storage/images/" + announcement.photo)
            .into(image)
        rentButton.visibility = View.INVISIBLE

        return rowView
    }

}