-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-04-2017 a las 23:07:28
-- Versión del servidor: 10.1.19-MariaDB
-- Versión de PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `onmarket`
--
SET FOREIGN_KEY_CHECKS=0;

-- Truncar tablas antes de insertar `lineas_pedido`
--
TRUNCATE TABLE `lineas_pedido`;

--  Truncar tablas antes de insertar `pedidos`
--
TRUNCATE TABLE `pedidos`;


-- Truncar tablas antes de insertar `productos`
--

TRUNCATE TABLE `productos`;
--
-- Truncar tablas antes de insertar `subcategorias`
--

TRUNCATE TABLE `subcategorias`;
--
--
-- Truncar tablas antes de insertar `categorias`

TRUNCATE TABLE `categorias`;

-- Truncar tablas antes de insertar `usuarios`
TRUNCATE TABLE `usuarios`;


SET FOREIGN_KEY_CHECKS=1;


-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `activo`) VALUES
(1, 'El Horno', 1),
(2, 'El Huerto', 1),
(3, 'Carnicería', 1),
(4, 'Charcutería', 1),
(5, 'Pescadería', 1),
(6, 'Despensa', 1),
(7, 'Bebidas', 1),
(8, 'Cuidado Personal', 1),
(9, 'Droguería y Hogar', 1),
(10, 'Congelados', 1),
(11, 'Productos Ecológicos', 0),
(12, 'Mascotas', 0),
(13, 'Productos Lácteos', 1);


INSERT INTO `subcategorias` (`id`, `nombre`, `categoria`, `activo`) VALUES
(1, 'pan', 1, 1),
(2, 'bollería', 1, 1),
(3, 'empanadas y bollería salada', 1, 0),
(4, 'Frutas', 2, 1),
(5, 'Verduras', 2, 1),
(6, 'Hortalizas', 2, 0),
(7, 'Huevos', 2, 1),
(8, 'Cerdo', 3, 1),
(9, 'Pollo', 3, 1),
(10, 'Pavo', 3, 1),
(11, 'Vacuno', 3, 1),
(12, 'Conejo', 3, 0),
(13, 'Cordero', 3, 0),
(14, 'Curados', 4, 1),
(15, 'Cocidos', 4, 1),
(16, 'Salchichas y Chistorras', 4, 1),
(17, 'Productos Para Untar', 4, 0),
(18, 'Pescados', 5, 1),
(19, 'Mariscos', 5, 1),
(20, 'Aceites y Condimentos', 6, 1),
(21, 'Conservas', 6, 1),
(22, 'Harinas y Levaduras', 6, 1),
(23, 'Arroz Pasta y Legumbres', 6, 1),
(24, 'Caldos Cremas y Purés', 6, 1),
(25, 'Platos Preparados', 6, 1),
(26, 'Aperitivos', 6, 0),
(27, 'Galletas y Meriendas', 6, 1),
(28, 'Chocolates y Dulces', 6, 1),
(29, 'Azúcar y Edulcorantes', 6, 1),
(30, 'Postres', 6, 0),
(31, 'Alimentación Infantil', 6, 0),
(32, 'Aguas', 7, 1),
(33, 'Zumos', 7, 1),
(34, 'Leche y Derivados', 7, 1),
(35, 'Refrescos', 7, 1),
(36, 'Cervezas', 7, 1),
(37, 'Vinos', 7, 1),
(38, 'Cavas', 7, 0),
(39, 'Licores', 7, 0),
(40, 'Aseo Personal', 8, 1),
(41, 'Cuidado Capilar', 8, 1),
(42, 'Cosmética Facial', 8, 1),
(43, 'Higiene Bucal', 8, 1),
(44, 'Higiene Íntima', 8, 1),
(45, 'Perfumes y Colonias', 8, 1),
(46, 'Afeitado', 8, 1),
(47, 'Cuidado Infantil', 8, 0),
(48, 'Complementos Alimenticios', 8, 0),
(49, 'Productos Naturales', 8, 1),
(50, 'Limpieza del Hogar', 9, 1),
(51, 'Lavavajillas', 9, 1),
(52, 'Cuidado de la Ropa', 9, 1),
(53, 'Productos de Papel', 9, 1),
(54, 'Bolsas', 9, 0),
(55, 'Ambientadores y Velas', 9, 1),
(66, 'Postres Fríos', 10, 1),
(67, 'Pescado y Maiscos', 10, 1),
(68, 'Platos Preparados Congelados', 10, 1),
(69, 'Pizzas', 10, 1),
(70, 'Congelados Varios', 10, 0),
(71, 'Bebidas Ecológicas', 11, 0),
(72, 'Postres Ecológicos', 11, 0),
(73, 'Conservas Ecológicas', 11, 0),
(74, 'Comida para Perros', 12, 0),
(75, 'Comida para Gatos', 12, 0),
(76, 'Comida para Peces', 12, 0),
(77, 'Yogures', 13, 1),
(78, 'Nata y Mantequillas', 13, 1),
(79, 'Quesos', 13, 1);

INSERT INTO `productos` (`id`, `nombre`, `activo`, `subcategoria`, `marca`, `precio`, `stock`, `peso_vol`, `foto`) VALUES
(1, 'Pan de molde', 1, 1, 'Bimbo', 2.50, 250, '300g', '1.jpg' ),
(2, 'Baguette', 1, 1, 'Hacendado', 0.70, 65, '100g', '2.jpg' ),
(3, 'Pan sin gluten', 1, 1, 'Hcendado', 0.90, 50, '100g', '3.jpg' ),
(4, 'Pan integral', 1, 1, 'Bimbo', 1.00, 150, '100g', '4.jpg' ),
(5, 'Pan sin sal', 1, 1, 'Hacendado', 1.20, 55, '100g', '5.jpg' ),

(6, 'Palmera', 1, 2, 'Bimbo', 1.00, 1000, '75g', '6.jpg' ),
(7, 'Napolitana', 1, 2, 'Martinez', 1.00, 1000, '65g', '7.jpg' ),
(8, 'Donuts', 1, 2, 'Bimbo', 1.00, 1000, '150g', '8.jpg' ),
(9, 'Tarta de chocolate', 1, 2, 'Martinez', 8.00, 500, '500g', '9.jpg' ),
(10, 'Donetes', 1, 2, 'Bimbo', 1.00, 1000, '100g', '10.jpg' ),

(11, 'Empanada de carne', 0, 3, 'Casa Tarradellas', 4.50, 300, '500g', '11.jpg' ),
(12, 'Empanada de atun', 0, 3, 'Casa Tarradellas', 4.50, 300, '500g', '12.jpg' ),
(13, 'Saladitos', 0, 3, 'Hacendado', 7.50, 750, '1Kg', '13.jpg' ),
(14, 'Napolitana de Jamon y queso', 0, 3, 'Martinez', 1.00, 400, '50g', '14.jpg' ),
(15, 'Empanada gallega', 0, 3, 'Casa Tarradellas', 5.50, 300, '500g', '15.jpg' ),

(16, 'Manzana', 1, 4, 'Marlene', 0.6, 2300, '1Kg', '16.jpg' ),
(17, 'Melón', 1, 4, 'EL bollo', 3.5, 300, '1.2Kg', '17.jpg' ),
(18, 'Plantano', 1, 4, 'Canarias', 0.8, 1500, '1Kg', '18.jpg' ),
(19, 'Pera', 1, 4, 'Marlene', 0.6, 1300, '1Kg', '19.jpg' ),
(20, 'Kiwi', 1, 4, 'Zespi', 0.6, 800, '1Kg', '20.jpg' ),

