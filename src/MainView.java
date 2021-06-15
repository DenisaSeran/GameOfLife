import com.sun.glass.ui.Screen;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

import java.awt.event.MouseEvent;
import java.sql.SQLOutput;

public class MainView extends VBox {
    private Button button;
    private Canvas canvas;

    private Affine affine;

    private Simulation simulation;
    private int drawMode  = 1;


    public MainView() {
         this.button = new Button("Step");

         this.button.setOnAction(actionEvent -> {
             simulation.step();
             draw();
         });

         this.canvas= new Canvas();
         this.canvas.setHeight(750);
         this.canvas.setWidth(1500);
         this.canvas.setOnMousePressed(this::handleDraw);
         this.canvas.setOnMouseDragged(this::handleDraw);
         this.setOnKeyPressed(this::onKeyPressed);

         this.getChildren().addAll(this.button, this.canvas);

         this.affine = new Affine();
         this.affine.appendScale(1500/50f,1500/50f);

         this.simulation = new Simulation(200 ,200);

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.D){
            this.drawMode = 1;
            System.out.println("Draw Mode");

        }else if(keyEvent.getCode()==KeyCode.E){
            this.drawMode = 0;
            System.out.println("Erase Mode");
        }
    }

    private void handleDraw(javafx.scene.input.MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        System.out.println(mouseX + ","+ mouseY);

        try {
            Point2D coord = this.affine.inverseTransform(mouseX, mouseY);
            int simX = (int) coord.getX();
            int simY = (int) coord.getY();

            System.out.print(simX + "," + simY);

            this.simulation.board[simX][simY]=drawMode;
            draw();

        }catch (NonInvertibleTransformException e){
            System.out.println("Not inverted");
        }
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 450, 450);
        int x,y;
        g.setFill(Color.LIGHTGREEN);
        for ( x = 0; x < this.simulation.width; x++) {
            for (y = 0; y < this.simulation.height; y++){
                if (this.simulation.getstate(x, y) == 1) {
                    g.fillRect(x, y, 1, 1);
                }
            }
         }
        g.setStroke(Color.GREEN);
        g.setLineWidth(0.05);

        for (x = 0; x <= this.simulation.width; x++) {
            g.strokeLine(x, 0, x, 50);
        }

        for (y = 0; y <= this.simulation.height; y++) {
            g.strokeLine(0, y, 50, y);
        }

    }
}

