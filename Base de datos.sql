CREATE SCHEMA proyecto_restaurante;

USE PROYECTO_RESTAURANTE;

CREATE TABLE EMPLEADOS(
	NUM_EMPLEADO INT(3) NOT NULL,
    NOMBRE VARCHAR(30) NOT NULL,
    A_PATERNO VARCHAR(15) NOT NULL,
    A_MATERNO VARCHAR(15) NOT NULL,
    PUESTO varchar(20) NULL,
    SUCURSAL INT(4) NOT NULL,
    
    PRIMARY KEY (NUM_EMPLEADO)
    );

CREATE TABLE HORASTRABAJADAS(
ID INT AUTO_INCREMENT NOT NULL,
FECHA_ENTRADA DATE NOT NULL,
H_ENTRADA TIME NOT NULL,
FECHA_SALIDA DATE NULL,
H_SALIDA TIME NULL DEFAULT '00:00',
ACTIVO_SISTEMA BOOLEAN DEFAULT FALSE,
NUM_EMPLEADO INT(3) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (NUM_EMPLEADO) REFERENCES EMPLEADOS(NUM_EMPLEADO)
);

CREATE TABLE PRODUCTOS(
COD_PRODUCTO INT NOT NULL AUTO_INCREMENT,
SECCION_PRODUCTO VARCHAR(30),
NOM_PRODUCTO VARCHAR(30),
PRE_PRODUCTO INT NOT NULL,
PRIMARY KEY(COD_PRODUCTO)
);

CREATE TABLE MESAS(
NUM_MESA INT(3) NOT NULL,
MESA_DISPONIBLE BOOLEAN NOT NULL DEFAULT TRUE,
PRIMARY KEY(NUM_MESA)
);

CREATE TABLE TICKET(
NUM_TICKET INT NOT NULL AUTO_INCREMENT,
ESTADO_TICKET BOOLEAN DEFAULT TRUE,
FECHA DATE,
HORA_REGISTRO TIME,
TOTAL DECIMAL NOT NULL,
NUM_EMPLEADO INT(3),
NUM_MESA INT(3),
PRIMARY KEY(NUM_TICKET),
FOREIGN KEY(NUM_EMPLEADO) REFERENCES EMPLEADOS(NUM_EMPLEADO),
FOREIGN KEY(NUM_MESA) REFERENCES MESAS(NUM_MESA)
);

CREATE TABLE ORDENES_AL_MOMENTO(
	ID INT NOT NULL AUTO_INCREMENT,
    NUM_MESA INT NOT NULL,
    COD_PRODUCTO INT NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY (NUM_MESA) REFERENCES MESAS(NUM_MESA),
    FOREIGN KEY(COD_PRODUCTO) REFERENCES PRODUCTOS(COD_PRODUCTO)
);

INSERT INTO EMPLEADOS(NUM_EMPLEADO, NOMBRE, A_PATERNO, A_MATERNO, PUESTO, SUCURSAL)
VALUES ('160','Gustavo Angel', 'Arroyo', 'Gutierrez', 'Mesero','1502'),
		('105','Paola Carolina', 'Gaspar', 'Susunaga', 'Mesero', '1502'),
        ('107','Carlos', 'Hinojosa', 'Perez', 'Mesero', '1502');

INSERT INTO MESAS(NUM_MESA) 
VALUES ('1'), ('2'), ('3'), ('4'), ('5'), ('21'), ('22'), ('23'), ('31'), ('32'), ('33'), ('34'),
		('101'), ('102'), ('103'), ('104'), ('105'), ('106'), ('107'), ('111'), ('112'), ('113'),
        ('114'), ('121'), ('122'), ('123'), ('131'), ('132'), ('133'), ('134'), ('135'), ('41'), 
        ('42'), ('51'), ('52'), ('61'), ('62'), ('63'), ('71'), ('72'), ('73');

