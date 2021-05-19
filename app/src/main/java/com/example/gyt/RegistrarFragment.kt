package com.example.gyt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_perfil_.*
import kotlinx.android.synthetic.main.fragment_registrar.*

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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private  val nameLiveData = MutableLiveData<String>()
    private  val countryLiveData = MutableLiveData<String>()
    private  val emailLiveData = MutableLiveData<String>()
    private  val passwordLiveData = MutableLiveData<String>()
    private  val birth_dayLiveData = MutableLiveData<String>()
    private  val dniLiveData = MutableLiveData<String>()
    private  val c_passwordLiveDta = MutableLiveData<String>()
    private val isValidLiveData  = MediatorLiveData<Boolean>().apply {
        this.value=false

        addSource(emailLiveData){ email->
            val password = passwordLiveData.value
            val name= nameLiveData.value
            val country=countryLiveData.value
            val dni = dniLiveData.value
            val c_password= c_passwordLiveDta.value
            val birth_day=birth_dayLiveData.value
            this.value=validateForm(email,password, name, country,birth_day,dni,c_password )

        }
        addSource(passwordLiveData){password->
            val email = emailLiveData.value
            val name= nameLiveData.value
            val country=countryLiveData.value
            val dni = dniLiveData.value
            val c_password= c_passwordLiveDta.value
            val birth_day=birth_dayLiveData.value
            this.value=validateForm(email,password, name, country,birth_day,dni,c_password )

        }
        addSource(nameLiveData){name->
            val email = emailLiveData.value
            val password= passwordLiveData.value
            val country=countryLiveData.value
            val dni = dniLiveData.value
            val c_password= c_passwordLiveDta.value
            val birth_day=birth_dayLiveData.value
            this.value=validateForm(email,password, name, country,birth_day,dni,c_password )

        }
        addSource(countryLiveData){country->
            val email = emailLiveData.value
            val password= passwordLiveData.value
            val name=nameLiveData.value
            val dni = dniLiveData.value
            val c_password= c_passwordLiveDta.value
            val birth_day=birth_dayLiveData.value
            this.value=validateForm(email,password, name, country,birth_day,dni,c_password )

        }
        addSource(birth_dayLiveData){ birth_day->
            val email = emailLiveData.value
            val password = passwordLiveData.value
            val name= nameLiveData.value
            val country=countryLiveData.value
            val dni = dniLiveData.value
            val c_password= c_passwordLiveDta.value
            this.value=validateForm(email,password, name, country,birth_day,dni,c_password )

        }
        addSource(c_passwordLiveDta){c_password->
            val password = passwordLiveData.value
            val email = emailLiveData.value
            val name= nameLiveData.value
            val country=countryLiveData.value
            val dni = dniLiveData.value
            val birth_day=birth_dayLiveData.value
            this.value=validateForm(email,password, name, country,birth_day,dni,c_password )

        }
        addSource(dniLiveData){dni->
            val name= nameLiveData.value
            val email = emailLiveData.value
            val password= passwordLiveData.value
            val country=countryLiveData.value
            val c_password= c_passwordLiveDta.value
            val birth_day=birth_dayLiveData.value
            this.value=validateForm(email,password, name, country,birth_day,dni,c_password )

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailLayout = view.findViewById<TextView>(R.id.email)
        val passwordLayout = view.findViewById<TextView>(R.id.password)
        val NameLayout = view.findViewById<TextView>(R.id.name)
        val countryLayout = view.findViewById<TextView>(R.id.country)
        val dniLayout = view.findViewById<TextView>(R.id.dni)
        val birth_dayLayout = view.findViewById<TextView>(R.id.birth_day)
        val c_passwordLayout = view.findViewById<TextView>(R.id.c_password)
        val button3 = view.findViewById<MaterialButton>(R.id.button3)
        button3.setOnClickListener{
            it.findNavController().navigate(R.id.action_registrarFragment_to_homeFragment)
        }

        emailLayout.doOnTextChanged{ text, _, _, _->
            emailLiveData.value= text.toString()
        }
        passwordLayout.doOnTextChanged{ text, _, _, _->
            passwordLiveData.value= text.toString()
        }
        NameLayout.doOnTextChanged{ text, _, _, _->
            nameLiveData.value= text.toString()
        }
        countryLayout.doOnTextChanged{ text, _, _, _->
            countryLiveData.value= text.toString()
        }
        dniLayout.doOnTextChanged{ text, _, _, _->
            dniLiveData.value= text.toString()
        }
        birth_dayLayout.doOnTextChanged{ text, _, _, _->
            birth_dayLiveData.value= text.toString()
        }
        c_passwordLayout.doOnTextChanged{ text, _, _, _->
            c_passwordLiveDta.value= text.toString()
        }

        isValidLiveData.observe(viewLifecycleOwner){isValid ->
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar, container, false)
    }

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
    private fun validateForm(email: String?, password: String?, name: String?, country: String?,birth_day:String?,dni:String?,c_password:String? ): Boolean{
        val isValidEmail = email!= null && email.isNotBlank() && email.contains("@")
        val isValidPassword=password!= null && password.isNotBlank() && password.length >= 8
        val isValidC_password=c_password!= null && c_password.isNotBlank() && c_password.length >= 8
        val isValidName=name!= null && name.isNotBlank()
        val isValidCountry=country!= null && country.isNotBlank()
        val isValidDni=dni!= null && dni.isNotBlank()
        val isValidBirth_Day=birth_day!= null && birth_day.isNotBlank()

        return isValidEmail && isValidPassword &&isValidName && isValidCountry && isValidDni && isValidBirth_Day && isValidC_password
    }
}