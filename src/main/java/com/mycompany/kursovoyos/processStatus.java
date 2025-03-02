package com.mycompany.kursovoyos;
// Перечисления состояний процессов
public enum processStatus {
    NEW("Новый"), RUNNING("Запущен"), FINISH("Завершён");
    // Строковое представление состояния процесса
    private final String statusName;
    // Конструктор
    processStatus(String statusName){
        this.statusName = statusName;
    }
    // Геттер для получения строкового представления состояния процесса
    public String getStatusName() {
        return statusName;
    } 
}
