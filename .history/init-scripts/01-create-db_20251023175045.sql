-- Crear la base de datos si no existe
SELECT 'CREATE DATABASE task'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'taskdb')\gexec