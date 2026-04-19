package com.brossard.taskmanager.service;

import com.brossard.taskmanager.entity.Task;
import com.brossard.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service   // 🔹 Marca esta clase como un bean de servicio (lógica de negocio)
public class TaskService {

    @Autowired   // 🔹 Inyecta automáticamente el bean de TaskRepository
    private TaskRepository taskRepository;

    // Obtener todas las tareas
    public List<Task> getAllTasks() {
        return taskRepository.findAll();  // Llama al método de JpaRepository
    }

    // Obtener una tarea por su ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Crear una nueva tarea
    public Task createTask(Task task) {
        // Podríamos agregar validaciones aquí (ej. título no vacío)
        return taskRepository.save(task);
    }

    // Actualizar una tarea existente
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        // Nota: no modificamos createdAt porque no debe cambiar
        return taskRepository.save(task);
    }

    // Eliminar una tarea
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}