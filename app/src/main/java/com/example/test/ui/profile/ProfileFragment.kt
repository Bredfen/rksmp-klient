package com.example.test.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView = binding.listViewProfile

        val dataList = listOf (
            ItemProfile(R.drawable.icons_status, "Заказы"),
            ItemProfile(R.drawable.icon_like, "Избранное"),
            ItemProfile(R.drawable.icon_message, "Обратная связь"),
            ItemProfile(R.drawable.icons_engineering, "Настройки"),
            ItemProfile(R.drawable.icons_info, "О приложении"),
            ItemProfile(R.drawable.icon_exit, "Выйти из аккаунта"),
        )

        val adapter = ProfileAdapter(requireContext(), dataList)
        listView.adapter = adapter


        binding.userdata.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_userDataFragment)
        }




        binding.listViewProfile.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as ItemProfile
            val itemName = selectedItem.nameprofile
            when (itemName) {
                "Заказы" -> {
                    findNavController().navigate(R.id.action_navigation_profile_to_zakazFragment)
                }
                "Избранное" -> {
                    findNavController().navigate(R.id.action_navigation_profile_to_likesFragment)
                }
                "Обратная связь" -> {
                    findNavController().navigate(R.id.action_navigation_profile_to_obratnayaSvyzFragment2)
                }
                "Настройки" -> {
                    findNavController().navigate(R.id.action_navigation_profile_to_settingsFragment)
                }
                "О приложении" -> {
                    findNavController().navigate(R.id.action_navigation_profile_to_informationFragment)
                }
                "Выйти из аккаунта" -> {

                }
            }
        }

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}