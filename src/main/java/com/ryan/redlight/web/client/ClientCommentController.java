package com.ryan.redlight.web.client;

import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.interceptor.LoginCheck;
import com.ryan.redlight.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/comments")
public class ClientCommentController {
    @Autowired
    CommentService commentService;

    @GetMapping(value = "/get/list")
    public String getList(Model model) {
//        List<Comment> comments = commentService.selectAll();
//        model.addAttribute("comments", comments);
        return "client/comments";
    }

    @LoginCheck
    @PostMapping(value = "/create")
    public String createComment(@RequestParam(value = "info") String info,
                                HttpSession session,
                                Model model) {
        // 组装对象
        Comment comment = new Comment();
        Client client = (Client) session.getAttribute("userInfo");
        comment.setCreatorId(client.getUserId());
        comment.setInfo(info);
        Msg msg = commentService.insertSelective(comment);

        model.addAttribute("Msg", msg);
        return "redirect:comments/get/list";
    }

    @LoginCheck
    @PostMapping(value = "/comments/get/list")
    public String getPersonalList(HttpSession session,
                                  Model model) {
        Client client = (Client) session.getAttribute("userInfo");

        List<Comment> comments = (List<Comment>) commentService.selectAllByCreatorId(0, client.getUserId());
        // TODO: 2022/5/13 分页处理
        return "";
    }
}
