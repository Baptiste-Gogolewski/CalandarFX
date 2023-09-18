package src.calendarfx;

import com.thoughtworks.xstream.io.xml.DomDriver;
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
import com.thoughtworks.xstream.XStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CalendarApplication extends Application
{
    int year = 2023;
    //Calendar Calendar = new GregorianCalendar(year, java.util.Calendar.JANUARY, 1);
    Calendar Calendar = java.util.Calendar.getInstance();

    FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("CalendarView.fxml"));

    // List des activité
    private EventList EventList = new EventList();

    static XStream Xstream = new XStream(new DomDriver());

    // Nom du fichier XML
    private static String FileName = "ListPersons.xml";

    //EventController eventController;


    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Xstream.alias("Event", Event.class);

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


        Image Image = new Image("C:\\Users\\gogol\\OneDrive\\Documents\\Java\\CalandarFX\\CalendarFX\\src\\main\\resources\\src\\calendarfx\\IconCalendar.png");
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

    public void AddXMLEvents(List<Event> listEvent) throws IOException
    {
        try
        {
            // Créez un objet FileWriter pour écrire dans le fichier
            FileWriter writer = new FileWriter(FileName);

            // Écrivez le XML dans le fichier
            String xml = Xstream.toXML(listEvent);
            writer.write(xml);

            // Fermez le FileWriter pour libérer les ressources
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<Event> ReadXMLListEvent() throws IOException
    {
        try
        {
            // Créez un objet FileReader pour lire le fichier XML
            FileReader reader = new FileReader(FileName);

            // Configuration personnalisée pour permettre la désérialisation de la classe Event car c'est elle qui est lu
            Xstream.allowTypes(new Class[]{Event.class});

            // Utilisez XStream pour désérialiser le XML en une liste d'event
            List<Event> listEvents = (List<Event>) Xstream.fromXML(reader);

            // Fermez le FileReader pour libérer les ressources
            reader.close();

            return listEvents;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}