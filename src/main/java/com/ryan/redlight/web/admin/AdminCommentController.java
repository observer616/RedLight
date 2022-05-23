package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.vo.CommentVo;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    public String commentList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
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
    @PostMapping(value = "/update")
    public String update(@RequestParam(value = "commentId")Integer commentId,
                         @RequestParam(value = "replyInfo") String replyInfo,
                         HttpSession httpSession,
                         RedirectAttributes redirectAttributes) {
        Admin admin = (Admin) httpSession.getAttribute("adminInfo");
        // 构造comment
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setIsReplied((byte) 1);
        comment.setReplyId(admin.getAdminId());
        comment.setReplyTime(new Date());
        comment.setReplyInfo(replyInfo);
        //todo
        System.out.println(comment);
        Msg msg = commentService.updateByPrimaryKeySelective(comment);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/comments/get/list";
    }
}
