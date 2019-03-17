package shapes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Polygon implements Visitable {

  public int nrVrtx;
  public int hashClr1;
  public int hashClr2;
  public Position[] plVrtx;

  /** {@docRoot.}
   * 
   */
  public Polygon(int nrVrtx, Position[] vrtx, int hashClr1, int hashClr2) {
    super();
    this.nrVrtx = nrVrtx;
    this.hashClr1 = hashClr1;
    this.hashClr2 = hashClr2;
    plVrtx = vrtx;
  }

  public void accept(Draw specificShape) {
    specificShape.draw(this);
  }

  /** {@inheritDoc.}
   * 
   */
  public Position calculateCentroid() {
    double a = 0;
    double cx = 0;
    double cy = 0;
    double sgArea = 0;

    for (int i = 0; i < nrVrtx; i ++) {
      a = plVrtx[i].posX * plVrtx[(i + 1) % nrVrtx].posY
          - plVrtx[(i + 1) % nrVrtx].posX * plVrtx[i].posY;
      sgArea += a;
      cx += (plVrtx[i].posX + plVrtx[(i + 1) % nrVrtx].posX) * a;
      cy += (plVrtx[i].posY + plVrtx[(i + 1) % nrVrtx].posY) * a;
    }

    sgArea /= 2;
    cx /= (6 * sgArea);
    cy /= (6 * sgArea);

    return new Position((int)Math.floor(cx), (int)Math.floor(cy));

  }

  /** {@inheritDoc.}
   * 
   */
  public void floodFill(BufferedImage img) {
    Position centralPos = calculateCentroid();
    img.setRGB(centralPos.posX, centralPos.posY, new java.awt.Color(255,0,0,100).hashCode());
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
