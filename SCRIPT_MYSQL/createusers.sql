CREATE USER 'visitatore'@'localhost' IDENTIFIED BY 'visitatore';
GRANT SELECT ON LaureaDB.* TO 'visitatore'@'localhost' IDENTIFIED BY 'visitatore';
FLUSH PRIVILEGES;
