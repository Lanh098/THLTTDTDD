import java.util.Scanner

class SinhVien(
    var maSV: String,
    var tenSV: String,
    var tuoiSV: Int,
    var chuyenNganh: String,
    var diemGPA: Double
) {
    fun inThongTin() {
        println(String.format("  %-12s | %-20s | %-5d | %-18s | %.2f", maSV, tenSV, tuoiSV, chuyenNganh, diemGPA))
    }
}

fun main() {
    val nhapLieu = Scanner(System.`in`)
    val danhSachSinhVien = ArrayList<SinhVien>()

    danhSachSinhVien.add(SinhVien("SV01", "Bui Ta Lanh", 20, "Cong nghe so", 8.5))
    danhSachSinhVien.add(SinhVien("SV02", "Nguyen Van An", 21, "Ky thuat", 4.5))
    danhSachSinhVien.add(SinhVien("SV03", "Tran Thi Binh", 19, "Kinh te", 9.2))
    danhSachSinhVien.add(SinhVien("SV04", "Le Hoang", 22, "Ngon ngu", 7.5))
    danhSachSinhVien.add(SinhVien("SV05", "Pham Dat", 20, "Cong nghe so", 12.5))

    while (true) {
        if (!nhapLieu.hasNextLine()) {
            break
        }

        println("\n================================================================================")
        println("                        QUAN LY THONG TIN SINH VIEN                             ")
        println("================================================================================")
        println(" 1. Them sinh vien                | 11. Tim sinh vien GPA tu 7.0 den 8.5")
        println(" 2. Hien thi tat ca sinh vien     | 12. Tim tat ca sinh vien theo nganh")
        println(" 3. Tim kiem sinh vien theo ID    | 13. Tim sinh vien theo phan ten")
        println(" 4. Tinh GPA trung binh           | 14. Sap xep sinh vien theo GPA giam dan")
        println(" 5. Tim sinh vien GPA cao nhat    | 15. Hien thi 3 sinh vien GPA cao nhat")
        println(" 6. Xoa sinh vien                 | 16. Sap xep sinh vien theo tuoi")
        println(" 7. Dem so sinh vien GPA >= 8.0   | 17. Sap xep sinh vien theo ho")
        println(" 8. Dem so sinh vien GPA < 5.0    | 18. Sap xep sinh vien theo ten")
        println(" 9. Tinh GPA trung binh theo nganh| 19. Kiem tra tinh hop le cua GPA")
        println(" 10. Tim sinh vien lon tuoi nhat  |  0. Thoat")
        println("================================================================================")
        print("Chon chuc nang: ")

        val luaChon = nhapLieu.nextLine().trim()
        println(luaChon) 

        println("--------------------------------------------------------------------------------")
        when (luaChon) {
            "1" -> {
                print("Nhap ID: ")
                val maSV = if (nhapLieu.hasNextLine()) nhapLieu.nextLine() else ""
                print("Nhap Ten: ")
                val tenSV = if (nhapLieu.hasNextLine()) nhapLieu.nextLine() else ""
                print("Nhap Tuoi: ")
                val tuoiSV = if (nhapLieu.hasNextLine()) nhapLieu.nextLine().toIntOrNull() ?: 0 else 0
                print("Nhap Nganh: ")
                val chuyenNganh = if (nhapLieu.hasNextLine()) nhapLieu.nextLine() else ""
                print("Nhap GPA: ")
                val diemGPA = if (nhapLieu.hasNextLine()) nhapLieu.nextLine().toDoubleOrNull() ?: 0.0 else 0.0
                
                danhSachSinhVien.add(SinhVien(maSV, tenSV, tuoiSV, chuyenNganh, diemGPA))
                println("=> Da them sinh vien thanh cong!")
            }
            "2" -> {
                println(String.format("  %-12s | %-20s | %-5s | %-18s | %s", "MA SV", "HO TEN", "TUOI", "NGANH", "GPA"))
                println("  ----------------------------------------------------------------------------")
                for (sv in danhSachSinhVien) sv.inThongTin()
            }
            "3" -> {
                print("Nhap ID can tim: ")
                val maSV = if (nhapLieu.hasNextLine()) nhapLieu.nextLine() else ""
                var timThay = false
                for (sv in danhSachSinhVien) {
                    if (sv.maSV == maSV) {
                        sv.inThongTin()
                        timThay = true
                    }
                }
                if (!timThay) println("=> Khong tim thay sinh vien.")
            }
            "4" -> {
                var tongDiem = 0.0
                for (sv in danhSachSinhVien) tongDiem += sv.diemGPA
                if (danhSachSinhVien.isNotEmpty()) println("=> GPA trung binh cua tat ca: ${String.format("%.2f", tongDiem / danhSachSinhVien.size)}")
                else println("=> Danh sach trong.")
            }
            "5" -> {
                if (danhSachSinhVien.isEmpty()) {
                    println("=> Danh sach trong.")
                } else {
                    var svDiemCaoNhat = danhSachSinhVien[0]
                    for (sv in danhSachSinhVien) {
                        if (sv.diemGPA > svDiemCaoNhat.diemGPA) {
                            svDiemCaoNhat = sv
                        }
                    }
                    println("=> Sinh vien co GPA cao nhat:")
                    svDiemCaoNhat.inThongTin()
                }
            }
            "6" -> {
                print("Nhap ID can xoa: ")
                val maSV = if (nhapLieu.hasNextLine()) nhapLieu.nextLine() else ""
                val truocKhiXoa = danhSachSinhVien.size
                danhSachSinhVien.removeIf { it.maSV == maSV }
                if (danhSachSinhVien.size < truocKhiXoa) println("=> Da xoa sinh vien thanh cong!")
                else println("=> Khong tim thay ID de xoa.")
            }
            "7" -> {
                var dem = 0
                for (sv in danhSachSinhVien) {
                    if (sv.diemGPA >= 8.0) dem++
                }
                println("=> So sinh vien co GPA >= 8.0: $dem")
            }
            "8" -> {
                var dem = 0
                for (sv in danhSachSinhVien) {
                    if (sv.diemGPA < 5.0) dem++
                }
                println("=> So sinh vien co GPA < 5.0: $dem")
            }
            "9" -> {
                print("Nhap ten nganh can tinh GPA trung binh: ")
                val chuyenNganh = if (nhapLieu.hasNextLine()) nhapLieu.nextLine() else ""
                var tongDiem = 0.0
                var dem = 0
                for (sv in danhSachSinhVien) {
                    if (sv.chuyenNganh.equals(chuyenNganh, ignoreCase = true)) {
                        tongDiem += sv.diemGPA
                        dem++
                    }
                }
                if (dem > 0) println("=> GPA trung binh nganh $chuyenNganh: ${String.format("%.2f", tongDiem / dem)}")
                else println("=> Khong co sinh vien nao trong nganh nay.")
            }
            "10" -> {
                if (danhSachSinhVien.isEmpty()) {
                    println("=> Danh sach trong.")
                } else {
                    var svLonTuoiNhat = danhSachSinhVien[0]
                    for (sv in danhSachSinhVien) {
                        if (sv.tuoiSV > svLonTuoiNhat.tuoiSV) {
                            svLonTuoiNhat = sv
                        }
                    }
                    println("=> Sinh vien lon tuoi nhat:")
                    svLonTuoiNhat.inThongTin()
                }
            }
            "11" -> {
                println("=> Danh sach sinh vien co GPA tu 7.0 den 8.5:")
                var co = false
                for (sv in danhSachSinhVien) {
                    if (sv.diemGPA in 7.0..8.5) {
                        sv.inThongTin()
                        co = true
                    }
                }
                if (!co) println("=> Khong co sinh vien nao thoa man.")
            }
            "12" -> {
                print("Nhap ten nganh can tim: ")
                val chuyenNganh = if (nhapLieu.hasNextLine()) nhapLieu.nextLine() else ""
                var co = false
                for (sv in danhSachSinhVien) {
                    if (sv.chuyenNganh.equals(chuyenNganh, ignoreCase = true)) {
                        sv.inThongTin()
                        co = true
                    }
                }
                if (!co) println("=> Khong tim thay sinh vien trong nganh nay.")
            }
            "13" -> {
                print("Nhap mot phan ten can tim: ")
                val ten = if (nhapLieu.hasNextLine()) nhapLieu.nextLine() else ""
                var co = false
                for (sv in danhSachSinhVien) {
                    if (sv.tenSV.contains(ten, ignoreCase = true)) {
                        sv.inThongTin()
                        co = true
                    }
                }
                if (!co) println("=> Khong tim thay sinh vien phu hop.")
            }
            "14" -> {
                val danhSachSapXep = danhSachSinhVien.sortedByDescending { it.diemGPA }
                println("=> Danh sach sap xep GPA giam dan:")
                for (sv in danhSachSapXep) sv.inThongTin()
            }
            "15" -> {
                val top3 = danhSachSinhVien.sortedByDescending { it.diemGPA }.take(3)
                println("=> Top 3 sinh vien GPA cao nhat:")
                for (sv in top3) sv.inThongTin()
            }
            "16" -> {
                val danhSachSapXep = danhSachSinhVien.sortedBy { it.tuoiSV }
                println("=> Danh sach sap xep theo tuoi tang dan:")
                for (sv in danhSachSapXep) sv.inThongTin()
            }
            "17" -> {
                val danhSachSapXep = danhSachSinhVien.sortedBy { it.tenSV }
                println("=> Danh sach sap xep theo ho A-Z:")
                for (sv in danhSachSapXep) sv.inThongTin()
            }
            "18" -> {
                val danhSachSapXep = danhSachSinhVien.sortedBy { it.tenSV.trim().substringAfterLast(" ") }
                println("=> Danh sach sap xep theo ten (A-Z):")
                for (sv in danhSachSapXep) sv.inThongTin()
            }
            "19" -> {
                println("=> Kiem tra tinh hop le GPA (0.0 den 10.0) cho toan bo danh sach:")
                var coLoi = false
                for (sv in danhSachSinhVien) {
                    if (sv.diemGPA < 0.0 || sv.diemGPA > 10.0) {
                        println("Phat hien GPA khong hop le:")
                        sv.inThongTin()
                        coLoi = true
                    }
                }
                if (!coLoi) {
                    println("=> Tat ca sinh vien trong danh sach deu co GPA hop le (0.0 - 10.0).")
                }
            }
            "0" -> {
                println("=> Thoat chuong trinh. Tam biet!")
                println("--------------------------------------------------------------------------------")
                return
            }
            else -> println("=> Lua chon khong hop le! Vui long chon tu 0 den 19.")
        }
    }
}