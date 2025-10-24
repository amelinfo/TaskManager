-- ===========================================
-- STORED PROCEDURES & FUNCTIONS (PL/pgSQL)
-- ===========================================

-- 1. Función para marcar tarea como completada
CREATE OR REPLACE FUNCTION mark_task_completed(task_id UUID)
RETURNS VOID AS $$
BEGIN
    UPDATE tasks 
    SET status = 'COMPLETED' 
    WHERE id = task_id;
    
    IF NOT FOUND THEN
        RAISE EXCEPTION 'Task not found with id: %', task_id;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- 2. Función para obtener estadísticas de tareas
CREATE OR REPLACE FUNCTION get_task_statistics()
RETURNS TABLE(status TEXT, task_count BIGINT) AS $$
BEGIN
    RETURN QUERY
    SELECT t.status::TEXT, COUNT(*) as task_count
    FROM tasks t
    GROUP BY t.status
    ORDER BY 
        CASE t.status 
            WHEN 'PENDING' THEN 1
            WHEN 'IN_PROGRESS' THEN 2
            WHEN 'COMPLETED' THEN 3
        END;
END;
$$ LANGUAGE plpgsql;

-- 3. Función para búsqueda avanzada de tareas
CREATE OR REPLACE FUNCTION search_tasks(search_term TEXT)
RETURNS TABLE(
    task_id UUID,
    task_title VARCHAR(100),
    task_description VARCHAR(500),
    task_status VARCHAR(20),
    task_created_at TIMESTAMP
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        t.id,
        t.title,
        t.description,
        t.status,
        t.created_at
    FROM tasks t
    WHERE 
        LOWER(t.title) LIKE LOWER('%' || search_term || '%')
        OR LOWER(t.description) LIKE LOWER('%' || search_term || '%')
    ORDER BY t.created_at DESC;
END;
$$ LANGUAGE plpgsql;

-- 4. Procedimiento para actualizar solo el título de una tarea
CREATE OR REPLACE FUNCTION update_task_title(
    task_id UUID,
    new_title VARCHAR(100)
) RETURNS VOID AS $$
BEGIN
    IF LENGTH(new_title) > 100 THEN
        RAISE EXCEPTION 'Title exceeds maximum length of 100 characters';
    END IF;
    
    UPDATE tasks 
    SET title = new_title 
    WHERE id = task_id;
    
    IF NOT FOUND THEN
        RAISE EXCEPTION 'Task not found with id: %', task_id;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- 5. Función para obtener tareas por estado con paginación
CREATE OR REPLACE FUNCTION get_tasks_by_status_paginated(
    task_status VARCHAR(20),
    page_size INTEGER DEFAULT 10,
    page_number INTEGER DEFAULT 1
) RETURNS TABLE(
    task_id UUID,
    task_title VARCHAR(100),
    task_description VARCHAR(500),
    task_created_at TIMESTAMP
) AS $$
DECLARE
    offset_value INTEGER;
BEGIN
    offset_value := (page_number - 1) * page_size;
    
    RETURN QUERY
    SELECT 
        t.id,
        t.title,
        t.description,
        t.created_at
    FROM tasks t
    WHERE t.status = task_status
    ORDER BY t.created_at DESC
    LIMIT page_size
    OFFSET offset_value;
END;
$$ LANGUAGE plpgsql;

-- 6. Función para contar tareas por estado
CREATE OR REPLACE FUNCTION count_tasks_by_status(task_status VARCHAR(20))
RETURNS INTEGER AS $$
DECLARE
    task_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO task_count
    FROM tasks
    WHERE status = task_status;
    
    RETURN task_count;
END;
$$ LANGUAGE plpgsql;