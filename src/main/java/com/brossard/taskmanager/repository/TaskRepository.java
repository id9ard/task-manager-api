package com.brossard.taskmanager.repository;

import com.brossard.taskmanager.entity.Task;
import com.brossard.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Listar todas las tareas de un usuario específico
    List<Task> findByUser(User user);

    // Buscar una tarea por su ID y por el usuario al que pertenece (útil para validar propiedad)
    Optional<Task> findByIdAndUser(Long id, User user);
}