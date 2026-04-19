package com.project.Board.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BoardDTO {

    private Long id;
    private String title;
    private String writer;
    private LocalDateTime createdAt;
}
