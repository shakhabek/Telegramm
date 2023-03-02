package shakha.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import shakha.myapplication.R
import shakha.myapplication.databinding.BottomSheetDialogBinding
import shakha.myapplication.databinding.FragmentBottomSheetBinding

class bottomSheet : Fragment() {
    private lateinit var binding: FragmentBottomSheetBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentBottomSheetBinding.inflate(layoutInflater)

        binding.imageView.setOnClickListener {
            rasmQoy.launch("image/*")
        }

        return binding.root
    }

    private val rasmQoy =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri ?: return@registerForActivityResult
            binding.imageView.setImageURI(uri)

        }


}