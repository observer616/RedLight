package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.HouseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
     * 创建房屋
     *
     * @param house              house
     * @param redirectAttributes flash attribute
     * @return redirect to house_list
     */
    @AdminCheck
    @PostMapping(value = "/create")
    public String create(@ModelAttribute(value = "house") House house,
//                         @RequestParam(value = "picture", required = false) MultipartFile picture,
                         RedirectAttributes redirectAttributes) {
        // TODO: 2022/5/22 上传文件，无法读取到文件
//        System.out.println(filePath);
//        if (picture != null && !picture.isEmpty()) {
//            System.out.println("鸡");
//            // 获取上传文件名
//            String filename = picture.getOriginalFilename();
//            // 定义上传文件保存路径
//            // 新建文件
//            assert filename != null;
//            File filepath = new File(filePath, filename);
//            // 判断路径是否存在，如果不存在就创建一个
//            if (!filepath.getParentFile().exists()) {
//                filepath.getParentFile().mkdirs();
//            }
//            try {
//                // 写入文件
//                picture.transferTo(new File(filePath + File.separator + filename));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        Msg msg = houseService.insertSelective(house);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/houses/get/list";
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
    public String update(@ModelAttribute(value = "house") House house,
                         Model model) {
        Msg msg = houseService.updateByPrimaryKeySelective(house);
        model.addAttribute("msg", msg);
        model.addAttribute("house", house);
        return "admin/house_detail";
    }
}
