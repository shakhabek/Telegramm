package shakha.myapplication

import android.annotation.SuppressLint
import android.icu.text.RelativeDateTimeFormatter.AbsoluteUnit
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialog
import shakha.myapplication.adapter.RvAdapter
import shakha.myapplication.databinding.ActivityMainBinding
import shakha.myapplication.databinding.BottomSheetDialogBinding
import shakha.myapplication.databinding.FragmentBottomSheetBinding
import shakha.myapplication.models.Profile
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var list: ArrayList<Profile>
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var absolutePath:String
    private lateinit var bottomSheetDialogBinding: BottomSheetDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        absolutePath = ""
        loadData()
        binding.btnAdd.setOnClickListener {
            dialogAdd()
        }

        rvAdapter = RvAdapter(list)
        binding.rv.adapter = rvAdapter

    }


    private fun dialogAdd() {
        val bottomSheetDialog = BottomSheetDialog(this, R.style.NewDialog)
        val bottomSheetDialogBinding = FragmentBottomSheetBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetDialogBinding.root)

        bottomSheetDialogBinding.btnSuccess.setOnClickListener {
            bottomSheetDialog.cancel()
        }
        bottomSheetDialogBinding.imageView.setOnClickListener {
            rasmQoy.launch("image/*")
        }
        bottomSheetDialog.show()
    }

    @SuppressLint("SimpleDateFormat")
    private val rasmQoy =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it ?: return@registerForActivityResult
            bottomSheetDialogBinding.imageView.setImageURI(it)
            val inputStream = contentResolver.openInputStream(it)
            val title = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
            val file = File(filesDir, "$title.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            absolutePath = file.absolutePath
            Toast.makeText(this, "$absolutePath", Toast.LENGTH_SHORT).show()
        }


    private fun loadData() {
        list = ArrayList()

    }
}