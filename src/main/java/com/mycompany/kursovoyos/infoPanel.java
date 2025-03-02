package com.mycompany.kursovoyos;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
// Панель, где будет отображаться информация о процессах
public class infoPanel extends JPanel{
    final int OTSTUP = 15;
    // 5 меток, где будет отображаться информация
    JLabel proclabel1, proclabel2, proclabel3, proclabel4, proclabel5;
    // Для перехода на новую строку использовалась разметка html с тегом <br>
    String str1 = "<html>FCFS без приоритетов<br>Количество процессов: " + processPanel.MAX_PROCESSES + "</html>";
    // Объект класса RepaintManager для двойной буферизации и Timer - для таймера (логично)
    RepaintManager rpm;
    private Timer timer, timer2;
    // Создание и добавление меток на панель с помощью конструктора
    public infoPanel(){
        proclabel1 = new JLabel(str1);
        proclabel2 = new JLabel(processPanel.str2);
        proclabel3 = new JLabel(processPanel.str3);
        proclabel4 = new JLabel(processPanel.str4);
        proclabel5 = new JLabel(processPanel.str5);
        add(proclabel1);
        add(proclabel2);
        add(proclabel3);
        add(proclabel4);
        add(proclabel5);
        // Добавление приёмника событий для компонента также, как и для processPanel
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                startInfoTimer();
            }
        });
    }
    private void startInfoTimer(){
        // На каждом шаге таймера добавлять текст с полей processPanel для актуальной
        // информации о процессах
        timer = new Timer(1000, e->{
            proclabel2.setText(processPanel.str2);
            proclabel3.setText(processPanel.str3);
            proclabel4.setText(processPanel.str4);
            proclabel5.setText(processPanel.str5);
            // Останавливать таймер при stopDrawFlag = true
            if(processHandler.stopDrawFlag){
                timer.stop();
                otherTimer();
            }
        });
        timer.start();
    }
    private void otherTimer(){
        timer2 = new Timer(1000, e->{
            proclabel5.setText("<html>Процесс: <br>" + processPanel.ph.processes.getLast().getProcessName() + "Статус: " + processStatus.FINISH.getStatusName() +
                    "<br>Время выполнения: " + processPanel.ph.processes.getLast().getSeconds() + "/" + processPanel.ph.processes.getLast().getSeconds() + "</html>");
            timer2.stop();
        });
        timer2.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        rpm = RepaintManager.currentManager(this);
        rpm.setDoubleBufferingEnabled(true);
        g2d.setStroke(new BasicStroke(4));
        // Задний фон
        g2d.setColor(drawColor.INFOBACK.getColor());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(drawColor.BLACK.getColor());
        // Добавление линии для визуального разделения панелей
        g2d.drawLine(0, 0, 0, this.getHeight());
        g2d.setColor(drawColor.BLACK.getColor());
        // Отрисовка одного прямоугольника, где будет отображено название алгоритма
        // и числа процессов. Отрисовка четырёх прямоугольников, на каждом из которых
        // будет храниться информация о своём процессе
        g2d.fillRect(OTSTUP, OTSTUP, this.getWidth() - OTSTUP * 2, (int) this.getHeight() / 12);
        g2d.setColor(drawColor.DARKBLUE.getColor());
        g2d.fillOval(OTSTUP, (int) this.getHeight() / 12 + OTSTUP * 2, this.getWidth() - OTSTUP * 2, (int) this.getHeight() / 5 - OTSTUP);
        g2d.setColor(drawColor.BLUE.getColor());
        g2d.fillOval(OTSTUP, (int) this.getHeight() / 5 + OTSTUP * 6, this.getWidth() - OTSTUP * 2, (int) this.getHeight() / 4 - OTSTUP * 4);
        g2d.setColor(drawColor.DARKGREEN.getColor());
        g2d.fillOval(OTSTUP, (int) this.getHeight() / 5 + OTSTUP * 17, this.getWidth() - OTSTUP * 2, (int) this.getHeight() / 4 - OTSTUP * 4);
        g2d.setColor(drawColor.DARKBLUE.getColor());
        g2d.fillOval(OTSTUP, (int) this.getHeight() / 2 + OTSTUP * 12, this.getWidth() - OTSTUP * 2, (int) this.getHeight() / 4 - OTSTUP * 4);
        g2d.setStroke(new BasicStroke(2));
        proclabel1.setLocation(OTSTUP * 4, OTSTUP + 10);
        proclabel1.setForeground(drawColor.LIGHTGRAY.getColor());
        proclabel2.setLocation(this.getWidth() / 3, (int) this.getHeight() / 12 + OTSTUP * 2 + 10);
        proclabel2.setForeground(drawColor.LIGHTGRAY.getColor());
        proclabel3.setLocation(this.getWidth() / 3, (int) this.getHeight() / 5 + OTSTUP * 6 + 10);
        proclabel3.setForeground(drawColor.LIGHTGRAY.getColor());
        proclabel4.setLocation(this.getWidth() / 3, (int) this.getHeight() / 5 + OTSTUP * 17 + 10);
        proclabel4.setForeground(drawColor.LIGHTGRAY.getColor());
        proclabel5.setLocation(this.getWidth() / 3, (int) this.getHeight() / 2 + OTSTUP * 12 + 10);
        proclabel5.setForeground(drawColor.LIGHTGRAY.getColor());
    }
}
