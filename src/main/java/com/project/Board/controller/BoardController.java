package com.project.Board.controller;

import com.project.Board.dto.BoardForm;
import com.project.Board.dto.BoardListResponse;
import com.project.Board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 목록 조회
    @GetMapping
    public String list(
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "10") int size,
                    @RequestParam(required = false) String keyword,
                    Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<BoardListResponse> boardPage = boardService.search(keyword, pageable);

        model.addAttribute("boards", boardPage.getContent());
        model.addAttribute("page", boardPage);
        model.addAttribute("keyword", keyword);


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
