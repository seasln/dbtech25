create table Angebot
(
    AID                  integer       not null,
    ArtikelBez           varchar2(30)  not null,
    AuktionEndeZeitpunkt date          not null,
    AuktionMinPreis      decimal(9, 2) not null,
    constraint pk_angebot primary key (AID)
);

create table Gebot
(
    GID             integer       not null,
    AID             integer       not null,
    GebotsZeitpunkt date null,
    GebotsPreis     decimal(9, 2) not null,
    constraint pk_gebot primary key (GID),
    constraint fk_angebot foreign key (AID) references Angebot
);




