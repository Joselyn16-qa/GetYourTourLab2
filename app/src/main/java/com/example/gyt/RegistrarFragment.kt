package com.example.gyt

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_perfil_.*
import kotlinx.android.synthetic.main.fragment_registrar.*
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrarFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private  val nombreLiveData = MutableLiveData<String>()
    private  val apellidoLiveData = MutableLiveData<String>()
    private  val emailLiveData = MutableLiveData<String>()
    private  val passwordLiveData = MutableLiveData<String>()
    private val isValidLiveData  = MediatorLiveData<Boolean>().apply {
        this.value=false

        addSource(emailLiveData){ email->
            val password = passwordLiveData.value
            val nombre= nombreLiveData.value
            val apellido=apellidoLiveData.value
            this.value=validateForm(email,password, nombre, apellido )

        }
        addSource(passwordLiveData){password->
            val email = emailLiveData.value
            val nombre= nombreLiveData.value
            val apellido=apellidoLiveData.value
            this.value=validateForm(email,password, nombre, apellido )

        }
        addSource(nombreLiveData){nombre->
            val email = emailLiveData.value
            val password= passwordLiveData.value
            val apellido=apellidoLiveData.value
            this.value=validateForm(email,password,nombre, apellido)

        }
        addSource(apellidoLiveData){apellido->
            val email = emailLiveData.value
            val password= passwordLiveData.value
            val nombre=nombreLiveData.value
            this.value=validateForm(email,password,nombre, apellido)

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar, container, false)
    }
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button3.setOnClickListener{
            it.findNavController().navigate(R.id.action_registrarFragment_to_homeFragment)
        }
        val emailLayout = view.findViewById<TextView>(R.id.email)
        val passwordLayout = view.findViewById<TextView>(R.id.password)
        val nombreLayout = view.findViewById<TextView>(R.id.nombre)
        val apellidoLayout = view.findViewById<TextView>(R.id.apellido)
        val button3 = view.findViewById<MaterialButton>(R.id.button3)

        emailLayout.doOnTextChanged{ text, _, _, _->
            emailLiveData.value= text.toString()
        }
        passwordLayout.doOnTextChanged{ text, _, _, _->
            passwordLiveData.value= text.toString()
        }
        nombreLayout.doOnTextChanged{ text, _, _, _->
            nombreLiveData.value= text.toString()
        }
        apellidoLayout.doOnTextChanged{ text, _, _, _->
            apellidoLiveData.value= text.toString()
        }

        isValidLiveData.observe(this){isValid ->
            button3.isEnabled= isValid

        }
        emailLayout.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(emailLayout.text.toString()).matches())
                else{
                    emailLayout.setError("Correo Invalido")
                }
            }


        }

        )
    }
      /*  nombreLayout.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validateNom(nombreLayout.text.toString()))
                else{
                    nombreLayout.setError("Invalid")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })*/

    /*
    private fun validateNom(text: String?): Boolean{
        var p= Pattern.compile("^[A-Z]{4}[\\d]{3}\$")
        var m= p.matcher(text)
        return  m.matches()

    }*/
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun validateForm(email: String?, password: String?, nombre: String?, apellido: String?): Boolean{
        val isValidEmail = email!= null && email.isNotBlank() && email.contains("@")
        val isValidPassword=password!= null && password.isNotBlank() && password.length >= 6
        val isValidNombre=nombre!= null && nombre.isNotBlank()
        val isValidApellido=apellido!= null && apellido.isNotBlank()

        return isValidEmail && isValidPassword &&isValidNombre && isValidApellido
    }
}