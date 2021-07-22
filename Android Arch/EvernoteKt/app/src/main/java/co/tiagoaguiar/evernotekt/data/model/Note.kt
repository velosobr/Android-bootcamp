package co.tiagoaguiar.evernotekt.data.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Maio, 18 2021
 * @author velosobr
 */
data class Note(
    var id: Int = 0,
    var title: String? = null,
    var desc: String? = null,
    var date: String? = null,
    var body: String? = null
) {
    val createdDate: String
        get() {
            return try {
                val locale = Locale("pt", "BR")
                val date = SimpleDateFormat("dd/MM/yyyy", locale).parse(date ?: "")

                SimpleDateFormat("MMM yyyy", locale).format(date).capitalize()
            } catch (e: ParseException) {
                ""
            }
        }
}
