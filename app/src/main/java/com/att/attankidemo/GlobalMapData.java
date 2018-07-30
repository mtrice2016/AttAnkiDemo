package com.att.attankidemo;

public class GlobalMapData {
    private static GlobalMapData singletonGlobalMapData;

    public static synchronized GlobalMapData getInstance() {
        if(null == singletonGlobalMapData){
            singletonGlobalMapData = new GlobalMapData();
        }
        return singletonGlobalMapData;
    }


    private int leftTurns;
    private int rightTurns;
    private int straights;

    public int getLeftTurns() {
        return leftTurns;
    }

    public int getRightTurns() {
        return rightTurns;
    }

    public int getStraights() {
        return straights;
    }


    public GlobalMapData() {}


    public void loadMap(int lefts, int rights, int straights) {
        this.leftTurns = lefts;
        this.rightTurns = rights;
        this.straights = straights;
    }

    public void loadMapFromMessage() {
        do stuff
    }
}
