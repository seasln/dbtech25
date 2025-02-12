alter
session set nls_date_format = 'YYYY-MM-DD';

--------------------------------------------------------------------------------------------------
-- Produkt
--------------------------------------------------------------------------------------------------
insert into Produkt(ID, KurzBez, Bez)
values (1, 'KFZV', 'KFZ-Versicherung');
insert into Produkt(ID, KurzBez, Bez)
values (2, 'LBV', 'Lebensversicherung');
insert into Produkt(ID, KurzBez, Bez)
values (3, 'HRV', 'Hausratversicherung');

--------------------------------------------------------------------------------------------------
-- Kunde
--------------------------------------------------------------------------------------------------
insert into Kunde(ID, Name, Geburtsdatum)
values (1, 'Gaul', '1980-03-24');
insert into Kunde(ID, Name, Geburtsdatum)
values (2, 'Krumm', '1973-07-09');
insert into Kunde(ID, Name, Geburtsdatum)
values (3, 'Axthelm', '1965-05-17');
insert into Kunde(ID, Name, Geburtsdatum)
values (4, 'Zawisla', '2019-06-13');
insert into Kunde(ID, Name, Geburtsdatum)
values (5, 'Last', '1920-01-05');
insert into Kunde(ID, Name, Geburtsdatum)
values (6, 'Emrich', '1942-02-07');
insert into Kunde(ID, Name, Geburtsdatum)
values (7, 'Eck', '1955-04-26');
insert into Kunde(ID, Name, Geburtsdatum)
values (8, 'Brodersen', '1990-03-11');
insert into Kunde(ID, Name, Geburtsdatum)
values (9, 'Schildhauer', '2001-03-08');
insert into Kunde(ID, Name, Geburtsdatum)
values (10, 'Fernando', '2001-03-08');
insert into Kunde(ID, Name, Geburtsdatum)
values (11, 'Gerster', '1967-03-26');

--------------------------------------------------------------------------------------------------
-- Deckungsart
--------------------------------------------------------------------------------------------------
insert into Deckungsart(ID, Produkt_FK, KurzBez, Bez)
values (1, 1, 'HT', 'Haftung');
insert into Deckungsart(ID, Produkt_FK, KurzBez, Bez)
values (2, 1, 'PS', 'Personenschaden');
insert into Deckungsart(ID, Produkt_FK, KurzBez, Bez)
values (3, 2, 'TD', 'Tod');
insert into Deckungsart(ID, Produkt_FK, KurzBez, Bez)
values (4, 3, 'BS', 'Brandschaden');
insert into Deckungsart(ID, Produkt_FK, KurzBez, Bez)
values (5, 3, 'GB', 'Glasbruch');
insert into Deckungsart(ID, Produkt_FK, KurzBez, Bez)
values (6, 3, 'FD', 'Fahrraddiebstahl');

--------------------------------------------------------------------------------------------------
-- Ablehnungsregel
--------------------------------------------------------------------------------------------------
insert into Ablehnungsregel(Deckungsart_FK, LfdNr, R_Betrag, R_Alter)
values (1, 1, '- -', '< 18');
insert into Ablehnungsregel(Deckungsart_FK, LfdNr, R_Betrag, R_Alter)
values (2, 1, '- -', '< 18');
insert into Ablehnungsregel(Deckungsart_FK, LfdNr, R_Betrag, R_Alter)
values (3, 1, '- -', '> 90');
insert into Ablehnungsregel(Deckungsart_FK, LfdNr, R_Betrag, R_Alter)
values (3, 2, '>= 300000', '> 60');
insert into Ablehnungsregel(Deckungsart_FK, LfdNr, R_Betrag, R_Alter)
values (3, 3, '>= 200000', '> 70');

--------------------------------------------------------------------------------------------------
-- Vertrag
--------------------------------------------------------------------------------------------------
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (1, 1, 1, '2017-04-01', '2018-03-31');
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (2, 1, 2, '2018-01-01', '2018-12-31');
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (3, 1, 3, '2019-12-31', '2020-12-30');
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (4, 2, 3, '2019-01-01', '2030-12-31');
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (5, 3, 1, '2019-01-01', '2019-12-31');
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (6, 1, 4, '2019-08-01', '2020-07-31');
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (7, 2, 5, '2019-01-01', '2020-12-31');
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (8, 2, 6, '2019-01-01', '2020-12-31');
insert into Vertrag(ID, Produkt_FK, Kunde_FK, Versicherungsbeginn, Versicherungsende)
values (9, 2, 7, '2019-01-01', '2020-12-31');

--------------------------------------------------------------------------------------------------
-- Deckungsbetrag
--------------------------------------------------------------------------------------------------
insert into Deckungsbetrag(ID, Deckungsart_FK, Deckungsbetrag)
values (1, 1, 100000000);
insert into Deckungsbetrag(ID, Deckungsart_FK, Deckungsbetrag)
values (2, 2, 15000000);
insert into Deckungsbetrag(ID, Deckungsart_FK, Deckungsbetrag)
values (3, 3, 100000);
insert into Deckungsbetrag(ID, Deckungsart_FK, Deckungsbetrag)
values (4, 3, 200000);
insert into Deckungsbetrag(ID, Deckungsart_FK, Deckungsbetrag)
values (5, 3, 300000);
insert into Deckungsbetrag(ID, Deckungsart_FK, Deckungsbetrag)
values (6, 4, 50000);
insert into Deckungsbetrag(ID, Deckungsart_FK, Deckungsbetrag)
values (7, 4, 150000);
insert into Deckungsbetrag(ID, Deckungsart_FK, Deckungsbetrag)
values (8, 5, 1500);

--------------------------------------------------------------------------------------------------
-- Deckungspreis
--------------------------------------------------------------------------------------------------
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (1, 1, '2017-01-01', '2017-12-31', 10);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (2, 1, '2018-01-01', '2018-12-31', 11);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (3, 1, '2019-01-01', '2099-12-31', 12);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (4, 2, '2017-01-01', '2018-12-31', 9);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (5, 2, '2019-01-01', '2099-12-31', 10);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (6, 3, '2017-01-01', '2099-12-31', 30);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (7, 4, '2017-01-01', '2018-12-31', 55);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (8, 4, '2019-01-01', '2099-12-31', 60);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (9, 5, '2017-01-01', '2017-12-31', 84);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (10, 5, '2018-01-01', '2018-12-31', 87);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (11, 5, '2019-01-01', '2099-12-31', 90);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (12, 6, '2017-01-01', '2099-12-31', 12);
insert into Deckungspreis(ID, Deckungsbetrag_FK, Gueltig_Von, Gueltig_Bis, Preis)
values (13, 7, '2017-01-01', '2018-12-31', 30);

--------------------------------------------------------------------------------------------------
-- Deckung
--------------------------------------------------------------------------------------------------
insert into Deckung(Vertrag_FK, Deckungsart_FK, Deckungsbetrag)
values (1, 1, 100000000);
insert into Deckung(Vertrag_FK, Deckungsart_FK, Deckungsbetrag)
values (1, 2, 15000000);
insert into Deckung(Vertrag_FK, Deckungsart_FK, Deckungsbetrag)
values (2, 1, 100000000);
insert into Deckung(Vertrag_FK, Deckungsart_FK, Deckungsbetrag)
values (2, 2, 15000000);
insert into Deckung(Vertrag_FK, Deckungsart_FK, Deckungsbetrag)
values (3, 1, 100000000);
insert into Deckung(Vertrag_FK, Deckungsart_FK, Deckungsbetrag)
values (3, 2, 15000000);

commit;