package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.myControllers.guiControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.Cell;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.CellConditions;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.CellState;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Field;
import javafx.event.ActionEvent;
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

public class GameController {

    public AnchorPane rootElementGame;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Label flagNum;

    @FXML
    private ImageView imageView00;

    @FXML
    private Text loseText;

    @FXML
    private Button menuButton;

    @FXML
    private Label timerLabel;

    @FXML
    private Text winText;

    boolean defeatCondition = false;
    boolean victoryCondition = false;

    int sizeOfCell = 25;

    @FXML
    private GridPane grid ;
    private int mineCount = 10;

    // Holds this controller's Stage
    private Stage thisStage;

    private ChoosingGameController controller1;


    public void getDataForGame(int fieldX, int fieldY, int mineCount) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.mineCount = mineCount;
        System.out.println("данные приняты");
        fieldPane.getChildren().clear();
        //fieldPane = new GridPane();
        this.initialize();
    }



    /*public GameController(ChoosingGameController controller1, ActionEvent actionEvent) {
        // We received the first controller, now let's make it usable throughout this controller.
        this.controller1 = controller1;


        // Create the new stage
        thisStage = new Stage();

        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));

            // Set this class as the controller



            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Passing Controllers Example - Layout2");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/


    /**
     * Show the stage that was loaded in the constructor
     */
    public void showStage() {
        thisStage.showAndWait();
    }


    public void initialize() {


        listOfCellsInFieldPane = new ArrayList<>();

        int numCols = fieldX ;
        int numRows = fieldY ;

        System.out.println("поле создано");

        fillTheField(numRows, numCols, mineCount,"nick", -1, -1);



        /*for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            //colConstraints.setPrefWidth(100);
            //colConstraints.setMinWidth(18);

            colConstraints.setHgrow(Priority.ALWAYS);
            fieldPane.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            //rowConstraints.setPercentHeight(100);
            //rowConstraints.setMaxHeight(24 * 18);

            rowConstraints.setVgrow(Priority.ALWAYS);
            fieldPane.getRowConstraints().add(rowConstraints);
        }*/

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

                tryToOpenTheCell(rowIndex, colIndex);
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                System.out.printf("Mouse right click cell [%d, %d]%n", colIndex, rowIndex);
                flagTheCell(rowIndex, colIndex);
            }

        });
        //pane.setMaxHeight(25);
        //pane.setMinHeight(25);
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

        imageView.setImage(new Image(this.getClass().
                getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/closed.png")));
        imageView.setFitWidth(sizeOfCell);
        imageView.setFitHeight(sizeOfCell);
    }

    void setFlagGUI(Cell modelcell, ImageView imageView) {
        field.setFlag(modelcell);

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

        defeatCondition = true;
        loseText.setDisable(false);

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
            modelcell.openThisCell();
            ImageView imageView = cellOfGridPane.image();

            imageView.setImage(chooseRightImageToOpenCell(modelcell));
            imageView.setFitWidth(sizeOfCell);
            imageView.setFitHeight(sizeOfCell);
        }


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
    @FXML
    public void paneClickHandler2(MouseEvent event) {
        if (!victoryCondition && !defeatCondition) {
            Node node = fieldPane.getChildren().get(0);
            double y = node.getLayoutY();
            double x = node.getLayoutX();

            /*System.out.println( "x = " + x);
            System.out.println( "y = " + y);*/


            double xOffset = 0 - x;
            double yOffset = 0 - y;
            if (event.getPickResult().getIntersectedNode().getParent().
                    getParent().getClass().getName().equals("javafx.scene.layout.BorderPane")) {
                int row = (int) ((event.getPickResult().getIntersectedNode().getLayoutX()  + xOffset) / 24);
                int column = (int) ((event.getPickResult().getIntersectedNode().getLayoutY() + yOffset) / 24);


                if (event.getButton() == MouseButton.PRIMARY) {
                    //openTheCell(row, column);
                    System.out.println("левый клик по клетке: (" + row + ", " + column + ") ");
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    System.out.println("правый клик по клетке: (" + row + ", " + column + ") ");
                    /*if (flagCount > 0) {
                        setFlag(row, column);
                    }
                    else if (flagCount == 0) {
                        if (field.getValue(row, column) >= FLAG_VALUE) {
                            Image image = new Image(this.getClass().getResourceAsStream(BLOCK_PATH));
                            fieldPane.add(new ImageView(image), row, column);
                            flagCount++;
                            flagNum.setText(Integer.toString(flagCount));

                            field.removeFlag(row, column);
                        }
                    }*/
                }
            }
        }
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

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
