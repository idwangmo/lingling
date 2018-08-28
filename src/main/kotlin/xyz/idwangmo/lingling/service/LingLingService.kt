package xyz.idwangmo.lingling.service

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import xyz.idwangmo.lingling.config.LingLingConfig
import xyz.idwangmo.lingling.config.LinglingURLConfig
import xyz.idwangmo.lingling.exception.LingLingException
import xyz.idwangmo.lingling.model.request.*
import xyz.idwangmo.lingling.model.response.*

@Service
class LingLingService {

    @Autowired
    lateinit var lingLingConfig: LingLingConfig


    /**
     * 获取令令Id
     */
    val linglingId: String?
        get() {
            val body = this.sendRequest(Any(), LinglingURLConfig.GET_LING_LING_ID)
            val response = gson().fromJson<LingLingResponse>(body, LingLingResponse::class.java)
            return response.requestResult?.linglingId
        }

    /**
     * 添加设备
     */
    fun addDevice(request: LinglingAddDeviceRequest): Long? {
        val body = this.sendRequest(request, LinglingURLConfig.ADD_DEVICE)
        val response = gson().fromJson<LingLingResponse>(body, LingLingResponse::class.java)
        return response.requestResult?.deviceId
    }

    /**
     * 删除设备
     */
    fun deleteDevice(request: LinglingDeleteDeviceReqeust) {
        this.sendRequest(request, LinglingURLConfig.DEL_DEVICE)
    }

    /**
     * 更新设备
     */
    fun updateDevice(request: LinglingUpdateDeviceRequest) {
        this.sendRequest(request, LinglingURLConfig.UPDATE_DEVICE)
    }

    /**
     * 查询设备列表
     */
    fun queryDevices(): List<RequestResult> {
        val body = this.sendRequest(null, LinglingURLConfig.QUERY_DEVICE_LIST)
        val response = gson().fromJson<LinglingDeviceQueryResponse>(body, LinglingDeviceQueryResponse::class.java)
        return response.requestResult
    }

    fun sendRequest(any: Any?, action: String): String? {
        val lingLingRequest = LinglingRequest(requestParam = any, header = Header(lingLingConfig.signature, lingLingConfig.openToken))

        val message = gson().toJson(lingLingRequest)

        val restTemplate = RestTemplate()

        val headers = HttpHeaders()

        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val map = LinkedMultiValueMap<String, String>()
        map.add("MESSAGE", message)

        val request = HttpEntity<MultiValueMap<String, String>>(map, headers)

        val url = ""

        val responseEntity = restTemplate.postForEntity<String>(url, request, String::class.java)

        this.verifyStatusCode(body = responseEntity.body)

        return responseEntity.body
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