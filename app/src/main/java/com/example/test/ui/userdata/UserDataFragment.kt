package com.example.test.ui.userdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.test.R

class UserDataFragment : Fragment() {

    private var isEditing = true
    private var notEditing = false
    private lateinit var editTextFIO: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var imageViewFIOconf: ImageView
    private lateinit var imageViewPhoneconf: ImageView
    private lateinit var imageViewEmailconf: ImageView
    private lateinit var imageViewPasswordconf: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_data, container, false)

        val buttonRedact = view.findViewById<Button>(R.id.buttonRedact)
        val buttonSave = view.findViewById<Button>(R.id.buttonSave)

        editTextFIO = view.findViewById(R.id.editTextFIO)
        editTextPhone = view.findViewById(R.id.editTextPhone)
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextPassword = view.findViewById(R.id.editTextPassword)

        imageViewFIOconf = view.findViewById(R.id.imageViewFIOconfirm)
        imageViewPhoneconf = view.findViewById(R.id.imageViewPhoneconfirm)
        imageViewEmailconf = view.findViewById(R.id.imageViewEmailconfirm)
        imageViewPasswordconf = view.findViewById(R.id.imageViewPasswordconfirm)

        // Устанавливаем поля ввода неактивными
        setEditTextsEnabled(false)

        buttonRedact.setOnClickListener {
            buttonSave.isEnabled = isEditing
            buttonRedact.isEnabled = notEditing
            setEditTextsEnabled(isEditing)

        }
        buttonSave.setOnClickListener {
            buttonSave.isEnabled = notEditing
            buttonRedact.isEnabled = isEditing
            setEditTextsEnabled(notEditing)
            if (areFieldsFilled(editTextFIO, editTextPhone, editTextEmail, editTextPassword)) {
                showConfirmIcons(true)
            }
        }

        return view
        }

        private fun setEditTextsEnabled(enabled: Boolean) {
            editTextFIO.isEnabled = enabled
            editTextPhone.isEnabled = enabled
            editTextEmail.isEnabled = enabled
            editTextPassword.isEnabled = enabled

            if (enabled) {
                showConfirmIcons(false)
            }
        }

        private fun showConfirmIcons(visible: Boolean) {
            val visibility = if (visible) View.VISIBLE else View.GONE
            imageViewFIOconf.visibility = visibility
            imageViewPhoneconf.visibility = visibility
            imageViewEmailconf.visibility = visibility
            imageViewPasswordconf.visibility = visibility
        }

        private fun areFieldsFilled(vararg fields: EditText): Boolean {
            for (field in fields) {
                if (field.text.trim().isEmpty()) {
                    return false
                }
            }
            return true
        }
}
