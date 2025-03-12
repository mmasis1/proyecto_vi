-- Crear la base de datos
CREATE DATABASE GYM;
USE GYM;

-- Tabla de Roles
CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(100)
);

-- Tabla de Usuarios
Drop table usuario;
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    id_rol INT NOT NULL,  
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado bool,
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);
ALTER TABLE rol 
MODIFY COLUMN nombre_rol  VARCHAR(100);





select * from usuario;
-- Tabla de Categorías de Productos
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla de Productos
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    id_categoria INT,
    imagen VARCHAR(255),
    estado VARCHAR(100),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

ALTER TABLE producto 
MODIFY COLUMN estado VARCHAR(100);

-- Tabla de Planes de Membresía
CREATE TABLE plan_membresia (
    id_plan INT AUTO_INCREMENT PRIMARY KEY,
    nombre_plan VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    duracion_meses INT NOT NULL
);

-- Tabla de Métodos de Pago
CREATE TABLE metodo_pago (
    id_metodo_pago INT AUTO_INCREMENT PRIMARY KEY,
    nombre_metodo ENUM('tarjeta', 'transferencia', 'aplicacion_movil') NOT NULL UNIQUE
);

-- Tabla de Pagos
CREATE TABLE pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    id_metodo_pago INT NOT NULL,
    fecha_pago DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_metodo_pago) REFERENCES metodo_pago(id_metodo_pago)
);

-- Tabla de Carrito de Compras
CREATE TABLE carrito_compra (
    id_carrito INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla de Detalles del Carrito
CREATE TABLE detalles_carrito (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_carrito INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (id_carrito) REFERENCES carrito_compra(id_carrito),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- Tabla de Preguntas Frecuentes
CREATE TABLE preguntas_frecuentes (
    id_pregunta INT AUTO_INCREMENT PRIMARY KEY,
    pregunta TEXT NOT NULL,
    respuesta TEXT NOT NULL
);

-- Tabla de Información "Sobre Nosotros"
CREATE TABLE sobre_nosotros (
    id_info INT AUTO_INCREMENT PRIMARY KEY,
    mision TEXT,
    vision TEXT,
    historia TEXT,
    valores TEXT
);

-- Tabla de Contacto (Formulario de Contacto)
CREATE TABLE contacto (
    id_contacto INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    asunto VARCHAR(200),
    mensaje TEXT NOT NULL,
    fecha_envio DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);
