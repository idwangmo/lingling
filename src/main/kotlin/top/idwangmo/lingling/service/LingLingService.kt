package top.idwangmo.lingling.service

import com.google.gson.Gson
import top.idwangmo.lingling.api.LingLingRequestInterface
import top.idwangmo.lingling.config.LingLingConfig
import top.idwangmo.lingling.config.LinglingURLConfig
import top.idwangmo.lingling.exception.LingLingException
import top.idwangmo.lingling.model.request.*
import top.idwangmo.lingling.model.response.*
import top.idwangmo.lingling.util.LinglingRequestStaticsSingleton


class LingLingService {

    lateinit var lingLingConfig: LingLingConfig

    /**
     * 获取令令Id
     */
    val linglingId: String?
        get() {
            val body = this.sendRequest(Any(), LinglingURLConfig.GET_LING_LING_ID)
            val response = gson().fromJson<LingLingResponse>(body, LingLingResponse::class.java)
            return response.responseResult?.linglingId
        }

    /**
     * 添加设备
     */
    fun addDevice(request: LinglingAddDeviceRequest): Long? {
        val body = this.sendRequest(request, LinglingURLConfig.ADD_DEVICE)
        val response = gson().fromJson<LingLingResponse>(body, LingLingResponse::class.java)
        return response.responseResult?.deviceId
    }

    /**
     * 删除设备
     */
    fun deleteDevice(request: LinglingDeleteDeviceReqeuest) {
        this.sendRequest(request, LinglingURLConfig.DEL_DEVICE)
    }

    /**
     * 更新设备
     */
    fun updateDevice(request: LingLingUpdateDeviceRequest) {
        this.sendRequest(request, LinglingURLConfig.UPDATE_DEVICE)
    }

    /**
     * 查询设备列表
     */
    fun queryDevices(): List<ResponseResult> {
        val body = this.sendRequest(Any(), LinglingURLConfig.QUERY_DEVICE_LIST)

        val response = gson().fromJson<LinglingDeviceQueryResponse>(body, LinglingDeviceQueryResponse::class.java)
        return response.responseResult
    }

    /**
     * 生成开门密钥
     */
    fun generateOpenDoorKey(linglingGenerateKeyRequest: LinglingGenerateKeyRequest): ResponseResult? {
        val body = this.sendRequest(linglingGenerateKeyRequest, LinglingURLConfig.MAKE_SDK_KEY)

        val response = gson().fromJson<LingLingResponse>(body, LingLingResponse::class.java)

        return response.responseResult
    }

    /**
     * 批量生成令令id
     */
    fun batchGenerateIds(linglingBatchIdsRequest: LinglingBatchIdsRequest): ResponseResult? {
        val body = this.sendRequest(linglingBatchIdsRequest, LinglingURLConfig.GET_LING_LING_ID)

        val response = gson().fromJson<LingLingResponse>(body, LingLingResponse::class.java)
        return response.responseResult

    }

    /**
     * 生成业主二维码
     */
    fun generateOwnerQrCode(linglingGenerateOwnerQrCodeRequest: LinglingGenerateOwnerQrCodeRequest): ResponseResult? {
        val body = this.sendRequest(linglingGenerateOwnerQrCodeRequest, LinglingURLConfig.ADD_OWNER_QR_CODE)

        val response = gson().fromJson<LingLingResponse>(body, LingLingResponse::class.java)

        return response.responseResult

    }

    /**
     * 添加门禁访客二维码
     */
    fun addVisitorQrCode(linglingGenerateOwnerQrCodeRequest: LinglingGenerateOwnerQrCodeRequest): ResponseResult? {
        val body = this.sendRequest(linglingGenerateOwnerQrCodeRequest, LinglingURLConfig.ADD_VISITOR_QR_CODE)

        val response = gson().fromJson<LingLingResponse>(body, LingLingResponse::class.java)

        return response.responseResult
    }

    /**
     * 日志查询
     */
    fun selectOpenDoorLog(linglingSelectOpenDoorLongRequest: LinglingSelectOpenDoorLongRequest): List<LinglingLogQueryResponseBody> {
        val body = this.sendRequest(linglingSelectOpenDoorLongRequest, LinglingURLConfig.SELECT_OPEN_DOOR_LOG)

        val response = gson().fromJson<LinglingLogQueryResponse>(body, LinglingLogQueryResponse::class.java)

        return response.responseResult
    }

    /**
     * 添加开门的门禁卡
     */
    fun addOpenDoorCard(linglingAddDeviceRequest: LinglingAddDeviceRequest) {
        this.sendRequest(linglingAddDeviceRequest, LinglingURLConfig.ADD_OPEN_DOOR_CARD)
    }

    /**
     * 删除门禁卡
     */
    fun delOpenDoorCard(linglingOpenDoorCardRequest: LinglingOpenDoorCardRequest) {
        this.sendRequest(linglingOpenDoorCardRequest, LinglingURLConfig.DEL_OPEN_DOOR_CARD)
    }

    /**
     * 查询门禁卡
     */
    fun queryOpenDoorCard(linglingOpenDoorCardRequest: LinglingOpenDoorCardRequest): String? {
        return this.sendRequest(linglingOpenDoorCardRequest, LinglingURLConfig.QUERY_OPEN_DOOR_CARD)
    }

    /**
     * 更新门禁卡权限
     */
    fun updateOpenDoorCard(linglingUpdateOpenDoorCardRequest: LinglingUpdateOpenDoorCardRequest) {
        this.sendRequest(linglingUpdateOpenDoorCardRequest, LinglingURLConfig.UPDATE_OPEN_DOOR_CARD)
    }

    /**
     * 远程开门
     */
    fun remoteOpenDoor(linglingRemoteOpenDoorRequest: LinglingRemoteOpenDoorRequest) {
        this.sendRequest(linglingRemoteOpenDoorRequest, LinglingURLConfig.REMOTE_OPEN_DOOR)
    }

    private fun sendRequest(any: Any?, action: String): String? {

        val lingLingRequest = LinglingRequest(requestParam = any, header = Header(lingLingConfig.signature, lingLingConfig.token))

        val message = gson().toJson(lingLingRequest)

        val retrofit = LinglingRequestStaticsSingleton.getInstance()

        val linglingRequest = retrofit.create(LingLingRequestInterface::class.java)

        val execute = linglingRequest.sendRequest(action = action + lingLingConfig.openToken , message =  message)

        val body = execute.execute()

        this.verifyStatusCode(body = body.body())

        return body.body()
    }

    private fun gson(): Gson = Gson()

    private fun verifyStatusCode(body: String?) {
        val response = gson().fromJson<LinglingBaseResponse>(body, LinglingBaseResponse::class.java)

        when (response.statusCode) {
            "0", "90009" -> {
                val error = gson().fromJson<LinglingErrorResponse>(body, LinglingErrorResponse::class.java)
                throw LingLingException(error.responseResult)
            }
            "10001" -> throw LingLingException("登陆/激活成功，但是没有关联到设备")
            "90001" -> throw LingLingException("参数个数错误")
            "90002", "90003" -> throw LingLingException("参数个数错误")
            "90004" -> throw LingLingException("openToken错误")
            "30004", "30012" -> throw LingLingException("当前开发者下没有此设备")
            "30016" -> throw LingLingException("设备不在线")
        }
    }
}