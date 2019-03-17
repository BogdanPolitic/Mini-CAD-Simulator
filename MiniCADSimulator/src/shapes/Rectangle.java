package shapes;

import java.awt.image.BufferedImage;

public class Rectangle implements Visitable {
  private Position firstPos = new Position();
  public final Position getFirstPos() {
    return firstPos;
  }
  public int height;
  public int width;
  public int hashClr1;
  public int hashClr2;

  /** {@docRoot.}
   *
   */
  public Rectangle(int x, int y, int h, int l2, int hashClr1, int hashClr2) {
    super();
    this.firstPos.posX = x;
    this.firstPos.posY = y;
    this.height = h;
    width = l2;
    this.hashClr1 = hashClr1;
    this.hashClr2 = hashClr2;
  }

  public void accept(Draw specificShape) {
    specificShape.draw(this);
  }

  /** {@inheritDoc.}
   * 
   */
  public void floodFill(BufferedImage img) {
    int replacementColor = hashClr2;

    for (int i = Math.max(firstPos.posX + 1, 0);
        i < Math.min(firstPos.posX + width, Canvas.width); i ++) {
      for (int j = Math.max(firstPos.posY + 1, 0);
          j < Math.min(firstPos.posY + height, Canvas.height); j ++) {
        img.setRGB(i, j, replacementColor);
      }
    }
  }
}
