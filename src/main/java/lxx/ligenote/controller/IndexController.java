package lxx.ligenote.controller;

import lxx.ligenote.mapper.UserMapper;
import lxx.ligenote.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String value = cookie.getValue();
                User user = userMapper.searchToken(value);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        return "index";

    }
}
