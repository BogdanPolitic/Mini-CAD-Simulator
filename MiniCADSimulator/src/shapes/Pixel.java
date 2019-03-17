package shapes;

public class Pixel {
  private Position pos;
  public Position getPos() {
    return pos;
  }

  private int fillNearby;
  public int getNeighbour() {
    return fillNearby;
  }
  public void getLowerNeighbour() {
    fillNearby--;
  }

  public Pixel(Position pos, int fillNearby) {
    this.pos = pos;
    this.fillNearby = fillNearby;
  }
}
