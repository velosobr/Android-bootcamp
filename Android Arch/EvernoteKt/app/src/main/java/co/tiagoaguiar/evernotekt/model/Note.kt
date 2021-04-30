package co.tiagoaguiar.evernotekt.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Setembro, 24 2019
 * @author suporte@moonjava.com.br (Tiago Aguiar).
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
            try {
                val locale = Locale("pt", "BR")
                val date = SimpleDateFormat("dd/MM/yyyy", locale).parse(date)

                return SimpleDateFormat("MMM yyyy", locale).format(date).capitalize()
            }catch (e: ParseException){
                return ""
            }


        }
}