-- create sample space stations
INSERT INTO SpaceStation(name, is_unionized) VALUES
('Alpha', 0)
,('Beta', 1)
,('Gamma', 0)
;
-- create sample items
INSERT INTO Item(name) VALUES
('Water')
,('Missile MK1')
,('Beef ration')
;
-- add sample shipment to space stations
INSERT INTO Shipment(id_space_station, id_item) VALUES
(1, 1), (1, 2), (1, 3)
,(2, 1), (2, 2)
,(3, 1), (3, 2), (3, 3)
;
