import Model.DataService
import Model.Observer
import View.View
import javafx.application.Platform

class Controller(private val dataService: DataService, private val view: View): Observer {

    private val queryEntities = ArrayList<QueryEntity>()

    init {
        val queryEntries = dataService.getQueryEntries()
        val queryResults = dataService.getQueryResults()
        for (i in 0 until queryEntries.size) {
            queryEntities.add(QueryEntity(
                    queryEntries[i],
                    queryResults[i],
                    view.addQueryEntryView(queryEntries[i]),
                    view.addQueryResultView(queryResults[i])
            ))
        }
        dataService.addObserver(this)
    }

    private fun updateResultViews() {
        Platform.runLater {
            view.setQueryResultViews(dataService.getQueryResults())
            for(queryEntity in queryEntities) {
                queryEntity.updateResults()
            }
        }
    }

    override fun notifyObserver() {
        updateResultViews()
    }
}