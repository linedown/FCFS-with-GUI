package com.mycompany.kursovoyos;
import java.util.LinkedList;
// Класс обработчика процесса
public class processHandler {
    // Список процессов, которые будут запускаться на выполнение
    LinkedList<drawProcess> processes;
    // Поле-флаг, которое будет true в случае выполнения последнего шага последнего процесса
    static boolean stopDrawFlag = false;
    // id, по которому будет проходится метод draw и максимальное допустимое число процессов
    private int id = 1;
    private final int maxProcesses;
    // (Конструктор) Создание списка и инициализация количества процессов
    public processHandler(int maxProcesses) {
        processes = new LinkedList<>();
        this.maxProcesses = maxProcesses;
    }
    // Метод, добавляющий процесс в список. Если список нулевой - добавляет и вызывает метод start
    public void addDrawProcess(drawProcess dp){
        if(processes.isEmpty()){
            processes.add(dp);
            dp.start();
        }
        processes.add(dp);
    }
    // Метод, управляющий отрисовкой процессов
    public void draw(){
        // При пустом списке - возврат из метода
        if(processes.isEmpty()) return;
        // Отрисовка процесса с нужным id
        processes.get(id).drawStr();
        // При завершении процесса переходить к следующему, пока не закончатся невыполненные
        if(processes.get(id).getStatus() == processStatus.FINISH){
            id++;
            if(id <= maxProcesses) processes.get(id).start();
            else stopDrawFlag = true;
        }
    } 
}