(21, 'Coliflor', 1, 5, 'El abuelo', 1.5, 3300, '1Kg', '21.jpg' ),
(22, 'Espinacas', 1, 5, 'El abuelo', 2.5, 3300, '1Kg', '22.jpg' ),
(23, 'Calbaza', 1, 5, 'El abuelo', 3.00, 3300, '1Kg', '23.jpg' ),
(24, 'Calabacin', 1, 5, 'El abuelo', 2.5, 3300, '1Kg', '24.jpg' ),
(25, 'Puerro', 1, 5, 'El abuelo', 5.00, 3300, '1Kg', '25.jpg' ),

(26, 'Patatas', 0, 6, 'El abuelo', 2.5, 3300, '1Kg', '26.jpg' ),
(27, 'Zanahorias', 0, 6, 'Campoviejo', 3.5, 950, '1Kg', '27.jpg' ),
(28, 'Acelgas', 0, 6, 'El abuelo', 1.5, 600, '500g', '28.jpg' ),
(29, 'Judia verde', 0, 6, 'El abuelo', 2.5, 2300, '1Kg', '29.jpg' ),
(30, 'Tomate', 0, 6, 'La tomatina', 2.5, 3300, '1Kg', '30.jpg' ),

(31, 'Docena:Huevo M', 1, 7, 'La gallinita ciega', 2.50, 300, '1Kg', '31.jpg' ),
(32, 'Docena:Huevo L', 1, 7, 'La gallinita ciega', 3.00, 900, '1Kg', '32.jpg' ),
(33, 'Docena:Huevo XL', 1, 7, 'La gallinita ciega', 3.50, 500, '1Kg', '33.jpg' ),
(34, 'Docena:Huevo ecologico', 1, 7, 'La gallinita ciega', 5.00, 50, '1Kg', '34.jpg' ),
(35, 'Docena:Huevo de corral', 1, 7, 'La gallinita ciega', 2.5, 100, '1Kg', '35.jpg' ),

(36, 'Solomillo', 1, 8, 'Campo frio', 9.5, 600, '1Kg', '36.jpg' ),
(37, 'Oreja', 1, 8, 'Campo frio', 5.00, 700, '1Kg', '37.jpg' ),
(38, 'Careta', 1, 8, 'Campo frio', 7.00, 300, '1Kg', '38.jpg' ),
(39, 'Filete', 1, 8, 'Campo frio', 6.50, 2600, '1Kg', '39.jpg' ),
(40, 'Morcilla', 1, 8, 'Guijuelo', 10.50, 600, '700Kg', '40.jpg' ),

(41, 'Pechuga', 1, 9, 'EL corral', 4.50, 750, '1Kg', '41.jpg' ),
(42, 'Filete de contramuslo', 1, 9, 'EL corral', 6.50, 800, '1Kg', '42.jpg' ),
(43, 'Pollo entero', 1, 9, 'EL corral', 3.50, 1600, '600g', '43.jpg' ),
(44, 'Alitas', 1, 9, 'EL corral', 4.50, 2600, '1Kg', '44.jpg' ),
(45, 'Muslos', 1, 9, 'EL corral', 4.50, 1900, '1Kg', '45.jpg' ),

(46, 'Filetes', 1, 10, 'EL corral', 4.50, 600, '1Kg', '46.jpg' ),
(47, 'Pavo entero', 1, 10, 'EL corral', 4.50, 600, '1Kg', '47.jpg' ),
(48, 'Muslo', 1, 10, 'EL corral', 4.50, 600, '1Kg', '48.jpg' ),
(49, 'Pechga', 1, 10, 'EL corral', 4.50, 600, '1Kg', '49.jpg' ),
(50, 'Medio pavo', 1, 10, 'EL corral', 4.50, 600, '1Kg', '50.jpg' ),

(51, 'Entrecot', 1, 11, 'Hnos Avila', 10.50, 400, '700g', '51.jpg' ),
(52, 'Filete', 1, 11, 'Hnos Avila', 4.50, 1600, '1Kg', '52.jpg' ),
(53, 'Callos', 1, 11, 'Hnos Avila', 4.50, 500, '500g', '53.jpg' ),
(54, 'Chuleton', 1, 11, 'Hnos Avila', 12.50, 600, '800Kg', '54.jpg' ),
(55, 'Rabo de toro', 1, 11, 'Hnos Avila', 6.00, 300, '400g', '55.jpg' ),

(56, 'Conejo entero', 0, 12, 'Hnos Avila', 12.00, 50, '3kg', '56.jpg' ),
(57, 'Medio Conejo', 0, 12, 'Hnos Avila', 6.00, 150, '2kg', '57.jpg' ),
(58, 'Conejo en trozos', 0, 12, 'Hnos Avila', 8.00, 300, '900g', '58.jpg' ),
(59, 'Conejo al ajillo', 0, 12, 'Hnos Avila', 13.00, 450, '3kg', '59.jpg' ),
(60, 'Conejo adobado', 0, 12, 'Hnos Avila', 13.00, 600, '3kg', '60.jpg' ),

(61, 'Cordero entero', 0, 13, 'El matadero', 25.00, 100, '4kg', '61.jpg' ),
(62, 'Chuletas', 0, 13, 'El matadero', 15.00, 2600, '1kg', '62.jpg' ),
(63, 'Cordero lechal', 0, 13, 'El matadero', 30.00, 300, '3kg', '63.jpg' ),
(64, 'Pierna', 0, 13, 'El matadero', 13.00, 700, '600g', '64.jpg' ),
(65, 'Paletilla', 0, 13, 'El matadero', 12.00, 800, '400g', '65.jpg' ),

(66, 'Jamón', 1, 14, 'Guijuelo', 200, 5000, '7kg', '66.jpg' ),
(67, 'Lomo cerdo', 1, 14, 'Guijuelo', 35.00, 6000, '3kg', '67.jpg' ),
(68, 'Queso de cabra', 1, 14, 'La cabra lechera', 13.00, 3000, '1kg', '68.jpg' ),
(69, 'Queso de oveja', 1, 14, 'La oveja lechera', 12.00, 4000, '1kg', '69.jpg' ),
(70, 'Chorizo', 1, 14, 'Guijuelo', 10.00, 5600, '700g', '70.jpg' ),

(71, 'Jamon York', 1, 15, 'CampoFrio', 1.50, 5000, '500g', '71.jpg' ),
(72, 'Pechga de pollo', 1, 15, 'CampoFrio', 1.50, 6000, '500', '72.jpg' ),
(73, 'Pechuga de pavo', 1, 15, 'CampoFrio', 1.50, 500, '500', '73.jpg' ),
(74, 'Mortadela', 1, 15, 'CampoFrio', 1.50, 5600, '500', '74.jpg' ),
(75, 'Mortadela con aceitunas', 1, 15, 'CampoFrio', 2.00, 600, '500', '75.jpg' ),

(76, 'Salchicha', 1, 16, 'Frankfurt', 2.00, 5400, '500g', '76.jpg' ),
(77, 'Salchicha de pollo', 1, 16, 'Frankfurt', 2.00, 6300, '500g', '77.jpg' ),
(78, 'Salchicha rellena de queso', 1, 16, 'Frankfurt', 2.50, 4500, '500g', '78.jpg' ),
(79, 'Salchicha alemana', 1, 16, 'Frankfurt', 3.00, 5900, '500g', '79.jpg' ),
(80, 'Chistorra', 1, 16, 'Hnos Avila', 6.00, 5600, '700g', '80.jpg' ),

(81, 'Queso de las finas hierbas', 0, 17, 'La vaca que rie', 2.00, 7800, '300g', '81.jpg' ),
(82, 'Queso semicurado', 0, 17, 'La vaca que rie', 2.00, 3200, '300g', '82.jpg' ),
(83, 'Nocilla', 0, 17, 'Nocilla', 3.00, 560, '700g', '83.jpg' ),
(84, 'Nutella', 0, 17, 'Nutella', 3.50, 7500, '700g', '84.jpg' ),
(85, 'Queso de cabra', 0, 17, 'La vaca que rie', 2.00, 300, '300g', '85.jpg' ),

