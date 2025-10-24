-- Initialize database with proper user and permissions
-- This script ensures the postgres user has the correct password
ALTER USER postgres PASSWORD 'password';

-- Grant all privileges on database
GRANT ALL PRIVILEGES ON DATABASE taskdb TO postgres;

-- Create schema if needed
CREATE SCHEMA IF NOT EXISTS public;
GRANT ALL ON SCHEMA public TO postgres;