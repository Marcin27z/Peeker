package Model

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory

class ConfigManager(filePath: String) {

    private val configFile = File(filePath)

    fun load(): List<QueryEntry> {
        val dbFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val dBuilder: DocumentBuilder = dbFactory.newDocumentBuilder()
        val document: Document = dBuilder.parse(configFile)
        val queryEntryList = ArrayList<QueryEntry>()
        document.documentElement.normalize()
        val entries = document.getElementsByTagName("entry")
        for (i in 0 until entries.length) {
            val entry: Node = entries.item(i)
            if (entry.nodeType == Node.ELEMENT_NODE) {
                val entryElement = entry as Element
                val queryEntry = QueryEntry(
                        entryElement.getElementsByTagName("xPath").item(0).textContent,
                        entryElement.getElementsByTagName("url").item(0).textContent,
                        entryElement.getElementsByTagName("loginUrl").item(0).textContent,
                        entryElement.getElementsByTagName("login").item(0).textContent,
                        entryElement.getElementsByTagName("password").item(0).textContent,
                        entryElement.getElementsByTagName("loginFieldXpath").item(0).textContent,
                        entryElement.getElementsByTagName("passwordFieldXpath").item(0).textContent,
                        entryElement.getElementsByTagName("submitButtonXpath").item(0).textContent)
                queryEntryList.add(queryEntry)
            }
        }
        return queryEntryList
    }

    fun save(queryEntryList: List<QueryEntry>) {
        val dbFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val dBuilder: DocumentBuilder = dbFactory.newDocumentBuilder()
        val document = dBuilder.newDocument()
        val element = document.createElement( "config")
        document.appendChild(element)
        for (queryEntry in queryEntryList) {
            val entryElement = document.createElement("entry")
            element.appendChild(entryElement)
            val xPathElement = document.createElement("xPath")
            xPathElement.appendChild(document.createTextNode(queryEntry.xPath))
            entryElement.appendChild(xPathElement)
            val urlElement = document.createElement("url")
            urlElement.appendChild(document.createTextNode(queryEntry.url))
            entryElement.appendChild(urlElement)
            val loginUrlElement = document.createElement("loginUrl")
            loginUrlElement.appendChild(document.createTextNode(queryEntry.loginUrl))
            entryElement.appendChild(loginUrlElement)
            val loginElement = document.createElement("login")
            loginElement.appendChild(document.createTextNode(queryEntry.login))
            entryElement.appendChild(loginElement)
            val passwordElement = document.createElement("password")
            passwordElement.appendChild(document.createTextNode(queryEntry.password))
            entryElement.appendChild(passwordElement)
            val loginFieldXpathElement = document.createElement("loginFieldXpath")
            loginFieldXpathElement.appendChild(document.createTextNode(queryEntry.loginFieldXpath))
            entryElement.appendChild(loginFieldXpathElement)
            val passwordFieldXpathElement = document.createElement("passwordFieldXpath")
            passwordFieldXpathElement.appendChild(document.createTextNode(queryEntry.passwordFieldXpath))
            entryElement.appendChild(passwordFieldXpathElement)
            val submitButtonXpathElement = document.createElement("submitButtonXpath")
            submitButtonXpathElement.appendChild(document.createTextNode(queryEntry.submitButtonXpath))
            entryElement.appendChild(submitButtonXpathElement)
        }
        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        val source = DOMSource(document)

        //write to console or file
        val console = StreamResult(System.out)
        //val file = StreamResult(File("/Users/pankaj/emps.xml"))

        //write data
        transformer.transform(source, console)
        //transformer.transform(source, file)
    }
}