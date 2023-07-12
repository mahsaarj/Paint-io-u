package com.example.paintio;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridPaneController {
    private static final int GRID_SIZE = 10;
    private static final int CELL_SIZE = 60;

    @FXML
    private Rectangle playerRect;

    @FXML
    private GridPane gridPane;

    public void initialize() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setFill(Color.LIGHTGRAY);
                if (!gridPane.getChildren().contains(cell)) {
                    gridPane.add(cell, col, row);
                }
            }
        }

        playerRect = new Rectangle(CELL_SIZE, CELL_SIZE);
        playerRect.setFill(Color.BLUE);
        gridPane.add(playerRect, 0, 0);

        gridPane.setOnKeyPressed(event -> handleKeyPressed(event.getCode()));
        gridPane.setOnMouseDragged(event -> handleMouseDragged(event.getX(), event.getY()));
    }

    @FXML
    public void handleKeyPressed(KeyCode keyCode) {
        int currentColumn = GridPane.getColumnIndex(playerRect);
        int currentRow = GridPane.getRowIndex(playerRect);

        switch (keyCode) {
            case UP:
                if (currentRow > 0) {
                    GridPane.setRowIndex(playerRect, currentRow - 1);
                }
                break;
            case DOWN:
                if (currentRow < GRID_SIZE - 1) {
                    GridPane.setRowIndex(playerRect, currentRow + 1);
                }
                break;
            case LEFT:
                if (currentColumn > 0) {
                    GridPane.setColumnIndex(playerRect, currentColumn - 1);
                }
                break;
            case RIGHT:
                if (currentColumn < GRID_SIZE - 1) {
                    GridPane.setColumnIndex(playerRect, currentColumn + 1);
                }
                break;
        }

        colorCurrentCell();
    }

    @FXML
    public void handleMouseDragged(double mouseX, double mouseY) {
        int newColumn = (int) (mouseX / CELL_SIZE);
        int newRow = (int) (mouseY / CELL_SIZE);

        GridPane.setColumnIndex(playerRect, newColumn);
        GridPane.setRowIndex(playerRect, newRow);

        colorCurrentCell();
    }

    private void colorCurrentCell() {
        int currentColumn = GridPane.getColumnIndex(playerRect);
        int currentRow = GridPane.getRowIndex(playerRect);

        Rectangle currentCell = (Rectangle) gridPane.getChildren().get(currentRow * GRID_SIZE + currentColumn);
        currentCell.setFill(Color.GREEN);

        //Rectangle currentCell = new Rectangle(CELL_SIZE , CELL_SIZE , Color.GREEN);
        //gridPane.add(currentCell , currentColumn , currentRow);
    }
}

