import java.awt.Color;
import java.awt.Graphics;

public class Houses {


    private Color lightBlue;
    private Color darkBlue;
    private Color raincolor;
    private Color white;
    private Color green;
    private Color lightYellow;
    private Color brown;
    private Color brown1;
    private Color darkBrown;
    private Color tan;
    private Color gray;
    private Color black;
    private Color darkgray;
    private Color lightgray;

    public Houses() {

        lightBlue = new Color(108,206,209);
        darkBlue = new Color(24,42,132);
        raincolor = new Color(3, 74, 236);
        white = new Color(254,225,234);
        green = new Color(12,172,28);
        lightYellow = new Color(247,238,99);
        brown = new Color(139,69,19);
        brown1 = new Color(150,89,49);
        darkBrown = new Color(110,49,7);
        black = new Color(10,20,8);
        lightgray = new Color(150,150,150);
        darkgray = new Color(60,60,60);

    }



    public void draw(Graphics g,int x, int y, int row, int col) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // building
                g.setColor(brown);
                g.fillRect(x + c * 35, y + r * 35, 30, 20);

                g.setColor(darkBrown);
                int[] xs = {x+c*35, x+15+c*35, x+30+c*35};
                int[] ys = {y+r*35, y-18+r*35, y+r*35};
                g.fillPolygon(xs,ys, 3);

                // window
                g.setColor(lightBlue);
                g.fillRect(x + c * 35 + 2, y + 3+ r * 35 + 4, 6, 6);

            }
        }
    }

}