package com.ute.btl

import android.content.Intent
import android.net.Uri
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
    // SINH VIÊN MẶC ĐỊNH

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
    // ON CREATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. VIEWBINDING
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 2. KHÔI PHỤC DỮ LIỆU
        if (savedInstanceState != null) {
            @Suppress("DEPRECATION")
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
            // Cập nhật giao diện
            bindStudentData(currentStudent)
            toast("Đã cập nhật GPA thành công!")
        }
        // 5. NÚT KHÔI PHỤC MẶC ĐỊNH
        binding.btnReset.setOnClickListener {
            showResetDialog()
        }
        // 6. NÚT GỬI BÁO CÁO HỌC TẬP
        binding.btnSendReport.setOnClickListener {
            sendStudyReport()
        }
    }
    // HIỂN THỊ THÔNG TIN SINH VIÊN
    private fun bindStudentData(student: Student) {
        with(binding) {
            // Họ tên
            tvStudentName.text = student.name
            // MSSV + lớp
            tvStudentDetails.text =
                "MSSV: ${student.id} • Lớp: ${student.className}"
            // Email
            tvStudentEmail.text =
                "Email: ${student.email}"
            // GPA + xếp loại
            tvGpaBadge.text =
                "${student.gpa} GPA • ${student.gpa.toAcademicRanking()}"
            // Đổi màu Badge theo GPA
            tvGpaBadge.setTextColor(
                student.gpa.toRankingColor()
            )
            // Hiển thị GPA hiện tại trong EditText
            edtGpaInput.setText(
                student.gpa.toString()
            )
        }
    }
    // TỰ MỞ RỘNG 2
    // ALERTDIALOG XÁC NHẬN KHÔI PHỤC
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

    // TỰ MỞ RỘNG 3
    // GỬI BÁO CÁO QUA GMAIL
    private fun sendStudyReport() {

        val student = currentStudent

        val subject =
            "[Báo cáo học tập] Sinh viên ${student.name} - MSSV ${student.id}"

        val body =
            """
        BÁO CÁO KẾT QUẢ HỌC TẬP
        
        Họ tên: ${student.name}
        MSSV: ${student.id}
        Lớp: ${student.className}
        Email: ${student.email}
        
        GPA: ${student.gpa}
        Xếp loại: ${student.gpa.toAcademicRanking()}
        """.trimIndent()

        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {

            data = Uri.parse(
                "mailto:${student.email}" +
                        "?subject=${Uri.encode(subject)}" +
                        "&body=${Uri.encode(body)}"
            )

            setPackage("com.google.android.gm")
        }

        try {
            startActivity(emailIntent)
        } catch (e: Exception) {
            toast(
                "Chưa cài đặt hoặc chưa bật Gmail trên LDPlayer"
            )
        }
    }
    // LƯU STATE KHI ACTIVITY BỊ HỦY
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