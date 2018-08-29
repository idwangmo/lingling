package top.idwangmo.lingling.config

data class LingLingConfig(
        var signature: String,
        var token: String,
        var openToken: String,
        var strKey: String
)


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