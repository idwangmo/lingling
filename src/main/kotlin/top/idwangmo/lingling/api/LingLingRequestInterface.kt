package top.idwangmo.lingling.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface LingLingRequestInterface {

    @FormUrlEncoded
    @POST("{action}")
    fun sendRequest(@Path("action", encoded = true) action: String, @Field("MESSAGE") message: String): Call<String>

}