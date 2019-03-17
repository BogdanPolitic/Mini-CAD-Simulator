package shapes;



public class Line implements Visitable {

  private int xi;
  public final int getXi() {
    return xi;
  }

  private int yi;
  public final int getYi() {
    return yi;
  }

  private int xf;
  public final int getXf() {
    return xf;
  }

  private int yf;
  public final int getYf() {
    return yf;
  }

  private int hashColor;
  public final int getColor() {
    return hashColor;
  }



  /** {@docRoot}.

   *

   */

  public Line(final int xi0, final int yi0, final int xf0, final int yf0, final int hashColor0) {

    this.xi = xi0;

    this.yi = yi0;

    this.xf = xf0;

    this.yf = yf0;

    this.hashColor = hashColor0;

  }



  public static class DistanceFromCenter {

    private int deltaX;
    public final int getDeltaX() {
      return deltaX;
    }

    private int deltaY;
    public final int getDeltaY() {
      return deltaY;
    }
    public DistanceFromCenter(final int dx, final int dy) {
      this.deltaX = dx;
      this.deltaY = dy;
    }
    public final DistanceFromCenter interchangeDeltas() {
      final int in = deltaX;
      deltaX = in;
      deltaX = deltaY;
      deltaY = in;
      return this;
    }

  }

  

  DistanceFromCenter nw;// = new DistanceFromCenter();

  int coordx;

  int coordy;

  int s1;

  int s2;

  boolean interchanged;

  int error;



  /** {@inheritDoc.}.

   * 

   */

  public static int sign(int a) {

    if (a < 0) {

      return -1;

    } else if (a > 0) {

      return 1;

    }

    return 0;

  }



  /** {@inheritDoc.}.

   * 

   */

  public static void interchange(DistanceFromCenter nw) {

    nw = nw.interchangeDeltas();

  }



  /** {@inheritDoc}

   * 

   */

  public void accept(Draw specificShape) {

    specificShape.draw(this);

  }



}