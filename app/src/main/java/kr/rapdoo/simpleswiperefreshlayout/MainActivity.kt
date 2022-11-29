package kr.rapdoo.simpleswiperefreshlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kr.rapdoo.simpleswiperefreshlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val FRUIT_SIZE = 35

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var adapter: FruitsAdapter

    lateinit var arrFruits: ArrayList<Fruits>
    private var isDesc = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        Logger.addLogAdapter(AndroidLogAdapter())

        arrFruits = ArrayList()

        itemAdd(isDesc)
        adapter = FruitsAdapter(arrFruits)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(this, R.color.swipe_color_1),
            ContextCompat.getColor(this, R.color.swipe_color_2),
            ContextCompat.getColor(this, R.color.swipe_color_3),
            ContextCompat.getColor(this, R.color.swipe_color_4),
        )
        binding.swipeRefreshLayout.setOnRefreshListener {
            Logger.d("setOnRefreshListener")
            isDesc = !isDesc
            Logger.d("isDesc: $isDesc")
            itemAdd(isDesc)
            adapter.notifyDataSetChanged()

            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun itemAdd(isDesc: Boolean) {
        val kor = resources.getStringArray(R.array.kor_fruits)
        val eng = resources.getStringArray(R.array.eng_fruits)
        Logger.d("isDesc: $isDesc")

        arrFruits.clear()

        if(!isDesc) {
            for(i in 0 until FRUIT_SIZE) {
                arrFruits.add(Fruits(kor[i], eng[i]))
            }
        } else {
            for(i in FRUIT_SIZE - 1 downTo  0) {
                Logger.d("$i ${kor.size} ${eng.size}")
                arrFruits.add(Fruits(kor[i], eng[i]))
            }
        }
    }
}