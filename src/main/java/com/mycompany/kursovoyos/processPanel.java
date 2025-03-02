package com.mycompany.kursovoyos;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
// Класс панели, на которой происходит отрисовка строк процессов
public class processPanel extends JPanel {
    // Отступ от края, индексы для изменения цвета заднего фона и смещению строк
    final int OTSTUP = 15;
    int timerIndex = 0, indexMain = 0, arrayind = 0;
    // Таймер (класс java.swing.Timer), строки, списки, где будут храниться накопленные строки
    private Timer timer;
    String cloudLine = "ООООООООООООООООООООООООООООООООООООООООО";
    String raindropsLine = "|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |";
    String rainLine = "|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | ";
    String puddleLine = "--------------------------------------------------------------------------------------------------------------";
    private final List<String> cloudList, raindropsList, rainList, puddleList;
    // Объект RepaintManager, используемый для двойной буферизации и processHandler, для контролирования процессов
    RepaintManager rpm;
    static processHandler ph;
    // Процессы, строки состояний процессов, константное число процессов - 4
    drawProcess drawProcess1, drawProcess2, drawProcess3, drawProcess4;
    static String str2, str3, str4, str5;
    static final int MAX_PROCESSES = 4;
    // Инициализация необходимых полей в конструкторе
    public processPanel(){
        cloudList = new ArrayList<>();
        raindropsList = new ArrayList<>();
        rainList = new ArrayList<>();
        puddleList = new ArrayList<>();
        ph = new processHandler(MAX_PROCESSES);
        drawProcess1 = new drawProcess(10, cloudList, cloudLine);
        drawProcess2 = new drawProcess(6, raindropsList, raindropsLine);
        drawProcess3 = new drawProcess(25, rainList, rainLine);
        drawProcess4 = new drawProcess(22, puddleList, puddleLine);
        ph.addDrawProcess(drawProcess3);
        ph.addDrawProcess(drawProcess4);
        ph.addDrawProcess(drawProcess2);
        ph.addDrawProcess(drawProcess1);
        // При конкатенации вызывается метод toString
        str2 = "" + ph.processes.get(1);
        str3 = "" + ph.processes.get(2);
        str4 = "" + ph.processes.get(3);
        str5 = "" + ph.processes.get(4);
        // Добавление приёмника событий для компонента
        addComponentListener(new ComponentAdapter() {
            // При изменении размера компонента (при создании они нулевые) - вызывать метод startTimer
            @Override
            public void componentResized(ComponentEvent ce) {
                try {
                    startTimer();
                } catch (InterruptedException e) {
                    System.out.println("Исключение: " + e.getMessage());
                }
            }
        });
    }
    private void startTimer() throws InterruptedException{
        //Thread.sleep(1000);
        // При обновлении таймера занесение информации о процессах и вызов метода draw
        timer = new Timer(1000, e ->{
            str2 = "" + ph.processes.get(1);
            str3 = "" + ph.processes.get(2);
            str4 = "" + ph.processes.get(3);
            str5 = "" + ph.processes.get(4);
            timerIndex++;
            ph.draw();
            // При флаге stopDrawFlag = true останавливать таймер
            if(processHandler.stopDrawFlag) {
                //str5 = "" + ph.processes.get(4);
                timer.stop();
            }
        });
        timer.start();
    }
    
    // Переопределение метода отрисовки компонента
    @Override
    protected void paintComponent(Graphics g) {
        rpm = RepaintManager.currentManager(this);
        // Установка двойной буферизации
        rpm.setDoubleBufferingEnabled(true);
        // Инициализация объекта Graphics2D для дополнительных возможностей отрсовки
        Graphics2D g2d = (Graphics2D) g;
        // Задание толщины строк, отрисовка прямоугольника на всю панель и задания ему цвета (экий задний фон)
        g2d.setStroke(new BasicStroke(4));
        if(220 - timerIndex * 2 >= 0) g2d.setColor(new Color(220 - timerIndex * 2, 220 - timerIndex * 2, 220 - timerIndex * 2));
        else g2d.setColor(drawColor.BLACK.getColor());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setStroke(new BasicStroke(5));
        // Задания параметров шрифта
        g2d.setFont(new Font("Times New Roman", Font.BOLD, 14));
        g2d.setColor(drawColor.DARKGRAY.getColor());
        // Отрисовка картины "дождливая погода" с помощью полей-списков, где хранятся строки и индексов
        for(String str : drawProcess1.getListOfStrings()) {
            g2d.drawString(str, this.getWidth() / 11, indexMain * OTSTUP + OTSTUP);
            indexMain++;
        }
        g2d.setColor(drawColor.LIGHTBLUE.getColor());
        for(String str : drawProcess2.getListOfStrings()) {
            g2d.drawString(str, this.getWidth() / 11, indexMain * OTSTUP + OTSTUP);
            indexMain++;
        }
        g2d.setColor(drawColor.DARKCYAN.getColor());
        for(String str : drawProcess3.getListOfStrings()){
            g2d.drawString(str, this.getWidth() / 10, indexMain * OTSTUP + OTSTUP);
            indexMain++;
        }
        indexMain = 0;
        g2d.setColor(drawColor.BLUE.getColor());
        for(String str : drawProcess4.getListOfStrings()){
           g2d.drawString(str, 0, this.getHeight() - (1.2f*OTSTUP + arrayind * (OTSTUP / 5)));
           arrayind++;
        }
        arrayind = 0;
    }
}
