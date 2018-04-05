package view;

import controller.GameController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import controller.AbstractController;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.AbstractModel;

import model.GameModel;

import java.util.ArrayList;

public class GameView extends AbstractView<GameController, GameModel> {
    GridPane pane;

    public GameView(GameController controller, GameModel model) {
        super(controller, model);
        pane = new GridPane();
        for(int x = 0; x < model.getDimensions(); x++) {
            for(int y = 0; y < model.getDimensions(); y++) {
                Button btn = new Button();
                btn.setPrefSize(50, 50);
                GridPane.setConstraints(btn, x, y);
                int col = x;
                int row = y;
                //int value = y*8 + x;
                //String lbl = String.valueOf(value);
                //btn.setText(lbl);
                btn.setOnAction(e -> {
                    controller.clickedButton(col, row);
                });
                pane.getChildren().addAll(btn);
            }
        }
        this.getChildren().add(pane);

        model.addView(this);
        this.updateView();
    }


    public void updateView() {
        int[][] board = model.getBoard();
        for(int x = 0; x < model.getDimensions(); x++) {
            for(int y = 0; y < model.getDimensions(); y++) {

                Node n = getNodeFromGridPane(this.pane, x, y);
                if(n instanceof Button) {
                    updateBackground((Button) n, board[x][y]);
                    updateText((Button) n, board[x][y]);
                }
            }
        }
    }

    public void updateBackground(Button btn, int value) {
        if(value == 2) {
            btn.setStyle("-fx-background-color: #ffffff");
        } else if(value == 1) {
            btn.setStyle("-fx-background-color: #000000");
        }
    }

    public void updateText(Button btn, int value) {
        if(value == 0 || value == 1 || value == 2) {
            btn.setText("");
        } else if(value == 3) {
            btn.setText("•");
        }
    }

    private Node getNodeFromGridPane(GridPane gp, int row, int col) {
        for (Node node : gp.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

}
