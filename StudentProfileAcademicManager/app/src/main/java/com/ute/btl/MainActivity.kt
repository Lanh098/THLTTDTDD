package com.ute.btl

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ute.btl.databinding.ActivityMainBinding
import com.ute.btl.model.Student
import com.ute.btl.utils.toAcademicRanking
import com.ute.btl.utils.toast
import com.ute.btl.utils.trimmedText
import com.ute.btl.utils.toRankingColor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Sinh viên mặc định
    private val defaultStudent = Student(
        id = "2415053122123",
        name = "Bùi Tá Lanh",
        className = "24T1",
        email = "2415053122123@ute.udn.vn",
        gpa = 3.75
    )

    // Sinh viên hiện tại
    private var currentStudent = defaultStudent

    companion object {
        private const val KEY_STUDENT = "KEY_STUDENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. VIEWBINDING
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. KHÔI PHỤC DỮ LIỆU
        if (savedInstanceState != null) {

            val saved =
                savedInstanceState.getSerializable(KEY_STUDENT) as? Student

            saved?.let {
                currentStudent = it
            }
        }

        // 3. HIỂN THỊ DỮ LIỆU
        bindStudentData(currentStudent)

        // 4. NÚT CẬP NHẬT GPA
        binding.btnUpdateGpa.setOnClickListener {

            val gpa =
                binding.edtGpaInput
                    .trimmedText()
                    .toDoubleOrNull()

            // GPA không hợp lệ
            if (gpa == null || gpa !in 0.0..4.0) {

                binding.edtGpaInput.error =
                    "GPA phải từ 0.0 đến 4.0"

                return@setOnClickListener
            }

            // Xóa lỗi cũ
            binding.edtGpaInput.error = null

            // Tạo Student mới bằng copy()
            currentStudent =
                currentStudent.copy(gpa = gpa)

            // Cập nhật lại giao diện
            bindStudentData(currentStudent)

            toast("Đã cập nhật GPA thành công!")
        }

        // 5. NÚT KHÔI PHỤC MẶC ĐỊNH
        binding.btnReset.setOnClickListener {
            showResetDialog()
        }
    }

    // HÀM HIỂN THỊ STUDENT LÊN GIAO DIỆN
    private fun bindStudentData(student: Student) {

        with(binding) {

            tvStudentName.text = student.name

            tvStudentDetails.text =
                "MSSV: ${student.id} • Lớp: ${student.className}"

            tvStudentEmail.text =
                "Email: ${student.email}"

            tvGpaBadge.text =
                "${student.gpa} GPA • ${student.gpa.toAcademicRanking()}"

            tvGpaBadge.setTextColor(
                student.gpa.toRankingColor()
            )

            edtGpaInput.setText(
                student.gpa.toString()
            )
        }
    }

    // TỰ MỞ RỘNG 2
    // HIỂN THỊ HỘP THOẠI XÁC NHẬN KHÔI PHỤC
    private fun showResetDialog() {

        AlertDialog.Builder(this).apply {

            setTitle("Xác nhận khôi phục")

            setMessage(
                "Bạn có chắc chắn muốn đặt lại " +
                        "điểm GPA ban đầu (3.75) không?"
            )

            // Nút Hủy
            setNegativeButton("Hủy") { dialog, _ ->
                dialog.dismiss()
            }

            // Nút Đồng ý
            setPositiveButton("Đồng ý") { _, _ ->

                currentStudent = defaultStudent

                bindStudentData(currentStudent)

                toast("Đã khôi phục dữ liệu mặc định")
            }

        }.show()
    }

    // LƯU STATE TRƯỚC KHI ACTIVITY BỊ HỦY
    override fun onSaveInstanceState(
        outState: Bundle
    ) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(
            KEY_STUDENT,
            currentStudent
        )
    }
}