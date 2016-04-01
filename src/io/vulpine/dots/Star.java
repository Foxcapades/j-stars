package io.vulpine.dots;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Star extends Dot
{
  public final double outerDistance;
  public final double innerDistance;

  public static final double OUTER_TOP_CENTER    = 1.570796;
  public static final double OUTER_TOP_LEFT      = 2.827433;
  public static final double OUTER_TOP_RIGHT     = 0.314159;
  public static final double OUTER_BOTTOM_LEFT   = 4.08407;
  public static final double OUTER_BOTTOM_RIGHT  = 5.340708;
  public static final double INNER_TOP_RIGHT     = 0.942474;
  public static final double INNER_TOP_LEFT      = 2.199111;
  public static final double INNER_BOTTOM_LEFT   = 3.455748;
  public static final double INNER_BOTTOM_RIGHT  = 5.959022;
  public static final double INNER_BOTTOM_CENTER = 4.712385;

  private double rotation = 0;
  private final double rotationSpeed;
  private final boolean rotationDirection;

  public Star()
  {
    super();
    rotationSpeed = randomDouble(0.005, 0.05);

    outerDistance = randomDouble(5, 9);
    innerDistance = (outerDistance /5)*2;
    rotationDirection = rand.nextBoolean();
  }

  @Override
  public void step()
  {
    super.step();

    if (rotationDirection) {
      rotation += rotationSpeed;
      if (rotation >= 5000) rotation = 0;
    } else {
      rotation -= rotationSpeed;
      if (rotation <= -5000) rotation = 0;
    }
  }

  public void drawStar(GraphicsContext gc)
  {
    final double[] x, y;
    final Color color;

    color = Color.hsb(hue, saturation, brightness);
    gc.setFill(color);
    gc.setStroke(color);

    x = new double[] {
      cos(OUTER_TOP_CENTER + rotation) * outerDistance + posX,
      cos(INNER_TOP_RIGHT + rotation) * innerDistance + posX,
      cos(OUTER_TOP_RIGHT + rotation) * outerDistance + posX,
      cos(INNER_BOTTOM_RIGHT + rotation) * innerDistance + posX,
      cos(OUTER_BOTTOM_RIGHT + rotation) * outerDistance + posX,
      cos(INNER_BOTTOM_CENTER + rotation) * innerDistance + posX,
      cos(OUTER_BOTTOM_LEFT + rotation) * outerDistance + posX,
      cos(INNER_BOTTOM_LEFT + rotation) * innerDistance + posX,
      cos(OUTER_TOP_LEFT + rotation) * outerDistance + posX,
      cos(INNER_TOP_LEFT + rotation) * innerDistance + posX
    };
    y = new double[] {
      sin(OUTER_TOP_CENTER + rotation) * outerDistance + posY,
      sin(INNER_TOP_RIGHT + rotation) * innerDistance + posY,
      sin(OUTER_TOP_RIGHT + rotation) * outerDistance + posY,
      sin(INNER_BOTTOM_RIGHT + rotation) * innerDistance + posY,
      sin(OUTER_BOTTOM_RIGHT + rotation) * outerDistance + posY,
      sin(INNER_BOTTOM_CENTER + rotation) * innerDistance + posY,
      sin(OUTER_BOTTOM_LEFT + rotation) * outerDistance + posY,
      sin(INNER_BOTTOM_LEFT + rotation) * innerDistance + posY,
      sin(OUTER_TOP_LEFT + rotation) * outerDistance + posY,
      sin(INNER_TOP_LEFT + rotation) * innerDistance + posY
    };

    gc.fillPolygon(x, y, 10);
  }
}
