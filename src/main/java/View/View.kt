package View

import Model.QueryEntry
import Model.QueryResult
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.RowConstraints


class View {

    @FXML
    lateinit var configPane: Pane
    @FXML
    lateinit var resultPane: Pane

    private val configGridPane = GridPane()
    private val resultGridPane = GridPane()
    private var queryEntries = 0
    private var queryResults = 0
    private var queryEntryViews = ArrayList<QueryEntryView>()
    private var queryResultViews = ArrayList<QueryResultView>()

    @FXML
    fun initialize() {
        configPane.children.add(configGridPane)
        resultPane.children.add(resultGridPane)
    }

    @FXML
    fun reset() {
        for (queryResultView in queryResultViews) {
            queryResultView.resetChanged()
        }
    }

    fun addEmptyQueryEntryView(): QueryEntryView {
        val fxmlLoader = FXMLLoader(Main.javaClass.getResource("queryEntryView.fxml"))
        val entry: Pane = fxmlLoader.load()
        val entryController: QueryEntryView = fxmlLoader.getController()
        queryEntryViews.add(entryController)
        configGridPane.add(entry, 0, queryEntries++)
        val rowConstraints = RowConstraints()
        rowConstraints.prefHeight = entry.prefHeight
        configGridPane.rowConstraints.add(rowConstraints)
        return entryController
    }

    fun addQueryEntryView(queryEntry: QueryEntry): QueryEntryView {
        val fxmlLoader = FXMLLoader(Main.javaClass.getResource("queryEntryView.fxml"))
        val entry: Pane = fxmlLoader.load()
        val entryController: QueryEntryView = fxmlLoader.getController()
        entryController.setView(queryEntry)
        queryEntryViews.add(entryController)
        configGridPane.add(entry, 0, queryEntries++)
        val rowConstraints = RowConstraints()
        rowConstraints.prefHeight = entry.prefHeight
        configGridPane.rowConstraints.add(rowConstraints)
        return entryController
    }

    fun addQueryResultView(queryResult: QueryResult): QueryResultView {
        val fxmlLoader = FXMLLoader(Main.javaClass.getResource("queryResultView.fxml"))
        val result: Pane = fxmlLoader.load()
        val resultController: QueryResultView = fxmlLoader.getController()
        resultController.setView(queryResult)
        queryResultViews.add(resultController)
        resultGridPane.add(result, queryResults % 4, Math.floorDiv(queryResults , 4))
        val rowConstraints = RowConstraints()
        val columnConstraints = ColumnConstraints()
        rowConstraints.prefHeight = result.prefHeight
        columnConstraints.prefWidth = result.prefWidth
        resultGridPane.rowConstraints.add(rowConstraints)
        resultGridPane.columnConstraints.add(columnConstraints)
        queryResults++
        return resultController
    }

    fun setQueryResultViews(queryResults: List<QueryResult>) {
        for (queryResultView in queryResultViews) {
            queryResultView.setView(queryResults[queryResultViews.indexOf(queryResultView)])
        }
    }

    fun setResultViewChanged(i: Int) {
        queryResultViews[i].setChanged()
    }

    fun resetResultViewChanged(i: Int) {
        queryResultViews[i].resetChanged()
    }
}
