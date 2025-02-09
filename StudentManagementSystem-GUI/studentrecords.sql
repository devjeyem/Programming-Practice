-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2024 at 03:58 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentrecords`
--

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `StudentID` varchar(10) NOT NULL,
  `Surname` varchar(50) NOT NULL,
  `Firstname` varchar(50) NOT NULL,
  `Birthdate` date NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`StudentID`, `Surname`, `Firstname`, `Birthdate`, `Gender`, `Address`) VALUES
('21-1-00111', 'Rivera', 'Marian', '2004-03-19', 'Female', 'Manila,City'),
('22-1-00099', 'Richards', 'Alden', '2000-01-10', 'Male', 'Pasig,City'),
('23-1-00069', 'Jimeno', 'Ken Cedrick', '2004-03-15', 'Male', 'Hilongos, Leyte'),
('23-1-00073', 'Pintin', 'Jm', '2004-03-09', 'Male', 'Hindang,Leyte'),
('23-1-11111', 'Smith', 'Sofia', '2005-07-09', 'Female', 'Cebu,City'),
('24-1-00001', 'Destajo', 'Abigail', '2004-09-28', 'Female', 'Hindang,Leyte');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`StudentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
