package com.cos.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;


@Controller
public class BoardController
{

    @Autowired
    private BoardService boardService;


    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 5,
                                                      sort = "id",
                                                      direction = Sort.Direction.DESC) Pageable pageable)
    {
        model.addAttribute("boards", boardService.getPostList(pageable));
        return "index";
    }


    @GetMapping("/board/saveForm")
    public String saveForm()
    {
        return "board/saveForm";
    }


    @GetMapping("/board/{id}")
    public String showBoardDetail(@PathVariable(value = "id") int id, Model model) throws Exception
    {
        model.addAttribute("board", boardService.detail(id));
        model.addAttribute("count", boardService.updateCount(id)); //조회수 추가
        return "board/detail";
    }


    @GetMapping("/board/update/{id}")
    public String updateBoard(@PathVariable int id, Model model) throws Exception
    {
        model.addAttribute("board", boardService.detail(id));
        return "board/updateForm";
    }

}