(86, 'Lubina', 1, 18, 'Capitan Pescanova', 8.00, 400, '700g', '86.jpg' ),
(87, 'Gallo', 1, 18, 'Capitan Pescanova', 7.50, 300, '700g', '87.jpg' ),
(88, 'Merluza', 1, 18, 'Capitan Pescanova', 10.00, 600, '700g', '88.jpg' ),
(89, 'Pescadilla', 1, 18, 'Capitan Pescanova', 12.00, 500, '700g', '89.jpg' ),
(90, 'Trucha', 1, 18, 'Capitan Pescanova', 11.00, 200, '700g', '90.jpg' ),

(91, 'Necora', 1, 19, 'Cantábrico', 15.00, 2500, '1kg', '91.jpg' ),
(92, 'Bogavante', 1, 19, 'Cantábrico', 17.00, 700, '1kg', '92.jpg' ),
(93, 'Langostino', 1, 19, 'Cantábrico', 9.00, 6000, '1kg', '93.jpg' ),
(94, 'Carabinero', 1, 19, 'Cantábrico', 12.00, 900, '1kg', '94.jpg' ),
(95, 'Percebes', 1, 19, 'Cantábrico', 40.00, 300, '1kg', '95.jpg' ),

(96, 'Aceite de oliva', 1, 20, 'Carbonell', 1.20, 250, '1l', '96.png' ),
(97, 'Aceite de girasol', 1, 20, 'Koipesol', 1.0, 300, '1l', '97.png' ),
(98, 'Vinagre de vino', 1, 20, 'Carbonell', 2.50, 60, '1l', '98.png' ),
(99, 'Vinagre de modena', 1, 20, 'Clemente', 3.50, 90, '1l', '99.png' ),
(100, 'Sal', 1, 20, 'Cantábrico', 1.50, 500, '300g', '100.png' ),

(101, 'Garbanzos en lata', 1, 21, 'Litoral', 2.50, 100, '500g', '101.png' ),
(102, 'Atun en aceite', 1, 21, 'Calvo', 2.0, 300, '400g', '102.png' ),
(103, 'Tomate en lata', 1, 21, 'Apis', 2.50, 80, '450g', '103.png' ),
(104, 'Mejillones en escabeche', 1, 21, 'Consorcio', 4.0, 330, '400g', '104.png' ),
(105, 'Bonito en aceite', 1, 21, 'Consorcio', 2.0, 200, '300g', '105.png' ),

(106, 'Harina de trigo', 1, 22, 'Gallo', 0.85, 550, '1kg', '106.png' ),
(107, 'Harina de fuerza', 1, 22, 'Gallo', 0.95, 230, '1kg', '107.png' ),
(108, 'Levadura de panaderia', 1, 22, 'Maizena', 2.55, 30, '300g', '108.png' ),
(109, 'Harina integral', 1, 22, 'Biojoy', 5.25, 40, '1kg', '109.png' ),
(110, 'Levadura en polvo', 1, 22, 'Royal', 1.25, 200, '300g', '110.png' ),

(111, 'Arroz Extra', 1, 23, 'SOS', 1.50, 500, '1kg', '111.png' ),
(112, 'Arroz integral', 1, 23, 'SOS', 1.55, 200, '1kg', '112.png' ),
(113, 'Fettuccini al huevo', 1, 23, 'Gallo', 0.80, 30, '250g', '113.png' ),
(114, 'Spaghettis', 1, 23, 'Gallo', 0.70, 400, '500g', '114.png' ),
(115, 'Lenteja pardina', 1, 23, 'Luengo', 1.25, 330, '500g', '115.png' ),

(116, 'Caldo de pollo', 1, 24, 'Gallina Blanca', 1.60, 222, '1l', '116.png' ),
(117, 'Caldo de verduras', 1, 24, 'Gallina Blanca', 1.60, 243, '1l', '117.png' ),
(118, 'Crema de verduras', 1, 24, 'Knorr', 1.55, 30, '0.5l', '118.png' ),
(119, 'Crema de setas del bosque', 1, 24, 'Knorr', 1.55, 40, '0.5l', '119.png' ),
(120, 'Pure de patatas', 1, 24, 'Maggi', 2.60, 330, '460g', '120.png' ),

(121, 'Ensalada de pasta con atun', 1, 25, 'Carretilla', 1.50, 50, '240g', '121.png' ),
(122, 'Albondigas con tomate', 1, 25, 'Carretilla', 1.80, 70, '300g', '122.png' ),
(123, 'Tortilla de patatas', 1, 25, 'Gourmet', 1.55, 60, '400g', '123.png' ),
(124, 'Paella valenciana', 1, 25, 'Gourmet', 1.55, 40, '400g', '124.png' ),
(125, 'Callos a la madrileña', 1, 25, 'Gourmet', 1.80, 30, '380g', '125.png' ),

(126, 'Riskettos', 1, 26, 'Risi', 0.90, 100, '120g', '126.png' ),
(127, 'Cocktail al horno', 1, 26, 'Matutano', 1.60, 243, '140g', '127.png' ),
(128, 'Pelotazos', 1, 26, 'Matutano', 0.90, 30, '160g', '128.png' ),
(129, 'Mix frutos secos', 1, 26, 'Mistercorn', 1.05, 60, '120g', '129.png' ),
(130, 'Bugles 3D', 1, 26, 'Matutano', 0.90, 80, '85g', '130.png' ),

(131, 'Kinder Delice', 1, 27, 'Kinder', 6.20, 100, '300g', '131.png' ),
(132, 'Minicampurrianas', 1, 27, 'Cuetara', 1.0, 300, '300g', '132.png' ),
(133, 'Chips Ahoy!', 1, 27, 'Chips Ahoy!', 1.30, 200, '160g', '133.png' ),
(134, 'Pantera rosa', 1, 27, 'Bimbo', 1.40, 230, '200g', '134.png' ),
(135, 'Nutella B-ready', 1, 27, 'Nutella', 3.40, 85, '400g', '135.png' ),

(136, 'Chocolate con leche extrafino', 1, 28, 'Nestle', 0.90, 100, '125g', '136.png' ),
(137, 'Chocolate 70 por ciento cacao', 1, 28, 'Valor', 1.95, 300, '300g', '137.png' ),
(138, 'Kit Kat', 1, 28, 'Kit Kat', 6.50, 200, '700g', '138.png' ),
(139, 'Dulce de leche', 1, 28, 'La lechera', 2.10, 230, '400g', '139.png' ),
(140, 'Toblerone Chocolate negro', 1, 28, 'Toblerone', 17.90, 85, '360g', '140.png' ),

(141, 'Azucar blanca', 1, 29, 'Azucarera', 0.70, 200, '1kg', '141.png' ),
(142, 'Azucar moreno', 1, 29, 'Azucarera', 1.25, 90, '800g', '142.png' ),
(143, 'Azucar organico de coco', 1, 29, 'Sevenhills Wholefoods', 13.90, 20, '1kg', '143.png' ),
(144, 'Edulcorante granulado en sobres', 1, 29, 'Natreen', 3.0, 100, '100g', '144.png' ),
(145, 'Edulcorante liquido', 1, 29, 'Gourmet', 0.70, 125, '0.1l', '145.png' ),

(146, 'Flan de vainilla', 1, 30, 'Pascual', 1.20, 200, '400g', '146.png' ),
(147, 'Tarta de oreo', 1, 30, 'Royal', 7.20, 90, '400g', '147.png' ),
(148, 'Tarta de chocolate', 1, 30, 'Dr Oetker', 3.80, 20, '355g', '148.png' ),
(149, 'Natillas de vainilla', 1, 30, 'Ram', 1.50, 100, '1l', '149.png' ),
(150, 'Natillas de chocolate', 1, 30, 'Pascual', 2.70, 125, '1kg', '150.png' ),

