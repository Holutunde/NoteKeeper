package com.example.notekeeper

//point of management for instances of the class created in NoteKeeperData
object DataManager {
    val courses   = HashMap<String, CourseInfo>()
    //An assign once property, HashMap helps to lookup, specify the specific type types to use within the type
    //String for lookups, by courseId
    //the type to be stored, CourseInfo
    val notes = ArrayList<NoteInfo>()
    //To hold collection of notes
    //ArrayList provide index base to its members

    init {
        initializeCourses()
        initializeNotes()

    }
    fun initializeNotes() {
        var note = NoteInfo(courses["android_intents"] as CourseInfo, "Dynamic intent resolution", "Wow intents allow components to be resolved at runtime")
        notes.add(note)

        note = NoteInfo(courses["android_intents"] as CourseInfo, "Deleting intents", "PendingIntents are powerful, they delegate much more than just a component invocation")
        notes.add(note)

        note = NoteInfo(courses["android_async"] as CourseInfo, "Service default threads", "Did you know that by default an Android Service will tie up the UI thread")
        notes.add(note)

        note = NoteInfo(courses["java_lang"] as CourseInfo, "Parameters", "Leverage variable-length parameters list")
        notes.add(note)

        note = NoteInfo(courses["java_lang"] as CourseInfo, "Anonymous classes", "Anonymous classes simplify implementing one use type")
        notes.add(note)

        note = NoteInfo(courses["java_core"] as CourseInfo, "Compiler options", "The .jar options isn't compatible with the -cp option")
        notes.add(note)

        note = NoteInfo(courses["java_core"] as CourseInfo, "Serialization", "Remember to include Serial/VersionUID to assure version compatibility")
        notes.add(note)
    }

    fun initializeCourses() {
       //Creating the courses instances
        var course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        //Add the course to t he courses collection, and use courseId to lookup
        courses.set(course.courseId, course)
        course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseId, course)
       //using named parameters courseId= and title-=
        course = CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses.set(course.courseId, course)
        //Using positional parameters
        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)

    }


}