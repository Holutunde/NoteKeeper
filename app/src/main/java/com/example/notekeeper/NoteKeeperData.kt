package com.example.notekeeper


data class CourseInfo (val courseId: String, val title: String) {
    override fun toString(): String {
        //The value to display -title
        return title;
    }
}

//For each note the user create, an instance of noteInfo is created
//Course is a reference to the courseInfo class
data class NoteInfo(var course: CourseInfo? = null, var title: String? = null, var text: String? = null)