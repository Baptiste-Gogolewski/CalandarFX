module src.calendarfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens src.calendarfx to javafx.fxml;
    exports src.calendarfx;
}