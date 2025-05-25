-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 25, 2025 at 05:16 PM
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
-- Database: `users_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `version` int DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `full_name`, `email`, `password`, `phone`, `address`, `version`) VALUES
(1, 'John Doe', 'ppryank4123@gmail.com', 'hashed_password_1', '1234567890', '123 Main St, Cityville, CA 12345', 0),
(2, 'Jane Smith', 'janesmith@example.com', 'hashed_password_2', '9876543210', '456 Elm St, Townsville, TX 67890', 0),
(3, 'Alice Johnson', 'alicej@example.com', 'hashed_password_3', '5551234567', '789 Oak St, Villagetown, FL 11223', 0),
(4, 'Bob Brown', 'bobbrown@example.com', 'hashed_password_4', '5557654321', '101 Pine St, Hamlet, NY 44556', 0),
(5, 'Charlie White', 'charliewhite@example.com', 'hashed_password_5', '5559876543', '202 Maple St, Metropolis, IL 77889', 0),
(7, 'Johnny', 'johnny@example.com', 'password123', '1234567893', '1233 Main St', 0),
(8, 'johonson', 'johndo2e@example.com', 'hashed_password_1', '5559876548', '233 Main St', 0),
(9, 'Ryan Adrian Ravikumar', 'ryan@gmail.com', '123', '0761885576', '316/5 Dippitigoda Road, Wattala', 0),
(10, 'Tom David ', 'tom@gmail.com', '123', '01234567878', '10/2 Kings road', 0),
(11, 'M Saleem', 'saleem@gmail.com', '123', '0761885578', '316/7 Dippitigoda Road, Wattala', 0),
(12, 'Semal F', 'semal@gmail.com', '123', '0987654321', 'pana', 0),
(14, 'Semal Fen', 'semal123@gmail.com', '123', '0987654399', 'panadura', 0),
(15, 'Umar ', 'mufareed2002@gmail.com', 'nigga123', '0112345675', 'jungle', 0),
(16, 'Abdur Rahman Fareed', 'arfareed2004@gmail.com', '123', '0704407270', '835/1 Kanthi Mawatha Hunupitiya, Wattala', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
