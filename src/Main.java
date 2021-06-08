import javafx.application.Application;

public class Main {
    public static void main(String[] args){
        Simulation simulation= new Simulation(8,5);
        simulation.setAlive(1,1);
        simulation.setAlive(2, 2);
        simulation.setAlive(3, 1);


        simulation.printBoard();

        //System.out.println(simulation.countAliveNeighbours(3,2));
        simulation.step();
        simulation.printBoard();
        simulation.step();
        simulation.printBoard();
    }
}

