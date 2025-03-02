package com.mycompany.kursovoyos;
import java.awt.Color;
// Перечисление цветов, которые используются при отрисовке на панелях
public enum drawColor {
    DARKGRAY("Тёмно-серый", new Color(68, 67, 67)),
    WHITE("Белый", Color.WHITE),
    MEDIUMGRAY("Серый", new Color(96, 97, 95)),
    LIGHTGRAY("Светло-серый", new Color(151, 150, 150)),
    LIGHTBLUE("Голубой", new Color(21, 123, 170)),
    BLUE("Синий", new Color(24, 85, 150)),
    BLACK("Чёрный", new Color(0, 1, 3)),
    INFOBACK("Заливка панели информации о процессах", new Color(105, 130, 156)),
    DARKBLUE("Тёмно-синий", new Color(26, 66, 99)),
    DARKGREEN("Тёмно-зелёный", new Color(13, 99, 81)),
    DARKCYAN("Бирюзовый", new Color(17, 96, 98));
    // Поля типов данных String - название цвета и Color - сам цвет в формате rgb
    private final String colorStr;
    private final Color color;
    // Конструктор, где инициализируются эти поля
    drawColor(String colorStr, Color color){
        this.colorStr = colorStr;
        this.color = color;
    }
    // Геттеры для получения названия цвета и самого цвета
    public String getColorStr() {
        return colorStr;
    }
    public Color getColor() {
        return color;
    }
}
