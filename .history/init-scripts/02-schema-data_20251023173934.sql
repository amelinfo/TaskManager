-- Crear tabla tasks
CREATE TABLE IF NOT EXISTS tasks (
    id UUID PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insertar datos de ejemplo
INSERT INTO tasks (id, title, description, status, created_at) VALUES
('5508e177-2d92-49e3-bd7b-7106d6447e38', 'Completar prueba técnica', 'Implementar API REST con Spring Boot y PL/pgSQL', 'PENDING', '2025-10-06 09:00:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e39', 'Configurar PostgreSQL', 'Configurar PostgreSQL con stored procedures PL/pgSQL', 'COMPLETED', '2025-10-06 08:00:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e40', 'Implementar funciones PL/pgSQL', 'Crear stored procedures y funciones en PostgreSQL', 'IN_PROGRESS', '2025-10-06 10:00:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e41', 'Probar endpoints con PL/pgSQL', 'Probar integración entre Spring Boot y PostgreSQL functions', 'PENDING', '2025-10-06 11:00:00')
ON CONFLICT (id) DO NOTHING;