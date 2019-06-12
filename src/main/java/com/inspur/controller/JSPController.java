package com.inspur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: YANG
 * Date: 2019/6/10-23:33
 * Description: No Description
 */
@Controller
public class JSPController {

    @RequestMapping("/jspIndex")
    public String jspIndex(){
        return "index";
    }

}
