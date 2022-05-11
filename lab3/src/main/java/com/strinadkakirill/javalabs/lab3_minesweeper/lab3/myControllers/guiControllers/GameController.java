package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.myControllers.guiControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Exchanger;

import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.Cell;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.CellConditions;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.CellState;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Field;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.myTimer.TemplateTimer;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.myTimer.TimerListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class GameController {

    public AnchorPane rootElementGame;
    @FXML
    private ResourceBundle resources;

    @FXML
    private Label flagNum;

    @FXML
    private URL location;

    Timeline timeLineChecker;

    private boolean fieldIsShown = false;

    private int fieldX = 5;
    private int fieldY = 5;

    private Field field;


    private Integer flagCount;


    /**
     * Сюда будем сохранять node из GridPane, чтобы потом можно было к ним обратиться
     */
    ArrayList<CellOfGridPane> listOfCellsInFieldPane;

    @FXML
    private Button backButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane fieldPane;


    @FXML
    private ImageView imageView00;

    @FXML
    private Text loseText;

    @FXML
    private Button menuButton;

    @FXML
    private Label timerLabel;


    private String timerString;

    @FXML
    private Text winText;

    boolean defeatCondition = false;
    boolean victoryCondition = false;

    int sizeOfCell = 25;

    private TemplateTimer templateTimer;
    private Exchanger<String> exchanger;

    private String time;

    Stage currentStage;

    @FXML
    private GridPane grid ;
    private int mineCount = 10;

    // Holds this controller's Stage
    private Stage thisStage;

    private ChoosingGameController controller1;


    public void getDataForGame(int fieldX, int fieldY, int mineCount, Stage stage) {
        this.currentStage = stage;
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.mineCount = mineCount;
        System.out.println("данные приняты");
        fieldPane.getChildren().clear();
        //fieldPane = new GridPane();
        this.initialize();
    }


    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    public void setTimer(TemplateTimer templateTimer) {
        this.templateTimer = templateTimer;
    }



    /**
     * Show the stage that was loaded in the constructor
     */
    public void showStage() {
        thisStage.showAndWait();
    }


    private void createTimerOnFieldPane(Stage currentStage) {
        this.templateTimer = field.getTemplateTimerFromModel();

        //setTimer(templateTimer);


        //возможно нужно убрать выключение времени в контроллере. Пусть оно будет в модели
        currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                templateTimer.shutdown();
            }
        });

        Timeline oneSecondWonder = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                //System.out.println("this is called every 1 seconds on UI thread");
                                setTimerLabel(field.getTimerString());

                            }
                        }));
        oneSecondWonder.setCycleCount(Timeline.INDEFINITE);
        oneSecondWonder.play();
        this.timeLineChecker = oneSecondWonder;

    }


    public void initialize() {


        if (currentStage != null) {
            createTimerOnFieldPane(currentStage);
        }




        listOfCellsInFieldPane = new ArrayList<>();

        int numCols = fieldX ;
        int numRows = fieldY ;

        System.out.println("поле создано");

        fillTheField(numRows, numCols, mineCount,"nick", -1, -1);




        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
    }


    /**
     * Добавляем listner для каждой cell, для отслеживания событий
     * @param colIndex - координата по Y клетки на поле
     * @param rowIndex - координата по Y клетки на поле
     */
    private void addPane(int colIndex, int rowIndex) {

        //Устанавливается Listener на клетку
        Pane pane = new Pane();
        pane.setOnMouseEntered(e -> {
            //System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
        });
        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                //openTheCell(row, column);
                System.out.printf("Mouse left click cell [%d, %d]%n", colIndex, rowIndex);
                if (fieldIsShown == false) {
                    tryToOpenTheCell(rowIndex, colIndex);
                }
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                System.out.printf("Mouse right click cell [%d, %d]%n", colIndex, rowIndex);
                flagTheCell(rowIndex, colIndex);
            }

        });

        fieldPane.add(pane, colIndex, rowIndex);
        //System.out.println("added pane for (" + colIndex + ", " + rowIndex + ") " );
    }


    /**
     * Проверяет можно ли в эту клетку поставить флаг и ставит, если можно.
     * И наоборот (убирает флаг)
     */
    private void flagTheCell(int rowIndex, int colIndex) {

        CellOfGridPane cellOfGridPane = listOfCellsInFieldPane.get(rowIndex*fieldX + colIndex);
        int x_coord = cellOfGridPane.x_coord();
        int y_coord = cellOfGridPane.y_coord();
        Cell modelcell = field.getCell(y_coord, x_coord);
        ImageView imageView = cellOfGridPane.image();

        if (modelcell.isClosed()) {
            if (modelcell.isFlagged() == false && modelcell.getState().equals(CellConditions.CLOSED) &&
                    field.getFlagsAmount() > 0) {

                setFlagGUI(modelcell, imageView);
            }

            else if (modelcell.isFlagged() == true && modelcell.getState().equals(CellConditions.FLAG) &&
                    field.getFlagsAmount() < field.getNumberOfMines()) {

                deleteFlagGUI(modelcell, imageView);
            }
        }


    }



    void deleteFlagGUI(Cell modelcell, ImageView imageView) {
        field.deleteFlag(modelcell);
        flagNum.setText(Integer.toString(field.getFlagsAmount()));

        imageView.setImage(new Image(this.getClass().
                getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/closed.png")));
        imageView.setFitWidth(sizeOfCell);
        imageView.setFitHeight(sizeOfCell);
    }

    void setFlagGUI(Cell modelcell, ImageView imageView) {
        field.setFlag(modelcell);
        flagNum.setText(Integer.toString(field.getFlagsAmount()));

        imageView.setImage(new Image(this.getClass().
                getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/flaged.png")));
        imageView.setFitWidth(sizeOfCell);
        imageView.setFitHeight(sizeOfCell);
    }

    /**
     * Показывает, что игрок проиграл
     * Больше нельзя взаимодействовать с полем
     */
    private void showDefeat () {
        templateTimer.shutdown();
        timeLineChecker.stop();
        defeatCondition = true;
        loseText.setDisable(false);

    }


    private void showVictory() {
        templateTimer.shutdown();
        timeLineChecker.stop();
    }


    /**
     * Пытается открыть клетку
     * @return - успешное открытие клетки или подрыв на мине
     */
    private boolean tryToOpenTheCell(int rowIndex, int colIndex) {
        boolean success = true;
        openTheCell(rowIndex, colIndex);

        CellOfGridPane cellOfGridPane = listOfCellsInFieldPane.get(rowIndex*fieldX + colIndex);
        int x_coord = cellOfGridPane.x_coord();
        int y_coord = cellOfGridPane.y_coord();

        Cell modelcell = field.getCell(y_coord, x_coord);

        if (modelcell.cellHasMine()) {
            success = false;

            if (!modelcell.isFlagged()) {
                modelcell.openThisCell();
                ImageView imageView = cellOfGridPane.image();

                showWholeField();

                imageView.setImage(new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/bombed.png")));
                System.out.println("поставилась картинка взорванной мины");
                imageView.setFitWidth(sizeOfCell);
                imageView.setFitHeight(sizeOfCell);

                showDefeat();
            }
        }




        return success;
    }


    /**
     * Ставит нужную картинку при открытии ячейки
     */
    private void openTheCell(int rowIndex, int colIndex) {

        CellOfGridPane cellOfGridPane = listOfCellsInFieldPane.get(rowIndex*fieldX + colIndex);
        int x_coord = cellOfGridPane.x_coord();
        int y_coord = cellOfGridPane.y_coord();

        Cell modelcell = field.getCell(y_coord, x_coord);

        if (!modelcell.isFlagged()) {
            field.openCell(modelcell);
            //modelcell.openThisCell();
            if(modelcell.getCellNumber() == 0 && !modelcell.cellHasMine()) {
                // если 0, то проверяем ближайшие клетки
                checkWholeFieldAndDraw();
            }

            ImageView imageView = cellOfGridPane.image();

            imageView.setImage(chooseRightImageToOpenCell(modelcell));
            imageView.setFitWidth(sizeOfCell);
            imageView.setFitHeight(sizeOfCell);
        }


    }


    /**
     * Заново отрисовывает все поле
     */
    private void checkWholeFieldAndDraw() {

        for (CellOfGridPane cellOfGridPane: listOfCellsInFieldPane) {
            int x_coord = cellOfGridPane.x_coord();
            int y_coord = cellOfGridPane.y_coord();

            Cell modelcell = field.getCell(y_coord, x_coord);
            if (modelcell.getState().equals(CellConditions.OPENED)) {

                //modelcell.openThisCell();
                ImageView imageView = cellOfGridPane.image();

                imageView.setImage(chooseRightImageForConcreteCell(modelcell));
                imageView.setFitWidth(sizeOfCell);
                imageView.setFitHeight(sizeOfCell);

            }



        }
    }


    /**
     * Ставит картинку по состоянию ячейки
     */
    private Image chooseRightImageForConcreteCell (Cell modelcell) {

        Image image = null;

        if (modelcell.getState().equals(CellConditions.CLOSED)) {
            //заменяем картинку
            image = new Image(this.getClass().
                    getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/closed.png"));
            return image;
        }


        if (modelcell.getState().equals(CellConditions.OPENED) && modelcell.getCellNumber() == 0
            && !modelcell.cellHasMine())
        {

            //заменяем картинку
            image = new Image(this.getClass().
                    getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/zero.png"));

        }
        else if (modelcell.hasNumber() && modelcell.getState().equals(CellConditions.OPENED)) {
            if (modelcell.getCellNumber() == 1) {
                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num1.png"));

            }
            if (modelcell.getCellNumber() == 2) {
                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num2.png"));

            }
            if (modelcell.getCellNumber() == 3) {
                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num3.png"));

            }
            if (modelcell.getCellNumber() == 4) {


                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num4.png"));

            }
            if (modelcell.getCellNumber() == 5) {

                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num5.png"));

            }
            if (modelcell.getCellNumber() == 6) {

                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num6.png"));

            }
            if (modelcell.getCellNumber() == 7) {

                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num7.png"));

            }
            if (modelcell.getCellNumber() == 8) {

                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num8.png"));
            }

        }


        return image;
    }




    /**
     * Возвращает правильную картинку при открытии ячейки
     */
    private Image chooseRightImageToOpenCell (Cell modelcell) {

        Image image = null;

        if (modelcell.cellHasMine()) {

            //заменяем картинку
            image = new Image(this.getClass().
                    getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/bomb.png"));

        }
        else if (modelcell.hasNumber()) {
            if (modelcell.getCellNumber() == 1) {
                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num1.png"));

            }
            if (modelcell.getCellNumber() == 2) {
                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num2.png"));

            }
            if (modelcell.getCellNumber() == 3) {
                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num3.png"));

            }
            if (modelcell.getCellNumber() == 4) {


                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num4.png"));

            }
            if (modelcell.getCellNumber() == 5) {

                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num5.png"));

            }
            if (modelcell.getCellNumber() == 6) {

                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num6.png"));

            }
            if (modelcell.getCellNumber() == 7) {

                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num7.png"));

            }
            if (modelcell.getCellNumber() == 8) {

                //заменяем картинку
                image = new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/num8.png"));
            }
            /*else {
                System.out.println("smth wrong with modelcell.getCellNumber() at (" + modelcell.getX_coordinate() +
                        ", " + modelcell.getY_coordinate() + "). Cells number is " + modelcell.getCellNumber());
            }*/

        }

        else {

            //заменяем картинку
            image = new Image(this.getClass().
                    getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/zero.png"));
        }

        return image;
    }


    @FXML
    private void paneClickHandler(MouseEvent e) {
        Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.println("paneClickHandler");
        //System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
    }


    /**
     * Создает игровое поле
     */
    public void fillTheField(int fieldY, int fieldX, int mineCount, String nickName,
                             double heightDifference, double widthDifference) {
        //this.fieldY = fieldY;
        //this.fieldX = fieldX;
        //this.mineCount = mineCount;
        //this.nickName = nickName;

        //nickNameLabel.setText(nickName);

        flagCount = mineCount;
        flagNum.setText(flagCount.toString());

        field = new Field(fieldY, fieldX, mineCount);

        field.printMainField();

        changeRowsColumnsAmount(heightDifference, widthDifference);
    }


    /**
     * Создаем поле в GridPane: задаем его размеры и добавлем cell динамически
     */
    private void changeRowsColumnsAmount(double heightDifference, double widthDifference) {
        /*if (heightDifference < 0) {
            borderPane.setPrefHeight(borderPane.getPrefHeight() + Math.abs(heightDifference) + 50);
        }
        if (widthDifference < 0) {
            borderPane.setPrefWidth(borderPane.getPrefWidth() + Math.abs(widthDifference) + 25);
        }*/
        int sizeOfCell = 25;


        System.out.println("fieldX = " + fieldX + ", fieldY = " + fieldY);
        fieldPane.setMinWidth(sizeOfCell * (fieldX ));
        fieldPane.setMinHeight(sizeOfCell * (fieldY ));
        fieldPane.setPrefHeight(sizeOfCell * (fieldY ));
        fieldPane.setPrefWidth(sizeOfCell * (fieldX ));
        fieldPane.setMaxWidth(sizeOfCell * (fieldX ));
        fieldPane.setMaxHeight(sizeOfCell * (fieldY ));



        fieldPane.getChildren().clear();
        for (int y = 0; y < fieldY; y++) {
            for (int x = 0; x < fieldX; x++) {
                ImageView image = new ImageView(new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/closed.png")));
                image.setFitWidth(sizeOfCell);
                image.setFitHeight(sizeOfCell);

                //image.setImage();

                CellOfGridPane cellOfGridPane = new CellOfGridPane(image, x, y);

                listOfCellsInFieldPane.add(cellOfGridPane);

                fieldPane.add(image, x, y);
            }
        }

        /*fieldPane.setHgap(0);
        fieldPane.setVgap(0);
        fieldPane.setPadding(new Insets(0, 0, 0, 0));*/

    }


    /**
     * Достает нужную cell из GridPane
     */
    Node getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : fieldPane.getChildren()) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }

    /**
     * Открывает все поле для просмотра и закрывает
     */
    public void showField(ActionEvent actionEvent) {
        showWholeField();
    }

    public void showWholeField() {
        if (!this.fieldIsShown) {

            field.resetFlagsAmount();
            this.fieldIsShown = true;

            for (CellOfGridPane cellOfGridPane: listOfCellsInFieldPane) {
                int x_coord = cellOfGridPane.x_coord();
                int y_coord = cellOfGridPane.y_coord();

                Cell modelcell = field.getCell(y_coord, x_coord);

                modelcell.openThisCell();
                ImageView imageView = cellOfGridPane.image();

                imageView.setImage(chooseRightImageToOpenCell(modelcell));
                imageView.setFitWidth(sizeOfCell);
                imageView.setFitHeight(sizeOfCell);

            }

        }
        else {

            field.resetFlagsAmount();
            this.fieldIsShown = false;

            for (CellOfGridPane cellOfGridPane: listOfCellsInFieldPane) {
                int x_coord = cellOfGridPane.x_coord();
                int y_coord = cellOfGridPane.y_coord();
                Cell modelcell = field.getCell(y_coord, x_coord);
                modelcell.setClosed();

                ImageView imageView = cellOfGridPane.image();
                //заменяем картинку
                imageView.setImage(new Image(this.getClass().
                        getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/closed.png")));
                imageView.setFitWidth(sizeOfCell);
                imageView.setFitHeight(sizeOfCell);

            }

        }
    }

    public void buttonBackAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();



        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("choosing-game-mode.fxml"));

            loader.load();

            Parent root = loader.getRoot();


            templateTimer.shutdown();
            timeLineChecker.stop();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
