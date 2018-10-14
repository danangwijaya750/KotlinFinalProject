package com.dngwjy.finalproject.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Spinner
import com.dngwjy.finalproject.R
import com.dngwjy.finalproject.fragments.match.NextFrag
import com.dngwjy.finalproject.fragments.match.PastFrag
import com.dngwjy.finalproject.views.MatchFragmentView
import org.jetbrains.anko.support.v4.ctx

class MatchFrag: Fragment(), MatchFragmentView {
    override fun changeFragment(fragment: Fragment) {
            val trans= this.fragmentManager!!.beginTransaction()
            trans.replace(R.id.frameMatch,fragment)
            trans.addToBackStack(null)
            trans.commit()
    }

    override fun Loading() {

    }

    override fun finishLoading() {

    }

    override fun init() {
    spinnerMatch=rootView.findViewById(R.id.spinnerMatch)
        val spinnerItem=resources.getStringArray(R.array.match)
        val spinnerAdapter= ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item,spinnerItem)
    spinnerMatch.adapter=spinnerAdapter
        spinnerMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
             val selected:String = spinnerMatch.selectedItem.toString()
                if(selected.equals("Past Match")){
                    changeFragment(PastFrag.newInstance())
                    Log.d("change Frag","frag now $selected")
                }else if(selected.equals("Next Match")){
                    changeFragment(NextFrag.newInstance())
                    Log.d("change Frag","frag now $selected")
                }
            }

        }
        frameDeploy=rootView.findViewById(R.id.frameMatch)
    }

    companion object {
        @JvmStatic
        fun newInstance(): MatchFrag {
            val matchFrag = MatchFrag()
            val args = Bundle()
            matchFrag.arguments = args
            return matchFrag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)
        init()
    }
    lateinit var rootView:View
    lateinit var spinnerMatch:Spinner
    lateinit var frameDeploy:FrameLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.match_frag,container,false)
        init()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance=true
        init()
    }

}