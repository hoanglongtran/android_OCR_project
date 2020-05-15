package com.example.capstoneproject.data.source.remote

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.UnknownHostException


object RecordImageInferenceHTTPRequest {


    fun uploadImage(imageData: ByteArray): JSONObject? {
        try {
            val MEDIA_TYPE_PNG = MediaType.parse("image/png")
            val req: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(
                    "file",
                    "image.png",
                    RequestBody.create(MEDIA_TYPE_PNG, imageData)
                ).build()
            val request = Request.Builder()
                .url("http://10.0.2.2:5000/")
                .post(req)
                .build()
            val client = OkHttpClient()
            client.newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call?, e: IOException?) {
                        // Error
                        /*UiThreadStatement.runOnUiThread(Runnable {
                            // For the example, you can show an error dialog or a toast
                            // on the main UI thread
                        })*/
                    }

                    @Throws(IOException::class)
                    override fun onResponse(call: Call?, response: Response) {
                        val res = response.body()!!.string()
                        Log.d("response", "uploadImage:$res")
                        // Do something with the response
                    }
                })
            //val response = client.newCall(request).execute()

            //return JSONObject(response.body()!!.string())
        } catch (e: UnknownHostException) {
            Log.e(TAG, "Error: " + e.getLocalizedMessage())
        } catch (e: UnsupportedEncodingException) {
            Log.e(TAG, "Error: " + e.getLocalizedMessage())
        }/* catch (e: Exception) {
            Log.e(TAG, "Other Error: " + e.localizedMessage)
        }*/
        return null
    }



        private const val TAG = "RecordsPresenter"

}