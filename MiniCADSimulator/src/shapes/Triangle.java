package shapes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Triangle implements Visitable {
  public int x1;
  public int y1;
  public int x2;
  public int y2;
  public int x3;
  public int y3;
  public int hashClr1;
  public int hashClr2;
  
  /** {@docRoot.}
  *
  */
  public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, int hashClr1, int hashClr2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.x3 = x3;
    this.y3 = y3;
    this.hashClr1 = hashClr1;
    this.hashClr2 = hashClr2;
  }

  public void accept(Draw specificShape) {
    specificShape.draw(this);
  }

  public Position calculateCentroid() {
    return new Position((x1 + x2 + x3) / 3, (y1 + y2 + y3) / 3);
  }

  /** {@inheritDoc.}
   * 
   */
  public void floodFill(BufferedImage img) {
    Position centralPos = calculateCentroid();
    img.setRGB(centralPos.posX, centralPos.posY, 1);
    int replacementColor = hashClr2;
    int boundColor = hashClr1;

    List<Pixel> pixels = new ArrayList<Pixel>();
    pixels.add(new Pixel(centralPos, 4));
    ListIterator<Pixel> it = pixels.listIterator();

    while (it.hasNext()) {
      Pixel px = it.next();

      img.setRGB(px.getPos().posX, px.getPos().posY, replacementColor);
      if (px.getPos().posX >= 1) {
        if (img.getRGB(px.getPos().posX - 1, px.getPos().posY) != replacementColor
            && img.getRGB(px.getPos().posX - 1, px.getPos().posY) != boundColor) {
          it.add(new Pixel(new Position(px.getPos().posX - 1, px.getPos().posY), 4));
          px.getLowerNeighbour();
          it.previous();
        }
      }
      if (px.getPos().posY >= 1) {
        if (img.getRGB(px.getPos().posX, px.getPos().posY - 1) != replacementColor
            && img.getRGB(px.getPos().posX, px.getPos().posY - 1) != boundColor) {
          it.add(new Pixel(new Position(px.getPos().posX, px.getPos().posY - 1), 4));
          px.getLowerNeighbour();
          it.previous();
        }
      }
      if (px.getPos().posX < Canvas.width - 1) {
        if (img.getRGB(px.getPos().posX + 1, px.getPos().posY) != replacementColor
            && img.getRGB(px.getPos().posX + 1, px.getPos().posY) != boundColor) {
          it.add(new Pixel(new Position(px.getPos().posX + 1, px.getPos().posY), 4));
          px.getLowerNeighbour();
          it.previous();
        }
      }
      if (px.getPos().posY < Canvas.height - 1) {
        if (img.getRGB(px.getPos().posX, px.getPos().posY + 1) != replacementColor
            && img.getRGB(px.getPos().posX, px.getPos().posY + 1) != boundColor) {
          it.add(new Pixel(new Position(px.getPos().posX, px.getPos().posY + 1), 4));
          px.getLowerNeighbour();
          it.previous();
        }
      }
      if (px.getNeighbour() == 0) {
        it.previous();
        it.next();
        it.remove();
      }
    }
  }

}
