/*
 * TileInfo
 */

package javaPlay;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author VisionLab/PUC-Rio
 */
public class TileInfo
{
    public int id;
    public Point min;
    public Point max;

    public TileInfo()
    {
       min = new Point();
       max = new Point();
    }

    public int getCentralY(){
        return ((this.min.y/2)+(this.max.y/2));
    }
    
    public int getCentralX(){
        return ((this.min.x/2)+(this.max.x/2));
    }

    public Point getRightPoint(){
        return new Point(this.max.x, this.getCentralY());
    }

    public Point getLeftPoint(){
        return new Point(this.min.x, this.getCentralY());
    }

    public Point getBottomPoint(){
        return new Point(this.getCentralX(), this.max.y);
    }

    public Point getTopPoint(){
        return new Point(this.getCentralX(), this.min.y);
    }
    
    public Rectangle getRetangle(){
        return new Rectangle(this.min.x, this.min.y, this.max.x, this.max.y);
    }
}
