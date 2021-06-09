import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class MainView extends VBox {
    private Button button;
    private Canvas canvas;

    private Affine affine;

    private Simulation simulation;

    public MainView() {
         this.button = new Button("Step");

         this.button.setOnAction(actionEvent -> {
             simulation.step();
             draw();
         });

         this.canvas= new Canvas();
         this.canvas.setHeight(750);
         this.canvas.setWidth(1500);

         this.getChildren().addAll(this.button, this.canvas);

         this.affine = new Affine();
         this.affine.appendScale(1500/50f,1500/50f);

         this.simulation = new Simulation(200 ,200);
         simulation.setAlive(1, 2);
         simulation.setAlive(2, 3);
         simulation.setAlive(3,5);
         simulation.setAlive(4, 4);

         simulation.setAlive(5, 5);
         simulation.setAlive(5,6);
         simulation.setAlive(6, 5);
         simulation.setAlive(6,6);
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

