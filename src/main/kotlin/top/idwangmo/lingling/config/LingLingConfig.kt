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

        val ADD_OWNER_QR_CODE = "qrcode/addOwnerQrCode/"

        val SELECT_OPEN_DOOR_LOG = "openDoorLog/selectOpenDoorLog"

        val ADD_OPEN_DOOR_CARD = "openDoorCard/addOpenDoorCard"

        val DEL_OPEN_DOOR_CARD = "openDoorCard/delOpenDoorCard"

        val QUERY_OPEN_DOOR_CARD = "openDoorCard/queryOpenDoorCard"

        val UPDATE_OPEN_DOOR_CARD = "openDoorCard/updateOpenDoorCard"

        val REMOTE_OPEN_DOOR = "key/remoteOpenDoor"

    }
}