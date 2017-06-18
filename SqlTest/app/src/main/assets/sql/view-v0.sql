-- used to display the current state of the world
CREATE VIEW IF NOT EXISTS vSpaceStationInventory AS
	 SELECT
	  ss.id_space_station, ss.name AS name_station, ss.tcreation AS timestamp_deployment
	  , i.id_item, i.name AS name_item
	 FROM
	 SpaceStation AS ss
	 INNER JOIN Shipment AS s ON (s.id_space_station = ss.id_space_station)
	 INNER JOIN Item AS i ON (i.id_item = s.id_item)
;
