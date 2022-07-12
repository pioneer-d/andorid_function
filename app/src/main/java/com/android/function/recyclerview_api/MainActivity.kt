package com.android.function.recyclerview_api

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.function.R
import com.android.function.api.SnaptagAPI
import com.android.function.api.get.Get
import com.android.function.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    val BaseUrl = "http://106.15.201.243/"  //중국 api test 용도

    var dataList = ArrayList<ProfileData>()
    val profileAdapter = ProfileAdapter(this)

    var image : String? = null
    var title : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        binding.activity = this@MainActivity

        setRcv()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRcv(){
        binding.mainRcv.layoutManager = LinearLayoutManager(this)
        binding.mainRcv.adapter = profileAdapter
//        profileAdapter.data = listOf(
//            ProfileData(profile = "R.drawable.dog", name = "Dong", age = 25),
//            ProfileData(profile = "R.drawable.dog", name = "Lee", age = 25)
//        )
        getApiData(1)

    }

    @SuppressLint("HardwareIds")
    fun getApiData(page : Int)  {
        var retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        var retrofitApi : SnaptagAPI = retrofit.create(SnaptagAPI::class.java)
        //retrofitApi.getData(Settings.Secure.getString(applicationContext.contentResolver,Settings.Secure.ANDROID_ID)).enqueue(Callback<Get>)

        retrofitApi.getData("36b66223-af32-4131-9a7f-3da59c7d4ee35",page).enqueue(object : Callback<Get>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Get>, response: Response<Get>) {
                Log.d("@@@@@","onRespose() 실행")
                if (response.isSuccessful){
                    var data: Get? = response.body()
                    Log.d("API Test, Size : ",data!!.getData().size.toString())
                    for (i in 0..data.getData().size-1){
                        Log.e("@@@@",i.toString())

                        image = data.getData().get(i).getProject().getBannerImage().toString()
                            ?: data.getData().get(i).getProduct().getBannerImageUrl().toString()
                            ?: data.getData().get(i).getProduct().getSourceImage().toString()

                        title = data.getData().get(i).getIndustry().getTitle() ?: "Test"
                        dataList.add(ProfileData(image!!,title!!,1))


                    }
                    profileAdapter.data = dataList
                    profileAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<Get>, t: Throwable) {
                t.stackTrace
                Log.e("error : ",t.stackTrace.toString())
                Log.e("error : ",t.message.toString())
            }

        })

    }

}