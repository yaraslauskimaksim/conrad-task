DROP TABLE IF EXISTS road_incidents;

CREATE TABLE road_incidents
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    object_number VARCHAR(5) NOT NULL,
    object_speed  INT        NOT NULL,
    incident_ts   TIMESTAMP  NOT NULL,
    event_ts      TIMESTAMP  NOT NULL,
    viewed        BOOLEAN    NOT NULL DEFAULT false,
    approved      BOOLEAN    NOT NULL DEFAULT false,
    penalty       BIGINT
);
