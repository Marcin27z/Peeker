package Model

class QueryEntry {

    var xPath: String = ""
    var url: String = ""
    var loginUrl: String = ""
    var login: String = ""
    var password: String = ""
    var loginFieldXpath: String = ""
    var passwordFieldXpath: String = ""
    var submitButtonXpath: String = ""

    constructor(xPath: String, url: String, loginUrl: String, login: String, password: String, loginFieldXpath: String, passwordFieldXpath: String, submitButtonXpath: String) {
        this.xPath = xPath
        this.url = url
        this.loginUrl = loginUrl
        this.login = login
        this.password = password
        this.loginFieldXpath = loginFieldXpath
        this.passwordFieldXpath = passwordFieldXpath
        this.submitButtonXpath = submitButtonXpath
    }
}