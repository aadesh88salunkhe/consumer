package com.ignite.consumer.interceptor;

import com.ignite.consumer.exception.ConsumerException;
import com.ignite.consumer.model.User;
import com.ignite.consumer.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.ignite.consumer.constants.Constants.TWEETS_URL;

@RequiredArgsConstructor
public class RequestProcessingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory
            .getLogger(RequestProcessingTimeInterceptor.class);

    final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        log(request, ":: Start Time=", System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        if (request.getMethod().equalsIgnoreCase("GET") && request.getRequestURI().equalsIgnoreCase(TWEETS_URL)) {
            Optional<User> user = userRepository.findUserByUsername(request.getHeader("username"));
            if (!user.isPresent())
                throw new ConsumerException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            if (!user.get().getUsername().equals(request.getHeader("username")))
                throw new ConsumerException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());

            if (!user.get().getPassword().equals(request.getHeader("password")))
                throw new ConsumerException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());

            if (!(user.get().getUsername().equals(request.getHeader("username")) && user.get().getPassword().equals(request.getHeader("password"))))
                throw new ConsumerException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());

            request.setAttribute("userId", user.get().getId());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("Request URL:: {} , Sent to Handler :: Current Time= {}" , request.getRequestURL().toString()
              , System.currentTimeMillis());
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        log(request, ":: End Time=", System.currentTimeMillis());
        log(request, ":: Time Taken=", (System.currentTimeMillis() - startTime));
    }

    private static void log(HttpServletRequest request, String text, long startTime) {
        logger.info("Request URL:: {} {} {}", request.getRequestURL().toString()
                , text , startTime);
    }
}
