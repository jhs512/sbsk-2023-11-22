package com.ll.app20231122.global.exceptions;

import com.ll.app20231122.global.rsData.RsData;
import com.ll.app20231122.standard.Empty;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final RsData<Empty> rsData;

    public GlobalException(String resultCode, String msg) {
        super("resultCode=" + resultCode + ",msg=" + msg);
        this.rsData = RsData.of(resultCode, msg);
    }
}
