-- Limpiar la tabla tasks si ya existe (opcional)
DELETE FROM tasks;

-- Insertar tareas de ejemplo
INSERT INTO tasks (id, title, description, status, created_at) VALUES
-- Tareas PENDIENTES
('5508e177-2d92-49e3-bd7b-7106d6447e38', 'Completar prueba técnica', 'Implementar API REST con Spring Boot para gestión de tareas', 'PENDING', '2025-10-06 09:00:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e39', 'Revisar documentación Spring Boot', 'Estudiar la documentación oficial de Spring Boot 3.x', 'PENDING', '2025-10-06 09:30:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e40', 'Preparar presentación del proyecto', 'Crear slides para la presentación del API desarrollada', 'PENDING', '2025-10-06 10:00:00'),

-- Tareas EN PROGRESO
('5508e177-2d92-49e3-bd7b-7106d6447e41', 'Desarrollar endpoints del API', 'Implementar los endpoints GET, POST, PUT, DELETE para tasks', 'IN_PROGRESS', '2025-10-06 08:00:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e42', 'Configurar base de datos PostgreSQL', 'Configurar Docker Compose y conexión a PostgreSQL', 'IN_PROGRESS', '2025-10-06 08:30:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e43', 'Implementar manejo de excepciones', 'Crear GlobalExceptionHandler para errores 404 y 400', 'IN_PROGRESS', '2025-10-06 09:15:00'),

-- Tareas COMPLETADAS
('5508e177-2d92-49e3-bd7b-7106d6447e44', 'Configurar proyecto Spring Boot', 'Crear proyecto base con Spring Initializr y dependencias necesarias', 'COMPLETED', '2025-10-05 14:00:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e45', 'Diseñar modelo de datos', 'Crear entidad Task con los campos requeridos', 'COMPLETED', '2025-10-05 15:30:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e46', 'Definir estructura de paquetes', 'Organizar controller, service, repository, model', 'COMPLETED', '2025-10-05 16:00:00'),

-- Tareas adicionales para testing
('5508e177-2d92-49e3-bd7b-7106d6447e47', 'Testing endpoints con Postman', 'Probar todos los endpoints con diferentes escenarios', 'PENDING', '2025-10-06 11:00:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e48', 'Optimizar consultas de base de datos', 'Revisar y optimizar las queries con índices necesarios', 'IN_PROGRESS', '2025-10-06 11:30:00'),
('5508e177-2d92-49e3-bd7b-7106d6447e49', 'Documentar API con Swagger', 'Agregar documentación OpenAPI para los endpoints', 'COMPLETED', '2025-10-05 17:00:00');

-- Mostrar estadísticas (opcional, para verificación)
SELECT 
    status,
    COUNT(*) as task_count,
    STRING_AGG(title, ', ') as titles
FROM tasks 
GROUP BY status 
ORDER BY 
    CASE status 
        WHEN 'PENDING' THEN 1
        WHEN 'IN_PROGRESS' THEN 2
        WHEN 'COMPLETED' THEN 3
    END;