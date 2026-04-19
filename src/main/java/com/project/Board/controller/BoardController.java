package com.project.Board.controller;

import com.project.Board.dto.BoardDTO;
import com.project.Board.dto.BoardForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    // 게시글 목록 조회
    @GetMapping
    public String list(Model model) {

        List<BoardDTO> boards = Arrays.asList(
                new BoardDTO(1L, "첫 번째 게시글", "홍길동", LocalDateTime.now()),
                new BoardDTO(2L, "두 번째 게시글", "태종", LocalDateTime.now().minusDays(1)),
                new BoardDTO(3L, "세 번째 게시글", "이순신", LocalDateTime.now().minusDays(2))
        );

        model.addAttribute("boards", boards);
        return "boards/list";
    }

    // 게시글 작성 폼 표시
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "boards/form";
    }

    // 새 글 등록 처리
    @PostMapping
    public String create(@Valid @ModelAttribute BoardForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "boards/form";
        }

        System.out.println("제목: " + form.getTitle());
        System.out.println("내용: " + form.getContent());
        System.out.println("작성자: " + form.getWriter());

        redirectAttributes.addFlashAttribute("message", "저장되었습니다");
        return "redirect:/boards";
    }
}
