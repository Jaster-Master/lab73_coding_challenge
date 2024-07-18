package net.zecher.backend;

public enum ObservationType {
    SPEED_LIMIT,
    PASSING_RESTRICTION,
    HIGHWAY_BEGIN,
    HIGHWAY_END,
    CITY_BEGIN,
    CITY_END;

    public static ObservationType parse(String type){
        try {
            return ObservationType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
