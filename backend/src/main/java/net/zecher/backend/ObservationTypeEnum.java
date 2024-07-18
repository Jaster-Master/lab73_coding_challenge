package net.zecher.backend;

public enum ObservationTypeEnum {
    SPEED_LIMIT,
    PASSING_RESTRICTION,
    HIGHWAY_BEGIN,
    HIGHWAY_END,
    CITY_BEGIN,
    CITY_END;

    public static ObservationTypeEnum parse(String type){
        try {
            return ObservationTypeEnum.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
