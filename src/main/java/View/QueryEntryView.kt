package View

import Model.QueryEntry
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField

class QueryEntryView {

    lateinit var xPathTextField: TextField
    lateinit var urlTextField: TextField
    lateinit var loginUrlTextField: TextField
    lateinit var loginTextField: TextField
    lateinit var passwordField: PasswordField
    lateinit var loginFieldXpathTextField: TextField
    lateinit var passwordFiledXpathTextField: TextField
    lateinit var submitButtonXpathTextField: TextField

    fun setView(queryEntry: QueryEntry) {
/*        xPathTextField.text = queryEntry.xPath
        urlTextField.text = queryEntry.url
        loginUrlTextField.text = queryEntry.loginUrl
        loginTextField.text = queryEntry.login
        passwordField.text = queryEntry.password
        loginFieldXpathTextField.text = queryEntry.loginFieldXpath
        passwordFiledXpathTextField.text = queryEntry.passwordFieldXpath
        submitButtonXpathTextField.text = queryEntry.submitButtonXpath*/
    }


}