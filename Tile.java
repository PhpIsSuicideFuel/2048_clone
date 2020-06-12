import KTU.ScreenKTU;

import java.awt.*;
import java.util.Random;

public class Tile {
    int value;
    Point pos = new Point();

    Tile(int x, int y)
    {
        Random rand = new Random();
        if(rand.nextInt(6) < 1) {
            value = 4;
        }
        else {
            value = 2;
        }
        pos.setLocation(x, y);
    }

    public void show(ScreenKTU scr) {
        int r = pos.y * 7 + pos.y;
        int c = pos.x * 7 + pos.x;
        scr.setColors(getColor(), getFontColor());
        scr.fillRect(r, c, 7, 7);
        scr.print(r + 3, c + 3 - String.valueOf(value).length()/2, value);
    }

    public Color getColor() {
        switch(value){
                case 2:    return new Color(0xeee4da);
                case 4:    return new Color(0xede0c8);
                case 8:    return new Color(0xf2b179);
                case 16:   return new Color(0xf59563);
                case 32:   return new Color(0xf67c5f);
                case 64:   return new Color(0xf65e3b);
                case 128:  return new Color(0xedcf72);
                case 256:  return new Color(0xedcc61);
                case 512:  return new Color(0xedc850);
                case 1024: return new Color(0xedc53f);
                case 2048: return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }

    public Color getFontColor() {
        return value < 16 ? new Color(0x776e65) :  new Color(0xf9f6f2);
    }

}
