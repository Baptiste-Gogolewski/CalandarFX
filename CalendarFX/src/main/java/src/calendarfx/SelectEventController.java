package src.calendarfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SelectEventController
{
    @FXML
    private Pane TitlePane;

    @FXML
    private TextField EventNameTextField;

    @FXML
    private TextField YearTextField;

    @FXML
    private TextField MonthTextField;

    @FXML
    private TextField DayTextField;

    @FXML
    private Button SelectButton;

    @FXML
    private Button CancelSelection;

    @FXML
    private ImageView CloseButton;

    @FXML
    private ImageView MinimizeButton;

    private double x, y;

    public CalendarApplication CalendarApplication;

    private Stage secondaryStage;

    public String Place;
    public String Time;
    public String Description;

    public void Init(Stage secondaryStage)
    {
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

    public void SelectGoodEvent(String eventName, String year, String month, String day)
    {
        for (Event event : CalendarApplication.GetEventList().GetEventActivities())
        {
            if (event.GetNameEvent().equals(eventName) && event.GetYear().equals(year) && event.GetMonth().equals(month) && event.GetDay().equals(day))
            {
                this.Place = event.GetPlace();
                this.Time = event.GetTime();
                this.Description = event.GetDescription();
            }
        }
    }

    @FXML
    private void SelectEvent() throws IOException
    {
        if (EventNameTextField.getText() != null && YearTextField.getText() != null && MonthTextField.getText() != null && DayTextField.getText() != null)
        {
            SelectGoodEvent(EventNameTextField.getText(), YearTextField.getText(), MonthTextField.getText(), DayTextField.getText());
            OpenEditEventView(EventNameTextField.getText(), YearTextField.getText(), MonthTextField.getText(), DayTextField.getText(), this.Place, this.Time, this.Description);
            this.secondaryStage.close();
        }
        else
        {
            System.out.println("Aucune donnée");
            this.secondaryStage.close();
        }
    }

    public void OpenEditEventView(String eventNameTextField, String yearTextField, String monthTextField, String dayTextField, String place, String time, String description) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarController.class.getResource("EditView.fxml"));
        AnchorPane AnchorPane = (AnchorPane) fxmlLoader.load();

        Stage EditWindow = new Stage();
        EditWindow.initModality(Modality.APPLICATION_MODAL);
        EditWindow.initOwner(secondaryStage);

        Scene Scene = new Scene(AnchorPane);

        EditWindow.initStyle(StageStyle.TRANSPARENT);
        Scene.setFill(Color.TRANSPARENT);

        EditWindow.setScene(Scene);

        ((EditController) fxmlLoader.getController()).Init(EditWindow);
        EditController editController = fxmlLoader.getController();
        editController.SetCalandarApp(CalendarApplication);

        editController.SetEventNameTextField(eventNameTextField);
        editController.SetYearTextField(yearTextField);
        editController.SetMonthTextField(monthTextField);
        editController.SetDayTextField(dayTextField);
        editController.SetPlaceTextField(place);
        editController.SetTimeTextField(time);
        editController.SetDescriptionArea(description);

        EditWindow.showAndWait();
    }

    @FXML
    private void CancelSelection()
    {
        secondaryStage.close();
    }

    @FXML
    private void SelectPressedButton()
    {
        SelectButton.setStyle("-fx-background-color: #ABB2B9;");
    }

    @FXML
    private void SelectUnPressedButton()
    {
        SelectButton.setStyle("-fx-background-color: #1C2833;");
    }

    @FXML
    private void CancelPressedButton()
    {
        CancelSelection.setStyle("-fx-background-color: #ABB2B9;");
    }

    @FXML
    private void CancelUnPressedButton()
    {
        CancelSelection.setStyle("-fx-background-color: #1C2833;");
    }

    public void SetCalandarApp(CalendarApplication calendarApplication)
    {
        this.CalendarApplication = calendarApplication;
    }
}
