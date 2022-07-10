package meh.daniel.com.tenkeyoho.presentation.mainscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import meh.daniel.com.tenkeyoho.R
import meh.daniel.com.tenkeyoho.databinding.FragmentMainscreenBinding

class MainScreenFragment : Fragment(R.layout.fragment_mainscreen) {

    private lateinit var binding: FragmentMainscreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainscreenBinding.bind(view)
    }

}