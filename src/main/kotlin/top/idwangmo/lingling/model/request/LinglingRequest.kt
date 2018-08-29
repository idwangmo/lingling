package top.idwangmo.lingling.model.request


data class LinglingRequest(var requestParam: Any?, var header: Header)

data class Header(var signature: String?, var token: String?)

data class LinglingAddDeviceRequest(
        var deviceName: String,
        var deviceCode: String
)

data class LinglingDeleteDeviceReqeust(
        var deviceId: String
)

data class LingLingUpdateDeviceRequest(
        var deviceId: Long,
        var newDeviceName: String?,
        var newDeviceCode: String?
)
