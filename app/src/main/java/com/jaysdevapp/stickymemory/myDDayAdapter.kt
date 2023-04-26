package com.example.allmyreview

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.jaysdevapp.stickymemory.calDate
import com.jaysdevapp.stickymemory.databinding.RecyclerDdayBinding
import com.jaysdevapp.stickymemory.dataclasses.Dday


class myDDayAdapter(private var data: ArrayList<Dday>) :
    RecyclerView.Adapter<myDDayAdapter.MyViewHolder>() {

    val TAG = "myDDayAdapter"
    private var checkedRadioButton: CompoundButton? = null
    var checkId = -1
    @SuppressLint("NotifyDataSetChanged")
    fun update(newDatas: List<Dday>) {
        data.clear()
        data.addAll(newDatas)
        notifyDataSetChanged()
    }
    // 생성된 뷰 홀더에 값 지정
    inner class MyViewHolder(val binding: RecyclerDdayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        @SuppressLint("SetTextI18n")
        fun bind(datas: Dday,position: Int) {
            binding.ddayNm.text=datas.ddayThing
            binding.ddayDate.text= calDate(datas.date)

            checkedRadioButton = binding.radioButton

            binding.radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
                checkedRadioButton?.apply { setChecked(!isChecked) }
                checkedRadioButton = buttonView.apply { setChecked(isChecked) }
                checkId = position
            }

        }

    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //val binding = RecyclerMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerDdayBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //여기~!
        val ddays = data
        holder.bind(ddays.get(position),position)

    }


    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return data.size
    }

    fun getRadioId() : Int{
        return checkId
    }

    fun getcheckData() : Dday{
        return data.get(getRadioId())
    }


}