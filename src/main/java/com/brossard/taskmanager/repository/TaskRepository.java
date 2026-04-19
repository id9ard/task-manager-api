package com.brossard.taskmanager.repository;

import com.brossard.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   // 🔹 Indica que es un componente de acceso a datos
public interface TaskRepository extends JpaRepository<Task, Long> {
    // No necesitamos escribir métodos; JpaRepository ya provee:
    // save(), findAll(), findById(), deleteById(), etc.
}