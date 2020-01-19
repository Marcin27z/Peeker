package View

import Model.QueryResult
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.Pane


class QueryResultView {

    @FXML
    private lateinit var result: Label
    @FXML
    lateinit var name: Label
    @FXML
    lateinit var background: Pane
    @FXML
    lateinit var button: Button


    fun setChanged() {
        button.style = "-fx-base: red"
    }

    fun resetChanged() {
        button.style = "-fx-base: green"
    }

    fun setView(queryResult: QueryResult) {
        this.result.text = queryResult.getResult()
        this.name.text = queryResult.getName()
    }
}