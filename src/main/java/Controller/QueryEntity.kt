import Model.QueryEntry
import Model.QueryResult
import View.QueryEntryView
import View.QueryResultView

class QueryEntity(val queryEntry: QueryEntry, val queryResult: QueryResult, val queryEntryView: QueryEntryView, val queryResultView: QueryResultView) {

    fun updateResults() {
        queryResultView.setView(queryResult)
        if (queryResult.getChangeFlag())
            queryResultView.setChanged()
    }

    fun updateEntries() {
        queryEntry.login = queryEntryView.loginTextField.text;
    }

}