(151, 'Potito de arroz con pollo', 1, 31, 'Hero', 0.9, 450, '235g', '151.png' ),
(152, 'Potito de ternera con verduras', 1, 31, 'Hero', 0.9, 120, '235g', '152.png' ),
(153, 'Potito de espaguetis con ternera y jamon', 1, 31, 'Hero', 0.9, 200, '235g', '153.png' ),
(154, 'Potito de frutas variadas', 1, 31, 'Nestle', 1.50, 300, '400g', '154.png' ),
(155, 'Potito de frutas variadas con galleta', 1, 31, 'Nestle', 1.60, 225, '400g', '155.png' ),

(156, 'Agua mineral natural', 1, 32, 'Bezoya', 2.40, 340, '9l', '156.png' ),
(157, 'Agua mineral natural', 1, 32, 'Fontvella', 0.65, 100, '2.5l', '157.png' ),
(158, 'Agua mineral natural', 1, 32, 'Solan de cabras', 0.65, 220, '1.5l', '158.png' ),
(159, 'Agua con aloe vera', 1, 32, 'Aleo', 1.0, 300, '0.5l', '159.png' ),
(160, 'Agua con sabor a limon', 1, 32, 'Fontvella', 1.10, 225, '0.4l', '160.png' ),

(161, 'Zumo de naranja exprimida', 1, 33, 'Don Simon', 1.25, 350, '1l', '161.png' ),
(162, 'Zumo de naranja dulce', 1, 33, 'Granini', 1.65, 140, '1l', '162.png' ),
(163, 'Zumo de piña y coco', 1, 33, 'Granini', 1.80, 70, '1l', '163.png' ),
(164, 'Zumo de manzana', 1, 33, 'Gourmet', 0.7, 300, '0.6l', '164.png' ),
(165, 'Zumo de melocoton', 1, 33, 'Juver', 1.90, 210, '2l', '165.png' ),

(166, 'Leche semidesnatada', 1, 34, 'Central Lechera Asturiana', 0.6, 400, '1l', '166.png' ),
(167, 'Leche entera', 1, 34, 'Central Lechera Asturiana', 0.6, 340, '1l', '167.png' ),
(168, 'Leche de soja', 1, 34, 'Pascual', 1.10, 70, '1l', '168.png' ),
(169, 'Batido de chocolate', 1, 34, 'Pascual', 3.0, 200, '1.8l', '169.png' ),
(170, 'Batido de fresa', 1, 34, 'Pascual', 2.90, 110, '1.8l', '170.png' ),

(171, 'Coca Cola', 1, 35, 'Coca Cola', 2.60, 500, '2l', '171.png' ),
(172, 'Coca Cola Zero', 1, 35, 'Coca Cola', 2.90, 450, '2l', '172.png' ),
(173, 'Fanta', 1, 35, 'Coca Cola', 2.0, 300, '4l', '173.png' ),
(174, 'Nestea', 1, 35, 'Nestle', 1.20, 200, '1.5l', '174.png' ),
(175, 'Sprite', 1, 35, 'Pascual', 1.10, 110, '2.3l', '175.png' ),

(176, 'Heineken', 1, 36, 'Heineken', 6.0, 500, '4l', '176.png' ),
(177, 'Mahou Clasica', 1, 36, 'Mahou', 14.0, 450, '8l', '177.png' ),
(178, 'Estrella del sur', 1, 36, 'Estrella del sur', 0.6, 300, '0.3l', '178.png' ),
(179, 'Cruzcampo', 1, 36, 'Cruzcampo', 9.0, 200, '6l', '179.png' ),
(180, 'San Miguel', 1, 36, 'San Miguel', 0.5, 110, '0.6l', '180.png' ),

(181, 'Vino blanco', 1, 37, 'Don Simon', 1.15, 180, '1l', '181.png' ),
(182, 'Vino tinto', 1, 37, 'Don Simon', 1.15, 140, '1l', '182.png' ),
(183, 'Tinto de verano', 1, 37, 'Don Simon', 1.35, 120, '1.5l', '183.png' ),
(184, 'Vino Rioja Crianza tinto', 1, 37, 'Azpilicueta', 7.0, 80, '0.7l', '184.png' ),
(185, 'Ribera del Duero tinto', 1, 37, 'Condado de Iznar', 3.5, 110, '0.7l', '185.png' ),

(186, 'Cava', 1, 38, 'Jaume Serra', 2.5, 170, '0.7l', '186.png' ),
(187, 'Cava reserva', 1, 38, 'Juve Camps', 14.5, 50, '1l', '187.png' ),
(188, 'Cava', 1, 38, 'Gran Cremant', 3.6, 120, '0.7l', '188.png' ),
(189, 'Cava', 1, 38, 'Codorniu', 4.0, 80, '0.6l', '189.png' ),
(190, 'Cava ecologico', 1, 38, 'Pinord', 4.5, 90, '0.7l', '190.png' ),

(191, 'Crema de orujo', 1, 39, 'Ruavieja', 10.5, 170, '0.7l', '191.png' ),
(192, 'Licor de hierbas', 1, 39, 'Ruavieja', 8.5, 50, '0.7l', '192.png' ),
(193, 'Licor de manzana', 1, 39, 'Bowling', 3.2, 120, '0.7l', '193.png' ),
(194, 'Baileys original', 1, 39, 'Baileys', 10.0, 80, '0.6l', '194.png' ),
(195, 'Licor', 1, 39, 'Jagermeister', 11.5, 90, '0.7l', '195.png' ) ,

(196, 'Jab�n L�quido', 1, 40, 'Palmolive', 2.50, 200, '300g', '196.jpg' ),
(197, 'Desodorante', 1, 40, 'Axe', 3.00, 100, '150g', '197.jpg' ),
(198, 'Esponja', 1, 40, 'Suavinex', 0.90, 50, '100g', '198.jpg' ),
(199, 'Talco', 1, 40, 'Johnson & Johnson', 1.75, 150, '400g', '199.jpg' ),
(200, 'Bastoncillos', 1, 40, 'Acofar', 1.00, 50, '100g', '200.jpg' ),

(201, 'Champ� Original Remedies "Tesoros de Miel"', 1, 41, 'Garnier', 2.20, 200, '0,5L', '201.jpg' ),
(202, 'Champ� Original Remedies "Oliva M�tica"', 1, 41, 'Garnier', 2.00, 100, '0,5L', '202.jpg' ),
(203, 'Champ� Original Remedies "Delicatesse de avena"', 1, 41, 'Garnier', 1.00, 100, '0,5L', '203.jpg' ),
(204, 'Frizz Miracle Acondicionador', 1, 41, 'Aussie', 5.00, 150, '0,25L', '204.jpg' ),
(205, 'Color Miracle Acondicionador', 1, 41, 'Aussie', 4.00, 100, '0,25L', '205.jpg' ),

(206, 'Volumen Flash Scandaleyes', 1, 42, 'Rimmel London', 2.00, 50, '13g', '206.jpg' ),
(207, 'Pro Longwear Lipcolour', 1, 42, 'Mac', 3.45, 100, '30g', '207.jpg' ),
(208, 'Accord Parfait Highlight', 1, 42, 'Loreal', 3.90, 50, '10g', '208.jpg' ),
(209, 'Age Rewind', 1, 42, 'Maybelline', 3.00, 150, '40g', '209.jpg' ),
(210, 'Mascarilla Facial', 1, 42, 'Fruit Fusion', 0.70, 50, '30g', '210.jpg' ),

