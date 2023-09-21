package src.calendarfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditController
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
    private Button EditButton;

    @FXML
    private Button CancelEdit;

    private double x, y;

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

    public void SetCalandarApp(CalendarApplication calendarApplication)
    {

    }

    @FXML
    private void CancelEdit()
    {
        secondaryStage.close();
    }

    @FXML
    private void CancelPressedButton()
    {
        CancelEdit.setStyle("-fx-background-color: #ABB2B9;");
    }

    @FXML
    private void CancelUnPressedButton()
    {
        CancelEdit.setStyle("-fx-background-color: #1C2833;");
    }

    @FXML
    private void EditPressedButton()
    {
        EditButton.setStyle("-fx-background-color: #ABB2B9;");
    }

    @FXML
    private void EditUnPressedButton()
    {
        EditButton.setStyle("-fx-background-color: #1C2833;");
    }
}
