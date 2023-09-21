package src.calendarfx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class CalendarController
{
    private double x, y;

    @FXML
    private GridPane DayGridPane;

    @FXML
    private Pane TitlePane;

    @FXML
    private ImageView CloseButton;

    @FXML
    private ImageView MinimizeButton;

    @FXML
    private Label MonthLabel;

    @FXML
    private Label TimeLabel;

    @FXML
    private Label CheckBoxLabel;

    @FXML
    private ChoiceBox<String> ChoiceBox;

    Calendar Calandar;
    Calendar SecondCalandar = java.util.Calendar.getInstance();
    Stage primaryStage;
    CalendarApplication CalendarApplication;

    //String CompteurMonth;

    public void Init(Stage primaryStage, Calendar calendar, CalendarApplication calendarApplication)
    {
        ChoiceBox.getItems().addAll("Add", "Edit");     // Add a list of string
        ChoiceBox.getSelectionModel().selectFirst();    // Select the first string when the app starts

        this.Calandar = calendar;
        this.primaryStage = primaryStage;
        this.CalendarApplication = calendarApplication;


        // Ferme la fenêtre
        CloseButton.setOnMouseClicked(mouseEvent -> {
            try
            {
                this.CalendarApplication.AddXMLEvents(this.CalendarApplication.GetEventList().GetEventActivities());
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            primaryStage.close();
        });

        // Minimize la fenetre
        MinimizeButton.setOnMouseClicked(mouseEvent -> {
            primaryStage.setIconified(true);
        });

        TitlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        TitlePane.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() - x);
            primaryStage.setY(mouseEvent.getScreenY() - y);
        });

        MonthLabel.setText(Calandar.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ENGLISH) + " " + Calandar.get(Calendar.YEAR));


        DrawCalandar();
    }

    @FXML
    private void Up()
    {
        //CompteurMonth = GetStringMonth(Calandar.get(Calendar.MONTH));
        //int tempMonth = Integer.parseInt(CompteurMonth);
        //tempMonth -= 1;
        //CompteurMonth = String.valueOf(tempMonth);
        Calandar.add(Calendar.MONTH, 1);
        MonthLabel.setText(Calandar.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ENGLISH) + " " + Calandar.get(Calendar.YEAR));
        DrawCalandar();
    }

    @FXML
    private void Down()
    {
        Calandar.add(Calendar.MONTH, -1);
        MonthLabel.setText(Calandar.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ENGLISH) + " " + Calandar.get(Calendar.YEAR));
        DrawCalandar();
    }

    public void SetTimeLabel(String time)
    {
        TimeLabel.setText(time);
    }

    public void InitCalandar()
    {
        DayGridPane.getChildren().clear();
        for (int row = 0; row < 5; row++)
        {
            for (int col = 0; col < 7; col++)
            {
                // Affiche le date du jour
                Label Label = new Label();
                Label.setTextFill(Color.rgb(204, 204, 204));
                //Label.setStyle("-fx-border-color: #000000;");

                // Pour l'ajout d'un evennement
                Pane Details = new Pane();
                Details.setPrefHeight(65);
                Details.setStyle("-fx-background-color: #1C2833;");

                VBox Box = new VBox();
                Box.setSpacing(5);     // Espacement entre les noeuds
                Box.setAlignment(Pos.TOP_CENTER);
                Box.setStyle("-fx-border-color: #2471A3; -fx-border-width: 2px;");

                VBox.setMargin(Details, new Insets(0, 5, 2, 5));

                Box.getChildren().add(Label);
                Box.getChildren().add(Details);

                DayGridPane.add(Box, col, row);
            }
        }
    }

    public void DrawCalandar()
    {
        InitCalandar();

        int dayOfMonth = 1;
        int daysInMonth = Calandar.getActualMaximum(Calendar.DAY_OF_MONTH);     // Mémorise le nombre de jour dans un mois
        int CurrentDayInMonth = Calandar.get(Calendar.DAY_OF_MONTH);     // Mémorise la date actuelle par exemple le 27 juin ...
        String CurrentMonth = GetStringMonth(SecondCalandar.get(Calendar.MONTH));

        // Decalage au 1er du mois
        Calandar.set(Calandar.DAY_OF_MONTH, 1);

        // Obtention du jour de la semaine correspondant au 1er juin
        int dayOfWeek = Calandar.get(Calendar.DAY_OF_WEEK) - 1;

        // Obtention du mois en chaine de caractères : 01 -> Janvier au lieu de 1
        String StringMonth = GetStringMonth(Calandar.get(Calendar.MONTH));
        //System.out.println(StringMonth);

        // Obtention de l'année
        String StringYear = String.valueOf(Calandar.get(Calendar.YEAR));
        //System.out.println(StringYear);

        // Rebascule à la date mémoriser
        Calandar.set(Calendar.DAY_OF_MONTH, CurrentDayInMonth);


        // Faire un décalage sur la première ligne et ensuite annuler pour la suivante
        for (int row = 0; row < 5; row++)
        {
            for (int col = 0; col < 7; col++)
            {
                if (row == 1)
                    dayOfWeek = 0;
                else if (col + dayOfWeek == 7)
                {
                    break;
                }

                if (dayOfMonth <= daysInMonth)
                {
                    // Le jour actuelle a modifier !
                    if (dayOfMonth == CurrentDayInMonth && CurrentMonth.equals(StringMonth))
                    {
                        // Affiche la date du jour
                        Label Label = new Label(String.valueOf(dayOfMonth));
                        Label.setTextFill(Color.rgb(204, 204, 204));
                        //Label.setStyle("-fx-border-color: #000000;");

                        // Pour l'ajout d'un evennement
                        Pane Details = new Pane();
                        Details.setPrefHeight(65);
                        Details.setStyle("-fx-background-color: #1C2833;");

                        // Contient le texte des events à afficher
                        VBox TextContainer = new VBox();

                        // Affichage d'un evenement
                        for (Event events : CalendarApplication.GetEventList().GetEventActivities())
                        {
                            if (events.GetYear().equals(StringYear) && events.GetMonth().equals(StringMonth) && events.GetDay().equals(CorrectDay(String.valueOf(dayOfMonth))))
                            {
                                Label Text = new Label(events.GetNameEvent());
                                Text.setTextFill(Color.rgb(204, 204, 204));

                                TextContainer.getChildren().add(Text);

                                //Details.getChildren().add(TextContainer);
                            }


                        }
                        Details.getChildren().add(TextContainer);

                        Details.setOnMouseClicked(mouseEvent -> {
                            try
                            {
                                if (ChoiceBox.getValue().equals("Add"))
                                    OpenAddEventView();
                                else
                                    OpenEditEventView();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                                System.out.println("Chargement impossible de la fenetre d'un evenement");
                            }
                        });

                        VBox Box = new VBox();
                        Box.setSpacing(5);     // Espacement entre les noeuds
                        Box.setAlignment(Pos.TOP_CENTER);
                        Box.setStyle("-fx-border-color: #CACFD2; -fx-border-width: 4px;");

                        VBox.setMargin(Details, new Insets(0, 5, 2, 5));

                        Box.getChildren().add(Label);
                        Box.getChildren().add(Details);

                        DayGridPane.add(Box, col + dayOfWeek, row);

                        dayOfMonth++;
                    }
                    else
                    {
                        // Affiche la date du jour
                        Label Label = new Label(String.valueOf(dayOfMonth));
                        Label.setTextFill(Color.rgb(204, 204, 204));
                        //Label.setStyle("-fx-border-color: #000000;");

                        // Pour l'ajout d'un evennement
                        Pane Details = new Pane();
                        Details.setPrefHeight(65);
                        Details.setStyle("-fx-background-color: #1C2833;");

                        // Contient le texte des events à afficher
                        VBox TextContainer = new VBox();

                        // Affichage d'un evenement
                        for (Event events : CalendarApplication.GetEventList().GetEventActivities())
                        {
                            if (events.GetYear().equals(StringYear) && events.GetMonth().equals(StringMonth) && events.GetDay().equals(CorrectDay(String.valueOf(dayOfMonth))))
                            {
                                Label Text = new Label(events.GetNameEvent());
                                Text.setTextFill(Color.rgb(204, 204, 204));

                                TextContainer.getChildren().add(Text);
                            }


                        }
                        Details.getChildren().add(TextContainer);


                        Details.setOnMouseClicked(mouseEvent -> {
                            try
                            {
                                if (ChoiceBox.getValue().equals("Add"))
                                    OpenAddEventView();
                                else
                                    OpenEditEventView();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                                System.out.println("Chargement impossible de la fenetre d'un evenement");
                            }
                        });

                        VBox Box = new VBox();
                        Box.setSpacing(5);     // Espacement entre les noeuds
                        Box.setAlignment(Pos.TOP_CENTER);
                        Box.setStyle("-fx-border-color: #2471A3; -fx-border-width: 2px;");

                        VBox.setMargin(Details, new Insets(0, 5, 2, 5));

                        Box.getChildren().add(Label);
                        Box.getChildren().add(Details);

                        DayGridPane.add(Box, col + dayOfWeek, row);

                        dayOfMonth++;
                    }
                }
            }
        }
    }


    public void OpenAddEventView() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarController.class.getResource("EventView.fxml"));
        AnchorPane AnchorPane = (AnchorPane) fxmlLoader.load();     // Cast


        Stage EventWindow = new Stage();
        EventWindow.initModality(Modality.APPLICATION_MODAL);
        EventWindow.initOwner(primaryStage);    // Spécifier la fenêtre parente d'un Stage

        Scene Scene = new Scene(AnchorPane);

        EventWindow.initStyle(StageStyle.TRANSPARENT);
        Scene.setFill(Color.TRANSPARENT);

        EventWindow.setScene(Scene);

        ((EventController) fxmlLoader.getController()).Init(EventWindow);
        EventController eventController = fxmlLoader.getController();
        eventController.SetCalandarApp(CalendarApplication);

        EventWindow.showAndWait();
    }

    public void OpenEditEventView() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(CalendarController.class.getResource("SelectEvent.fxml"));
        AnchorPane AnchorPane = (AnchorPane) fxmlLoader.load();

        Stage SelectWindow = new Stage();
        SelectWindow.initModality(Modality.APPLICATION_MODAL);
        SelectWindow.initOwner(primaryStage);

        Scene Scene = new Scene(AnchorPane);

        SelectWindow.initStyle(StageStyle.TRANSPARENT);
        Scene.setFill(Color.TRANSPARENT);

        SelectWindow.setScene(Scene);

        ((SelectEventController) fxmlLoader.getController()).Init(SelectWindow);
        SelectEventController selectEventController = fxmlLoader.getController();
        selectEventController.SetCalandarApp(CalendarApplication);

        SelectWindow.showAndWait();
    }

    // Tri la liste de base et retourne une liste avec les evenemnts d'un meme jour
    public EventList TriEventList(Event event)
    {
        EventList EventList = new EventList();

        for (Event event1 : CalendarApplication.GetEventList().GetEventActivities())
        {
            if (event.GetYear().equals(event1.GetYear()) && event.GetMonth().equals(event1.GetMonth()) && event.GetDay().equals(event1.GetDay()))
            {
                EventList.AddEvent(event1);
            }
        }

        return EventList;
    }

    public String GetStringMonth(int number)
    {
        switch (number)
        {
            case 0:
                return "0" + String.valueOf(number + 1);

            case 1:
                return "0" + String.valueOf(number + 1);

            case 2:
                return "0" + String.valueOf(number + 1);

            case 3:
                return "0" + String.valueOf(number + 1);

            case 4:
                return "0" + String.valueOf(number + 1);

            case 5:
                return "0" + String.valueOf(number + 1);

            case 6:
                return "0" + String.valueOf(number + 1);

            case 7:
                return "0" + String.valueOf(number + 1);

            case 8:
                return "0" + String.valueOf(number + 1);

            case 9:
                return String.valueOf(number + 1);

            case 10:
                return String.valueOf(number + 1);

            case 11:
                return String.valueOf(number + 1);

            default:
                break;
        }

        return null;
    }

    // Retourne une chaine de caractère sous la forme de 1 -> 01 ou 5 -> 05
    public String CorrectDay(String day)
    {
        if (Integer.parseInt(day) < 10)
        {
            switch (day)
            {
                case "1":
                    return "0" + day;

                case "2":
                    return "0" + day;

                case "3":
                    return "0" + day;

                case "4":
                    return "0" + day;

                case "5":
                    return "0" + day;

                case "6":
                    return "0" + day;

                case "7":
                    return "0" + day;

                case "8":
                    return "0" + day;

                case "9":
                    return "0" + day;

                default:
                    return day;
            }
        }

        return day;
    }
}