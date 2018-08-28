package xyz.idwangmo.lingling.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("lingling")
class LingLingConfig {
    lateinit var signature: String
    lateinit var token: String
    lateinit var openToken: String
    lateinit var strKey: String
}


class LinglingURLConfig {

    companion object {


        val URL = "http://llkmc.linglingkaimen.com:8889/cgi-bin/"

        val ADD_DEVICE = "device/addDevice/"

        val DEL_DEVICE = "device/delDevice/"

        val UPDATE_DEVICE = "device/updateDevice/"

        val QUERY_DEVICE_LIST = "device/queryDeviceList/"

        val MAKE_SDK_KEY = "key/makeSdkKey/"

        val GET_LING_LING_ID = "qrcode/getLingLingId/"

        val ADD_VISITOR_QR_CODE = "qrcode/addVisitorQrCode/"

    }
}