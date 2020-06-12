import KTU.ScreenKTU;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    int score = 0;
    List<Tile> tiles = new ArrayList();
    List<Point> emptyPositions = new ArrayList();
    Random rand = new Random();
    Point moveDir = new Point();


    Player()
    {
        fillEmptyPositions();

        addNewTile();
        addNewTile();
    }


    void fillEmptyPositions(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                emptyPositions.add(new Point(i, j));
            }
        }
    }

    void setEmptyPositions(){
        emptyPositions.clear();

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++){
                if(getValue(i, j) == 0){
                    emptyPositions.add(new Point(i,j));
                }
            }
        }
    }

    void show(ScreenKTU scr){
        for(Tile tile:tiles)
        {
            tile.show(scr);
        }
    }

    private boolean isFull() {
        return emptyPositions.size() == 0;
    }

    boolean canMove() {
        if (!isFull()) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile t = getTile(i, j);
                if ((i < 3 && t.value == getTile(i + 1, j).value)
                        || ((j < 3) && t.value == getTile(i, j + 1).value)) {
                    return true;
                }
            }
        }
        return false;
    }

    void move()
    {

        List<Point> sortingOrder = new ArrayList<>();
        Point sortP = new Point();
        boolean vert = false, moved = false;
        if(moveDir.x == 1) {
            sortP = new Point(3,0);
            vert = false;
        } else if (moveDir.x == -1){
            sortP = new Point(0,0);
            vert = false;
        } else if (moveDir.y == 1){
            sortP = new Point(0,3);
            vert = true;
        } else if (moveDir.y == -1){
            sortP = new Point(0,0);
            vert = true;
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Point temp = new Point(sortP.x, sortP.y);
                if(vert){
                    temp.x += j;
                } else {
                    temp.y += j;
                }
                sortingOrder.add(temp);

            }
            sortP.setLocation(sortP.x - moveDir.x, sortP.y - moveDir.y);
        }


        for(int j = 0; j < sortingOrder.size(); j++) {
            for (int i = 0; i < tiles.size(); i++) {
                if(tiles.get(i).pos.x == sortingOrder.get(j).x && tiles.get(i).pos.y == sortingOrder.get(j).y ) {
                    Point moveTo = new Point(tiles.get(i).pos.x + moveDir.x, tiles.get(i).pos.y + moveDir.y);
                    int valueOfMoveTo = getValue(moveTo.x, moveTo.y);
                    while (valueOfMoveTo == 0) { // moving tiles
                        moved = true;
                        tiles.get(i).pos = new Point(moveTo.x, moveTo.y);
                        moveTo = new Point(tiles.get(i).pos.x + moveDir.x, tiles.get(i).pos.y + moveDir.y);
                        valueOfMoveTo = getValue(moveTo.x, moveTo.y);
                    }
                    if(valueOfMoveTo == tiles.get(i).value){ // merging tiles
                        moved = true;
                        getTile(moveTo.x, moveTo.y).value*=2;
                        tiles.remove(i);
                    }
                }
            }
        }

        if(moved) {
            setEmptyPositions();
            addNewTile();
        }
    }

    void addNewTile() {
        Point temp = emptyPositions.remove(rand.nextInt(emptyPositions.size()));
        tiles.add(new Tile(temp.x, temp.y));
    }

    int getValue(int x, int y) {
        if(x > 3 || y > 3 || x < 0 || y < 0){
            return -1;
        }

        for(int i = 0; i < tiles.size(); i++)
        {
            if(tiles.get(i).pos.x == x && tiles.get(i).pos.y == y){
                return tiles.get(i).value;
            }
        }
        return 0;
    }

    Tile getTile(int x, int y) {
        for(int i = 0; i < tiles.size(); i++)
        {
            if(tiles.get(i).pos.x == x && tiles.get(i).pos.y ==y){
                return tiles.get(i);
            }
        }
        return null;
    }

}
