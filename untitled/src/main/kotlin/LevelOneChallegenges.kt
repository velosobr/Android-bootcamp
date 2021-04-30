fun adjacentElementsProduct(inputArray: MutableList<Int>): Int {
    val first = inputArray.indices.first()
    val last = inputArray.indices.last()
    var element = 0
    var actualElement = 0
    for (i in inputArray.indices) {

        if (i == first) {
            element = inputArray[i] * inputArray[i + 1]
            actualElement = element
        } else if (i == last) {
            actualElement = inputArray[i] * inputArray[i - 1]
            if (actualElement > element) {
                element = actualElement
            }
        } else {
            actualElement = inputArray[i] * inputArray[i + 1]
            if (actualElement > element) {
                element = actualElement
            }
            actualElement = inputArray[i] * inputArray[i - 1]
            if (actualElement > element) {
                element = actualElement
            }
        }
    }
    return element
}