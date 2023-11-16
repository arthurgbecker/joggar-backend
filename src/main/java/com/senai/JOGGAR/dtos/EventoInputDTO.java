package com.senai.JOGGAR.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class EventoInputDTO{
    private String titleEvento;
    private String imageEvento;
    private String dateEvento;
    private String timeEvento;
    private String addressEvento;
    private String privacyEvento;
    private String descriptionEvento;
    private String activityEvento;
    private String audienceEvento;
    private String activityTypeEvento;
}
