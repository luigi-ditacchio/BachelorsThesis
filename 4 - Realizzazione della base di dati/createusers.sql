CREATE USER utente IDENTIFIED BY 'utente';
GRANT SELECT ON laureadb.* TO 'utente'@'%' IDENTIFIED BY 'utente';
FLUSH PRIVILEGES;
