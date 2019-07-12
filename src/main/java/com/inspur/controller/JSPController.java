package com.inspur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * User: YANG
 * Date: 2019/6/10-23:33
 * Description: No Description
 */
@Controller
public class JSPController {

    @RequestMapping("/jspIndex")
    public String jspIndex(HttpServletRequest request, Model model){
        String realIP = request.getHeader("X-Real-IP");
        System.out.println("real_ip is ------------->:" + realIP);
        model.addAttribute("real_ip", realIP);
        return "index";
    }

}
