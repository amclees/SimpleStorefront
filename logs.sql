-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 03, 2016 at 01:41 PM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cs3220stu22`
--

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE IF NOT EXISTS `logs` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `text` varchar(12000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`id`, `text`) VALUES
(13, 'Order by Round Test\nOrder Total: $64448.53\n23 of item MacBook Pro\n24 of item Baseball\n'),
(14, 'Order by George Smith\nOrder Total: $16304.95\n2 of item Mouse\n1 of item MacBook Pro\n10 of item Gold Ingot\n'),
(15, 'Order by Foo Bar with email foo@example.com\nOrder Total: $6848.99\n1 of item MacBook Pro\n3 of item Gold Ingot\n'),
(16, 'Order by Mac User with email macuser@apple.app\nOrder Total: $2798.99\n1 of item MacBook Pro each priced at $2798.99 with a total item cost of $2798.99\n'),
(17, 'Order by Snake Owner with email snake@reptiles.zoo\nOrder Total: $17918.49\n3 of item MacBook Pro each priced at $2798.99 with a total item cost of $8396.97\n7 of item Gold Ingot each priced at $1350 with a total item cost of $9450\n24 of item Mouse each priced at $2.98 with a total item cost of $71.52\n'),
(18, 'Order by John Smith with email john@smith.com\nOrder Total: $5612.88\n2 of item MacBook Pro each priced at $2798.99 with a total item cost of $5597.98\n5 of item Mouse each priced at $2.98 with a total item cost of $14.9\n'),
(19, 'Order by John Smith with email john@smith.com\nOrder Total: $11170.82\n2 of item MacBook Pro each priced at $2798.99 with a total item cost of $5597.98\n4 of item Gold Ingot each priced at $1350 with a total item cost of $5400\n58 of item Mouse each priced at $2.98 with a total item cost of $172.84\n'),
(20, 'Order by Kevin Tong with email REDACTED@calstatela.edu\nOrder Total: $2801.97\n1 of item Mouse each priced at $2.98 with a total item cost of $2.98\n1 of item MacBook Pro each priced at $2798.99 with a total item cost of $2798.99\n');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
