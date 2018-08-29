package top.idwangmo.lingling.model.response

data class LinglingBaseResponse(var statusCode: String?, var methodName: String?)

data class LinglingErrorResponse(var statusCode: String?, var methodName: String?, var responseResult: String?)

data class LingLingResponse(var statusCode: String?, var methodName: String?, var requestResult: RequestResult?)

data class RequestResult(
        var deviceId: Long?,
        var sdkKey: String?,
        var linglingId: String?,
        var codeId: Long?,
        var qrcodekey: String?,
        var isOnline: Int?,
        var deviceCode: String?
)

data class LinglingDeviceQueryResponse(
        var statusCode: String?,
        var methodName: String?,
        var requestResult: List<RequestResult>
)
