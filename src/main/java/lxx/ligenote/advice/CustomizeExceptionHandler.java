package lxx.ligenote.advice;

        import com.alibaba.fastjson.JSON;
        import lxx.ligenote.dto.ResultDTO;
        import lxx.ligenote.exception.CustomizeErrorCode;
        import lxx.ligenote.exception.CustomizeException;
        import org.springframework.ui.Model;
        import org.springframework.util.StringUtils;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.servlet.ModelAndView;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.io.PrintWriter;

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
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e, Model model, HttpServletResponse response) { // 出现异常之后会跳转到此方法

        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //返回json
            ResultDTO resultDTO;
            if(e instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            }else {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            }

            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
        else {
            //返回错误页面
            if (e instanceof CustomizeException) {

                model.addAttribute("message", e.getMessage());
            } else {
//                e.printStackTrace();
                model.addAttribute("message", CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            ModelAndView mav = new ModelAndView("error"); // 设置跳转路径
//            mav.addObject("exception", e); // 将异常对象传递过去
//            mav.addObject("url", request.getRequestURL()); // 获得请求的路径
            return mav;
        }
    }
}
