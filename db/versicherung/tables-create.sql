create table Kunde
(
    ID           integer       not null,
    Name         varchar2(100) not null,
    Geburtsdatum date,
    constraint kunde_pk primary key (ID)
);

create table Produkt
(
    ID      integer       not null,
    KurzBez varchar2(20)  not null,
    Bez     varchar2(200) not null,
    constraint produkt_pk primary key (ID)
);

create table Vertrag
(
    ID                  integer not null,
    Produkt_FK          integer not null,
    Kunde_FK            integer not null,
    Versicherungsbeginn date    not null,
    Versicherungsende   date    not null,
    constraint vertrag_pk primary key (ID),
    constraint produkt_fk1 foreign key (Produkt_FK) references Produkt,
    constraint kunde_fk foreign key (Kunde_FK) references Kunde
);

create table Deckungsart
(
    ID         integer       not null,
    Produkt_FK integer       not null,
    KurzBez    varchar2(20)  not null,
    Bez        varchar2(200) not null,
    constraint deckungsart_pk primary key (ID),
    constraint produkt_fk2 foreign key (Produkt_FK) references Produkt
);

create table Ablehnungsregel
(
    Deckungsart_FK integer       not null,
    LfdNr          integer       not null,
    R_Betrag       varchar2(100) not null,
    R_Alter        varchar2(100) not null,
    constraint regel_pk primary key (Deckungsart_FK, LfdNr),
    constraint deckungsart_fk foreign key (Deckungsart_FK) references Deckungsart
);

create table Deckung
(
    Vertrag_FK     integer        not null,
    Deckungsart_FK integer        not null,
    Deckungsbetrag decimal(13, 2) not null,
    constraint deckung_pk primary key (Vertrag_FK, Deckungsart_FK),
    constraint vertrag_fk foreign key (Vertrag_FK) references Vertrag,
    constraint deckungsart_fk1 foreign key (Deckungsart_FK) references Deckungsart
);

create table Deckungsbetrag
(
    ID             integer        not null,
    Deckungsart_FK integer        not null,
    Deckungsbetrag decimal(13, 2) not null,
    constraint deckungsbetrag_pk primary key (ID),
    constraint deckungsart_fk2 foreign key (Deckungsart_FK) references Deckungsart
);

create table Deckungspreis
(
    ID                integer        not null,
    Deckungsbetrag_FK integer        not null,
    Gueltig_Von       date           not null,
    Gueltig_Bis       date           not null,
    Preis             decimal(13, 2) not null,
    constraint deckungspreis_pk primary key (ID),
    constraint deckungsbetrag_fk foreign key (Deckungsbetrag_FK) references Deckungsbetrag
);
