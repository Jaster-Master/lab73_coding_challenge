CREATE DATABASE IF NOT EXISTS traffic_sign_observations;

USE traffic_sign_observations;

DROP TABLE IF EXISTS Traffic_Sign_Observation;

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