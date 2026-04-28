package com.brossard.taskmanager.service;

import com.brossard.taskmanager.entity.Task;
import com.brossard.taskmanager.repository.TaskRepository;
import com.brossard.taskmanager.repository.UserRepository;
import com.brossard.taskmanager.security.UserPrincipal;
import com.brossard.taskmanager.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service   // 🔹 Marca esta clase como un bean de servicio (lógica de negocio)
public class TaskService {

    @Autowired   // 🔹 Inyecta automáticamente el bean de TaskRepository
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Obtener todas las tareas
    public List<Task> getAllTasks() {
    User currentUser = getCurrentUser();
    return taskRepository.findByUser(currentUser);
}

    // Obtener una tarea por su ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Crear una nueva tarea
    public Task createTask(Task task) {
    User currentUser = getCurrentUser();
    task.setUser(currentUser);
    return taskRepository.save(task);
}

    // Actualizar una tarea existente
    public Task updateTask(Long id, Task taskDetails) {
    User currentUser = getCurrentUser();
    Task task = taskRepository.findByIdAndUser(id, currentUser)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada o no pertenece al usuario con id: " + id));
    task.setTitle(taskDetails.getTitle());
    task.setDescription(taskDetails.getDescription());
    task.setCompleted(taskDetails.isCompleted());
    return taskRepository.save(task);
}

    // Eliminar una tarea
    public void deleteTask(Long id) {
    User currentUser = getCurrentUser();
    Task task = taskRepository.findByIdAndUser(id, currentUser)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada o no pertenece al usuario con id: " + id));
    taskRepository.delete(task);
}

    // Listar tareas de un usuario específico
    private User getCurrentUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserPrincipal) {
        Long userId = ((UserPrincipal) principal).getId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + userId));
    }
    throw new RuntimeException("Usuario no autenticado");
    }
}