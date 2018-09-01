package top.idwangmo.lingling.model.response

data class LinglingBaseResponse(var statusCode: String?, var methodName: String?)

data class LinglingErrorResponse(var statusCode: String?, var methodName: String?, var responseResult: String?)

data class LingLingResponse(var statusCode: String?, var methodName: String?, var responseResult: ResponseResult?)

data class ResponseResult(
        var deviceId: Long?,
        var sdkKey: String?,
        var linglingId: String?,
        var codeId: Long?,
        var qrcodekey: String?,
        var isOnline: Int?,
        var deviceCode: String?,
        var lingLingIds: List<String>
)

data class LinglingDeviceQueryResponse(
        var statusCode: String?,
        var methodName: String?,
        var responseResult: List<ResponseResult>
)

data class LinglingLogQueryResponse(
        var statusCode: String?,
        var methodName: String?,
        var responseResult: List<LinglingLogQueryResponseBody>
)

data class LinglingLogQueryResponseBody(
        var total: Long,
        var rows: List<String>,
        var deviceId: Long,
        var openTime: Long,
        var openType: Long,
        var linglingId: String?
)

data class LinglingAddOpenDoorCard(
        var cardUids: List<String>,
        var deviceIds: List<String>,
        var endTime: Long
)
