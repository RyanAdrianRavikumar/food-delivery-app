-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 25, 2025 at 05:17 PM
-- Server version: 8.4.4
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `orders_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `restaurant_id` int NOT NULL,
  `driver_id` int DEFAULT NULL,
  `discount_id` int DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `restaurant_id`, `driver_id`, `discount_id`, `total_amount`) VALUES
(2, 1, 1, NULL, NULL, 0),
(3, 1, 1, NULL, NULL, 0),
(4, 1, 1, NULL, NULL, 0),
(5, 1, 1, NULL, NULL, 0),
(6, 1, 1, NULL, NULL, 0),
(7, 1, 1, NULL, NULL, 0),
(8, 1, 1, NULL, NULL, 0),
(9, 1, 1, NULL, NULL, 0),
(10, 1, 1, NULL, NULL, 0),
(11, 1, 1, NULL, NULL, 0),
(12, 1, 1, NULL, NULL, 0),
(13, 1, 1, NULL, NULL, 0),
(14, 1, 1, NULL, NULL, 0),
(15, 1, 4, NULL, NULL, 0),
(16, 1, 1, NULL, NULL, 0),
(17, 15, 1, NULL, NULL, 0),
(18, 15, 1, NULL, NULL, 0),
(19, 1, 1, NULL, NULL, 0),
(20, 1, 1, NULL, NULL, 0),
(21, 1, 1, NULL, NULL, 0),
(22, 1, 2, NULL, NULL, 0),
(23, 1, 1, NULL, NULL, 0),
(24, 1, 1, NULL, NULL, 12.99),
(25, 1, 1, NULL, NULL, 43.97),
(26, 1, 1, NULL, NULL, 21),
(27, 1, 1, NULL, NULL, 21),
(28, 1, 1, NULL, NULL, 21),
(29, 1, 1, NULL, NULL, 30.98),
(30, 1, 1, NULL, NULL, 15.99),
(31, 1, 1, NULL, NULL, 15.99),
(32, 1, 1, NULL, NULL, 51.980000000000004),
(33, 1, 1, NULL, NULL, 35.99),
(34, 16, 2, NULL, NULL, 21.48),
(35, 16, 2, NULL, NULL, 8.99),
(36, 16, 2, NULL, NULL, 17.98),
(37, 16, 2, NULL, NULL, 54.45),
(38, 1, 5, NULL, NULL, 24.98),
(39, 1, 5, NULL, NULL, 24.98),
(40, 1, 5, NULL, NULL, 56.46),
(41, 1, 5, NULL, NULL, 167.87),
(42, 1, 1, NULL, NULL, 114.94),
(43, 1, 3, NULL, NULL, 18.98),
(44, 1, 3, NULL, NULL, 67.44),
(45, 1, 3, NULL, NULL, 10.99),
(46, 16, 3, NULL, NULL, 64.44),
(47, 16, 3, NULL, 1, 9.49),
(48, 16, 3, NULL, 1, 8.541),
(49, 1, 3, NULL, 1, 8.541),
(50, 1, 3, NULL, 1, 28.323),
(51, 1, 3, NULL, 1, 17.082),
(52, 1, 2, NULL, 1, 10.341000000000001),
(53, 1, 2, NULL, 1, 16.182000000000002),
(54, 1, 1, NULL, 2, 17.745),
(55, 1, 3, NULL, 5, 21.23),
(56, 1, 3, NULL, 5, 4.745),
(57, 1, 1, NULL, 5, 10.5),
(58, 1, 1, NULL, 5, 31.5),
(59, 1, 1, NULL, 5, 10.5);

-- --------------------------------------------------------

--
-- Table structure for table `order_menu_items`
--

DROP TABLE IF EXISTS `order_menu_items`;
CREATE TABLE IF NOT EXISTS `order_menu_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `menu_item_id` int NOT NULL,
  `price` double DEFAULT NULL,
  `qty` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `order_menu_items`
--

