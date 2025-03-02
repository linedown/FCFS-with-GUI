package com.mycompany.kursovoyos;
import java.awt.*;
import javax.swing.*;
// Класс, наследуемый от класса окна (JFrame)
public class Fcfs extends JFrame{
    // Путь к изображению, которое будет использовано для создания новой иконки
    private static final String ICONPATH = "C:\\Users\\press f\\Documents\\NetBeans20Project\\kursovoyos\\src\\main\\java\\com\\mycompany\\kursovoyos\\images\\rain.png";
    // Объект, типа менеджера компоновки GridBagLayout и объект, контролирующий отрисовку на GridBagLayout
    GridBagLayout gbl;
    GridBagConstraints gbc;
    // 2 объекта классов панелей
    JPanel ProcessPanel;
    JPanel InfoPanel;
    // Таймер для перерисовки панелей
    Timer timer;
    // Конструктор
    public Fcfs(int x, int y){
        // Создание объекта типа ImageIcon
        ImageIcon icon = new ImageIcon(ICONPATH);
        // Установка заголовка окна, его размеров, отсутствие масштабируемости
        // Задание стандартной операции по закрытию окна, его видимости и установка иконки
        this.setTitle("FCFS с GUI");
        this.setSize(x, y);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setIconImage(icon.getImage());
        // Создание менеджера компоновки GridBagLayout
        gbl = new GridBagLayout();
        // Установка соотношения размеров панелей - 3 к 4
        gbl.columnWidths = new int[] {3*x/4, y/4};
        // Установка минимальных высот строк
        gbl.rowHeights = new int[] {x};
        // Установка весов столбцов
        gbl.columnWeights = new double[] {1, 1};
        // Установка весов строк
        gbl.rowWeights = new double[] {1};
        // Установка менеджера компоновки
        this.setLayout(gbl);
        // Создание объекта служебного класса GridBagConstraints
        gbc = new GridBagConstraints();
        // В случае, если область отображения больше, чем указанный размер, задать
        // изменение компонента по вертикали и горизонтали
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        // Создание и добавление панелей в класс Fcfs, запуск таймера
        ProcessPanel = new processPanel();
        this.addPanels(0, 0, 1, 1, ProcessPanel);
        InfoPanel = new infoPanel();
        this.addPanels(0, 1, 1, 2, InfoPanel);
        startRepaintTimer();
    }
    // Метод, добавляющий панели в окно
    private void addPanels(int row, int col, int height, int width,
        Component com) {
        // Установка переднего и верхнего краёв области отображения
        gbc.gridx = col;
        gbc.gridy = row;
        // Количество ячеек в столбце и в строке для области отображения компонента
        gbc.gridheight = height;
        gbc.gridwidth = width;
        gbl.setConstraints(com, gbc);
        this.getContentPane().add(com);
    }
    private void startRepaintTimer(){
        timer = new Timer(1000, e->{
            // Перерисовка панелей
            ProcessPanel.repaint();
            InfoPanel.repaint();
            // До тех пор, пока stopDrawFlag возвращает false. Иначе - остановка таймера
            if(processHandler.stopDrawFlag) timer.stop();
        });
        timer.start();
    }
    public static void main(String[] args){
        new Fcfs(850, 850); // Создание объекта класса Fcfs размером 850х850
    }
}
