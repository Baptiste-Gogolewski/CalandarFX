package src.calendarfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

    @FXML
    private void SelectEvent()
    {
        if (EventNameTextField.getText() != null && YearTextField.getText() != null && MonthTextField.getText() != null && DayTextField.getText() != null)
        {
            System.out.println("Name of event : " + EventNameTextField.getText());
            this.secondaryStage.close();
        }
        else
        {
            System.out.println("Aucune donnée");
            this.secondaryStage.close();
        }
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
