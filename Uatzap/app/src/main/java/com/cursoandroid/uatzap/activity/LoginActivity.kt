package com.cursoandroid.uatzap.activity

import android.content.Intent
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
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser

@Suppress("SpellCheckingInspection")
class LoginActivity : AppCompatActivity() {

    private lateinit var campoEmail: TextInputEditText
    private lateinit var campoSenha: TextInputEditText
    private lateinit var autenticacao: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.campoEmail = findViewById(R.id.editLoginEmail)
        this.campoSenha = findViewById(R.id.editLoginSenha)

        this.autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()
    }

//    override fun onStart() {
//        super.onStart()
//
//        var usuarioAtual: FirebaseUser? = autenticacao.currentUser
//        if (usuarioAtual != null) {
//            abrirTelaPrincipal()
//        }
//
//
//    }

    fun abrirTelaCadastro(view: View) {
        intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    private fun abrirTelaPrincipal() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun logarUsuario(usuario: Usuario) {

        autenticacao.signInWithEmailAndPassword(usuario.email, usuario.senha)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Sucesso ao logar usuario",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                    abrirTelaPrincipal()
                } else {
                    task.exception?.let {
                        it.printStackTrace()

                        val excecao = when(it) {
                            is FirebaseAuthInvalidCredentialsException -> "E-mail e senha não correspondem a um usuário cadastrado"
                            is FirebaseAuthInvalidUserException -> "Usuário não está cadastrado"
                            else -> "Usuário não está cadastrado"
                        }

                        Toast.makeText(
                            this,
                            it.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    val excecao: String
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidUserException) {
                        excecao = "Usuário não está cadastrado"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        excecao = "E-mail e senha não correspondem a um usuário cadastrado"
                    } catch (e: Exception) {
                        excecao = "Erro ao logar"
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

    fun validarLoginUsuario(view: View) {
        val textoEmail: String = campoEmail.text.toString()
        val textoSenha: String = campoSenha.text.toString()

        if (textoEmail.isNotEmpty()) {
            if (textoSenha.isNotEmpty()) {
                val usuario = Usuario(textoEmail, textoSenha)
                logarUsuario(usuario)
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

    }


}