INSERT INTO PRODUCTOS(SECCION_PRODUCTO, NOM_PRODUCTO, PRE_PRODUCTO)
VALUES ('Entradas','TRIPLE DIPPER', 229),
		('Entradas', 'ULTIMATE DIPPER', 259),
        ('Entradas','BB WINGS', 149),
		('Entradas', 'WINGS', 159),
        ('Entradas','LOADED WINGS', 189),
        ('Entradas','EGGROLLS', 159),
        ('Entradas','FRIED CHZ', 139),
        ('Entradas','TX FRIES', 129),
        ('Entradas','CHIPS', 75),
        ('Entradas','CHZ POPPERS', 129),
        ('Entradas','BITES', 169),
        ('Entradas','GUACAMOLE', 129),
        ('Entradas','NACHOS', 165),
        ('Ensaladas', 'SANTA FE', 179),
        ('Ensaladas', 'BUFF SALAD', 179),
        ('Ensaladas', 'CAR SALAD', 184),
        ('Ensaladas', 'EXPLO SALAD', 184),
        ('Ensaladas', 'D CAES', 72),
        ('Ensaladas', 'CRISP SALAD', 179),
        ('Ensaladas', 'D HOUSE', 72),
        ('Del Mar', 'CF FISH', 175),
        ('Del Mar', 'GG SALMON', 279),
        ('Del Mar', 'GG TILAPIA', 175),
        ('Del Mar', 'FISH & SHRIMP', 265),
        ('Del Mar', 'ANCHO SALMON', 289),
        ('Cortes', 'NEW YORK', 299),
        ('Cortes', 'RIBEYE', 359),
        ('Cortes', 'SIRLOIN', 295),
        ('Fajitas', 'CK FAJ', 199),
        ('Fajitas', 'BF FAJ', 279),
        ('Fajitas', 'DBL CK FAJ', 359),
        ('Fajitas', 'DBL BR FAJ', 499),
        ('Fajitas', 'FAJ TRIO', 309),
        ('Clasicos pollo', 'CRISP', 179),
        ('Clasicos pollo', 'HC CRISP', 179),
        ('Clasicos pollo', 'BLUE CRISP', 179),
        ('Clasicos pollo', 'MONTY', 185),
        ('Clasicos pollo', 'MILE HIGH', 209),
        ('Clasicos pollo', 'RITA CK', 179),
        ('Clasicos pollo', 'CFCH', 185),
        ('Sandwich', 'CHICKY SANDWICH', 174),
        ('Sandwich', 'RANCH SANDWICH', 169),
        ('Sandwich', 'WD SANDWICH', 149),
        ('Costillas', 'RIBS NAC', 299),
        ('Costillas', '1/2 RIBS NAC', 219),
        ('Costillas', 'RIBS IMP', 419),
        ('Costillas', '1/2 RIBS IMP', 294),
        ('Comida Empleado', 'TACOS', 0),
        ('Comida Empleado', 'SANDWICH', 0),
        ('Comida Empleado', 'HAMBURGUESA', 0),
        ('Algo ligero', 'GG SALMON', 279),
        ('Algo ligero', 'BLK FISH', 149),
        ('Algo ligero', 'SANTA FE', 179),
        ('Algo ligero', 'CK CAES', 149),
        ('Hamburguesas', 'OTC', 155),
        ('Hamburguesas', 'BACON BURGER', 169),
        ('Hamburguesas', 'ROOM', 169),
        ('Hamburguesas', 'SWEET AVOCADO', 209),
        ('Hamburguesas', 'CHILI AMOR', 209),
        ('Hamburguesas', 'DEAR GUACAMOLE', 209),
        ('Hamburguesas', 'ULTIMATE LOVE', 259),
        ('Hamburguesas', 'BACON ME', 229),
        ('Hamburguesas', 'GREG BURGER', 229),
        ('Sopas', 'ENCHA', 69),
        ('Sopas', 'NOODLE', 69),
        ('Sopas', 'BROCCOLI', 74),
        ('Bebidas', 'REFRESCO', 47),
        ('Bebidas', 'LATA', 42),
        ('Bebidas', 'TE', 46),
        ('Bebidas', 'LIMONADA', 49),
        ('Bebidas', 'LIM FRESA', 56),
        ('Bebidas', 'LIM MANGO', 56),
        ('Bebidas', 'KID BEBIDA', 25),
        ('Infantil', 'KID PIZZA', 89),
        ('Infantil', 'KID BB WINGS', 89),
        ('Infantil', 'KIG POLLO', 89),
        ('Infantil', 'KID TENDERS', 89),
        ('Postres', 'MOLTEN', 139),
        ('Postres', 'MINI MOLTEN', 95),
        ('Postres', 'CHZCAKE', 129),
        ('Postres', 'APPLE TART', 129),
        ('Postres', 'PARADISE', 139),
        ('Extras', '6 oz POLLO', 30),
        ('Extras', '4 oz BF', 45),
        ('Extras', 'AGUACATE', 30),
        ('Extras', 'TOCINO', 25),
        ('Extras', 'CARNE BURGER', 30),
        ('Extras', 'ADEREZO', 15),
        ('Extras', 'QUESO AMARILLO', 15),
        ('Extras', 'QUESO SUIZO', 15),
        ('Extras', '1 PAN AJO', 20),
        ('Extras', '2 PAN AJO', 38),
		('Pastas', 'ALF POLLO', 199),
        ('Pastas', 'ALF SHRM', 239),
        ('Pastas', 'ALF SALMON', 269),
        ('Pastas', 'CHI POLLO', 199),
        ('Pastas', 'CHI SHRM', 239),
        ('Pastas', 'CHI SALMON', 269),
        ('Pastas', 'CIL POLLO', 199),
        ('Pastas', 'CIL SHRM', 239),
        ('Pastas', 'CIL SALMON', 269),
        ('Pastas', 'RED POLLO', 199),
        ('Pastas', 'RED SHRM', 239),
        ('Pastas', 'RED SALMON', 269),
        ('Quesadillas', 'QUESA SANTA FE', 159),
        ('Quesadillas', 'BACON RANCH', 159),
        ('Quesadillas', 'CK QUESA', 159),
        ('Quesadillas', 'BF QUESA', 185);
        