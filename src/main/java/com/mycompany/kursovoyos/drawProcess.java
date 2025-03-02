package com.mycompany.kursovoyos;
import java.util.List;
// Класс, описывающий процесс отрисовки необходимых элементов на панель processPanel
public class drawProcess {
    // Поля: длительность отрисовки (секунды), список строк, которые будут отрисовываться
    private final int seconds;
    private final List<String> listOfStrings;
    // Заданная строка, счётчик выполненных шагов (1 шаг - 1 секунда) процесса
    private final String str;
    private int count;
    // Состояние процесса и его строковое представление
    private processStatus ps;
    private String psStr;
    private final String processName;
    // Конструктор, инициализирующий поля. Задание состояние процесса как НОВЫЙ
    public drawProcess(int seconds, List<String> listOfStrings, String str) {
        this.seconds = seconds;
        this.listOfStrings = listOfStrings;
        this.str = str;
        this.ps = processStatus.NEW;
        this.psStr = ps.getStatusName();
        this.count = 0;
        // Иницализация названия процесса (4 варианта)
        switch(str){
            case "ООООООООООООООООООООООООООООООООООООООООО" 
                    -> this.processName = ProcessName.CLOUDS.getName();
            case "|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |" 
                    -> this.processName = ProcessName.RAINDROPS.getName();
            case "|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | "
                    -> this.processName = ProcessName.RAIN.getName();
            case "--------------------------------------------------------------------------------------------------------------"
                    -> this.processName = ProcessName.PUDDLE.getName();
            default -> this.processName = "";
        }
    }
    // Геттеры для получения состояния процесса и списка строк на отрисовку
    public processStatus getStatus(){
        return ps;
    }
    public List<String> getListOfStrings() {
        return listOfStrings;
    }
    // Метод для перевода состояния процесса в ЗАПУЩЕН
    public void start() {
        ps = processStatus.RUNNING;
    }
    // Метод при срабатывании таймера для отрисовки строки.
    public void drawStr(){
        // Если состояние не ЗАПУЩЕН, возврат из метода
        count++;
        // Если счётчик равен длительности процесса, перевод состояния в ЗАВЕРШЕН
        if(count == seconds){
            ps = processStatus.FINISH;
            psStr = ps.getStatusName();
        }
        psStr = ps.getStatusName();
        // Частный случай для отрисовки облака - специальная каскадная отрисовка
        // Если счётчик меньше 10 - добавление в список подстроки в диапазоне от первого до 4*count + 1
        if(str.charAt(0) == 'О' && count < 10) listOfStrings.add(str.substring(0, 4*count + 1));
        // Иначе простое добавление строки в список
        else listOfStrings.add(str);
    }
    public int getSeconds() {
        return seconds;
    }
    public String getProcessName() {
        return processName;
    }
    // Переопределение метода toString так, чтобы он выдавал необходимую информацию о процессе,
    // которая будет отображаться на информационной панели (4 варианта)
    @Override
    public String toString() {
        return "<html>Процесс: <br>" + processName + "Статус: " + psStr +
                    "<br>Время выполнения: " + count + "/" + seconds + "</html>";
    }   
}
