package lxx.ligenote.advice;

        import lxx.ligenote.exception.CustomizeException;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.servlet.ModelAndView;

        import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:CustomizeExceptionHandler
 * Package:lxx.ligenote.advice
 * Description:
 *
 * @Date:2019/12/29 15:32
 * @Author:857251389@qq.com
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class) // 所有的异常都是Exception子类
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e, Model model) { // 出现异常之后会跳转到此方法
        if(e instanceof CustomizeException){

            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","服务器抽风");
        }
        ModelAndView mav = new ModelAndView("error"); // 设置跳转路径
        mav.addObject("exception", e); // 将异常对象传递过去
        mav.addObject("url", request.getRequestURL()); // 获得请求的路径
        return mav;
    }
}
