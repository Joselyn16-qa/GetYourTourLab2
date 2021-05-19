package com.example.gyt

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
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_perfil_.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [perfil_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class perfil_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private  val emailLiveData = MutableLiveData<String>()
    private  val passwordLiveData = MutableLiveData<String>()
    lateinit var editTextTextPassword : EditText
    lateinit var editTextTextEmailAddress : EditText
    private val isValidLiveData  = MediatorLiveData<Boolean>().apply {
        this.value=false

        addSource(emailLiveData){ email->
            val password = passwordLiveData.value
            this.value=validateForm(email, password)

        }
        addSource(passwordLiveData){ password->
            val email = emailLiveData.value
            this.value=validateForm(email, password)

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
       return inflater.inflate(R.layout.fragment_perfil_, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment perfil_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            perfil_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener{
         it.findNavController().navigate(R.id.action_perfil_Fragment_to_homeFragment2)
     }
        button.setOnClickListener{
            it.findNavController().navigate(R.id.action_perfil_Fragment_to_registrarFragment)
        }

        val emailLayout = view.findViewById<TextView>(R.id.email)
        val passwordLayout = view.findViewById<TextView>(R.id.password)
        val buttonR = view.findViewById<MaterialButton>(R.id.btnIni)

        emailLayout.doOnTextChanged{ text, _, _, _->
            emailLiveData.value= text.toString()
        }
        passwordLayout.doOnTextChanged{ text, _, _, _->
            passwordLiveData.value= text.toString()
        }

        isValidLiveData.observe(viewLifecycleOwner){ isValid ->
            buttonR.isEnabled= isValid

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
        editTextTextEmailAddress= view.findViewById(R.id.email)
        editTextTextPassword= view.findViewById(R.id.password)



    }
    private fun validateForm(email: String?, password: String?): Boolean{
        val isValidEmail = email!= null && email.isNotBlank() && email.contains("@")
        val isValidPassword=password!= null && password.isNotBlank() && password.length >= 8
        if(editTextTextEmailAddress.text.toString().length==0) {
            Toast.makeText(getActivity(), "valores insuficientes", Toast.LENGTH_SHORT).show()
        }
        return isValidEmail && isValidPassword
    }
}