(211, 'Cepillos X-Action', 1, 43, 'Bont�', 1.00, 30, '10g', '211.jpg' ),
(212, 'Triple Action', 1, 43, 'Colgate',1.50, 50, '75g', '212.jpg' ),
(213, 'Hilo Dental Menta', 1, 43, 'Oral B', 1.45, 50, '35g', '213.jpg' ),
(214, 'Cepillos Interdentales Regular', 1, 43, 'GUM', 2.15, 50, '10g', '214.jpg' ),
(215, 'KIT Port�til', 1, 43, 'Colgate', 3.00, 50, '10g', '215.jpg'),

(216, 'Compresas Normal con Alas', 1, 44, 'Evax', 2.70, 200, '12u', '216.jpg' ),
(217, 'Salvaslips', 1, 44, 'Carefree', 2.00, 70, '20u', '217.jpg' ),
(218, 'Toallitas �ntimas', 1, 44, 'Chilly', 3.20, 50, '15u', '218.jpg' ),
(219, 'Tampones Compak Pearl', 1, 44, 'Tampax', 3.00, 150, '18u', '219.jpg' ),
(220, 'Compresas Liberty', 1, 44, 'Evax', 2.75, 100, '12u', '220.jpg' ),

(221, 'Perfume Hombre', 1, 45, 'Hugo Boss', 50.00, 60, '0,1L', '221.jpg' ),
(222, 'Perfume Mujer Natural', 1, 45, 'Lacoste', 45.00, 50, '0,1L', '222.jpg' ),
(223, 'Perfume Hombre "1 MILLION"', 1, 45, 'Paco Rabanne', 60.00, 50, '0,2L', '223.jpg' ),
(224, 'Colonia "Acqua di Gioia"', 1, 45, 'Giorgio Armani', 89.00, 50, '0,1L', '224.jpg' ),
(225, 'Eau de Toilette Code', 1, 45, 'Armani', 89.00, 100, '0,2L', '225.jpg' ),

(226, 'Maquinilla de afeitar Fusion Proglide', 1, 46, 'Gillette', 3.50, 100, '150g', '226.jpg' ),
(227, 'Espuma de afeitar', 1, 46, 'Nivea Men', 3.00, 100, '0,2L', '227.jpg' ),
(228, 'Gel After Shave', 1, 46, 'Deliplus', 1.00, 50, '0,2L', '228.jpg' ),
(229, 'Maquinillas desechables', 1, 46, 'Gillette', 1.00, 150, '100g', '229.jpg' ),
(230, 'Crema de afeitar', 1, 46, 'Lea', 1.45, 50, '0,2L', '230.jpg' ),

(231, 'Toallitas para beb�s', 0, 47, 'Natracare', 3.00, 100, '50u', '231.jpg' ),
(232, 'Baby Shampoo Camomila', 0, 47, 'Johnson & Johnson', 2.39, 100, '0,5L', '232.jpg' ),
(233, 'Agua de colonia', 0, 47, 'Nenuco', 4.40, 60, '0,3L', '233.jpg' ),
(234, 'Crema protectora de pa�al', 0, 47, 'Johnson & Johnson', 3.89, 100, '0,1L', '234.jpg' ),
(235, 'Activity Pa�ales', 0, 47, 'Dodot', 1.00, 50, '70u', '235.jpg' ),

(236, 'Jalea Real', 0, 48, 'Arkopharma', 10.44, 50, '20u', '236.jpg' ),
(237, 'Supradyn activo', 0, 48, 'Bayern', 9.00, 50, '30u', '237.jpg' ),
(238, 'Alcachofa', 0, 48, 'Viv�sima', 9.75, 50, '50u', '238.jpg' ),
(239, 'Multivitam�nico y multimineral para Mujer', 0, 48, 'Multicentrum', 15.95, 80, '150g', '239.jpg' ),
(240, 'Aquilea Artinova Complex', 0, 48, 'Aquilea', 18.00, 80, '150g', '240.jpg' ),

(241, 'Aceite de Arg�n', 1, 49, 'Lanthome', 7.50, 100, '30g', '241.jpg' ),
(242, 'Caf� Verde Descafeinado', 1, 49, 'Vitale', 7.00, 100, '50g', '242.jpg' ),
(243, 'Aloe Vera Gel', 1, 49, 'Aloe Pura', 5.90, 50, '0,3L', '243.jpg' ),
(244, 'Semillas de Ch�a', 1, 49, 'Soria Natural', 6.75, 150, '250g', '244.jpg' ),
(245, 'S�samo Natural', 1, 49, 'El Viejo Molino', 9.00, 100, '250g', '245.jpg' ),

(246, 'Limpiador de vidrios', 1, 50, 'Zep', 2.50, 200, '1L', '246.jpg' ),
(247, 'Quitagrasas', 1, 50, 'Kh-7', 3.00, 100, '1L', '247.jpg' ),
(248, 'Crema Vitro', 1, 50, 'Kh-7', 4.90, 100, '1L', '248.jpg' ),
(249, 'Lej�a gel', 1, 50, 'WC Net', 2.75, 150, '0,5L', '249.jpg' ),
(250, 'Limpiador muebles', 1, 50, 'Pledge', 3.40, 100, '0,2L', '250.jpg' ),

(251, 'Fairy Ultra', 1, 51, 'P&G', 2.80, 200, '1L', '251.jpg' ),
(252, 'Gel lavavajillas m�quina', 1, 51, 'Aliada', 3.89, 100, '0,8L', '252.jpg' ),
(253, 'Limpia m�quinas', 1, 51, 'Somat', 3.90, 100, '20g', '253.jpg' ),
(254, 'Fairy Platinum m�quina', 1, 51, 'P&G', 4.75, 100, '40g', '254.jpg' ),
(255, 'Abrillantador para m�quinas lavavajillas', 1, 51, 'Mical Profesional', 5.00, 90, '4L', '255.jpg' ),

(256, 'Quitamanchas l�quido', 1, 52, 'Vanish', 2.50, 200, '1L', '256.jpg' ),
(257, 'Detergente en polvo Fresh Sensations', 1, 52, 'Ariel', 3.00, 100, '4Kg', '257.jpg' ),
(258, 'Suavizante', 1, 52, 'Flor', 3.25, 50, '2L', '258.jpg' ),
(259, 'Micolor detergente', 1, 52, 'Henkel', 5.49, 150, '1,5L', '259.jpg' ),
(260, 'Dixan detergente', 1, 52, 'Henkel', 5.75, 100, '1L', '260.jpg' ),

(261, 'Papel Higi�nico Rindemax', 1, 53, 'Scott', 3.50, 200, '4u', '261.jpg' ),
(262, 'Papel cocina megarollo', 1, 53, 'Scottex', 3.00, 100, '2u', '262.jpg' ),
(263, 'Pa�uelos Balsam', 1, 53, 'Kleenex', 1.90, 50, '12u', '263.jpg' ),
(264, 'Paquete de folios', 1, 53, 'Navigator', 11.00, 150, '500u', '264.jpg' ),
(265, 'Baking cups', 1, 53, 'Tamcha', 1.00, 50, '34u', '265.jpg' ),

(266, 'Bolsas de basura', 0, 54, 'Albal', 2.95, 100, '10u', '266.jpg' ),
(267, 'Bolsas de basura peque�as ', 0, 54, 'Aliada', 1.50, 100, '40u', '267.jpg' ),
(268, 'Bolsas para c�sped y ramas', 0, 54, 'Albal', 17.00, 90, '8u', '268.jpg' ),
(269, 'Bolsas para almacenamiento comprimidas al vac�o', 0, 54, 'Dibag', 14.00, 100, '6u', '269.jpg' ),
(270, 'Bolsa porta alimentos', 0, 54, 'Valuart', 15.00, 50, '40g', '270.jpg' ),

