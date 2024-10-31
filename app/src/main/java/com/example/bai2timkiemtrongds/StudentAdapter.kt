package com.example.bai2timkiemtrongds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter cho RecyclerView để hiển thị danh sách sinh viên
class StudentAdapter(private var studentList: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    // Lớp ViewHolder đại diện cho từng item (một sinh viên)
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val mssvTextView: TextView = itemView.findViewById(R.id.mssvTextView)
    }

    // Tạo ViewHolder và liên kết với layout item_student.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    // Gán dữ liệu sinh viên vào ViewHolder
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.nameTextView.text = student.name
        holder.mssvTextView.text = student.mssv
    }

    // Trả về số lượng sinh viên trong danh sách
    override fun getItemCount(): Int {
        return studentList.size
    }

    // Cập nhật lại danh sách sinh viên và thông báo cho adapter
    fun updateList(newList: List<Student>) {
        studentList = newList
        notifyDataSetChanged() // Thông báo rằng dữ liệu đã thay đổi
    }
}