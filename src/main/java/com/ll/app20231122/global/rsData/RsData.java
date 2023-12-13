package com.ll.app20231122.global.rsData;

import com.ll.app20231122.standard.Empty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class RsData<T> {
    @NonNull
    private String resultCode;
    @NonNull
    private int statusCode;
    @NonNull
    private String msg;
    @NonNull
    private T data;

    public static <T> RsData<T> of(String resultCode, String msg) {
        return of(resultCode, msg, (T) new Empty());
    }

    public static <T> RsData<T> of(String resultCode, String msg, T data) {
        int statusCode = Integer.parseInt(resultCode.split("-", 2)[0]);

        return RsData.<T>builder()
                .resultCode(resultCode)
                .statusCode(statusCode)
                .msg(msg)
                .data(data)
                .build();
    }

    @NonNull
    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 400;
    }

    @NonNull
    public boolean isFail() {
        return !isSuccess();
    }
}
