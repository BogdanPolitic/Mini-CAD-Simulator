import java.awt.Color;
import java.awt.image.BufferedImage;
import shapes.Canvas;
import shapes.Circle;
import shapes.Diamond;
import shapes.Draw;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

public class Visitor implements Draw {
  private BufferedImage img;
  
  public Visitor(BufferedImage img) {
    this.img = img;
  }

  private shapes.Line.DistanceFromCenter nw;
  int xx = 0;
  int yy = 0;
  int s1;
  int s2;
  boolean interchanged;
  int error;

  @Override

  public void draw(Line line) {



    nw = new shapes.Line.DistanceFromCenter(line.getXf() - line.getXi(),
line.getYf() - line.getYi());

    xx = line.getXi();

    yy = line.getYi();

    //nw.deltaX = Math.abs(line.getXf() - line.getXi());

    //nw.deltaY = Math.abs(line.getYf() - line.getYi());

    int s1 = Line.sign(line.getXf() - line.getXi());

    int s2 = Line.sign(line.getYf() - line.getYi());

    if (nw.getDeltaY() > nw.getDeltaX()) {

      Line.interchange(nw);

      interchanged = true;

    } else {

      interchanged = false;

    }

    error = 2 * nw.getDeltaY() - nw.getDeltaX();



    for (int i = 0; i <= nw.getDeltaX(); i ++) {

      if ((xx >= 0 && xx < Canvas.width) && (yy >= 0 && yy < Canvas.height)) {

        img.setRGB(xx, yy, line.getColor());

      }

      while (error > 0) {

        if (interchanged == true) {

          xx += s1;

        } else {

          yy += s2;

        }

        error -= 2 * nw.getDeltaX();

      }



      if (interchanged == true) {

        yy += s2;

      } else {

        xx += s1;

      }



      error += 2 * nw.getDeltaY();

    }

  }

  int edge;
  int hashClr1;
  int hashClr2;

  @Override
  public void draw(Square square) {
    xx = square.firstPos.posX;
    x0 = square.firstPos.posX;
    yy = square.firstPos.posY;
    y0 = square.firstPos.posY;
    edge = square.length;
    hashClr1 = square.hashClr1;
    hashClr2 = square.hashClr2;
    draw(new Line(xx, yy, xx + edge, yy, hashClr1));
    xx = x0;
    yy = y0;
    draw(new Line(xx + edge, yy, xx + edge, yy + edge, hashClr1));
    xx = x0;
    yy = y0;
    draw(new Line(xx + edge, yy + edge, xx, yy + edge, hashClr1));
    xx = x0;
    yy = y0;
    draw(new Line(xx, yy + edge, xx, yy, hashClr1));
    (new shapes.Square(x0, y0, edge, hashClr1, hashClr2)).floodFill(img);
  }

  int rectH;
  int rectL;

  @Override
  public void draw(Rectangle rectangle) {
    xx = rectangle.getFirstPos().posX;
    x0 = rectangle.getFirstPos().posX;
    yy = rectangle.getFirstPos().posY;
    y0 = rectangle.getFirstPos().posY;
    rectL = rectangle.width;
    rectH = rectangle.height;
    hashClr1 = rectangle.hashClr1;
    hashClr2 = rectangle.hashClr2;
    draw(new Line(xx, yy, xx + rectL, yy, hashClr1));
    xx = x0;
    yy = y0;
    draw(new Line(xx + rectL, yy, xx + rectL, yy + rectH, hashClr1));
    xx = x0;
    yy = y0;
    draw(new Line(xx + rectL, yy + rectH, xx, yy + rectH, hashClr1));
    xx = x0;
    yy = y0;
    draw(new Line(xx, yy + rectH, xx, yy, hashClr1));
    (new shapes.Rectangle(x0, y0, rectH, rectL, hashClr1, hashClr2)).floodFill(img);
  }

  int xc0;
  int xc;
  int yc0;
  int yc;
  int rad;
  
  @Override
  public void draw(Circle circle) {
    hashClr1 = circle.hashClr1;
    Color ghostColor = new Color(0, 0, 0, 101);
    draw1(circle, true, ghostColor.hashCode());
    draw1(circle, false, hashClr1);
  }
  
