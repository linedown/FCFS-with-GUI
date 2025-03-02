package com.mycompany.kursovoyos;

public enum ProcessName {
    CLOUDS("☁☁☁<br>отрисовка облаков <br>с помощью символа 'O'<br>"), 
    RAINDROPS("💧💧💧<br>отрисовка капель дождя с помощью <br>символов-палочек<br>"), 
    RAIN("🌨🌨🌨<br>имитация дождя путём постепенной<br>отрисовки капель<br>"), 
    PUDDLE("🌊<br>отрисовка лужи <br>с помощью символа '-'<br>");
    private final String name;
    private ProcessName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
