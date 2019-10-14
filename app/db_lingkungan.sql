-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2019 at 04:50 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_lingkungan`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `data_akses`
--
CREATE TABLE IF NOT EXISTS `data_akses` (
`ID_AKSES` int(11)
,`NIK` varchar(16)
,`NAMA` varchar(60)
,`ALAMAT` text
,`RT` varchar(3)
,`RW` varchar(3)
,`KEL` varchar(30)
,`KAB` varchar(30)
,`JK` enum('Laki-Laki','Perempuan')
,`NEGARA` varchar(60)
,`AKSES` enum('Ketua','Petugas')
,`PASS` varchar(60)
,`PHOTO` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `data_akun`
--
CREATE TABLE IF NOT EXISTS `data_akun` (
`id` int(11)
,`nik` varchar(16)
,`nama` varchar(60)
,`alamat` text
,`rt` varchar(3)
,`rw` varchar(3)
,`kel` varchar(30)
,`kab` varchar(30)
,`negara` varchar(60)
,`aktif` set('Y','T')
,`pass` varchar(50)
,`image` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `izin_tamu`
--
CREATE TABLE IF NOT EXISTS `izin_tamu` (
`NIK` varchar(16)
,`NAMA` varchar(60)
,`JENIS` enum('Bertamu','Pergi','Warga Baru')
,`TGL_DATANG` date
,`TGL_PULANG` date
,`PESAN` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `lap_baru`
--
CREATE TABLE IF NOT EXISTS `lap_baru` (
`NIK` varchar(16)
,`NAMA` varchar(60)
,`JENIS` enum('Bertamu','Pergi','Warga Baru')
,`TGL` date
,`PESAN` text
,`STATUS_LAP` enum('Proses','Selesai')
);

-- --------------------------------------------------------

--
-- Table structure for table `tb_akses`
--

CREATE TABLE IF NOT EXISTS `tb_akses` (
  `id_akses` int(11) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `pass_akses` varchar(60) NOT NULL,
  `akses` enum('Ketua','Petugas') NOT NULL,
  `blokir` set('Y','T') DEFAULT NULL,
  `lastlogin` timestamp NULL DEFAULT NULL,
  `ipaddress` varchar(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_akses`
--

INSERT INTO `tb_akses` (`id_akses`, `nik`, `pass_akses`, `akses`, `blokir`, `lastlogin`, `ipaddress`) VALUES
(1, '2222222222', '12345', 'Ketua', NULL, '2019-10-09 19:05:21', '::1');

-- --------------------------------------------------------

--
-- Table structure for table `tb_aktivasi`
--

