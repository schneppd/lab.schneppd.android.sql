-- used to directly correct a station name (a test trigger)
CREATE TRIGGER correct_station_name_from_inventory
    INSTEAD OF UPDATE OF name_station ON vSpaceStationInventory
    BEGIN
        UPDATE SpaceStation SET name=NEW.name_station WHERE id_space_station=NEW.id_space_station;
    END
;