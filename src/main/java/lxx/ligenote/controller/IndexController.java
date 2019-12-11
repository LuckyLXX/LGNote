package lxx.ligenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName:IndexController
 * Package:lxx.ligenote.controller
 * Description:
 *
 * @Date:2019/12/11 9:19
 * @Author:857251389@qq.com
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
