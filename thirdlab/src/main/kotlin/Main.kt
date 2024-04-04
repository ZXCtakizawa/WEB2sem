package org.example

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    val ctx: ApplicationContext = AnnotationConfigApplicationContext(ToDoConfig::class.java)

    var toDo: ToDo = ctx.getBean(ToDo::class.java)

    toDo.add(ToDoItem("Помыть посуду", Status.ACTIVE))
    toDo.add(ToDoItem("Убраться", Status.ACTIVE))
    toDo.add(ToDoItem("Погулять с собакой", Status.DONE))

    //toDo.listOutPut()

    //toDo.deleteDone()

    //toDo.listOutPut()
    //toDo.listOutPutDesc("Помыть посуду")

    //toDo.addSubItem("Помыть посуду", "Достать тряпку", Status.ACTIVE)
    //toDo.addSubItem("Убраться","Налить воды", Status.ACTIVE)
    //toDo.listOutPutWithSubTasks()


    var option: String?
    do {
        println("Выберите действие:")
        println("1. Вывести список задач")
        println("2. Удалить завершенные задачи")
        println("3. Добавить подзадачу")
        println("4. Выйти из программы")


        option = readLine()

        when (option) {
            "1" -> {
                println("Список задач:")
                toDo.listOutPutWithSubTasks()
            }
            "2" -> {
                println("Удаление завершенных задач:")
                toDo.deleteDone()
            }
            "3" -> {
                println("Добавление подзадачи:")
                println("Введите описание родительской задачи:")
                val parentDescription = readLine() ?: ""
                println("Введите описание подзадачи:")
                val subDescription = readLine() ?: ""
                println("Введите статус подзадачи (ACTIVE или DONE):")
                val subStatus = readLine()?.let { Status.valueOf(it) } ?: Status.ACTIVE
                toDo.addSubItem(parentDescription, subDescription, subStatus)
            }
            "4" -> {
                println("Удаление подзадачи")
                println("Выберите подзадачу")
                toDo.listOutPutWithSubTasks()


            }
            "8" -> {
                println("Выход из программы")
            }
            else -> {
                println("Некорректный ввод")
            }
        }
    } while (option != "8")
}