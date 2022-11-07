package com.hassan.hanoykotlin.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hassan.hanoykotlin.adapters.TableAdapter
import com.hassan.hanoykotlin.databinding.ActivityMainBinding
import com.hassan.hanoykotlin.interfaces.IRequestListener
import com.hassan.hanoykotlin.models.Item
import com.hassan.hanoykotlin.models.Table
import com.hassan.hanoykotlin.network.VolleyManager
import com.hassan.hanoykotlin.utils.Constants.Companion.API_URL
import com.hassan.hanoykotlin.utils.Constants.Companion.DATA_CRITERIA1
import com.hassan.hanoykotlin.utils.Constants.Companion.DATA_CRITERIA2
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BasicActivity(), IRequestListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mTables: ArrayList<Table>
    private lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initValues() {
        super.initValues()
        this.mTables = ArrayList()
        this.mProgressDialog = ProgressDialog(this)
        this.mProgressDialog.setMessage("Please wait")
        this.mProgressDialog.setCancelable(false)
        this.mProgressDialog.show()
        VolleyManager.getInstance(this).sendApiCall(API_URL, this)
    }

    override fun initValuesInViews() {
        super.initValuesInViews()
        val date = Date()
        val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        binding.date.text = format.format(date)
    }

    override fun onResponse(response: JSONObject) {
        try {
            val mItemsBetStock = arrayListOf<Item>()
            val mItemsBetGov = arrayListOf<Item>()
            mProgressDialog.dismiss()
            val data: JSONArray = response.getJSONArray("data")
            for (i in 0 until data.length()) {
                val jsonObject = data.getJSONObject(i)
                if (DATA_CRITERIA1.contains(jsonObject.getString("betType"))) {
                    val item = Item(
                        jsonObject.getString("name"),
                        jsonObject.getString("resultBon3"),
                        jsonObject.getString("resultLang2")
                    )
                    mItemsBetGov.add(item)
                } else if (DATA_CRITERIA2.contains(jsonObject.getString("betType"))) {
                    val item = Item(
                        jsonObject.getString("name"),
                        jsonObject.getString("resultBon3"),
                        jsonObject.getString("resultLang2")
                    )
                    mItemsBetStock.add(item)
                }
            }
            mTables.add(Table(mItemsBetGov))
            mTables.add(Table(mItemsBetStock))

            val mAdapter = TableAdapter(mTables)
            this.binding.recyclerView.adapter = mAdapter
            this.binding.recyclerView.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun onError(error: String) {
        mProgressDialog.dismiss()
        Toast.makeText(this, "Something went wrong. Please try again later.", Toast.LENGTH_LONG)
            .show()
        finish()
    }

}