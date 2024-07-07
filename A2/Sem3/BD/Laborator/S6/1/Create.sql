
--create database MersulTrenurilor
--go
 
--use MersulTrenurilor
--go
 
create table TipTren(
    id int primary key identity,
    descriere varchar(50)
)
 
create table Tren(
    id int primary key identity,
    nume varchar(50),
    id_tip int foreign key references TipTren(id)
)
 
create table Ruta(
    id int primary key identity,
    nume varchar(50),
    id_tren int foreign key references Tren(id)
)
 
create table Statie(
    id int primary key identity,
    nume varchar(50)
)
 
create table StatieRuta(
    idRuta int foreign key references Ruta(id),
    idStatie int foreign key references Statie(id),
    OraS time,
    OraP time,
    constraint pk_Statie_Ruta primary key (idRuta,idStatie)
)

