package org.example

class ToDo(private var toDoList: MutableList<ToDoItem> = mutableListOf()) {
    fun add(item: ToDoItem) = toDoList.add(item)

    fun delete(description: String): Boolean = toDoList.removeIf{ it.description.equals(description)}

    fun deleteActive(): Boolean = toDoList.removeIf { it.status.equals(Status.ACTIVE)}

    fun deleteDone(): Boolean = toDoList.removeIf { it.status.equals(Status.DONE)}

    fun deleteAll() = toDoList.clear()

    fun changeDescription(descriptionOld: String, descriptionNew: String, statusNew: Status): Boolean {

        var item = find(descriptionOld)

        if (item != null && descriptionNew != null) {
            toDoList.set(toDoList.indexOf(item), item.apply {this.description = descriptionNew})
            return true
        }
        else
            return false
    }
    fun changeStatus(description: String, status: Status): Boolean {
        var item = find(description)

        if (item != null) {
            toDoList.set(toDoList.indexOf(item), item.apply{ this.status = status})
            return true
        }
        else
            return false
    }
    fun listToDo(status: Status? = null): List<ToDoItem> = when(status) {
        Status.ACTIVE -> toDoList.filter { it.status.equals(Status.ACTIVE)}
        Status.DONE -> toDoList.filter {it.status.equals(Status.DONE)}
        null -> toDoList
    }
    private fun find(description: String): ToDoItem? = toDoList.find { it.description.equals(description)}

    fun listOutPut(){
        for (item in toDoList) {
            println("Описание: ${item.description}, Статус: ${item.status}")
        }
    }
    fun listOutPutDesc(description: String){
        val foundItem = find(description)
        if (foundItem != null) {
            println("Описание: ${foundItem.description}, Статус: ${foundItem.status}")
        }
        else {
            println("ToDo не найдено")
        }
    }
    fun addSubItem(parentDescription: String, subDescription: String, subStatus: Status) {
        val parentItem = find(parentDescription)
        if (parentItem != null) {
            val subTask = ToDoItem(subDescription, subStatus)
            parentItem.addSubTask(subTask)
        } else {
            println("Parent ToDo not found.")
        }
    }
    fun listOutPutWithSubTasks() {
        for (item in toDoList) {
            println("Описание: ${item.description}, Статус: ${item.status}")
            if (item.subTasks.isNotEmpty()) {
                println("Подзадачи:")
                for (subTask in item.subTasks) {
                    println("   - Описание: ${subTask.description}, Статус: ${subTask.status}")
                    println(" ")
                }
            }
        }
    }
}
