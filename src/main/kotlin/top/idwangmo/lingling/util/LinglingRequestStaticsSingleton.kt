package top.idwangmo.lingling.util

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import top.idwangmo.lingling.config.LinglingURLConfig

class LinglingRequestStaticsSingleton private constructor() {

    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = Retrofit.Builder().baseUrl(LinglingURLConfig.URL).addConverterFactory(ScalarsConverterFactory.create()).build();
    }

}