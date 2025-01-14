public class FloorNode {
    private int dirt;
    private final int floorType;
    private final boolean wallNorth, wallSouth, wallEast, wallWest;
    private int doorNorth, doorSouth, doorEast, doorWest;
    private int obstacle;
    private boolean chargingStation;
    private int posX;
    private int posY;
    private String data;


    /**
     * Construct a FloorNode
     * @param string 11-digit code used to generate floor node
     * @param x X-position of floor node
     * @param y Y-position of floor node
     */
    public FloorNode(String string, int x, int y){

        int[] parsed = new int[11];
        for(int i = 0; i < string.length();i++){
            parsed[i] = Integer.parseInt(string.substring(i,i+1));
        }
        floorType = parsed[0];
        obstacle = parsed[1];
        if (obstacle == 2){ chargingStation = true;}
        wallNorth = (parsed[2] == 1);
        wallSouth = (parsed[3] == 1);
        wallEast = (parsed[4] == 1);
        wallWest = (parsed[5] == 1);
        doorNorth = parsed[6];
        doorSouth = parsed[7];
        doorEast = parsed[8];
        doorWest = parsed[9];
        dirt = parsed[10];
        posX = x;
        posY = y;
        data = string;
    }

    //Returns X position on floormap
    public int posX(){
        return posX;
    }

    //Returns Y position on floormap
    public int posY(){
        return posY;
    }

    //Returns amount of dirt on tile
    //0 dirt is clean, anything more is dirty
    public int getDirt(){
        return dirt;
    }

    //Reduces dirt amount by 1
    //0 dirt is clean and thus cannot be cleaned further
    public int cleanDirt(){
        if (dirt > 0){
            dirt--;  
        }
        return dirt;
    }

    //Input cardinal direction (N,S,E,W), returns whether there is wall in that direction
    public boolean getWall(String string) throws IllegalArgumentException{
        String temp = string.toLowerCase();
        if (temp == "north"){
            return wallNorth;
        }
        else if (temp == "south"){
            return wallSouth;
        }
        else if (temp == "east"){
            return wallEast;
        }
        else if (temp == "west"){
            return wallWest;
        }
        throw new IllegalArgumentException ("Invalid direction");
    }

    //Input cardinal direction(N,S,E,W), returns whether there is door in that direction
    //0 = No door
    //1 = Open door (passible)
    //2 = Closed door (impassible)
    public int getDoor(String string) throws IllegalArgumentException{
        String temp = string.toLowerCase();
        if (temp == "north"){
            return doorNorth;
        }
        else if (temp == "south"){
            return doorSouth;
        }
        else if (temp == "east"){
            return doorEast;
        }
        else if (temp == "west"){
            return doorWest;
        }
        throw new IllegalArgumentException ("Invalid direction");
    }

    public boolean getChargingStation(){
        return chargingStation;
    }

    //Returns floor type
    //0 = Bare floor
    //1 = Low-pile carpet
    //2 = High-pile carpet
    public int getFloorType(){
        return floorType;
    }

    //Returns presence of obstacle on tile
    //0 = Nothing
    //1 = Furniture
    //2 = Charging Station (passable)
    //3 = Stairs
    public int getObstacle(){
        return obstacle;
    }
    
    public boolean equals(FloorNode other){
        if(posX == other.posX() && posY == other.posY())
            return true;
        return false;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        if (getObstacle() != 0){
            if (getObstacle() == 1) str.append("F / F");
            else if (chargingStation) str.append("C / C");
            else if (getObstacle() == 3) str.append("S / S");
        }
        else{
            str.append(getDirt() + " / " + getFloorType());
        }
        str.append("; ");
        return str.toString();
    }
}
