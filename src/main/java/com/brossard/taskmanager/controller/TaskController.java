package com.brossard.taskmanager.controller;

import com.brossard.taskmanager.entity.Task;
import com.brossard.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                    // 🔹 Esta clase maneja peticiones HTTP y devuelve JSON
@RequestMapping("/api/tasks")     // 🔹 Todas las rutas comienzan con /api/tasks
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET /api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)          // Si existe, responde 200 OK con la tarea
                .orElse(ResponseEntity.notFound().build()); // Si no, 404 Not Found
    }

    // POST /api/tasks
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)   // Responde 201 Created
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        try {
            Task updated = taskService.updateTask(id, taskDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}