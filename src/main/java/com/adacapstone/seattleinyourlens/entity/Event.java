package com.adacapstone.seattleinyourlens.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

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



}
