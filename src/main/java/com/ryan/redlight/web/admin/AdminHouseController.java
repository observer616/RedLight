package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.entity.vo.VisualHouseTypeVo;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/admin/houses")
public class AdminHouseController {
    final
    HouseService houseService;

    // TODO: 2022/5/22 文件上传，文件保存路径
//    @Value("${file.upload.path.relative}")
//    private String filePath;

    public AdminHouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * 进入详情页、翻页与模糊搜索
     */
    @AdminCheck
    @RequestMapping(value = "/get/list")
    public String select(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam(required = false, value = "condition") String condition,
                         Model model) {
        PageInfo<House> housePageInfo = houseService.selectList(pageNum, condition);
        List<House> houseList = housePageInfo.getList();
        model.addAttribute("housePageInfo", housePageInfo);
        model.addAttribute("houseList", houseList);
        model.addAttribute("condition", condition);
        // 用于添加功能
        House house = new House();
        model.addAttribute("house", house);
        return "admin/house_list";
    }

    /**
     * 创建房屋
     *
     * @param house              house
     * @param redirectAttributes flash attribute
     * @return redirect to house_list
     */
    @AdminCheck
    @PostMapping(value = "/create")
    public String create(@ModelAttribute(value = "house") House house,
                         @RequestParam(value = "picture", required = false) MultipartFile picture,
                         RedirectAttributes redirectAttributes) {
        if (picture != null) {
            //获取文件名
            String fileName = picture.getOriginalFilename();
            //获取文件后缀名
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID() + suffixName;
            //指定本地文件夹存储图片，写到需要保存的目录下
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\house_img\\";
            try {
                //将图片保存到static文件夹里
                picture.transferTo(new File(filePath + fileName));
                //返回提示信息
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 添加house
            house.setPicture("..\\img\\house_img\\" + fileName);
        }
        Msg msg = houseService.insertSelective(house);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/houses/get/list";
    }

    @AdminCheck
    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "houseId") Integer houseId,
                         RedirectAttributes redirectAttributes) {
        Msg msg = houseService.deleteByPrimaryKey(houseId);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/houses/get/list";
    }

    @AdminCheck
    @RequestMapping(value = "/get/single")
    public String houseDetailPage(@RequestParam(value = "houseId") Integer houseId,
                                  Model model) {
        House house = houseService.selectByPrimaryKey(houseId);
        model.addAttribute("house", house);
        return "admin/house_detail";
    }

    @AdminCheck
    @PostMapping(value = "/update")
    public String update(@RequestParam(value = "houseId") Integer houseId,
                         @ModelAttribute(value = "house") House house,
                         RedirectAttributes redirectAttributes) {
        house.setHouseId(houseId);
        Msg msg = houseService.updateByPrimaryKeySelective(house);
        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/admin/clients/get/single?houseId=" + houseId;
    }

    @AdminCheck
    @PostMapping(value = "/visual/type")
    public String visualType(@RequestParam(value = "visualInfoCount", required = false, defaultValue = "5") Integer visualInfoCount,
                             Model model) {
        List<VisualHouseTypeVo> visualTypeList = houseService.selectVisualHouseTypeVo(visualInfoCount);
        model.addAttribute("visualTypeList", visualTypeList);
        return "admin/house_visual";
    }
}
