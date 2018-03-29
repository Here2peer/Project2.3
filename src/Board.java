import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Board extends JPanel {
    private JPanel[][] board;
    private StackPane boardFrame;
    private Container mainPanel;

    public Board(){
        boardFrame = new StackPane();
        boardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        boardFrame.setSize(800,700);
    }

    public void playfield(){
        mainPanel = boardFrame.getContentPane();
        mainPanel.setLayout(new GridLayout(8,8));
        this.board = new JPanel[8][8];
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel panel = new JPanel();
                panel = newGame(panel, i, j);
                this.mainPanel.add(panel);
                if(i == 3 && j == 3){
                    this.board[i][j] = whitePiece();
                }else if (i == 3 && j == 4){
                    jLabel.setText("BLACK");
                }else if (i == 4 && j == 3){
                    jLabel.setText("BLACK");
                }else if (i == 4 && j == 4){
                    jLabel.setText("WHITE");
                }
                this.board[i][j] = panel;
            }
        }

    }

    public JPanel newGame(JPanel panel, int i, int j){
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(blackline);
        JLabel jLabel = new JLabel("EMPTY");
        if(i == 3 && j == 3){
            jLabel.setText("WHITE");
        }else if (i == 3 && j == 4){
            jLabel.setText("BLACK");
        }else if (i == 4 && j == 3){
            jLabel.setText("BLACK");
        }else if (i == 4 && j == 4){
            jLabel.setText("WHITE");
        }
        jLabel.setFont(new Font("Verdana",1,20));

        panel.add(jLabel);
        return panel;
    }

    public Circle whitePiece(){
        Circle circle = new Circle();
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(50.0f);
        circle.setFill(javafx.scene.paint.Color.WHITE);
        return circle;
    }

    public Circle blackPiece(){
        Circle circle = new Circle();
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(50.0f);
        circle.setFill(javafx.scene.paint.Color.BLACK);
        return circle;
    }


    public void visable(){
        boardFrame.setVisible(true);
    }

}
