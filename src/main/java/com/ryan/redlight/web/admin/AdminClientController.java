package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/admin/clients")
public class AdminClientController {
    final
    ClientService clientService;

    public AdminClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // 翻页与模糊搜索
    @AdminCheck
    @RequestMapping(value = "/get/list")
    public String select(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam(required = false, value = "condition") String condition,
                         Model model) {
        PageInfo<Client> clientPageInfo = clientService.selectList(pageNum, condition);
        List<Client> clientList = clientPageInfo.getList();
        model.addAttribute("clientPageInfo", clientPageInfo);
        model.addAttribute("clientList", clientList);
        model.addAttribute("condition", condition);
        model.addAttribute("client", new Client());
        return "admin/client_list";
    }

    @AdminCheck
    @PostMapping(value = "/create")
    public String create(@ModelAttribute(value = "client") Client client,
                         RedirectAttributes redirectAttributes) {
        Msg msg = clientService.insertSelective(client);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/clients/get/list";
    }

    @AdminCheck
    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "clientId") Integer clientId,
                         RedirectAttributes redirectAttributes) {
        Msg msg = clientService.deleteByPrimaryKey(clientId);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/clients/get/list";
    }

    @AdminCheck
    @RequestMapping(value = "/get/single")
    public String clientDetailPage(@RequestParam(value = "clientId") Integer clientId,
                                   Model model) {
        Client client = clientService.selectByPrimaryKey(clientId);
        model.addAttribute("client", client);
        return "admin/client_detail";
    }

    @AdminCheck
    @PostMapping(value = "/update")
    public String update(@RequestParam(value="clientId") Integer clientId,
                        @ModelAttribute(value = "client") Client client,
                         RedirectAttributes redirectAttributes) {
        client.setClientId(clientId);
        Msg msg = clientService.updateByPrimaryKeySelective(client);
        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/admin/clients/get/single?clientId="+clientId;
    }
}
