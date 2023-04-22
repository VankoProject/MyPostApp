package com.example.mypostapp.presentation.auth

import android.os.Bundle
import android.text.Selection
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mypostapp.R
import com.example.mypostapp.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAuthBinding.bind(view)
        checkVisibilityIcon()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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