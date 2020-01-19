package Model

import java.util.*

class DataService: Observer {

    private val queryEngine = QueryEngine()
    private var queryEntries: List<QueryEntry>
    private var queryResults = ArrayList<QueryResult>()
    private val configManager = ConfigManager("src/main/resources/config.xml")
    private var queryEngineThread: Thread
    private var observers = ArrayList<Observer>()

    init {
        queryEntries = configManager.load()
        queryEngine.setQueryEntries(queryEntries)
        queryEngine.setQueryResults(queryResults)
        queryEngine.addObserver(this)
        for(queryEntry in queryEntries) {
            queryResults.add(QueryResult())
        }
        queryEngineThread = Thread(queryEngine)
        queryEngineThread.start()
    }

    fun saveConfig() {
        configManager.save(Arrays.asList(QueryEntry(
                "/html/body/div[3]/div[3]/div/main/div/div/div/div/table[3]/tbody[2]/tr[2]/td[3]/div/span",
                "https://usosweb.usos.pw.edu.pl/kontroler.php?_action=dla_stud/studia/oceny/index",
                "https://cas.usos.pw.edu.pl/cas/login?service=https%3A%2F%2Fusosweb.usos.pw.edu.pl%2Fkontroler.php%3F_action%3Dlogowaniecas%2Findex&locale=pl",
                "",
                "",
                "//*[@id=\"username\"]",
                "//*[@id=\"password\"]",
                "/html/body/div/div/div[2]/div[2]/form/div[5]/input[4]"),
                QueryEntry(
                        "/html/body/div[3]/div[3]/div/main/div/div/div/div/table[3]/tbody[2]/tr[6]/td[3]/div/span",
                        "https://usosweb.usos.pw.edu.pl/kontroler.php?_action=dla_stud/studia/oceny/index",
                        "https://cas.usos.pw.edu.pl/cas/login?service=https%3A%2F%2Fusosweb.usos.pw.edu.pl%2Fkontroler.php%3F_action%3Dlogowaniecas%2Findex&locale=pl",
                        "",
                        "",
                        "//*[@id=\"username\"]",
                        "//*[@id=\"password\"]",
                        "/html/body/div/div/div[2]/div[2]/form/div[5]/input[4]")))
    }

    fun getQueryEntries(): List<QueryEntry> {
        return queryEntries
    }

    fun getQueryResults(): List<QueryResult> {
        return queryResults
    }

    fun setQueryEntries(queryEntries: List<QueryEntry>) {
        this.queryEntries = queryEntries
    }

    fun setQueryResults(queryResults: List<QueryResult>) {
        this.queryResults = queryResults as ArrayList<QueryResult>
    }

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun notifyObserver() {
        for(observer in observers) {
            observer.notifyObserver()
        }
    }

}