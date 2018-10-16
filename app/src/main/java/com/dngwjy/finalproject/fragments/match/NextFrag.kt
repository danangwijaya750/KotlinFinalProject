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
import com.dngwjy.finalproject.presenters.match.NextMatchPres
import com.dngwjy.finalproject.views.match.NextMatchFragView
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh

class NextFrag : Fragment(), NextMatchFragView{
    var data:MutableList<Event> = mutableListOf()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: EventAdapter
    lateinit var presenter: NextMatchPres
    lateinit var spinner: Spinner
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var pgBar: ProgressBar
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

    lateinit var rootView:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView=inflater.inflate(R.layout.nextmatch_frag,container,false)
        init()
        return rootView
    }

    companion object {
        /**
         * new instance pattern for fragment
         */

        @JvmStatic
        fun newInstance(): NextFrag {
            val nextFrag = NextFrag()
            val args = Bundle()
            nextFrag.arguments = args
            return nextFrag
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Fragment>.onCreate(savedInstanceState)
        retainInstance=false
    }

    fun init(){
        swipeRefreshLayout=rootView.findViewById(R.id.swiping)
        val spinnerItems=resources.getStringArray(liga)
        val spinnerAdapter=ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item,spinnerItems)
        val gson=Gson()
        val request=ApiRequest()
        pgBar=rootView.findViewById(R.id.pgBars)
        presenter= NextMatchPres(this, request, gson)
        //presenter.getLiga()
        spinner=rootView.findViewById(R.id.spinnerLiga)
        spinner.adapter=spinnerAdapter
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var x:String=spinner.selectedItem.toString().substring(0,4)
                Log.d("selected liga",x)
                Log.d("asd",spinner.selectedItem.toString())
                presenter.getData(x)
            }
        }
        swipeRefreshLayout.onRefresh {
            presenter.getData(spinner.selectedItem.toString().substring(0,4))
        }
        recyclerView=rootView.findViewById(R.id.recNext)
        adapter= EventAdapter(this.context!!,data){
	        DetailMatch.caller="match"
            val intent = Intent(this.context,DetailMatch::class.java)
            intent.putExtra("idMatch",it)
            startActivity(intent)
        }
        val linMan=LinearLayoutManager(this.context)
        linMan.orientation=LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=linMan
        recyclerView.adapter=adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance=true
        init()
    }
}