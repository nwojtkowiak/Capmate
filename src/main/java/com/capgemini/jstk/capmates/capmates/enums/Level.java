package com.capgemini.jstk.capmates.capmates.enums;

public enum Level {
    BEGINNER(1,0), INTERMEDIATE(2,500), ADVANCED(3,1000);

    private final int value;
    private final int pointsRequired;

    Level(int value, int pointsRequired) {
        this.value = value;
        this.pointsRequired = pointsRequired;
    }

    public int getValue() {
        return value;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }

    public static Level getLevelByValue(int value) {
        for(Level level : Level.values())
        {
            if(level.getValue() == value) {
                return level;
            }
        }
        return null;
    }
}
