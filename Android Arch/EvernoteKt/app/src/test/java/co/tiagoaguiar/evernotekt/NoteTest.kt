package co.tiagoaguiar.evernotekt

import co.tiagoaguiar.evernotekt.model.Note
import org.junit.Assert
import org.junit.Test

class NoteTest {

    @Test
    fun `test should format date pattern to month and year`() {
        val note = Note(title = "NotaA", body = "NotaA conteudo", date = "20/02/2019")
        Assert.assertEquals("Fev 2019", note.createdDate)
    }

    @Test
    fun `test should format date case empty`() {
        val note = Note(title = "NotaA", body = "NotaA conteudo", date = "")
        Assert.assertEquals("", note.createdDate)
    }

    @Test
    fun `test should format date case null`() {
        val note = Note(title = "NotaA", body = "NotaA conteudo")
        Assert.assertEquals("Fev 2019", note.createdDate)
    }
}