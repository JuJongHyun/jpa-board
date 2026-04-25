package com.project.Board.dto;


import com.project.Board.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponse {

    private final Long id;
    private final String title;
    private final String writerName;
    private final int viewCount;
    private final LocalDateTime createdAt;

    public BoardListResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.writerName = board.getWriterName();
        this.viewCount = board.getViewCount();
        this.createdAt = board.getCreatedAt();
    }

}
