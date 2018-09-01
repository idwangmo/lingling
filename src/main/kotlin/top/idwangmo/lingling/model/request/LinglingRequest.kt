package top.idwangmo.lingling.model.request


data class LinglingRequest(var requestParam: Any?, var header: Header)

data class Header(var signature: String?, var token: String?)

data class LinglingAddDeviceRequest(
        var deviceName: String,
        var deviceCode: String
)

data class LinglingDeleteDeviceReqeuest(
        var deviceId: String
)

data class LingLingUpdateDeviceRequest(
        var deviceId: Long,
        var newDeviceName: String?,
        var newDeviceCode: String?
)

data class LinglingGenerateKeyRequest(
        var deviceIds: List<String>
)

data class LinglingBatchIdsRequest(
        var count: Long
)

data class LinglingGenerateOwnerQrCodeRequest(
        var lingLingId: String,
        var sdkKeys: List<String>,
        var endTime: Long,
        var effectNumber: Long?,
        var strKey: String
)

data class LinglingSelectOpenDoorLongRequest(
        var page: Long?,
        var rows: Long?,
        var deviceId: Long?,
        var startTime: Long?,
        var endTime: Long?,
        var lingLingId: String?
)

data class LinglingOpenDoorCardRequest(
        var cardUids: List<String>
)

data class LinglingUpdateOpenDoorCardRequest(
        var cardUids: List<String>,
        var newDeviceIds: List<String>,
        var newEndTime: Long
)

data class LinglingRemoteOpenDoorRequest(
        var sdkKey: String
)
