/**
 * Tetrad class to be completed for Tetris project
 * 
 * @author Nikash Chhadia
 * @version 4/10/21
 */
import java.awt.*;

public class Tetrad
{
    private Block[] blocks;

    public Tetrad(BoundedGrid<Block> grid)
    {
        //Exercise 1.2  Insert code here to
        blocks = new Block[4];
        for(int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
        }
        Color color = Color.BLACK;
        Location[] locs = new Location[4];

        //Exercise 2.0  Insert code here to
        int shape = (int)(Math.random()*7);

        //Exercise 1.2  Insert code here to
        if(shape == 0)
        {
            color = Color.CYAN;
            locs[0] = new Location(0,4);
            locs[1] = new Location(0,3);
            locs[2] = new Location(0,5);
            locs[3] = new Location(0,6);
        }
        else if(shape == 1)
        {
            color = Color.MAGENTA;
            locs[0] = new Location(1,4);
            locs[1] = new Location(0,4);
            locs[2] = new Location(1,3);
            locs[3] = new Location(1,5);
        }
        else if(shape == 2)
        {
            color = Color.YELLOW;
            locs[0] = new Location(0,4);
            locs[1] = new Location(0,5);
            locs[2] = new Location(1,4);
            locs[3] = new Location(1,5);
        }
        else if(shape == 3)
        {
            color = Color.ORANGE;
            locs[0] = new Location(1,5);
            locs[1] = new Location(1,3);
            locs[2] = new Location(1,4);
            locs[3] = new Location(0,5);
        }
        else if(shape == 4)
        {
            color = Color.BLUE;
            locs[0] = new Location(1,3);
            locs[1] = new Location(0,3);
            locs[2] = new Location(1,4);
            locs[3] = new Location(1,5);
        }
        else if(shape == 5)
        {
            color = Color.GREEN;
            locs[0] = new Location(1,4);
            locs[1] = new Location(0,5);
            locs[2] = new Location(1,3);
            locs[3] = new Location(0,4);
        }
        else if(shape == 6)
        {
            color = Color.RED;
            locs[0] = new Location(1,4);
            locs[1] = new Location(0,4);
            locs[2] = new Location(0,3);
            locs[3] = new Location(1,5);
        }

        //Exercise 1.2  Insert code here (after the above if statements) to
        for(int i = 0; i < blocks.length; i++)
        {
            blocks[i].setColor(color);
        }
        addToLocations(grid, locs);
    }

    //precondition:  blocks are not in any grid;
    //               blocks.length = locs.length = 4.
    //postcondition: The locations of blocks match locs,
    //               and blocks have been put in the grid.
    private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
    {
        for(int i = 0; i < blocks.length; i++)
        {
            blocks[i].putSelfInGrid(grid,locs[i]);
        }
    }

    //precondition:  Blocks are in the grid.
    //postcondition: Returns old locations of blocks;
    //               blocks have been removed from grid.
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[4];
        for(int i = 0; i < blocks.length; i++)
        {
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return locs;
    }

    //postcondition: Returns true if each of locs is
    //               valid (on the board) AND empty in
    //               grid; false otherwise.
    private boolean areEmpty(BoundedGrid<Block> grid,
    Location[] locs)
    {
        for(Location l : locs)
        {
            if(!grid.isValid(l) || grid.get(l) != null)
                return false;
        }
        return true;
    }

    //postcondition: Attempts to move this tetrad deltaRow
    //               rows down and deltaCol columns to the
    //               right, if those positions are valid
    //               and empty; returns true if successful
    //               and false otherwise.
    public boolean translate(int deltaRow, int deltaCol)
    {
        //Exercise 2.2    Insert code here to
        BoundedGrid<Block> gr = blocks[0].getGrid();
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        for(int i = 0; i < 4; i++)
        {
            newLocs[i] = new Location(oldLocs[i].getRow() + deltaRow, 
                oldLocs[i].getCol() + deltaCol);
        }
        if(areEmpty(gr,newLocs))
        {
            addToLocations(gr,newLocs);
            return true; 
        }
        else
        {
            addToLocations(gr,oldLocs);
            return false;
        }    
    }

    //postcondition: Attempts to rotate this tetrad
    //               clockwise by 90 degrees about its
    //               center, if the necessary positions
    //               are empty; returns true if successful
    //               and false otherwise.
    public boolean rotate()
    {
        //Exercise 3.0  Insert code here to
        BoundedGrid<Block> gr = blocks[0].getGrid();
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        for(int i = 0; i < 4; i++)
        {
            newLocs[i] = new Location(oldLocs[0].getRow()-oldLocs[0].getCol()+
                oldLocs[i].getCol(), oldLocs[0].getRow()+oldLocs[0].getCol()-
                oldLocs[i].getRow());
        }
        if(areEmpty(gr,newLocs))
        {
            addToLocations(gr,newLocs);
            return true; 
        }
        else
        {
            addToLocations(gr,oldLocs);
            return false;
        }   
    }
}