package com.example.a12_mavzu_draganddrop

import android.content.ClipData.Item
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.a12_mavzu_draganddrop.adapter.UserAdapter
import com.example.a12_mavzu_draganddrop.databinding.ActivityMainBinding
import com.example.a12_mavzu_draganddrop.models.User

class MainActivity : AppCompatActivity() {
    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    lateinit var list: ArrayList<User>
    lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        list=ArrayList()
        for (i in 0 until 100 ){
            list.add(User("Bexruz", i.toString()))
        }
        userAdapter = UserAdapter(list)
        binding.rv.adapter = userAdapter

        val itemtochethelper = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               userAdapter.onItemDis(viewHolder.adapterPosition)

            }

        }

        val  itemtoch = ItemTouchHelper(itemtochethelper)
        itemtoch.attachToRecyclerView(binding.rv)


    }
}