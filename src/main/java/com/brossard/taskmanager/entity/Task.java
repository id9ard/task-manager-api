package com.brossard.taskmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity                     // 🔹 Esta clase será una tabla en la BD
@Table(name = "tasks")      // 🔹 Nombre de la tabla (opcional, por defecto usa el nombre de la clase)
public class Task {

    @Id                     // 🔹 Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🔹 Auto-incremental (MySQL)
    private Long id;

    @Column(nullable = false, length = 100) // 🔹 No puede ser nulo, máximo 100 caracteres
    private String title;

    @Column(length = 500)   // 🔹 Opcional, hasta 500 caracteres
    private String description;

    @Column(nullable = false)
    private boolean completed = false;      // 🔹 Por defecto, no completada

    @Column(name = "created_at", updatable = false) // 🔹 No se modifica después de crear
    private LocalDateTime createdAt = LocalDateTime.now(); // 🔹 Fecha/hora actual

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructores (necesario el vacío para JPA)
    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters y Setters (Lombok podría evitarlos, pero los escribimos para entender)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}