(271, 'Ambientador el�ctrico White Blanquet recambio', 1, 55, 'Air Wick', 4.40, 50, '1u', '271.jpg' ),
(272, 'Ambientador spray Lavander Vanilla & Comfort', 1, 55, 'Ambi Pur', 3.00, 50, '275g', '272.jpg' ),
(273, 'Gel aromatizante Glade Jazm�n', 1, 55, 'Johnson', 1.90, 50, '70g', '273.jpg' ),
(274, 'Perfumador coche', 1, 55, 'Don Algod�n', 2.99, 90, '1u', '274.jpg' ),
(275, 'Vela c�nica Canela en rama', 1, 55, 'Cristalinas', 3.79, 50, '1u', '275.jpg' ),

(326, 'Tarta San Marcos congelada', 1, 66, 'Frigo', 7.5, 100, '600g', '326.png' ),
(327, 'Cono de chocolate', 1, 66, 'Frigo', 4.5, 50, '400g', '327.png' ),
(328, 'Cono de vainilla', 1, 66, 'Frigo', 4.2, 70, '400g', '328.png' ),
(329, 'Pop Tarts de fresa', 1, 66, 'Kellogs', 8.40, 40, '400g', '329.png' ),
(330, 'Contesa', 1, 66, 'San Martin', 9.25, 90, '500g', '330.png' ) ,

(331, 'Medallon de merluza', 1, 67, 'Pescanova', 8.5, 40, '500g', '331.png' ),
(332, 'Calamares a la romana', 1, 67, 'Pescanova', 3.5, 10, '300g', '332.png' ),
(333, 'Palitos de cangrejo', 1, 67, 'Findus', 4.5, 40, '200g', '333.png' ),
(334, 'Pulpo cocido troceado', 1, 67, 'Findus', 7.40, 80, '400g', '334.png' ),
(335, 'Gamba pelada', 1, 67, 'Findus', 7.25, 60, '400g', '335.png' ) ,

(336, 'Lasaña de carne', 1, 68, 'La cocinera', 3.5, 20, '600g', '336.png' ),
(337, 'Croquetas de jamon', 1, 68, 'La cocinera', 2.6, 35, '400g', '337.png' ),
(338, 'Empanadillas', 1, 68, 'Findus', 3.7, 40, '225g', '338.png' ),
(339, 'Revuelto de gulas y ajetes', 1, 68, 'Findus', 3.4, 70, '300g', '339.png' ),
(340, 'Canelones de atun', 1, 68, 'La cocinera', 4.25, 30, '600g', '340.png' ) ,

(341, 'Pizza Mozarella', 1, 69, 'Dr Oetker', 3.25, 200, '400g', '341.png' ),
(342, 'Pizza Campestre', 1, 69, 'Dr Oetker', 3.25, 65, '400g', '342.png' ),
(343, 'Pizza Barbacoa', 1, 69, 'Dr Oetker', 3.25, 45, '400g', '343.png' ),
(344, 'Panini Jamon y queso', 1, 69, 'Casa Tarradellas', 2.6, 70, '300g', '344.png' ),
(345, 'Panini 5 quesos', 1, 69, 'Casa Tarradellas', 2.6, 60, '300g', '345.png' ) ,
(346, 'Panini Barbacoa', 1, 69, 'Casa Tarradellas', 2.7, 85, '300g', '346.png' ),
(347, 'Picolinis 4 quesos', 1, 69, 'Buitoni', 3.1, 105, '270g', '347.png' ),
(348, 'Piccolinis Jamon y queso', 1, 69, 'Buitoni', 3.1, 120, '270g', '348.png' ),
(349, 'Pizza Jamon y queso', 1, 69, 'Ristorante', 3.25, 70, '300g', '349.png' ),
(350, 'Pizza Peperoni', 1, 69, 'Ristorante', 3.25, 30, '600g', '350.png' ) ,

(351, 'Patatas fritas golden', 1, 70, 'McCain', 2.6, 200, '1kg', '351.png' ),
(352, 'Patatas fritas gajo', 1, 70, 'McCain', 1.1, 75, '300g', '352.png' ),
(353, 'Menestra de verduras congelada', 1, 70, 'Findus', 3.25, 55, '400g', '353.png' ),
(354, 'Judias verdes congeladas', 1, 70, 'Findus', 1.05, 80, '1kg', '354.png' ),
(355, 'Alcachofas congeladas', 1, 70, 'Findus', 1.6, 30, '400g', '355.png' ) ,

(356, 'Bebida vegetal de avena', 1, 71, 'La Finestra', 2.6, 20, '1l', '356.png' ),
(357, 'Bebida de castañas ecologica', 1, 71, 'Ecomil', 2.1, 15, '1l', '357.png' ),
(358, 'Bebida de almendras ecologica', 1, 71, 'Amandin', 2.25, 35, '1l', '358.png' ),
(359, 'Bebida de arroz ecologica', 1, 71, 'Amandin', 2.65, 20, '1l', '359.png' ),
(360, 'Leche de sesamo en polvo', 1, 71, 'EcoMil', 15.0, 20, '400g', '360.png' ) ,

(361, 'Cuajada de bebida de soja', 1, 72, 'Soria Natural', 3.6, 20, '240g', '361.png' ),
(362, 'Natillas de bebida de arroz', 1, 72, 'Soria Natural', 3.6, 15, '240g', '362.png' ),
(363, 'Postre de avena a la vainilla', 1, 72, 'Soria Natural', 4.5, 35, '260g', '363.png' ),
(364, 'Postre de avena al chocolate', 1, 72, 'Soria Natural', 4.5, 20, '260g', '364.png' ),
(365, 'Leche de sesamo en polvo', 1, 72, 'Soria Natural', 5.0, 20, '260g', '365.png' ) ,

(366, 'Bonito en aceite de oliva ecologico', 1, 73, 'Emperatriz', 6.3, 20, '0.3l', '366.png' ),
(367, 'Fritada ecologica', 1, 73, 'Emperatriz', 3.4, 15, '0.4l', '367.png' ),
(368, 'Tomate en aceite de oliva ecologico', 1, 73, 'Emperatriz', 2.3, 35, '0.2l', '368.png' ),
(369, 'Bonito en aceite de oliva ecologico', 1, 73, 'Edulis', 5.6, 40, '0.3l', '369.png' ),
(370, 'Boletus en aceite de oliva ecologico', 1, 73, 'Edulis', 5.5, 30, '0.2l', '370.png' ) ,

(371, 'Pollo y verdura para perros', 1, 74, 'AS Snack', 1.8, 20, '800g', '371.png' ),
(372, 'Salchicha maxi para perros', 1, 74, 'AS Snack', 1.9, 35, '700g', '372.png' ),
(373, 'Galletas rellenas de carne para perros', 1, 74, 'Dog Selection', 2.3, 45, '1kg', '373.png' ),
(374, 'Pate con trozos de pollo para perros', 1, 74, 'Dog Selection', 2.2, 50, '1kg', '374.png' ),
(375, 'Albondigas de ave en lata para perros', 1, 74, 'Dog Selection', 2.1, 60, '800g', '375.png' ) ,

(376, 'Mix buey, ternera y verduras para gatos', 1, 75, 'Brekkies', 5.8, 25, '1.5k', '376.png' ),
(377, 'Carne para gatos', 1, 75, 'Brekkies', 4.9, 85, '1.5k', '377.png' ),
(378, 'Trozos en salsa para gatos', 1, 75, 'AS Snack', 1.3, 65, '400g', '378.png' ),
(379, 'Albondigas atun y verdura para gatos', 1, 75, 'AS Snack', 0.7, 50, '400g', '379.png' ),
(380, 'Salmon y atun para gatos', 1, 75, 'Cat Selection', 5.1, 60, '3kg', '380.png' ) ,

