package com.adacapstone.seattleinyourlens.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String event_title;
    private String event_description;
    private String event_season;
    private String event_type;
    private String cost_level;
    private LocalDateTime event_date;
    private Integer likes = 0;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JsonIgnoreProperties("event")
    private List<Review> reviews = new ArrayList<>();
}