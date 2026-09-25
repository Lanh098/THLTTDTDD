package com.ute.btl

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ute.btl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị dữ liệu ban đầu
        displayStudent(
            name = "Bùi Tá Lanh",
            gpa = 3.75,
            email = "2415053122123"
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

            progressBar.visibility = View.GONE
        }
    }

    // LET + ALSO + RUN
    private fun updateGpa() {

        val rawInput: String? =
            binding.edtGpa.text?.toString()?.trim()

        rawInput?.let { input ->

            val gpa = input.toDoubleOrNull()

            if (gpa == null) {

                Toast.makeText(
                    this,
                    "GPA không hợp lệ",
                    Toast.LENGTH_SHORT
                ).show()

                return@let
            }

            if (gpa !in 0.0..4.0) {

                Toast.makeText(
                    this,
                    "GPA phải từ 0.0 đến 4.0",
                    Toast.LENGTH_SHORT
                ).show()

                return@let
            }

            // ALSO
            gpa.also { validGpa ->

                Log.d(
                    "STUDENT_GPA",
                    "GPA mới: $validGpa"
                )

                Toast.makeText(
                    this,
                    "Đã nhận GPA: $validGpa",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // RUN
            val ranking = gpa.run {

                when {
                    this >= 3.6 -> "Xuất sắc"
                    this >= 3.2 -> "Giỏi"
                    this >= 2.5 -> "Khá"
                    this >= 2.0 -> "Trung bình"
                    this >= 1.0 -> "Yếu"
                    else -> "Kém"
                }
            }

            binding.tvGpa.text =
                "GPA: $gpa - Xếp loại: $ranking"
        }
    }
}