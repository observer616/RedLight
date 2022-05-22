package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.vo.CommentVo;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/admin/comments")
public class AdminCommentController {
    final
    CommentService commentService;

    public AdminCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @AdminCheck
    @RequestMapping(value = "/get/list")
    public String commentList(@RequestParam(value = "pageNum") Integer pageNum,
                              Model model) {
        PageInfo<CommentVo> commentVoPageInfo = commentService.selectAll(pageNum);
        List<CommentVo> commentVoList = commentVoPageInfo.getList();
        model.addAttribute("commentVoPageInfo", commentVoPageInfo);
        model.addAttribute("commentVoList", commentVoList);
        model.addAttribute("comment", new Comment());
        return "admin/comment_list";
    }

    // 回复留言
    @AdminCheck
    @GetMapping(value = "/update")
    public String update(@RequestParam(value = "comment") Comment comment,
                         Model model) {
        commentService.updateByPrimaryKeySelective(comment);
        // TODO: 2022/5/15 msg
        return "redirect:/admin/comments/get/list";
    }
}
