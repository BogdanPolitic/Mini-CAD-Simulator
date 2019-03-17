import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Position;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;
import shapes.Visitable;

public class GetShape {
  String line;

  public GetShape(String line) {
    this.line = line;
  }

  public String getType() {
    return Main.shapeInfo.readWord(line, 0);
  }

  /** {@inheritDoc}
   * 
   */
  public Visitable getAttributes() {
    switch (getType()) {
      case "LINE" :
        return assignLine();
      case "SQUARE" :
        return assignSquare();
      case "RECTANGLE" :
        return assignRectangle();
      case "CIRCLE" :
        return assignCircle();
      case "TRIANGLE" :
        return assignTriangle();
      case "DIAMOND" :
        return assignDiamond();
      case "POLYGON" :
        return assignPolygon();
      default :
        return null;
    }
  }

  /** {@inheritDoc} 
   * 
   */
  public Line assignLine() {
    int xi;
    int yi;
    xi = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    yi = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int xf = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int yf = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int hashColor = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    return new Line(xi, yi, xf, yf, hashColor);
  }

  /** {@inheritDoc} 
   * 
   */
  public Square assignSquare() {
    int x;
    int y;
    int l;
    x = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    y = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    l = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    l --;
    int hashClr1 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    int hashClr2 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    return new Square(x, y, l, hashClr1, hashClr2);
  }

  /** {@inheritDoc} 
   * 
   */
  public Rectangle assignRectangle() {
    int x = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int y = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int rectH = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int rectL = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    rectL --;
    rectH --;
    int hashClr1 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    int hashClr2 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    return new Rectangle(x, y, rectH, rectL, hashClr1, hashClr2);
  }

  /** {@inheritDoc} 
   * 
   */
  public Circle assignCircle() {
    int xc = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int yc = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int r = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int hashClr1 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    int hashClr2 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    return new Circle(xc, yc, r, hashClr1, hashClr2);
  }

  /** {@inheritDoc} 
   * 
   */
  public Triangle assignTriangle() {
    int x1 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int y1 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int x2 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int y2 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int x3 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int y3 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int hashClr1 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    int hashClr2 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    return new Triangle(x1, y1, x2, y2, x3, y3, hashClr1, hashClr2);
  }

  /** {@inheritDoc} 
   * 
   */
  public Diamond assignDiamond() {
    int x;
    int y;
    x = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    y = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int diagHoriz = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int diagVert = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    int hashClr1 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    int hashClr2 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    return new Diamond(x, y, diagHoriz, diagVert, hashClr1, hashClr2);
  }

  /** {@inheritDoc} 
   * 
   */
  public Polygon assignPolygon() {
    int nrVrtx;
    nrVrtx = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    Position[] plVrtx = new Position[nrVrtx];
    for (int i = 0; i < nrVrtx; i ++) {
      plVrtx[i] = new Position();
      plVrtx[i].posX = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
      plVrtx[i].posY = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, false);
    }
    int hashClr1 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    int hashClr2 = Main.shapeInfo.readInt(line, GetFromFile.placeAfter, true);
    return new Polygon(nrVrtx, plVrtx, hashClr1, hashClr2);
  }

}
