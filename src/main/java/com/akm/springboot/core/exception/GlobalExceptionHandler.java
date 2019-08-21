package com.akm.springboot.core.exception;

import com.akm.springboot.core.domain.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Set;

/**
 * 全局异常处理类
 * 异常大概f分三种情况：
 * 一：在进入Controller之前，譬如请求一个不存在的地址，404错误。
 * 二：在执行@RequestMapping时，进入逻辑处理阶段前。譬如传的参数类型错误。
 * 三：以上都正常时，在controller里执行逻辑代码时出的异常。譬如NullPointerException。
 */
@ControllerAdvice
@Controller  // 这里使用@Controller加@ResponseBody而不用@RestController，是不想让swagger扫描到，目前swagger针对@RestController进行扫描
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * 处理进入controller前失败的异常,如404
     */
    @RequestMapping(value = "/error")
    public ResultBean error(HttpServletResponse response, HttpServletRequest request) {
        int status = response.getStatus();
        String msg = "请求出错";
        if (status == 404) {
            msg = "未找到请求地址,请检查地址输入是否有误";
        }
        return ResultBean.fail(status, msg);
    }

    /**
     * 处理无法进入controller的异常，如:参数不匹配，get post方法不对等
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        ResultBean<String> resultBean = ResultBean.fail(status.value(), "", ex.toString());
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            Set<HttpMethod> supportedHttpMethods = ((HttpRequestMethodNotSupportedException) ex).getSupportedHttpMethods();
            String method = ((HttpRequestMethodNotSupportedException) ex).getMethod();
            resultBean.setResult(String.format("请求方法[%s]不被支持,支持%s", method, supportedHttpMethods));
        }
        if (ex instanceof NoHandlerFoundException) {
            resultBean.setResult("未找到处理方法，请求url可能不正确：" + ex.toString());
        }
        if (ex instanceof HttpMessageNotReadableException) {
            resultBean.setResult("请求参数解析失败：" + ex.toString());
        }
        return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 运行时自定义的业务异常，返回的msg可用于前端展示
     * 返回状态码: 510,前端可根据是510判断是业务异常
     */
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ResultBean> businessException(HttpServletRequest request, BusinessException ex) {
        this.consoleLog(request, ex);
        ResultBean resultBean = ResultBean.fail(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(resultBean, HttpStatus.NOT_EXTENDED);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ResultBean> dataIntegrityViolationException(HttpServletRequest request, Exception ex) {
        this.consoleLog(request, ex);
        ResultBean<String> resultBean = ResultBean.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", "数据库执行操作异常：" + ex.toString());
        return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理其他运行时发生的异常
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResultBean> exception(HttpServletRequest request, Exception ex) {
        this.consoleLog(request, ex);
        ResultBean<String> resultBean = ResultBean.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", ex.toString());
        return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private <T extends Throwable> void consoleLog(HttpServletRequest request, T ex) {
        logger.error("************************异常开始*******************************");
        logger.error("请求地址：" + request.getRequestURL());
        logger.error("请求参数");
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }
        ex.printStackTrace();
        logger.error("************************异常结束*******************************");
    }

}
