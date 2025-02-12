--select table_name from user_tables;
select *
from Angebot
order by AID;
select *
from Gebot
order by GID;

delete
from Gebot
where GID = 15002;

select *
from Angebot;
select *
from Gebot;

select a.aid, a.ArtikelBez, AuktionEndeZeitpunkt, GebotsZeitpunkt, AuktionMinPreis, GebotsPreis
from angebot a,
     gebot g
where a.aid = g.aid;

select a.aid, a.ArtikelBez, AuktionEndeZeitpunkt, GebotsZeitpunkt, AuktionMinPreis, GebotsPreis
from angebot a,
     gebot g
where a.aid = g.aid
  and GebotsZeitpunkt > AuktionEndeZeitpunkt;

select a.aid, a.ArtikelBez, AuktionEndeZeitpunkt, GebotsZeitpunkt, AuktionMinPreis, GebotsPreis
from angebot a,
     gebot g
where a.aid = g.aid
  and GebotsPreis < AuktionMinPreis;

select g1.gid, g2.gid
from gebot g1,
     gebot g2
where g1.aid = g2.aid
  and g1.GebotsZeitpunkt < g2.GebotsZeitpunkt
  and g1.GebotsPreis >= g2.GebotsPreis;

select g1.gid, g2.gid
from gebot g1,
     gebot g2
where g1.aid = g2.aid
  and g1.GebotsZeitpunkt = g2.GebotsZeitpunkt
  and g1.gid <> g2.gid;

