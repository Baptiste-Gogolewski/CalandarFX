module src.calendarfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires xstream;


    opens src.calendarfx to javafx.fxml, xstream;
    exports src.calendarfx;
}