package com.example.bai2timkiemtrongds

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Khai báo các thành phần giao diện
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter

    // Danh sách sinh viên ban đầu
    private val studentList = listOf(
        Student("Nguyen Van A", "B180001"),
        Student("Tran Thi B", "B180002"),
        Student("Le Van C", "B180003"),
        Student("Pham Thi D", "B180004"),
        Student("Hoang Van E", "B180005")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liên kết các thành phần giao diện
        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recyclerView)

        // Thiết lập RecyclerView với LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Khởi tạo adapter với danh sách sinh viên ban đầu
        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        // Xử lý sự kiện nhập từ khóa tìm kiếm trong SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false // Không xử lý khi nhấn nút "Tìm kiếm"
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Khi nội dung ô tìm kiếm thay đổi
                val filteredList = if (newText.isNullOrEmpty() || newText.length <= 2) {
                    // Nếu không có từ khóa hoặc từ khóa ngắn hơn 3 ký tự, hiển thị toàn bộ danh sách
                    studentList
                } else {
                    // Lọc danh sách sinh viên dựa trên từ khóa (tìm trong tên hoặc MSSV)
                    studentList.filter {
                        it.name.contains(newText, ignoreCase = true) || it.mssv.contains(newText, ignoreCase = true)
                    }
                }

                // Cập nhật danh sách hiển thị trong RecyclerView
                studentAdapter.updateList(filteredList)

                return true
            }
        })
    }
}