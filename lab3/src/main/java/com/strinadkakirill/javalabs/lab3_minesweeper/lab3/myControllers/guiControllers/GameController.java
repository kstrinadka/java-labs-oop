package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.myControllers.guiControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.Cell;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Field;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private boolean fieldIsShown = false;

    private int fieldX = 9;
    private int fieldY = 9;

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
    private int mineCount;

    public void initialize() {

        listOfCellsInFieldPane = new ArrayList<>();

        int numCols = 30 ;
        int numRows = 24 ;

        fillTheField(numRows, numCols, 10, "nick", -1, -1);



        for (int i = 0 ; i < numCols ; i++) {
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
        }

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

                openTheCell(rowIndex, colIndex);
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                System.out.printf("Mouse right click cell [%d, %d]%n", colIndex, rowIndex);
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

        });
        fieldPane.add(pane, colIndex, rowIndex);
        //System.out.println("added pane for (" + colIndex + ", " + rowIndex + ") " );
    }

    private void openTheCell(int rowIndex, int colIndex) {
        ImageView image = new ImageView(new Image(this.getClass().
                getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/zero.png")));
        image.setFitWidth(sizeOfCell);
        image.setFitHeight(sizeOfCell);

        fieldPane.add(image, colIndex, rowIndex);

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



    @FXML
    void initialize2() {

        fillTheField(9, 9, 10, "nick", -1, -1);

        backButton.setOnAction(event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
            //templateTimer.shutdown();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choosing-game-mode.fxml"));
            Parent rootNode = null;
            try {
                rootNode = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            Scene scene = new Scene(rootNode);
            stage.setTitle("");
            //stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_PATH)));
            stage.setScene(scene);
            stage.show();
        });


        //еще кнопку меню сюда




    }


    //создаем поле
    public void fillTheField(int fieldY, int fieldX, int mineCount, String nickName,
                             double heightDifference, double widthDifference) {
        this.fieldY = fieldY;
        this.fieldX = fieldX;
        this.mineCount = mineCount;
        //this.nickName = nickName;

        //nickNameLabel.setText(nickName);

        flagCount = mineCount;
        flagNum.setText(flagCount.toString());

        field = new Field(fieldY, fieldX, mineCount);

        field.printMainField();

        changeRowsColumnsAmount(heightDifference, widthDifference);
    }


    /**
     * Создаем поле в GridPane: задаем его размеры и добавлем cell
     */
    private void changeRowsColumnsAmount(double heightDifference, double widthDifference) {
        /*if (heightDifference < 0) {
            borderPane.setPrefHeight(borderPane.getPrefHeight() + Math.abs(heightDifference) + 50);
        }
        if (widthDifference < 0) {
            borderPane.setPrefWidth(borderPane.getPrefWidth() + Math.abs(widthDifference) + 25);
        }*/
        int sizeOfCell = 25;


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

        if (!fieldIsShown) {


            for (CellOfGridPane cellOfGridPane: listOfCellsInFieldPane) {
                int x_coord = cellOfGridPane.x_coord();
                int y_coord = cellOfGridPane.y_coord();

                // +1 т.к. там есть какой-то сдвиг к класса Field
                Cell modelcell = field.getCell(y_coord + 1, x_coord + 1);

                ImageView imageView = cellOfGridPane.image();
                if (modelcell.cellHasMine()) {




                    //заменяем картинку
                    imageView.setImage(new Image(this.getClass().
                            getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/bomb.png")));

//                    imageView = new ImageView(new Image(this.getClass().
//                            getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/bomb.png")));
                    imageView.setFitWidth(sizeOfCell);
                    imageView.setFitHeight(sizeOfCell);

                    //fieldPane.add(imageView, x_coord, y_coord);
                }
                else {
                    imageView.setImage(new Image(this.getClass().
                            getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/zero.png")));

                    /*ImageView image = new ImageView(new Image(this.getClass().
                            getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/zero.png")));*/
                    imageView.setFitWidth(sizeOfCell);
                    imageView.setFitHeight(sizeOfCell);

                    //fieldPane.add(image, x_coord, y_coord);
                }

            }


            for (com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.Cell cell: field.getMainField()) {
                int x_coordinate = cell.getX_coordinate();
                int y_coordinate = cell.getY_coordinate();
                if (cell.cellHasMine()) {
                    System.out.println("cell (" + x_coordinate + ", " + y_coordinate + ") hasMine");
                }
            }


//            for (com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.Cell cell: field.getMainField()) {
//
//                int x_coordinate = cell.getX_coordinate();
//                int y_coordinate = cell.getY_coordinate();
//
//                CellOfGridPane cellOfGridPane = listOfCellsInFieldPane.get(y_coordinate * fieldX + x_coordinate);
//                if (x_coordinate != cellOfGridPane.x_coord() && y_coordinate != cellOfGridPane.y_coord()) {
//                    System.out.println("smth wrong with image coords");
//                }
//
//                ImageView imageView = cellOfGridPane.image();
//
//
//                ;
//                if (cell.cellHasMine()) {
//
//                    System.out.println("ставим мину в node из GridPane c координатами (" + x_coordinate + ", " + y_coordinate + ") ");
//
//
//                    imageView = new ImageView(new Image(this.getClass().
//                            getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/bomb.png")));
//                    imageView.setFitWidth(sizeOfCell);
//                    imageView.setFitHeight(sizeOfCell);
//
//                    fieldPane.add(imageView, x_coordinate, y_coordinate);
//                }
//                else {
//
//                    ImageView image = new ImageView(new Image(this.getClass().
//                            getResourceAsStream("/com/strinadkakirill/javalabs/lab3_minesweeper/lab3/img/zero.png")));
//                    image.setFitWidth(sizeOfCell);
//                    image.setFitHeight(sizeOfCell);
//
//                    fieldPane.add(image, x_coordinate, y_coordinate);
//
//
//                }
//            }


        }
        else {



        }

    }
}
