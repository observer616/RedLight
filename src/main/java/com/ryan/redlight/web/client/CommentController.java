package com.ryan.redlight.web.client;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.entity.vo.CommentVo;
import com.ryan.redlight.interceptor.LoginCheck;
import com.ryan.redlight.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/comments")
public class CommentController {
    final
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/get/list")
    public String getCommentList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                 Model model) {
        PageInfo<CommentVo> commentVoPageInfo = commentService.selectAll(pageNum);
        List<CommentVo> commentVoList = commentVoPageInfo.getList();
        model.addAttribute("commentVoPageInfo", commentVoPageInfo);
        model.addAttribute("commentVoList", commentVoList);
        return "client/comment_list";
    }

    @LoginCheck
    @PostMapping(value = "/create")
    public String createComment(@RequestParam(value = "info") String info,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        // 组装对象
        Comment comment = new Comment();
        Client client = (Client) session.getAttribute("clientInfo");
        Msg msg;
        if (client == null) {
            // 排除管理员
            msg = new Msg("权限错误", "请确保用户身份");
        } else {
            // 创建预约
            comment.setCreatorId(client.getClientId());
            comment.setInfo(info);
            msg = commentService.insertSelective(comment);
        }
        redirectAttributes.addFlashAttribute("Msg", msg);
        return "redirect:/comments/get/list";
    }
}
