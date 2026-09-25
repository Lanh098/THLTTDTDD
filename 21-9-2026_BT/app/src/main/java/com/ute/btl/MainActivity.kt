package com.ute.btl

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ute.btl.databinding.ActivityMainBinding
import com.ute.btl.utils.gone
import com.ute.btl.utils.toAcademicRanking
import com.ute.btl.utils.toast
import com.ute.btl.utils.trimmedText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị dữ liệu sinh viên ban đầu
        displayStudent(
            name = "Bùi Tá Lanh",
            gpa = 3.75,
            email = "2415053122123@sv.ute.udn.vn"
        )

        // APPLY
        binding.btnUpdate.apply {
            text = "Cập nhật GPA"
            isEnabled = true

            setOnClickListener {
                updateGpa()
            }
        }
    }

    // WITH
    private fun displayStudent(
        name: String,
        gpa: Double,
        email: String
    ) {
        with(binding) {
            tvName.text = "Họ tên: $name"
            tvGpa.text = "GPA: $gpa"
            tvEmail.text = "Email: $email"

            progressBar.gone()
        }
    }

    // LET + ALSO
    private fun updateGpa() {

        val rawInput = binding.edtGpa.trimmedText()

        rawInput.let { input ->

            val gpa = input.toDoubleOrNull()

            // Kiểm tra dữ liệu nhập
            if (gpa == null) {
                toast("GPA không hợp lệ")
                return@let
            }

            // Kiểm tra khoảng GPA
            if (gpa !in 0.0..4.0) {
                toast("GPA phải từ 0.0 đến 4.0")
                return@let
            }

            // ALSO
            gpa.also { validGpa ->

                Log.d(
                    "STUDENT_GPA",
                    "GPA mới: $validGpa"
                )

                toast("Đã nhận GPA: $validGpa")
            }

            // Extension Function
            val ranking = gpa.toAcademicRanking()

            binding.tvGpa.text =
                "GPA: $gpa - Xếp loại: $ranking"
        }
    }
}