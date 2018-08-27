package xyz.idwangmo.lingling.model.request


class LingLingRequest{
    var requestParam: Any = ""
    var header: Header = Header()

    constructor()

    constructor(requestParam: Any, header: Header)

}


class Header {
    var signature: String = ""
    var token: String = ""

    constructor()

    constructor(signature: String, token: String)
}