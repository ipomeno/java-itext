package org.javaitext.utils;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;

public class RoundedSolidBorder extends SolidBorder {

  private Rectangle rectangle = null;
  private Color backgroundColor = null;
  private float radius = 0;

  public RoundedSolidBorder(Color color, float width, float radius) {
    super(color, width);
    this.setRadius(radius);
    this.setRectangle(new Rectangle(0, 0, 0, 0));
  }

  public float getRadius() {
    return this.radius;
  }
  
  public void setRadius(float radius) {
    this.radius = radius;
  }

  public Color getBackgroundColor() {
    return this.backgroundColor;
  }
  
  public void setBackgroundColor(Color color) {
    this.backgroundColor = color;
  }

  private Rectangle getRectangle() {
    return this.rectangle;
  }
  
  private void setRectangle(Rectangle rectangle) {
    this.rectangle = rectangle;
  }

  @Override
  public void draw(PdfCanvas canvas, float x1, float y1, float x2, float y2, Side side, float borderWidthBefore,
      float borderWidthAfter) {

    Border.Side borderSide = this.getBorderSide(x1, y1, x2, y2, Border.Side.TOP);

    if (borderSide == Side.TOP) {
      this.getRectangle().setX(x1 - this.width);
      this.getRectangle().setY(y1 + this.width);
      this.getRectangle().setWidth( (x2 - x1) + (this.width*2) );
    }

    if (borderSide == Side.RIGHT) {
      this.getRectangle().setHeight((y1-y2) + (this.width*2));
    }

    if (borderSide == Side.LEFT) {
      canvas.saveState();
      canvas.setFillColor(transparentColor.getColor());
      transparentColor.applyFillTransparency(canvas);

      canvas.setLineWidth(this.width);
      canvas.setColor(this.getColor(), false);
      canvas.setFillColor(this.getBackgroundColor());

      canvas.roundRectangle(this.getRectangle().getX(), 
        this.getRectangle().getY() - this.getRectangle().getHeight(),
        this.getRectangle().getWidth(), 
        this.getRectangle().getHeight(), 
        this.getRadius());

      canvas.fillStroke();
      canvas.restoreState();
    }
  }

}