(381, 'Comida para peces de agua dulce', 1, 76, 'Sera Pond', 17.2, 15, '300g', '381.png' ),
(382, 'Comida para peces de agua salada', 1, 76, 'Sera Pond', 10.3, 15, '400g', '382.png' ),
(383, 'Comida para peces tropicales', 1, 76, 'Sera Pond', 12.3, 15, '400g', '383.png' ),
(384, 'Comida en copos para peces', 1, 76, 'Tetramin', 11.7, 10, '400g', '384.png' ),
(385, 'Comida de colores para peces', 1, 76, 'Tetramin', 15.1, 10, '300g', '385.png' ) ,

(386, 'Petit nesquick', 1, 77, 'Nestle', 1.45, 55, '300g', '386.png' ),
(387, 'Yogur liquido fresa', 1, 77, 'Danacol', 3.3, 75, '1l', '387.png' ),
(388, 'Yogur natural edulcorado', 1, 77, 'Vitalinea', 1.0, 95, '0.5l', '388.png' ),
(389, 'Yogur bifidus con cereales', 1, 77, 'Vitalinea', 2.5, 30, '400g', '389.png' ),
(390, 'Yogur liquido Actimel', 1, 77, 'Danone', 5.0, 70, '1l', '390.png' ) ,

(391, 'Nata para cocinar', 1, 78, 'Central Lechera Asturiana', 2.45, 45, '0.4l', '391.png' ),
(392, 'Nata para montar', 1, 78, 'Danacol', 2.0, 105, '0.3l', '392.png' ),
(393, 'Nata azucarada', 1, 78, 'Vitalinea', 2.1, 105, '0.2l', '393.png' ),
(394, 'Mantequilla', 1, 78, 'Tulipan', 1.2, 120, '250g', '394.png' ),
(395, 'Margarina', 1, 78, 'Tulipan', 1.8, 130, '500g', '395.png' ) ,

(396, 'Queso cheddar lonchas', 1, 79, 'President', 2.55, 45, '300g', '396.png' ),
(397, 'Queso mozarella en lonchas', 1, 79, 'President', 2.3, 105, '300g', '397.png' ),
(398, 'Queso emmental taco', 1, 79, 'Garcia Baquero', 2.6, 105, '300g', '398.png' ),
(399, 'Queso de cabra cortado', 1, 79, 'Garcia Baquero', 2.25, 130, '250g', '399.png' ),
(400, 'Queso azul taco', 1, 79, 'President', 2.8, 110, '400g', '400.png' ) ;
--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `mail`, `password`, `es_admin`, `nombre`, `apellido`, `direccion`, `telefono`, `f_nacimiento`, `activo`, `foto`, `n_tarjeta`, `dir_entrega`) VALUES
(1, 'admin@onmarket.es', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, 'admin', 'admin', NULL, NULL, NULL, 1, NULL, NULL, NULL),
(2, 'jagomont@onmarket.es', 'bab42becf7779a701a75b5fb9409d979dc63b5fc720c302cd107278b7ba7c886', 0, 'jagoba', 'montes', 'general diaz prolier ,45', '647859651', '1993-11-10', 1, 'jagomont@onmarket.es.jpg', '123456789', 'general diaz porlier, 45'),
(3, 'alexp@onmarket.es', '06f8aa28c4c328ccb33b1c595ab1b39d27ed9ddb2bbfc2fc51d556f56d2e699f', 0, 'Alex', 'Povedano', 'Casa de Al lado de jago,1', '123456789', '1995-10-12', 1, 'alexp@onmarket.es.jpg', '45678989', 'casa de la lado de jago,1'),
(4, 'dani@onmarket.es', 'd37c5e7961d0ed500ecf0475346027cf2d7010b7083c411f953936b0e38b1ab2', 0, 'Daniel', 'Reyes', 'casa de jago del otro lado,1', '123789456', '1820-05-01', 1, 'dani@onmarket.es.jpg', '147258369', 'casa de jago del otro lado,1'),
(5, 'david@onmarket.es', '07d046d5fac12b3f82daf5035b9aae86db5adc8275ebfbf05ec83005a4a8ba3e', 0, 'David', 'Garcia', 'casa al lado de dani,2', '147369852', '1993-05-08', 1, 'david@onmarket.es.jpg', '1237989456', 'casa de al lado de dani,2'),
(6, 'kike@ormarket.es', 'c3de533e9b7fe63b79f648687a30d2861edd92fe7c3cd1f2c485e0a605367624', 0, 'kike', 'fuertes', 'casa de al lado de alex,2', '124567894', '2017-05-02', 1, 'kike@ormarket.es.jpg', '12346578941', 'casa de al lado de alex,2'),
(7, 'gonzalo@onmarket.es', '4859d1729b697a9ccc9bf17b6a3c676bc935834e5640d203a7cfa489bd45d619', 0, 'gonzalo', 'pablos', 'casa de al lado de kike,3', '12348798', '1995-05-01', 1, 'gonzalo@onmarket.es.jpg', '1245689741', 'casa de al lado de kike,3'),
(8, 'alexpedrosa@onmarket.es', '95c413594f54b3b97049be2ed3f96910dffb9b80683a3c8be199da3f68c1ffc9\r\n', 0, 'alex', 'pedrosa', 'al lado de casa de gonzalo', '145236789', '1995-05-09', 1, 'alexpedrosa@onmarket.es.jpg', '123457896', 'al lado de casa de gonzalo'),
(9, 'alexsevilla@onmarket.es', 'c6d0dbe3980f8c024f9a8c5b06460fdd66e5bd38e795d3e720795881d7f3b450\r\n', 0, 'alex', 'sevilla', 'casa de al aldo de alex pedrosa', '147369852', '1995-05-23', 1, 'alexsevilla@onmarket.es.jpg', '124587963', 'cas de al lado de alex pedrosa'),
(10, 'alexnieto@onmarket.es', 'fb6982234c2310fd0a3c227b68794a438cec31ab2c0af376784540f971269f04\r\n', 0, 'alex', 'nieto', 'casa de al lado de alex sevilla', '145896327', '1995-05-14', 1, 'alexsevilla@onmarket.es.jpg', '147963852', 'casa de al lado de alex sevilla'),
(11, 'miriam@onmarket.es', '263df4565e2ff1a307063e087e68d88190ad01ae3dbb164aa1e8ec6781b9fd26\r\n', 0, 'miriam', 'cabana', 'casa de al lado de alex nieto', '159753842', '1995-05-22', 1, 'miriam@onmarket.es.jpg', '148956235', 'casa de al lado de alex nieto'),
(12, 'valentin@onmarket.es', 'bfb301b26ca5590c4cd741bea37c36d5b4e5fb92dc4880e8c89448bf82b2b94c\r\n', 0, 'valentin', 'campillo', 'casa de al lado de miriam', '159758643', '1995-05-09', 1, 'valentin@onmarket.es.jpg', '124578963', 'casa de al lado de miriam'),
(29, 'chema@onmarket.es', '66d8bc4d042a5819628ac52e85612eb33676856295ba31315e27a0f8c0fbce5e', 0, 'chema', 'alonso', 'casa de al lado de valentin', '147854997', '1960-05-14', 1, 'chema@telefonica.es', '1486321454', 'casa de al lado de valentin'),
(30, 'alvaro@hotmail.es', 'ee7d81103f122bb171ce1eb2b8da9b44403f2b2da7924b48b3fafe0ba36b5a81\r\n', 0, 'alvaro', 'magdalena', 'casa de al lado de chema', '154874312', '1987-05-01', 1, 'alvaro@hotmail.es', '15468745132', 'casa de al lado de chema'),
(31, 'marta@hotmail.es', 'e420eb81d1179e1d06fa9d50691b06a53b8ed79035f4d6c1d3028e9fc4e04487\r\n', 0, 'marta', 'sanchez', 'casa de al lado de alvaro', '164562524', '1997-05-30', 1, 'marta@hotmail.es', '1248645230', 'casa de al lado de alvaro'),
(32, 'lucia@gmail.com', '6326e0e8cfdaab9af83026a0620bafd05179e3a0cd1b812222682d86285b30cc\r\n', 0, 'lucia', 'fernandez', 'casa de al lado de marta', '147415741', '1950-05-07', 1, 'lucia@gmail.com', '126485211', 'casa de al lado de marta'),
(33, 'elena@hotmail.es', '0ce93c9606f0685bf60e051265891d256381f639d05c0aec67c84eec49d33cc1\r\n', 0, 'elena', 'sentada', 'casa de al lado de lucia', '154674144', '1987-05-08', 1, 'elena@hotmail.es', '1541278784', 'casa de la lado de lucia'),
(34, 'martin@ucm.es', 'b6f8d434a847fb0f0c1a8d9b936b8ca952e224f205a55f4ba9b2c20f88fdc9e7\r\n', 0, 'martin', 'montoya', 'casa de la lado de elena', '444445685', '1990-05-15', 1, 'martin@ucm.es', '1559747478', 'cas de al lado de elena'),
(35, 'alejandro@ucm.es', 'a9010fd21a93c687b3c4c506313993b5a2ade87b719d09792b120a27b852f749\r\n', 0, 'alejandro', 'puentes', 'casa de al lado de martin', '589746521', '0001-05-01', 1, 'alejandro@ucm.es', '154788452', 'casa de la lado de martin');





