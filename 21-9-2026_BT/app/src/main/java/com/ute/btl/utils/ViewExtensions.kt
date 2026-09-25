package com.ute.btl.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast

// Hiện View
fun View.show() {
    visibility = View.VISIBLE
}

// Ẩn View và không chiếm chỗ
fun View.gone() {
    visibility = View.GONE
}

// Ẩn View nhưng vẫn giữ chỗ
fun View.invisible() {
    visibility = View.INVISIBLE
}

// Hiển thị Toast
fun Context.toast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(this, message, duration).show()
}

// Lấy nội dung EditText và xóa khoảng trắng đầu/cuối
fun EditText.trimmedText(): String {
    return text.toString().trim()
}

// Xếp loại học lực theo GPA
fun Double.toAcademicRanking(): String = when {
    this >= 3.6 -> "Xuất sắc (Excellent)"
    this >= 3.2 -> "Giỏi (Very Good)"
    this >= 2.5 -> "Khá (Good)"
    this >= 2.0 -> "Trung bình (Average)"
    this >= 1.0 -> "Yếu (Weak)"
    else -> "Kém (Poor)"
}