CREATE TABLE IF NOT EXISTS `tb_aktivasi` (
  `id` int(11) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `passsword` varchar(50) NOT NULL,
  `notlp` varchar(13) NOT NULL,
  `aktif` set('Y','T') DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_aktivasi`
--

INSERT INTO `tb_aktivasi` (`id`, `nik`, `passsword`, `notlp`, `aktif`) VALUES
(1, '1234567890', '123456', '2356890', 'Y'),
(2, '2222222222', '12345678', '32342434', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `tb_laporan`
--

CREATE TABLE IF NOT EXISTS `tb_laporan` (
  `id_laporan` int(11) NOT NULL,
  `nik` varchar(16) DEFAULT NULL,
  `tgl_pergi` date DEFAULT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `jenis_laporan` enum('Bertamu','Pergi','Warga Baru') DEFAULT NULL,
  `pesan` text,
  `tgl_laporan` date DEFAULT NULL,
  `status_lap` enum('Proses','Selesai') DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_laporan`
--

INSERT INTO `tb_laporan` (`id_laporan`, `nik`, `tgl_pergi`, `tgl_kembali`, `jenis_laporan`, `pesan`, `tgl_laporan`, `status_lap`) VALUES
(1, '2222222222', '2019-10-09', '2019-10-11', 'Pergi', 'kami sekeluarga pergi meninggalkan rumah selama 3 hari', '2019-10-09', 'Proses');

-- --------------------------------------------------------

--
-- Table structure for table `tb_masyarakat`
--

CREATE TABLE IF NOT EXISTS `tb_masyarakat` (
  `nik` varchar(16) NOT NULL,
  `nama_lengkap` varchar(60) DEFAULT NULL,
  `jenis_kelamin` enum('Laki-Laki','Perempuan') DEFAULT NULL,
  `alamat` text,
  `rt` varchar(3) DEFAULT NULL,
  `rw` varchar(3) DEFAULT NULL,
  `kel_desa` varchar(30) DEFAULT NULL,
  `kab` varchar(30) DEFAULT NULL,
  `warganegara` varchar(60) DEFAULT NULL,
  `photo` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_masyarakat`
--

INSERT INTO `tb_masyarakat` (`nik`, `nama_lengkap`, `jenis_kelamin`, `alamat`, `rt`, `rw`, `kel_desa`, `kab`, `warganegara`, `photo`) VALUES
('1234567890', 'Uciha Sasuke', 'Laki-Laki', 'SUKABUMI', '01', '02', 'Sukakarya', 'Kota Sukabumi', 'Indonesia', 'photo'),
('2222222222', 'Web Admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_pekerjaan`
--

CREATE TABLE IF NOT EXISTS `tb_pekerjaan` (
  `id_pekerjaan` int(11) NOT NULL,
  `nama_pekerjaan` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_warga`
--

CREATE TABLE IF NOT EXISTS `tb_warga` (
  `id_warga` int(11) NOT NULL,
  `nik` varchar(16) DEFAULT NULL,
  `status_warga` enum('Residen','Nonresiden') DEFAULT NULL,
  `asal` text,
  `pekerjaan` varchar(30) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_warga`
--

INSERT INTO `tb_warga` (`id_warga`, `nik`, `status_warga`, `asal`, `pekerjaan`) VALUES
(1, '2222222222', 'Residen', 'Bogor', NULL),
(2, '1234567890', 'Nonresiden', 'Jakarta', NULL);

-- --------------------------------------------------------

--
-- Stand-in structure for view `warga_baru`
--
CREATE TABLE IF NOT EXISTS `warga_baru` (
`NIK` varchar(16)
,`NAMA` varchar(60)
,`JENIS` enum('Bertamu','Pergi','Warga Baru')
,`TGL_DATANG` date
,`PESAN` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `warga_kembali`
--
CREATE TABLE IF NOT EXISTS `warga_kembali` (
`NIK` varchar(16)
,`NAMA` varchar(60)
,`JENIS` enum('Bertamu','Pergi','Warga Baru')
,`TGL_DATANG` date
,`TGL_PULANG` date
,`PESAN` text
);

-- --------------------------------------------------------

--
-- Structure for view `data_akses`
--
DROP TABLE IF EXISTS `data_akses`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `data_akses` AS (select `tb_akses`.`id_akses` AS `ID_AKSES`,`tb_akses`.`nik` AS `NIK`,`tb_masyarakat`.`nama_lengkap` AS `NAMA`,`tb_masyarakat`.`alamat` AS `ALAMAT`,`tb_masyarakat`.`rt` AS `RT`,`tb_masyarakat`.`rw` AS `RW`,`tb_masyarakat`.`kel_desa` AS `KEL`,`tb_masyarakat`.`kab` AS `KAB`,`tb_masyarakat`.`jenis_kelamin` AS `JK`,`tb_masyarakat`.`warganegara` AS `NEGARA`,`tb_akses`.`akses` AS `AKSES`,`tb_akses`.`pass_akses` AS `PASS`,`tb_masyarakat`.`photo` AS `PHOTO` from (`tb_akses` join `tb_masyarakat`) where (`tb_akses`.`nik` = `tb_masyarakat`.`nik`));

-- --------------------------------------------------------

--
-- Structure for view `data_akun`
--
DROP TABLE IF EXISTS `data_akun`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `data_akun` AS (select `tb_aktivasi`.`id` AS `id`,`tb_aktivasi`.`nik` AS `nik`,`tb_masyarakat`.`nama_lengkap` AS `nama`,`tb_masyarakat`.`alamat` AS `alamat`,`tb_masyarakat`.`rt` AS `rt`,`tb_masyarakat`.`rw` AS `rw`,`tb_masyarakat`.`kel_desa` AS `kel`,`tb_masyarakat`.`kab` AS `kab`,`tb_masyarakat`.`warganegara` AS `negara`,`tb_aktivasi`.`aktif` AS `aktif`,`tb_aktivasi`.`passsword` AS `pass`,`tb_masyarakat`.`photo` AS `image` from (`tb_aktivasi` join `tb_masyarakat`) where (`tb_aktivasi`.`nik` = `tb_masyarakat`.`nik`));

-- --------------------------------------------------------

--
-- Structure for view `izin_tamu`
--
DROP TABLE IF EXISTS `izin_tamu`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `izin_tamu` AS (select `tb_masyarakat`.`nik` AS `NIK`,`tb_masyarakat`.`nama_lengkap` AS `NAMA`,`tb_laporan`.`jenis_laporan` AS `JENIS`,`tb_laporan`.`tgl_pergi` AS `TGL_DATANG`,`tb_laporan`.`tgl_kembali` AS `TGL_PULANG`,`tb_laporan`.`pesan` AS `PESAN` from (`tb_laporan` join `tb_masyarakat`) where ((`tb_laporan`.`nik` = `tb_masyarakat`.`nik`) and (`tb_laporan`.`jenis_laporan` = 'Bertamu')));

-- --------------------------------------------------------

--
-- Structure for view `lap_baru`
--
DROP TABLE IF EXISTS `lap_baru`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `lap_baru` AS (select `tb_masyarakat`.`nik` AS `NIK`,`tb_masyarakat`.`nama_lengkap` AS `NAMA`,`tb_laporan`.`jenis_laporan` AS `JENIS`,`tb_laporan`.`tgl_laporan` AS `TGL`,`tb_laporan`.`pesan` AS `PESAN`,`tb_laporan`.`status_lap` AS `STATUS_LAP` from (`tb_laporan` join `tb_masyarakat`) where (`tb_laporan`.`nik` = `tb_masyarakat`.`nik`));

-- --------------------------------------------------------

--
-- Structure for view `warga_baru`
--
DROP TABLE IF EXISTS `warga_baru`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `warga_baru` AS (select `tb_masyarakat`.`nik` AS `NIK`,`tb_masyarakat`.`nama_lengkap` AS `NAMA`,`tb_laporan`.`jenis_laporan` AS `JENIS`,`tb_laporan`.`tgl_pergi` AS `TGL_DATANG`,`tb_laporan`.`pesan` AS `PESAN` from (`tb_laporan` join `tb_masyarakat`) where ((`tb_laporan`.`nik` = `tb_masyarakat`.`nik`) and (`tb_laporan`.`jenis_laporan` = 'Warga Baru')));

-- --------------------------------------------------------

--
-- Structure for view `warga_kembali`
--
DROP TABLE IF EXISTS `warga_kembali`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `warga_kembali` AS (select `tb_masyarakat`.`nik` AS `NIK`,`tb_masyarakat`.`nama_lengkap` AS `NAMA`,`tb_laporan`.`jenis_laporan` AS `JENIS`,`tb_laporan`.`tgl_pergi` AS `TGL_DATANG`,`tb_laporan`.`tgl_kembali` AS `TGL_PULANG`,`tb_laporan`.`pesan` AS `PESAN` from (`tb_laporan` join `tb_masyarakat`) where ((`tb_laporan`.`nik` = `tb_masyarakat`.`nik`) and (`tb_laporan`.`jenis_laporan` = 'Pergi')));

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_akses`
--
ALTER TABLE `tb_akses`
  ADD PRIMARY KEY (`id_akses`), ADD KEY `nik` (`nik`);

--
-- Indexes for table `tb_aktivasi`
--
ALTER TABLE `tb_aktivasi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_laporan`
--
ALTER TABLE `tb_laporan`
  ADD PRIMARY KEY (`id_laporan`);

--
-- Indexes for table `tb_masyarakat`
--
ALTER TABLE `tb_masyarakat`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `tb_pekerjaan`
--
ALTER TABLE `tb_pekerjaan`
  ADD PRIMARY KEY (`id_pekerjaan`);

--
-- Indexes for table `tb_warga`
--
ALTER TABLE `tb_warga`
  ADD PRIMARY KEY (`id_warga`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_akses`
--
ALTER TABLE `tb_akses`
  MODIFY `id_akses` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tb_aktivasi`
--
ALTER TABLE `tb_aktivasi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tb_laporan`
--
ALTER TABLE `tb_laporan`
  MODIFY `id_laporan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tb_pekerjaan`
--
ALTER TABLE `tb_pekerjaan`
  MODIFY `id_pekerjaan` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tb_warga`
--
ALTER TABLE `tb_warga`
  MODIFY `id_warga` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
