package org.example

data class ToDoItem(var description: String, var status: Status, var subTasks: MutableList<ToDoItem> = mutableListOf()){
    fun addSubTask(subTask: ToDoItem) {
        subTasks.add(subTask)
    }
    fun removeSubTask(subTask: ToDoItem): Boolean {
        return subTasks.remove(subTask)
    }
}
data class toDoSubItem(var description: String, var status: Status, var data: String) {

}
enum class Status{
    DONE, ACTIVE
}
