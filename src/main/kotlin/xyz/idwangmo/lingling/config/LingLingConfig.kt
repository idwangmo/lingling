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