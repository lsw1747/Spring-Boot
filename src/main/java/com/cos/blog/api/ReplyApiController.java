package com.cos.blog.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Reply;
import com.cos.blog.service.ReplyService;


@RestController
public class ReplyApiController
{
    @Autowired
    ReplyService replyService;


    @PostMapping(value = "/api/reply/{id}")
    public ResponseDto<Integer> save(@RequestBody Reply reply, @PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal)
    {
        replyService.save(reply, id, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @DeleteMapping(value = "/api/reply")
    public ResponseDto<Integer> delete(@RequestParam(value = "replyId") int replyId)
    {
        replyService.delete(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @GetMapping(value = "/callApi/reply")
    public List<Reply> callList()
    {
        return replyService.findAll();
    }
}
