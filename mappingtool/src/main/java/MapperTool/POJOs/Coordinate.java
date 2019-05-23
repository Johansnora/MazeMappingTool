package MapperTool.POJOs;

public class Coordinate {

    private int xPos;
    private int yPos;
    private Coordinate prevCoord;

    public Coordinate(int xPos, int yPos){
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setPrevCoord(null);
    }

    public Coordinate(int xPos, int yPos, Coordinate prevCoord){
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setPrevCoord(prevCoord);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public Coordinate getPrevCoord() {
        return prevCoord;
    }

    public void setPrevCoord(Coordinate prevCoord) {
        this.prevCoord = prevCoord;
    }
}
