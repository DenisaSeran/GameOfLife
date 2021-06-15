public class Simulation {
    public int width, height;
    int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public void printBoard() {
        System.out.println("____");

        for (int i = 0; i < height; i++) {
            String line = "|";
            for (int j = 0; j < width; j++) {
                if (this.board[j][i] == 0) {
                    line = line + ".";
                } else {
                    line = line + "*";
                }
            }
            line = line + "|";
            System.out.println(line);
        }
        System.out.println("___\n");
    }

    public void setAlive(int x, int y) {
        this.setState(x,y,0);
    }

    public void setDead(int x, int y) {
        this.board[x][y] = 0;
    }

    public void setState(int x, int y, int state){
        if (x < 0 || x >= width) {
            return ;
        }
        if (y < 0 || y >= height) {
            return ;
        }
    }
    public int countAliveNeighbours(int x, int y) {
        int count = 0;

        count += getstate(x - 1, y - 1);
        count += getstate(x , y - 1);
        count += getstate(x + 1, y - 1);

        count += getstate(x - 1, y);
        count += getstate(x + 1, y);

        count += getstate(x - 1, y + 1);
        count += getstate(x, y + 1);
        count += getstate(x + 1, y + 1);

        return count;

    }

    int getstate(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }
        if (y < 0 || y >= height) {
            return 0;
        }
        return this.board[x][y];
    }

    public void step(){
        int [][] newboard = new int[width][height];

        for(int x=0; x<height; x++){
            for (int y= 0; y< width; y++){
                int aliveNeighbours= countAliveNeighbours(x,y);
                 if(getstate(x,y)==1){
                    //underpopulation
                    if(aliveNeighbours<2){
                        newboard[x][y]=0;
                    }//alive cell with 2 or 3 neighbours lives on to the next generation
                     else if(aliveNeighbours==2||aliveNeighbours==3) {
                        newboard[x][y] = 1;
                    }//overpopulation
                     else{
                        newboard[x][y]=0;
                    }

                }//reproduction
                else{
                    if(aliveNeighbours==3) {
                        newboard[x][y] = 1;
                    }

                }

            }
        }
        this.board=newboard;
    }


}

