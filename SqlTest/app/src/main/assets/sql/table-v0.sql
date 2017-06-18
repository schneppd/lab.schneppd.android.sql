PRAGMA foreign_keys = ON
;
-- each station to track in the app
CREATE TABLE SpaceStation(
 id_space_station INTEGER PRIMARY KEY AUTOINCREMENT
, name TEXT NOT NULL UNIQUE
, tcreation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
, is_unionized BOOLEAN DEFAULT 0
)
;
-- each item sellable in the app
CREATE TABLE Item(
 id_item INTEGER PRIMARY KEY AUTOINCREMENT
, name TEXT NOT NULL
, tcreation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
;

-- record of each station's shipments
CREATE TABLE Shipment(
 id_space_station INTEGER NOT NULL
,  id_item INTEGER NOT NULL
, tcreation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
, PRIMARY KEY(id_space_station, id_item, tcreation)
, FOREIGN KEY(id_space_station) REFERENCES SpaceStation(id_space_station) ON UPDATE CASCADE ON DELETE CASCADE
, FOREIGN KEY(id_item) REFERENCES Item(id_item) ON UPDATE CASCADE ON DELETE CASCADE
)
;
