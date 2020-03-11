package com.cursoandroid.uatzap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cursoandroid.uatzap.R
import com.cursoandroid.uatzap.config.ConfiguracaoFirebase
import com.cursoandroid.uatzap.model.Usuario
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlin.Exception

@Suppress("SpellCheckingInspection")
class CadastroActivity : AppCompatActivity() {

    private lateinit var campoNome: TextInputEditText
    private lateinit var campoEmail: TextInputEditText
    private lateinit var campoSenha: TextInputEditText
    private lateinit var autenticacao: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.campoNome = findViewById(R.id.editCadastroNome)
        this.campoEmail = findViewById(R.id.editCadastroEmail)
        this.campoSenha = findViewById(R.id.editCadastroSenha)
    }

    fun cadastrarUsuario(usuario: Usuario) {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
        autenticacao.createUserWithEmailAndPassword(usuario.email, usuario.senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Sucesso ao cadastrar usuario",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    var excecao = ""
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        excecao = "Digite uma senha mais forte"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        excecao = "Por favor, digite um e-mail valido"
                    } catch (e: FirebaseAuthUserCollisionException) {
                        excecao = "Esta conta já foi cadastrada"
                    } catch (e: Exception) {
                        excecao = "Erro ao cadastrar usuario: " + e.message
                        e.printStackTrace()
                    }

                    Toast.makeText(
                        this,
                        excecao,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }

    fun validarCadastroUsuario(view: View) {
        val textoNome: String = campoNome.text.toString()
        val textoEmail: String = campoEmail.text.toString()
        val textoSenha: String = campoSenha.text.toString()

        if (textoNome.isNotEmpty()) {
            if (textoEmail.isNotEmpty()) {
                if (textoSenha.isNotEmpty()) {
                    val usuario = Usuario(textoEmail, textoSenha)
                    usuario.nome = textoNome
                    cadastrarUsuario(usuario)
                } else {
                    Toast.makeText(
                        this,
                        "Preencha a senha",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Preencha o Email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "Preencha o nome",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
