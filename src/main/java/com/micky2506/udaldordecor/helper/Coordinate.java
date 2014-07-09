package com.micky2506.udaldordecor.helper;

public class Coordinate
{
    public double x = 0;
    public double y = 0;
    public double z = 0;

    public Coordinate()
    {

    }

    public Coordinate(double x, double y, double z)
    {
        this.setCoords(x, y, z);
    }

    public void setCoords(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
