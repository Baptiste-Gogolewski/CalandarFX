package src.calendarfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class EventController
{
    @FXML
    private Pane TitlePane;

    @FXML
    private ImageView CloseButton;

    @FXML
    private ImageView MinimizeButton;

    @FXML
    private TextField EventNameTextField;

    @FXML
    private TextField PlaceTextField;

    @FXML
    private TextField TimeTextField;

    @FXML
    private TextField YearTextField;

    @FXML
    private TextField MonthTextField;

    @FXML
    private TextField DayTextField;

    @FXML
    private TextArea DescriptionArea;

    @FXML
    private Button AddButton, CancelEvent;

    private double x, y;
    private Stage secondaryStage;

    //private EventList EventList = new EventList();
    public CalendarApplication CalendarApplication;


    public void Init(Stage secondaryStage)
    {
        //EventList = new EventList();

        this.secondaryStage = secondaryStage;

        // Move the window
        TitlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        TitlePane.setOnMouseDragged(mouseEvent -> {
            secondaryStage.setX(mouseEvent.getScreenX() - x);
            secondaryStage.setY(mouseEvent.getScreenY() - y);
        });

        // Close the window
        CloseButton.setOnMouseClicked(mouseEvent -> {
            secondaryStage.close();
        });

        // Minimize the window
        MinimizeButton.setOnMouseClicked(mouseEvent -> {
            secondaryStage.setIconified(true);
        });
    }

    // Add an event to the calandar
    // Doit lier et ajouter au calendrier ou avant faire en console
    @FXML
    private void AddEvent()
    {
        if (InputValid())
        {
            Event Event = new Event(EventNameTextField.getText(), YearTextField.getText(), MonthTextField.getText(), DayTextField.getText(), PlaceTextField.getText(), DescriptionArea.getText(), TimeTextField.getText());

            CalendarApplication.AddEvent(Event);

            ShowListEvent();

            secondaryStage.close();
        }
        else if (InputValid())
        {
            Event Event = new Event(EventNameTextField.getText(), PlaceTextField.getText(), TimeTextField.getText(), YearTextField.getText(), MonthTextField.getText(), DayTextField.getText());

            CalendarApplication.AddEvent(Event);

            //ShowListEvent();

            secondaryStage.close();
        }
        else if (InputValid())
        {
            Event Event = new Event(EventNameTextField.getText(), PlaceTextField.getText(), YearTextField.getText(), MonthTextField.getText(), DayTextField.getText());

            CalendarApplication.AddEvent(Event);

            //ShowListEvent();

            secondaryStage.close();
        }
        else if (InputValid())
        {
            Event Event = new Event(EventNameTextField.getText(), YearTextField.getText(), MonthTextField.getText(), DayTextField.getText());

            CalendarApplication.AddEvent(Event);

            //ShowListEvent();

            secondaryStage.close();
        }
    }

    private void ShowListEvent()
    {
        for (Event events : CalendarApplication.GetEventList().GetEventActivities())
            System.out.println("NameEvent : " + events.GetNameEvent() + " , Date : " + events.GetYear() + "/" + events.GetMonth() + "/" + events.GetDay());
    }

    public boolean InputValid()
    {
        if (EventNameTextField.getText() != null && YearTextField.getText() != null && MonthTextField.getText() != null && DayTextField.getText() != null && PlaceTextField.getText() != null && DescriptionArea.getText() != null && TimeTextField.getText() != null)
        {
            return true;
        }
        else if (EventNameTextField.getText() != null && PlaceTextField.getText() != null && TimeTextField.getText() != null && YearTextField.getText() != null && MonthTextField.getText() != null && DayTextField.getText() != null)
        {
            return true;
        } else if (EventNameTextField.getText() != null && PlaceTextField.getText() != null && YearTextField.getText() != null && MonthTextField.getText() != null && DayTextField.getText() != null)
        {
            return true;
        }
        else if (EventNameTextField.getText() != null && YearTextField.getText() != null && MonthTextField.getText() != null && DayTextField.getText() != null)
        {
            return true;
        }
        else
            return false;
    }

    @FXML
    private void CancelEvent()
    {
        secondaryStage.close();
    }

    @FXML
    private void AddPressedButton()
    {
        AddButton.setStyle("-fx-background-color: #ABB2B9;");
    }

    @FXML
    private void AddUnPressedButton()
    {
        AddButton.setStyle("-fx-background-color: #1C2833;");
    }

    @FXML
    private void CancelPressedButton()
    {
        CancelEvent.setStyle("-fx-background-color: #ABB2B9;");
    }

    @FXML
    private void CancelUnPressedButton()
    {
        CancelEvent.setStyle("-fx-background-color: #1C2833;");
    }

    public void SetCalandarApp(CalendarApplication calendarApplication)
    {
        this.CalendarApplication = calendarApplication;
    }
}
