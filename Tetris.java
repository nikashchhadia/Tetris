/**
 * Tetris class to be completed for Tetris project
 * 
 * @author Nikash Chhadia
 * @version 4/10/21
 */
public class Tetris implements ArrowListener
{
    public static void main(String[] args)
    {
        Tetris tetris = new Tetris();
        tetris.play();
    }

    private BoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad activeTetrad;
    private int score;

    public Tetris()
    {
        grid = new BoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Tetris");
        activeTetrad = new Tetrad(grid);
        score = 0;
    }

    public void upPressed()
    {
        activeTetrad.rotate();
        display.showBlocks();
    }

    public void downPressed()
    {
        activeTetrad.translate(1,0);
        display.showBlocks();
    }

    public void leftPressed()
    {
        activeTetrad.translate(0,-1);
        display.showBlocks();
    }

    public void rightPressed()
    {
        activeTetrad.translate(0,1);
        display.showBlocks();
    }

    public void spacePressed()
    {
        while(activeTetrad.translate(1,0))
        {
        }
        display.showBlocks();
    }

    public void play()
    {
        int gameSpeed = 1000;
        while (true)
        {
            try { Thread.sleep(gameSpeed); } catch(Exception e) {}
            if(!activeTetrad.translate(1,0))
            {
                score += clearCompletedRows();
                if(!topRowsEmpty())
                {
                    display.setTitle("GAME OVER ////// SCORE: " + score);
                    break;
                }
                activeTetrad = new Tetrad(grid);
            }
            display.showBlocks();
            gameSpeed = 1000 - score * 10;
            display.setTitle("SCORE: " + score);
        }
    }

    //precondition:  0 <= row < number of rows
    //postcondition: Returns true if every cell in the
    //               given row is occupied;
    //               returns false otherwise.
    private boolean isCompletedRow(int row)
    {
        for(int i = 0; i < 10; i++)
        {
            if(grid.get(new Location(row,i)) == null)
                return false;
        }
        return true;
    }

    //precondition:  0 <= row < number of rows;
    //               given row is full of blocks
    //postcondition: Every block in the given row has been
    //               removed, and every block above row
    //               has been moved down one row.
    private void clearRow(int row)
    {
        for(int i = 0; i < 10; i++)
        {
            grid.get(new Location(row,i)).removeSelfFromGrid();
        }
        for(int r = row - 1; r >= 0; r--)
        {
            for(int c = 0; c < 10; c++)
            {
                if(grid.get(new Location(r,c)) != null)
                    grid.get(new Location(r,c)).moveTo(new Location(r+1,c));
            } 
        }
    }

    //postcondition: All completed rows have been cleared.
    private int clearCompletedRows()
    {
        int rowsCleared = 0;
        for(int i = 19; i >= 0; i--)
        {
            if(isCompletedRow(i))
            {
                clearRow(i);
                rowsCleared++;
                i++;
            }
        }
        return rowsCleared;
    }

    //returns true if top two rows of the grid are empty (no blocks), false otherwise
    private boolean topRowsEmpty()
    {
        for(int r = 0; r < 2; r++)
        {
            for(int c = 0; c < 10; c++)
            {
                if(grid.get(new Location(r,c)) != null)
                    return false;
            }
        }
        return true;
    }

}