-- Volcado de datos para la tabla `pedidos`

--

INSERT INTO `pedidos` (`id`, `usuario`, `estado`, `dir_entrega`, `fecha_entrega`, `hora_entrega_ini`, `hora_entrega_fin`, `telefono`, `total`, `activo`) VALUES
(1, 2, 'Enviado', 'Calle Puerto de los Leones 2', '2017-06-22', 13, 18, '674239547', 11.6, 1),
(2, 2, 'En Curso', 'Avenida de Guadarrama 9', '2017-06-23', 10, 12, '680591218', 18.8, 1),
(3, 2, 'Enviado', 'Avenida Reyes Católicos 5', '2017-06-23', 19, 21, '675541148', 45, 1),
(4, 3, 'Cancelado', 'Calle As de Bastos 14', '2017-06-24', 14, 16, '692542211', 6.2, 0),
(5, 3, 'Entregado', 'Calle Charaima 22', '2017-06-25', 15, 19, '630794248', 46.1, 0),
(6, 3, 'En Curso', 'Calle Nuevos Robles 13', '2017-06-25', 17, 21, '640521117', 11.9, 1),
(7, 4, 'En Curso', 'Calle Las Huertas 54', '2017-06-26', 10, 20, '620691179', 22.5, 1),
(8, 5, 'Enviado', 'Avenida Cáceres 9', '2017-06-26', 13, 19, '630797278', 27, 1),
(9, 5, 'Enviado', 'Calle Doctor Calero 3', '2017-06-27', 11, 21, '680573278', 20.4, 1),
(10, 5, 'En Curso', 'Avenida de Guadarrama 12', '2017-06-27', 20, 21, '640792268', 33, 1),
(11, 6, '1', 'calle de la prueba 2, escalera 1, 1ºD', '2017-05-10', 10, 17, '676318957', 2.5, 1),
(12, 6, '1', 'calle de la prueba 2, escalera 1, 1ºD', '2017-05-12', 10, 17, '676318957', 3.4, 1),
(13, 7, '1', 'calle de la prueba 2, escalera 1, 1ºD', '2017-05-20', 10, 13, '676318957', 4.5, 1),
(14, 7, '1', 'calle de la prueba 2, escalera 1, 1ºD ', '2017-06-01', 9, 13, '676318957', 6, 1),
(15, 7, '1', 'calle de la prueba ', '2017-06-03', 9, 13, '676318957', 12, 1),
(16, 8, '1', 'calle de la prueba2, bajo A ', '2017-06-15', 15, 19, '676318957', 10, 1),
(17, 9, '1', 'calle de la prueba2, bajo A ', '2017-06-15', 13, 17, '676318957', 24, 1),
(18, 10, '1', 'calle de la prueba2, bajo A ', '2017-06-21', 8, 13, '676318957', 13, 1),
(19, 10, '1', 'calle de la prueba2, bajo A ', '2017-06-25', 10, 13, '676318957', 11.7, 1),
(20, 10, '1', 'calle de la prueba2, bajo A ', '2017-07-10', 9, 10, '676318957', 3.6000000000000005, 1);

-- Volcado de datos para la tabla `lineas_pedido`

--

INSERT INTO `lineas_pedido` (`id`, `pedido`, `producto`, `cantidad`, `precio`) VALUES
(1, 1, 1, 1, 2.5),
(2, 1, 3, 3, 2.7),
(3, 1, 5, 2, 2.4),
(4, 1, 7, 4, 4),
(5, 5, 9, 1, 8),
(6, 5, 11, 2, 9),
(7, 5, 13, 2, 15),
(8, 5, 15, 2, 11),
(9, 5, 17, 3, 10.5),
(10, 5, 19, 1, 0.6),
(11, 2, 2, 1, 0.7),
(12, 2, 2, 3, 2.0999999999999996),
(13, 2, 6, 2, 2),
(14, 2, 8, 4, 4),
(15, 2, 10, 1, 1),
(16, 2, 12, 2, 9),
(17, 4, 14, 2, 2),
(18, 4, 16, 2, 1.2),
(19, 4, 18, 3, 2.4000000000000004),
(20, 4, 20, 1, 0.6),
(21, 3, 3, 1, 0.9),
(22, 3, 6, 3, 3),
(23, 3, 9, 2, 16),
(24, 3, 12, 4, 18),
(25, 3, 15, 1, 5.5),
(26, 3, 18, 2, 1.6),
(27, 6, 2, 2, 1.4),
(28, 6, 5, 2, 2.4),
(29, 6, 8, 3, 3),
(30, 6, 12, 1, 4.5),
(31, 6, 16, 1, 0.6),
(32, 7, 20, 3, 1.7999999999999998),
(33, 7, 4, 2, 2),
(34, 7, 8, 4, 4),
(35, 7, 12, 1, 4.5),
(36, 7, 16, 2, 1.2),
(37, 7, 1, 2, 5),
(38, 7, 17, 2, 7),
(39, 8, 13, 3, 22.5),
(40, 8, 19, 1, 0.6),
(41, 8, 5, 1, 1.2),
(42, 8, 3, 3, 2.7),
(43, 9, 1, 2, 5),
(44, 9, 7, 4, 4),
(45, 9, 9, 1, 8),
(46, 9, 18, 2, 1.6),
(47, 9, 3, 2, 1.8),
(48, 10, 5, 2, 2.4),
(49, 10, 7, 3, 3),
(50, 10, 19, 1, 0.6),
(51, 10, 12, 1, 4.5),
(52, 10, 13, 3, 22.5),
(53, 11, 1, 1, 2.5),
(54, 12, 3, 3, 2.7),
(55, 13, 5, 2, 2.4),
(56, 14, 7, 4, 4),
(57, 15, 9, 1, 8),
(58, 16, 11, 2, 9),
(59, 17, 13, 2, 15),
(60, 18, 15, 2, 11),
(61, 19, 17, 3, 10.5),
(62, 20, 19, 1, 0.6),
(63, 12, 2, 1, 0.7),
(64, 13, 2, 3, 2.0999999999999996),
(65, 14, 6, 2, 2),
(66, 15, 8, 4, 4),
(67, 16, 10, 1, 1),
(68, 17, 12, 2, 9),
(69, 18, 14, 2, 2),
(70, 19, 16, 2, 1.2),
(71, 20, 18, 3, 2.4000000000000004),
(72, 12, 20, 1, 0.6);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
