package com.example.readalphabate

import android.content.res.Configuration
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var recyclerList: MutableList<RecyclerModel>
    var t1: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        setupRecycler()

        t1 = TextToSpeech(applicationContext) { status ->
            if (status != TextToSpeech.ERROR) {
                t1!!.language = Locale.JAPAN
            }
        }

        findViewById<FloatingActionButton>(R.id.fab).setSafeOnClickListener { view ->
            MyDialogUty.showInfoDialog(
                context = this,
                myDialogCallback = object : MyDialogUty.MyDialogCallback<String> {
                    override fun myDialogCallback(
                        action: String,
                        pressOk: Boolean,
                        requireData: String?
                    ) {
                        setDefaultData()
                        selected(requireData.toString())
                    }
                },
                action = "",
                title = "Enter text!",
                message = "Please Try again.",
                positive = getString(android.R.string.yes)
            )
        }
    }

    private fun selected(customInput: String) {

        var tmp = customInput

        val selectedList: ArrayList<String> = ArrayList()
        tmp?.forEach {
            selectedList.add(it.toString())
        }

//        val selectedListDistinct = selectedList.distinct()
        val selectedListDistinct = selectedList

        var recyclerListSelected: ArrayList<RecyclerModel> = ArrayList()
        selectedListDistinct.forEach {
            val aaa = recyclerList.filter { aa -> aa.alphabet == it }
            recyclerListSelected.add(aaa.single())
        }
        recyclerList.clear()
        recyclerList.addAll(recyclerListSelected)
        recyclerAdapter.notifyDataChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecycler() {
        if ((resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE)
            //tablet view
            recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(
                this,
                5,
                RecyclerView.VERTICAL,
                false
            )
        else
            //phone screen view
            recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(
                this,
                5,
                RecyclerView.VERTICAL,
                false
            )
        recyclerView.isNestedScrollingEnabled = false
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
        recyclerList = ArrayList()
        recyclerAdapter = RecyclerAdapter(recyclerList, object :
            MyRecyclerViewRowClickListener {
            override fun onRowClicked(position: Int) {
                Log.e("click", "onRowClicked: ${recyclerList[position].alphabet}")
//                startActivity(
//                    Intent(applicationContext, DetailActivity::class.java)
//                        .putExtra("id", searchList[position].id)
//                )
                t1?.speak(recyclerList[position].alphabetJapan, TextToSpeech.QUEUE_FLUSH, null)
            }
        })
        recyclerView.adapter = recyclerAdapter
        setDefaultData()
    }

    private fun setDefaultData() {
        recyclerList.clear()
        recyclerList.add(RecyclerModel("a", "エイ"))
        recyclerList.add(RecyclerModel("b", "ビー"))
        recyclerList.add(RecyclerModel("c", "シー"))
        recyclerList.add(RecyclerModel("d", "ディー"))
        recyclerList.add(RecyclerModel("e", "イー"))
        recyclerList.add(RecyclerModel("f", "エフ"))
        recyclerList.add(RecyclerModel("g", "ジー"))
        recyclerList.add(RecyclerModel("h", "エイチ"))
        recyclerList.add(RecyclerModel("i", "アイ"))
        recyclerList.add(RecyclerModel("j", "ジェイ"))
        recyclerList.add(RecyclerModel("k", "ケイ"))
        recyclerList.add(RecyclerModel("l", "エル"))
        recyclerList.add(RecyclerModel("m", "エム"))
        recyclerList.add(RecyclerModel("n", "エヌ"))
        recyclerList.add(RecyclerModel("o", "オー"))
        recyclerList.add(RecyclerModel("p", "ピー"))
        recyclerList.add(RecyclerModel("q", "キュー"))
        recyclerList.add(RecyclerModel("r", "アール"))
        recyclerList.add(RecyclerModel("s", "エス"))
        recyclerList.add(RecyclerModel("t", "ティー"))
        recyclerList.add(RecyclerModel("u", "ユー"))
        recyclerList.add(RecyclerModel("v", "ヴィー"))
        recyclerList.add(RecyclerModel("w", "ダブリュー"))
        recyclerList.add(RecyclerModel("x", "エックス"))
        recyclerList.add(RecyclerModel("y", "ワイ"))
        recyclerList.add(RecyclerModel("z", "ゼッド"))

        recyclerList.add(RecyclerModel("0", "0"))
        recyclerList.add(RecyclerModel("1", "1"))
        recyclerList.add(RecyclerModel("2", "2"))
        recyclerList.add(RecyclerModel("3", "3"))
        recyclerList.add(RecyclerModel("4", "4"))
        recyclerList.add(RecyclerModel("5", "5"))
        recyclerList.add(RecyclerModel("6", "6"))
        recyclerList.add(RecyclerModel("7", "7"))
        recyclerList.add(RecyclerModel("8", "8"))
        recyclerList.add(RecyclerModel("9", "9"))
        recyclerList.add(RecyclerModel("10", "10"))

        recyclerList.add(RecyclerModel(" ", "空白"))
        recyclerList.add(RecyclerModel("@", "@"))
        recyclerList.add(RecyclerModel("#", "#"))
        recyclerList.add(RecyclerModel("$", "$"))
        recyclerList.add(RecyclerModel("%", "%"))
        recyclerList.add(RecyclerModel("^", "^"))
        recyclerList.add(RecyclerModel("&", "&"))
        recyclerList.add(RecyclerModel("*", "*"))
        recyclerList.add(RecyclerModel("+", "+"))
        recyclerList.add(RecyclerModel("~", "~"))
        recyclerList.add(RecyclerModel("=", "="))
        recyclerList.add(RecyclerModel("\'","\'"))
//        searchList.add(SearchModel("(", "("))
//        searchList.add(SearchModel(")", ")"))
//        searchList.add(SearchModel("-", "-"))
//        searchList.add(SearchModel("`", "`"))
//        searchList.add(SearchModel("_", "_"))
//        searchList.add(SearchModel("{", "{"))
//        searchList.add(SearchModel("}", "}"))
//        searchList.add(SearchModel("[", "["))
//        searchList.add(SearchModel("]", "]"))
//        searchList.add(SearchModel("\\", "\\"))
//        searchList.add(SearchModel("|", "|"))
//        searchList.add(SearchModel(";", ";"))
//        searchList.add(SearchModel(":", ":"))
//        searchList.add(SearchModel(""","""))
//        searchList.add(SearchModel("/", "/"))
//        searchList.add(SearchModel(".", "."))
//        searchList.add(SearchModel("!", "!"))
        recyclerAdapter.notifyDataChanged()
    }
}