package com.github.arsengir.authorization.resolver;

import com.github.arsengir.authorization.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String user = nativeWebRequest.getParameter("user");
        String password = nativeWebRequest.getParameter("password");

        User userObj = new User(user, password);

        if (webDataBinderFactory != null) {
            WebDataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest, userObj, "user");
            binder.validate();
            BindingResult bindingResult = binder.getBindingResult();
            if (bindingResult.getErrorCount() > 0) {
                throw new MethodArgumentNotValidException(methodParameter, bindingResult);
            }
        }
        return userObj;
    }
}