  /** {@inheritDoc}
   * 
   */
  public void draw1(Circle circle, boolean firstAppeal, int hashClr1) {
    xc0 = circle.xc;
    xc = circle.xc;
    yc0 = circle.yc;
    yc = circle.yc;
    rad = circle.rad;
    hashClr2 = circle.hashClr2;
    (new shapes.Circle(xc, yc, rad, hashClr1, hashClr2)).bresenham_alg(img);
    xc = xc0;
    yc = yc0;
    if (firstAppeal) {
      (new shapes.Circle(xc, yc, rad, hashClr1, hashClr2)).floodFill(img);
    }
  }

  int x1;
  int y1;
  int x2;
  int y2;
  int x3;
  int y3;
  int x10;
  int y10;
  int x20;
  int y20;
  int x30;
  int y30;
  
  @Override
  public void draw(Triangle triangle) {
    x1 = triangle.x1;
    x10 = triangle.x1;
    x2 = triangle.x2;
    x20 = triangle.x2;
    x3 = triangle.x3;
    x30 = triangle.x3;
    y1 = triangle.y1;
    y10 = triangle.y1;
    y2 = triangle.y2;
    y20 = triangle.y2;
    y3 = triangle.y3;
    y30 = triangle.y3;
    draw(new Line(x1, y1, x2, y2, triangle.hashClr1));
    x1 = x10;
    x2 = x20;
    x3 = x30;
    y1 = y10;
    y2 = y20;
    y3 = y30;
    draw(new Line(x2, y2, x3, y3, triangle.hashClr1));
    x1 = x10;
    x2 = x20;
    x3 = x30;
    y1 = y10;
    y2 = y20;
    y3 = y30;
    draw(new Line(x3, y3, x1, y1, triangle.hashClr1));
    x1 = x10;
    x2 = x20;
    x3 = x30;
    y1 = y10;
    y2 = y20;
    y3 = y30;
    (new shapes.Triangle(x1, y1, x2, y2, x3, y3, triangle.hashClr1,
        triangle.hashClr2)).floodFill(img);
  }

  int x0;
  int y0;
  int diagHoriz = 0;
  int diagVert = 0;
  
  @Override
  public void draw(Diamond diamond) {
    x0 = diamond.coordx;
    y0 = diamond.coordy;
    diagHoriz = diamond.diagHoriz;
    diagVert = diamond.diagVert;
    hashClr1 = diamond.hashClr1;
    hashClr2 = diamond.hashClr2;
    
    int diagVertDiv2 = (int) Math.floor((float)diagVert / 2);
    shapes.Position posU = new shapes.Position();
    posU.posX = x0;
    posU.posY = y0 + diagVertDiv2;
    shapes.Position posD = new shapes.Position();
    posD.posX = x0;
    posD.posY = y0 - diagVertDiv2;
    
    int diagHorizDiv2 = (int) Math.floor((float)diagHoriz / 2);
    shapes.Position posL = new shapes.Position();
    posL.posX = x0 - diagHorizDiv2;
    posL.posY = y0;
    shapes.Position posR = new shapes.Position();
    posR.posX = x0 + diagHorizDiv2;
    posR.posY = y0;
    
    draw(new Line(posU.posX, posU.posY, posR.posX, posR.posY, hashClr1));
    draw(new Line(posR.posX, posR.posY, posD.posX, posD.posY, hashClr1));
    draw(new Line(posD.posX, posD.posY, posL.posX, posL.posY, hashClr1));
    draw(new Line(posL.posX, posL.posY, posU.posX, posU.posY, hashClr1));
    (new shapes.Diamond(x0, y0, diagHoriz, diagVert, hashClr1, hashClr2)).floodFill(img);
  }

  int nrVrtx;
  shapes.Position[] plVrtx;
  
  @Override
  public void draw(Polygon polygon) {
    nrVrtx = polygon.nrVrtx;
    plVrtx = polygon.plVrtx;
    hashClr1 = polygon.hashClr1;
    hashClr2 = polygon.hashClr2;

    for (int i = 0; i < nrVrtx - 1; i ++) {
      draw(new Line(plVrtx[i].posX, plVrtx[i].posY,
          plVrtx[i + 1].posX, plVrtx[i + 1].posY, hashClr1));
    }
    draw(new Line(plVrtx[nrVrtx - 1].posX, plVrtx[nrVrtx - 1].posY,
        plVrtx[0].posX, plVrtx[0].posY, hashClr1));

    (new shapes.Polygon(nrVrtx, plVrtx, hashClr1, hashClr2)).floodFill(img);
  }

}
