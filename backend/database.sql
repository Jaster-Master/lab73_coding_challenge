CREATE DATABASE IF NOT EXISTS traffic_sign_observations;

USE traffic_sign_observations;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Traffic_Sign_Observation;

CREATE TABLE User
(
    USER_ID       BIGINT       NOT NULL AUTO_INCREMENT,
    USERNAME      VARCHAR(255) NOT NULL,
    PASSWORD_HASH VARCHAR(60) NOT NULL,
    SALT          VARCHAR(29) NOT NULL,
    PRIMARY KEY (USER_ID)
);

CREATE TABLE Traffic_Sign_Observation
(
    TRAFFIC_SIGN_OBSERVATION_ID BIGINT       NOT NULL AUTO_INCREMENT,
    LATITUDE                    DOUBLE       NOT NULL,
    LONGITUDE                   DOUBLE       NOT NULL,
    HEADING                     INT          NOT NULL,
    OBSERVATION_TYPE            VARCHAR(255) NOT NULL,
    VALUE                       VARCHAR(255),
    PRIMARY KEY (TRAFFIC_SIGN_OBSERVATION_ID)
);