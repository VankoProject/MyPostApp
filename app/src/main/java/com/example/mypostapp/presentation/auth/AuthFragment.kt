package com.example.mypostapp.presentation.auth

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mypostapp.R
import com.example.mypostapp.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAuthBinding.bind(view)
        hideKeyBoard()
        checkVisibilityIcon()
        binding.etEmail.addTextChangedListener(createTextWatcher())
        binding.etPassword.addTextChangedListener(createTextWatcher())
        binding.enterButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }
        binding.checkboxProfile.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

            } else {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun createTextWatcher(): TextWatcher {
        return object : MyTextWatcher() {
            @SuppressLint("SuspiciousIndentation")
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun afterTextChanged(s: Editable?) {
                with(binding) {
                    val inputEmail = etEmail.text.toString().trim()
                    val inputPassword = etPassword.text.toString().trim()
                    val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()
                    val isValidPassword = inputPassword.length >= 6

                    etEmail.setOnFocusChangeListener { _, hasFocus ->
                        if (!hasFocus && !isValidEmail) {
                            etEmail.textCursorDrawable =
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.custom_cursor
                                )
                            tilEmail.error = "Check email format"
                        } else {
                            tilEmail.error = null
                        }
                    }
                  etEmail.textCursorDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.custom_cursor)

                    etPassword.setOnFocusChangeListener { _, hasFocus ->
                        checkVisibilityIcon()
                        if (!hasFocus && !isValidPassword) {
                            tilPassword.error = "Password should consist at least 6 characters"
                        } else {
                            tilPassword.error = null
                        }
                    }
                    etPassword.textCursorDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.custom_cursor)

                    enterButton.isEnabled = isValidEmail && isValidPassword
                }
            }
        }
    }

    private fun hideKeyBoard() {
        val keyboard = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        requireView().setOnClickListener {
            hideKeyBoard()
            binding.etEmail.clearFocus()
            binding.etPassword.clearFocus()
        }
    }

    private fun checkVisibilityIcon() {
        val endIcon = binding.tilPassword
        val inputPassword = endIcon.editText
        endIcon.endIconDrawable = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.ic_visible
        )
        endIcon.setEndIconOnClickListener {
            val currentPosition = Selection.getSelectionEnd(inputPassword?.text)
            if (inputPassword?.transformationMethod is PasswordTransformationMethod) {
                inputPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                endIcon.setEndIconDrawable(R.drawable.ic_visible)
            } else {
                inputPassword?.transformationMethod = PasswordTransformationMethod.getInstance()
                endIcon.setEndIconDrawable(R.drawable.ic_visibility_off)
            }
            inputPassword?.setSelection(currentPosition)
        }
    }
}