INSERT INTO `order_menu_items` (`id`, `order_id`, `menu_item_id`, `price`, `qty`) VALUES
(1, 2, 1, 0, NULL),
(2, 2, 2, 0, NULL),
(3, 3, 1, 0, NULL),
(4, 3, 2, 0, NULL),
(5, 4, 1, 0, NULL),
(6, 4, 2, 0, NULL),
(7, 5, 1, 0, NULL),
(8, 5, 2, 0, NULL),
(9, 6, 1, 0, NULL),
(10, 6, 2, 0, NULL),
(11, 7, 1, 0, NULL),
(12, 7, 2, 0, NULL),
(13, 8, 1, 0, NULL),
(14, 8, 2, 0, NULL),
(15, 9, 1, 0, NULL),
(16, 9, 2, 0, NULL),
(17, 10, 1, 0, NULL),
(18, 10, 2, 0, NULL),
(19, 11, 1, 0, NULL),
(20, 11, 2, 0, NULL),
(21, 12, 1, 0, NULL),
(22, 13, 1, 0, NULL),
(23, 14, 1, 0, NULL),
(24, 15, 10, 0, NULL),
(25, 16, 1, 0, NULL),
(26, 17, 2, 0, NULL),
(27, 18, 2, 0, NULL),
(28, 19, 1, 0, NULL),
(29, 19, 2, 0, NULL),
(30, 19, 3, 0, NULL),
(31, 20, 1, 0, NULL),
(32, 20, 2, 0, NULL),
(33, 21, 1, 0, NULL),
(34, 21, 2, 0, NULL),
(35, 22, 4, 0, NULL),
(36, 23, 1, 0, NULL),
(37, 24, 1, 0, NULL),
(38, 25, 1, 0, NULL),
(39, 25, 2, 0, NULL),
(40, 25, 3, 0, NULL),
(41, 26, 1, NULL, NULL),
(42, 27, 1, NULL, NULL),
(43, 28, 1, NULL, NULL),
(44, 29, 3, NULL, NULL),
(45, 29, 2, NULL, NULL),
(46, 30, 3, 0, NULL),
(47, 31, 3, 0, NULL),
(48, 32, 2, 0, NULL),
(49, 32, 1, 0, NULL),
(50, 32, 3, 0, NULL),
(51, 33, 2, 0, NULL),
(52, 33, 1, 0, NULL),
(53, 34, 5, 0, NULL),
(54, 34, 6, 0, NULL),
(55, 35, 4, 0, NULL),
(56, 36, 4, 0, 2),
(57, 37, 5, 0, 2),
(58, 37, 6, 0, 3),
(59, 38, 14, 0, 2),
(60, 39, 14, 12.49, 2),
(61, 40, 13, 13.99, 1),
(62, 40, 14, 12.49, 1),
(63, 40, 15, 14.99, 2),
(64, 41, 13, 13.99, 2),
(65, 41, 14, 12.49, 10),
(66, 41, 15, 14.99, 1),
(67, 42, 1, 21, 1),
(68, 42, 2, 14.99, 2),
(69, 42, 3, 15.99, 4),
(70, 43, 9, 9.49, 2),
(71, 44, 7, 10.99, 3),
(72, 44, 8, 12.49, 2),
(73, 44, 9, 9.49, 1),
(74, 45, 7, 10.99, 1),
(75, 46, 7, 10.99, 1),
(76, 46, 8, 12.49, 2),
(77, 46, 9, 9.49, 3),
(78, 47, 9, 9.49, 1),
(79, 48, 9, 9.49, 1),
(80, 49, 9, 9.49, 1),
(81, 50, 8, 12.49, 1),
(82, 50, 9, 9.49, 2),
(83, 51, 9, 9.49, 2),
(84, 52, 6, 11.49, 1),
(85, 53, 4, 8.99, 2),
(86, 54, 1, 21, 1),
(87, 55, 7, 10.99, 1),
(88, 55, 8, 12.49, 1),
(89, 55, 9, 9.49, 2),
(90, 56, 9, 9.49, 1),
(91, 57, 1, 21, 1),
(92, 58, 1, 21, 3),
(93, 59, 1, 21, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_menu_items`
--
ALTER TABLE `order_menu_items`
  ADD CONSTRAINT `order_menu_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
