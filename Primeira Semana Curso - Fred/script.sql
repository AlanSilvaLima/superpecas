
CREATE DATABASE SuperPecas;

USE SuperPecas;

CREATE TABLE Carros (
    CarroID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NomeModelo VARCHAR(255) NOT NULL,
    Fabricante VARCHAR(255) NOT NULL,
    CodigoUnico VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Pecas (
    PecaID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    Nome VARCHAR(255) NOT NULL,
    Descricao TEXT NOT NULL,
    NumeroSerie VARCHAR(255) NOT NULL UNIQUE,
    Fabricante VARCHAR(255) NOT NULL,
    ModeloCarro VARCHAR(255) NOT NULL,
    CarroID INT NOT NULL,
    FOREIGN KEY (CarroID) REFERENCES Carros(CarroID) ON DELETE RESTRICT
);

CREATE INDEX idx_nome_modelo ON Carros(NomeModelo);
CREATE INDEX idx_fabricante ON Carros(Fabricante);
CREATE INDEX idx_numero_serie ON Pecas(NumeroSerie);
CREATE INDEX idx_modelo_carro ON Pecas(ModeloCarro);
