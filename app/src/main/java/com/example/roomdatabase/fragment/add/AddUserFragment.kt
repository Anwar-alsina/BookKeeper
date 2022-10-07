package com.example.roomdatabase.fragment.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.model.User
import com.example.roomdatabase.viewmodel.UserViewModel
import com.example.roomdatabase.databinding.FragmentAddUserBinding


class AddUserFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root

    }

    private fun insertDataToDatabase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text

        if (inputCheck(firstName, lastName, age)){
            //Check User Object
            val user = User(
                0,
                firstName,
                lastName,
                Integer.parseInt(age.toString()))
            //Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully Saved",Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_addUserFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all fields",Toast.LENGTH_LONG).show()
        }

    }
    private fun inputCheck(firstName:String, lastName: String,age:Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}