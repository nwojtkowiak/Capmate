package com.capgemini.jstk.capmates.enums;

public enum Level {
    ADVANCED(3,1000), INTERMEDIATE(2,500),BEGINNER(1,0) ;

    private final int number;
    private final long pointsRequired;

    Level(int number, long pointsRequired) {
        this.number = number;
        this.pointsRequired = pointsRequired;
    }

    public int getNumber() {
        return number;
    }

    public long getPointsRequired() {
        return pointsRequired;
    }

    public static Level getLevelByPoints(long points) {
        for(Level level : Level.values())
        {
            if(points >= level.getPointsRequired()) {
                return level;
            }
        }
        return null;
    }
}
