-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 11, 2020 at 12:14 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cycleworldca2`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `colour` varchar(20) NOT NULL,
  `productName` varchar(55) NOT NULL,
  `storeId` int(11) NOT NULL,
  `type` enum('bicycle','bicycle accessory') NOT NULL,
  `gearCount` tinyint(4) DEFAULT NULL,
  `modelNo` int(50) DEFAULT NULL,
  `weight` decimal(10,0) DEFAULT NULL,
  `brand` varchar(35) DEFAULT NULL,
  `baType` varchar(35) DEFAULT NULL,
  `inStock` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `price`, `colour`, `productName`, `storeId`, `type`, `gearCount`, `modelNo`, `weight`, `brand`, `baType`, `inStock`) VALUES
(1, '20.00', 'Black', 'Ultra sports bike', 2, 'bicycle', 12, 184712, '12', 'FT210', NULL, NULL),
(2, '20.00', 'Red', 'Start', 1, 'bicycle', 10, 7247623, '10', 'GFast', NULL, NULL),
(3, '200.00', 'White', 'Mountain line B2', 1, 'bicycle', 8, 2023, '10', 'JFit', NULL, NULL),
(4, '320.00', 'Blue', 'TrakKing', 1, 'bicycle', 6, 24981, '10', 'JFit', NULL, NULL),
(5, '10.00', 'Black', 'Helmet Flashlight', 1, 'bicycle accessory', NULL, NULL, NULL, NULL, 'Flashlight', 1),
(6, '25.00', 'Black', 'Thermal Top', 1, 'bicycle accessory', NULL, NULL, NULL, NULL, 'Clothing', 0),
(7, '30.00', 'Bright yellow', 'Hi-Vis Vest', 1, 'bicycle accessory', NULL, NULL, NULL, NULL, 'Clothing', 1),
(8, '20.00', 'Black', 'Tire pump', 2, 'bicycle accessory', NULL, NULL, NULL, NULL, 'Utility', 1),
(9, '10.00', 'White', 'Bell', 3, 'bicycle accessory', NULL, NULL, NULL, NULL, 'Safety', 1),
(10, '230.00', 'Blue', 'Racing 23', 3, 'bicycle', 8, 23845, '12', 'Focus', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `name` varchar(55) NOT NULL,
  `storeAddress` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `store`
--

INSERT INTO `store` (`id`, `name`, `storeAddress`) VALUES
(1, 'Mike\'s Store', 'Dublin 21, Rainbow Road'),
(2, 'Sam\'s bike shop', 'Louth, Castle hill'),
(3, 'Think Bikes', 'Cork, Main St 2814'),
(4, 'Total fitness', 'Dublin 15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `storeId` (`storeId`);

--
-- Indexes for table `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `store`
--
ALTER TABLE `store`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`storeId`) REFERENCES `store` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
