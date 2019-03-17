package shapes;

import java.awt.image.BufferedImage;

public class Square implements Visitable {
  public Position firstPos = new Position();
  public int length;
  public int hashClr1;
  public int hashClr2;

  /** {@docRoot.}
  *
  */
  public Square(int x, int y, int l, int hashClr1, int hashClr2) {
    this.firstPos.posX = x;
    this.firstPos.posY = y;
    this.length = l;
    this.hashClr1 = hashClr1;
    this.hashClr2 = hashClr2;
  }

  /** {@inheritDoc.}
   * 
   */
  public void accept(Draw specificShape) {
    specificShape.draw(this);
  }

  /** {@inheritDoc.}
   * 
   */
  public void floodFill(BufferedImage img) {
    int replacementColor = hashClr2;
    for (int i = Math.max(firstPos.posY + 1, 0);
        i < Math.min(firstPos.posY + length, Canvas.height); i ++) {
      for (int j = Math.max(firstPos.posX + 1, 0);
          j < Math.min(firstPos.posX + length, Canvas.width); j ++) {
        img.setRGB(j, i, replacementColor);
      }
    }
  }
}
