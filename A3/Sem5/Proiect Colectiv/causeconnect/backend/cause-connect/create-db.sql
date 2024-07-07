SELECT 'CREATE DATABASE proiect_colectiv'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'proiect_colectiv')\gexec