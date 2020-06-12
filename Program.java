import KTU.ScreenKTU;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Program extends ScreenKTU {
    static final private int cHeight = 20, cWidth = 20;
    Player p = new Player();
    boolean lost = false;
    Program()
    {
        super(cHeight, cWidth, 31, 31);
        clearAll(Color.black);
        p.show(this);
        refresh();
    }
    public static void main(String[] args) {
        new Program();

    }

    void gameOver()
    {
        if(!p.canMove())
        {
            clearAll(Color.black);
            setColors(Color.black, Color.red);
            print(16, 11, "GAME OVER");
            lost = true;
            refresh();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if(lost == false) {
            switch (ke.getKeyChar()) {
                case 'w':
                    p.moveDir = new Point(0, -1);
                    p.move();
                    break;
                case 'a':
                    p.moveDir = new Point(-1, 0);
                    p.move();
                    break;
                case 's':
                    p.moveDir = new Point(0, 1);
                    p.move();
                    break;
                case 'd':
                    p.moveDir = new Point(1, 0);
                    p.move();
                    break;
            }
            clearAll(Color.black);
            p.show(this);
            refresh();
            gameOver();
        }
    }

}
