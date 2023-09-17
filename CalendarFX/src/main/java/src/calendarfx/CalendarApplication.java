package src.calendarfx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarApplication extends Application
{
    int year = 2023;
    //Calendar Calendar = new GregorianCalendar(year, java.util.Calendar.JANUARY, 1);
    Calendar Calendar = java.util.Calendar.getInstance();

    FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("CalendarView.fxml"));

    // List des activité
    private EventList EventList = new EventList();

    EventController eventController;


    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Scene Scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Calendar");

        primaryStage.setResizable(false);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene.setFill(Color.TRANSPARENT);

        CalendarController calendarController = fxmlLoader.getController();

        primaryStage.setScene(Scene);

        primaryStage.show();

        ((CalendarController)fxmlLoader.getController()).Init(primaryStage, Calendar, this);
        // Methode init pour EventList en parametre dans Init(...)


        Image Image = new Image("C:\\Users\\gogol\\OneDrive\\Documents\\Java\\CalendarFX\\src\\main\\resources\\src\\calendarfx\\IconCalendar.png");
        primaryStage.getIcons().add(Image);

        // Time
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> UpdateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public void UpdateTime()
    {
        Calendar Calendar = java.util.Calendar.getInstance();   // Peut causer des bugs ?
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String Time = simpleDateFormat.format(Calendar.getTime());
        ((CalendarController)fxmlLoader.getController()).SetTimeLabel(Time);
    }


    public void AddEvent(Event event)
    {
        this.EventList.AddEvent(event);
    }

    public CalendarApplication GetCalandarApp()
    {
        return this;
    }

    public EventList GetEventList()
    {
        return this.EventList;
    }
}