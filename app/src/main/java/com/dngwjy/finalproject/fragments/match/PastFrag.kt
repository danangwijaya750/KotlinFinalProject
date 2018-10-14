package com.dngwjy.finalproject.fragments.match

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.R.array.liga
import com.dngwjy.finalproject.activities.DetailMatch

import com.dngwjy.finalproject.api.ApiRequest
import com.dngwjy.finalproject.data.EventAdapter
import com.dngwjy.finalproject.data.models.Event
import com.dngwjy.finalproject.presenters.match.PastMatchPres
import com.dngwjy.finalproject.views.match.PastMatchFragView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.ctx
import com.dngwjy.finalproject.utils.Utils
import org.jetbrains.anko.support.v4.onRefresh

class PastFrag : Fragment(), PastMatchFragView {
    var data : MutableList<Event> = mutableListOf()
    lateinit var spinner: Spinner
    lateinit var presenter : PastMatchPres
    lateinit var adapter: EventAdapter
    lateinit var pgBar:ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    override fun Loading() {
        swipeRefreshLayout.isRefreshing=true
    }

    override fun FinishLoad() {
        swipeRefreshLayout.isRefreshing=false
    }

    override fun showData(data: List<Event>) {
        this.data.clear()
        this.data.addAll(data)
        this.adapter.notifyDataSetChanged()
    }

    companion object {
        /**
         * new instance pattern for fragment
         */

        @JvmStatic
        fun newInstance(): PastFrag {
            val pastFrag = PastFrag()
            val args = Bundle()
            pastFrag.arguments = args
            return pastFrag
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init(){
        spinner=rootView.findViewById(R.id.spinnerLiga)
        swipeRefreshLayout=rootView.findViewById(R.id.swiping)

        val spinnerItem=resources.getStringArray(liga)
        val spinnerAdapter=ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item,spinnerItem)
        spinner.adapter=spinnerAdapter
        val request=ApiRequest()
        val gson = Gson()
        pgBar=rootView.findViewById(R.id.pgBars)
        presenter= PastMatchPres(this, request, gson)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //presenter.getData(ligaSelect)
                var x:String=spinner.selectedItem.toString().substring(0,4)
                Log.d("selected liga",x)
                presenter.getData(x)
                Log.d("asd",spinner.selectedItem.toString())
               // Log.d("selected liga", liga_idItem.get(0))
            }

        }
        swipeRefreshLayout.onRefresh {
            presenter.getData(spinner.selectedItem.toString().substring(0,4))
        }
        adapter= EventAdapter(this.context!!,data){
            var intent=Intent(this.context,DetailMatch::class.java)
            intent.putExtra("idMatch",it.idEvent)
            startActivity(intent)
        }
        val linMan=LinearLayoutManager(this.context)
        linMan.orientation=LinearLayoutManager.VERTICAL
        recyclerView=rootView.findViewById(R.id.recPast)
        recyclerView.layoutManager=linMan
        recyclerView.adapter=adapter

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
    }

    var ligaSelect:String=""
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       init()
    }

    lateinit var rootView:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView=inflater.inflate(R.layout.pastmatch_frag,container,false)
        init()
        return rootView
    }
}