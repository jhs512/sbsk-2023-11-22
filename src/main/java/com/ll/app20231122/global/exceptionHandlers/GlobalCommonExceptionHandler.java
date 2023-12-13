package com.ll.app20231122.global.exceptionHandlers;

import com.ll.app20231122.global.exceptions.GlobalException;
import com.ll.app20231122.global.rq.Rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class GlobalCommonExceptionHandler {
    private final Rq rq;

    @ExceptionHandler(GlobalException.class)
    public String handle(GlobalException ex) {
        return rq.historyBack(ex);
    }
}
