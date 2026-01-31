package com.adacapstone.seattleinyourlens.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating; // 1–5
    private String comment; //one event can have many comments
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    @JsonIgnoreProperties("reviews")
    private Event event;

    @PrePersist // Fixed the null problem of not required user to enter a time
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}