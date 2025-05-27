-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2025 at 05:47 AM
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
-- Database: `property`
--

-- --------------------------------------------------------

--
-- Table structure for table `logs_table`
--

CREATE TABLE `logs_table` (
  `logs_id` int(11) NOT NULL,
  `i_id` int(11) NOT NULL,
  `logs_action` text NOT NULL,
  `logs_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logs_table`
--

INSERT INTO `logs_table` (`logs_id`, `i_id`, `logs_action`, `logs_date`) VALUES
(1, 1, 'Admin Logged in', '2025-05-19 01:06:13'),
(2, 1, 'Admin Logged in', '2025-05-21 00:50:41'),
(3, 1, 'Admin Logged out', '2025-05-21 00:50:43'),
(4, 1, 'User Logged in', '2025-05-21 00:55:51'),
(5, 1, 'User Logged in', '2025-05-21 00:56:41'),
(6, 1, 'User Logged out', '2025-05-21 00:57:09'),
(7, 1, 'User Logged in', '2025-05-21 00:58:17'),
(9, 1, 'User Logged out', '2025-05-21 00:58:50'),
(10, 1, 'User Logged in', '2025-05-21 00:59:29'),
(11, 1, 'User Logged out', '2025-05-21 00:59:32'),
(12, 2, 'User Logged in', '2025-05-27 01:38:05'),
(13, 2, 'User Logged in', '2025-05-27 01:45:47'),
(14, 2, 'User Logged in', '2025-05-27 01:47:59'),
(15, 2, 'User Logged in', '2025-05-27 01:50:17'),
(16, 2, 'Admin Logged in', '2025-05-27 01:52:09'),
(17, 2, 'Admin Logged in', '2025-05-27 01:54:39'),
(18, 2, 'Admin Logged in', '2025-05-27 01:55:18'),
(19, 2, 'Admin Logged out', '2025-05-27 01:55:27'),
(20, 2, 'User Logged in', '2025-05-27 01:55:48'),
(21, 2, 'User Logged in', '2025-05-27 02:00:20'),
(22, 2, 'User Logged in', '2025-05-27 02:01:10'),
(23, 2, 'User Logged in', '2025-05-27 02:02:04'),
(24, 2, 'User Logged in', '2025-05-27 02:24:43'),
(25, 2, 'User Logged in', '2025-05-27 02:35:13'),
(26, 3, 'Admin Logged in', '2025-05-27 02:37:00'),
(27, 2, 'User Logged in', '2025-05-27 02:41:08'),
(28, 2, 'User Logged in', '2025-05-27 02:41:42'),
(29, 2, 'User Logged in', '2025-05-27 02:45:00'),
(30, 2, 'User Logged in', '2025-05-27 02:45:43'),
(31, 2, 'User Logged in', '2025-05-27 02:48:31'),
(32, 2, 'User Logged in', '2025-05-27 02:59:53'),
(33, 2, 'User Logged in', '2025-05-27 03:01:47'),
(34, 2, 'User Logged in', '2025-05-27 03:02:13'),
(35, 2, 'User Logged in', '2025-05-27 03:02:49'),
(36, 2, 'User Logged in', '2025-05-27 03:04:31'),
(37, 2, 'User Logged in', '2025-05-27 03:04:55'),
(38, 2, 'User Logged in', '2025-05-27 03:06:24'),
(39, 2, 'User Logged in', '2025-05-27 03:07:58'),
(40, 2, 'User Logged in', '2025-05-27 03:12:29'),
(41, 2, 'User Logged in', '2025-05-27 03:16:15'),
(42, 3, 'Admin Logged in', '2025-05-27 03:26:57'),
(43, 3, 'Admin Logged in', '2025-05-27 03:37:53'),
(44, 3, 'Admin Logged in', '2025-05-27 03:39:33'),
(45, 3, 'Admin Logged in', '2025-05-27 03:41:03'),
(46, 2, 'User Logged in', '2025-05-27 03:42:12');

-- --------------------------------------------------------

--
-- Table structure for table `properties`
--

CREATE TABLE `properties` (
  `id` int(20) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `structure` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'Available',
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `properties`
--

INSERT INTO `properties` (`id`, `type`, `structure`, `price`, `status`, `image`) VALUES
(1, 'Commercial', 'Single', 2000000, 'Sold', 'aaaa0900.jpg'),
(2, 'Mansion', 'Single', 5000000, 'Sold', '123.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `purchased_properties`
--

CREATE TABLE `purchased_properties` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `purchase_date` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchased_properties`
--

INSERT INTO `purchased_properties` (`id`, `user_id`, `property_id`, `purchase_date`) VALUES
(1, 2, 2, '2025-05-27 10:45:11');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `i_id` int(55) NOT NULL,
  `i_fname` varchar(55) NOT NULL,
  `i_lname` varchar(55) NOT NULL,
  `i_username` varchar(55) NOT NULL,
  `i_password` varchar(55) NOT NULL,
  `i_email` varchar(55) NOT NULL,
  `i_phonenumber` varchar(55) NOT NULL,
  `i_type` varchar(55) NOT NULL,
  `status` varchar(55) NOT NULL,
  `i_image` varchar(200) DEFAULT NULL,
  `security_question` varchar(255) NOT NULL,
  `security_answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`i_id`, `i_fname`, `i_lname`, `i_username`, `i_password`, `i_email`, `i_phonenumber`, `i_type`, `status`, `i_image`, `security_question`, `security_answer`) VALUES
(1, 'ross', 'sabio', 'ross123', 'DR6kwlbNUKKnzL/SKz2ZWfb9ML2EC5/zx8Ze5OId8G0=', 'ross123@gmail.com', '09123457892', 'User', 'Active', NULL, 'What\'s your favorite Song?', 'loveyourself'),
(2, 'gummy', 'bear', 'gummybear0200', 'LINbqJZtkCEg+0UEA3+tNO/6S5Rh6YjkxNoHOtUNroI=', 'gummy@gmail.com', '092022376442', 'User', 'Active', '', 'What\'s your favorite Sports?', 'Jabol'),
(3, 'admin', 'admin', 'administrator', 'LINbqJZtkCEg+0UEA3+tNO/6S5Rh6YjkxNoHOtUNroI=', 'admin@gmail.com', '092434234545', 'Admin', 'Active', '', 'What\'s your favorite Movie?', 'pornhub');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `logs_table`
--
ALTER TABLE `logs_table`
  ADD PRIMARY KEY (`logs_id`),
  ADD KEY `i_id` (`i_id`);

--
-- Indexes for table `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `purchased_properties`
--
ALTER TABLE `purchased_properties`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `property_id` (`property_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`i_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `logs_table`
--
ALTER TABLE `logs_table`
  MODIFY `logs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `properties`
--
ALTER TABLE `properties`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `purchased_properties`
--
ALTER TABLE `purchased_properties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `i_id` int(55) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `logs_table`
--
ALTER TABLE `logs_table`
  ADD CONSTRAINT `logs_table_ibfk_1` FOREIGN KEY (`i_id`) REFERENCES `user` (`i_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `purchased_properties`
--
ALTER TABLE `purchased_properties`
  ADD CONSTRAINT `purchased_properties_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`i_id`),
  ADD CONSTRAINT `purchased_properties_ibfk_2` FOREIGN KEY (`property_id`) REFERENCES `properties` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
