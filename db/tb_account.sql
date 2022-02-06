-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Feb 2022 pada 08.01
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_android`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_account`
--

CREATE TABLE `tb_account` (
  `id` int(11) NOT NULL,
  `nama_depan` varchar(50) NOT NULL,
  `nama_belakang` varchar(50) NOT NULL,
  `alamat` varchar(250) NOT NULL,
  `no_rekening` varchar(12) NOT NULL,
  `status` varchar(10) NOT NULL,
  `cif` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_account`
--

INSERT INTO `tb_account` (`id`, `nama_depan`, `nama_belakang`, `alamat`, `no_rekening`, `status`, `cif`) VALUES
(1, 'Damian', 'Smith', 'INDONESIA', '101121', 'AKTIF', '1011'),
(2, 'George', 'Walker', 'BELANDA', '120130', 'AKTIF', '1201'),
(3, 'John', 'Adriane', 'TURKI', '110380', 'BLOKIR', '1103'),
(4, 'Jennifer', 'Moriana', 'INDONESIA', '201121', 'AKTIF', '2011'),
(5, 'Daniel', 'Xavier', 'ITALIA', '909111', 'AKTIF', '9091');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_account`
--
ALTER TABLE `tb_account`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_account`
--
ALTER TABLE `tb_account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
