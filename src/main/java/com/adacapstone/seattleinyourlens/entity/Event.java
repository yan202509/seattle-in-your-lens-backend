package com.adacapstone.seattleinyourlens.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevents Serialization Errors
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_id;

    // the naming convention is off, and must use CamelCase to make search bar work
    @Column(name = "event_title")
    private String eventTitle;

    private String event_description;

    private String event_season;
    private String event_type;
    private String cost_level;
    private LocalDateTime event_date;
    private Integer likes = 0;

    //orphanRemoval means to delete comments as well when event is deleted
    // lazy because loading related data only when needed, rather than fetching everything eagerly
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)

    @JsonIgnoreProperties("event")
    private List<Review> reviews = new ArrayList<>();

    // This links the Event to a User
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("events")
    private User creator;
}