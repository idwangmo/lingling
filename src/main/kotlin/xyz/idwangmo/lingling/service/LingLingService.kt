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
import xyz.idwangmo.lingling.model.request.Header
import xyz.idwangmo.lingling.model.request.LingLingRequest

@Service
class LingLingService {

    @Autowired
    lateinit var lingLingConfig: LingLingConfig;

    fun sendRequest(any: Any, action: String): String? {
        val lingLingRequest = LingLingRequest(requestParam = any, header = Header(lingLingConfig.signature, lingLingConfig.openToken))

        val message = gson().toJson(lingLingRequest)

        val restTemplate = RestTemplate()

        val headers = HttpHeaders()

        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val map = LinkedMultiValueMap<String, String>()
        map.add("MESSAGE", message)

        val request = HttpEntity<MultiValueMap<String, String>>(map, headers)

        val url = ""

        val responseEntity = restTemplate.postForEntity<String>(url, request, String::class.java)
        return responseEntity.body
    }

    fun gson(): Gson = Gson()
}