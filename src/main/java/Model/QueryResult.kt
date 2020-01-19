package Model

class QueryResult {

    private var name: String = ""
    private var result: String = ""
    private var changeFlag: Boolean = false

    fun getResult(): String {
        return result;
    }

    fun setResult(newResult: String) {
        if (newResult != result)
            changeFlag = true
        result = newResult
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return name
    }

    fun getChangeFlag(): Boolean {
        return changeFlag
    }

    fun resetChangeFlag() {
        changeFlag = false
    }
}