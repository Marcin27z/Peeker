import Model.DataService
import View.View
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage) {

        val fxmlLoader = FXMLLoader(javaClass.getResource("view.fxml"))
        val root: Parent = fxmlLoader.load()
        val view: View = fxmlLoader.getController()
        val dataService = DataService()
        val controller = Controller(dataService, view)
        stage.scene = Scene(root)
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}