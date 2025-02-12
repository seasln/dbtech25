alter
session set NLS_DATE_FORMAT='DD.MM.YYYY HH24:MI:SS';

insert into Angebot (AID, ArtikelBez, AuktionEndeZeitpunkt, AuktionMinPreis)
values (1, 'Harry Potter Band 1', '20.03.2015 10:45:00', 1.00);
insert into Angebot (AID, ArtikelBez, AuktionEndeZeitpunkt, AuktionMinPreis)
values (2, 'Game of Thrones, Staffel 1', '21.03.2015 16:10:00', 5.00);
insert into Angebot (AID, ArtikelBez, AuktionEndeZeitpunkt, AuktionMinPreis)
values (3, 'Avengers: Age of Ultron', '20.03.2015 15:39:00', 1.00);
insert into Angebot (AID, ArtikelBez, AuktionEndeZeitpunkt, AuktionMinPreis)
values (4, 'Homeland, Staffel 1', '19.03.2015 11:05:00', 3.00);
insert into Angebot (AID, ArtikelBez, AuktionEndeZeitpunkt, AuktionMinPreis)
values (5, 'Das Boot', '12.03.2015 12:07:00', 1.00);
insert into Angebot (AID, ArtikelBez, AuktionEndeZeitpunkt, AuktionMinPreis)
values (6, 'Inception', '31.12.2030 08:00:00', 1.00);
insert into Angebot (AID, ArtikelBez, AuktionEndeZeitpunkt, AuktionMinPreis)
values (7, 'Enders Game', '31.12.2030 08:00:00', 2.00);



insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (10001, 1, '19.03.2015 12:07:29', 1.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (10002, 1, '20.03.2015 10:01:05', 5.00);

insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (11001, 2, '08.03.2015 10:17:02', 14.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (11002, 2, '21.03.2015 16:09:59', 15.00);

insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (12001, 3, '08.03.2015 07:30:33', 1.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (12002, 3, '09.03.2015 15:12:43', 3.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (12003, 3, '10.03.2015 13:18:05', 7.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (12004, 3, '11.03.2015 12:26:56', 8.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (12005, 3, '12.03.2015 21:08:10', 9.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (12006, 3, '14.03.2015 23:08:12', 10.00);

insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (13001, 4, '10.03.2015 21:09:09', 3.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (13002, 4, '11.03.2015 10:12:33', 8.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (13003, 4, '12.03.2015 19:25:39', 12.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (13004, 4, '18.03.2015 11:21:00', 14.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (13005, 4, '19.03.2015 11:04:30', 15.00);


insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (14001, 5, '20.03.2015 10:09:12', 1.00);
insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (14002, 5, '20.03.2015 11:30:21', 7.00);

insert into Gebot (GID, AID, GebotsZeitpunkt, GebotsPreis)
values (15001, 6, '01.10.2019 10:09:12', 3.00);

