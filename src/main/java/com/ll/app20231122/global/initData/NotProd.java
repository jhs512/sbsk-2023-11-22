package com.ll.app20231122.global.initData;

import com.ll.app20231122.domain.member.member.entity.Member;
import com.ll.app20231122.domain.member.member.service.MemberService;
import com.ll.app20231122.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;
    private final PostService postService;

    @Bean
    ApplicationRunner initNotProd() {
        return args -> {
            Member member1 = memberService.join("admin", "1234").getData();
            Member member2 = memberService.join("user1", "1234").getData();
            Member member3 = memberService.join("user2", "1234").getData();

            postService.write(member1, "제목1", "내용1");
            postService.write(member1, "제목2", "내용2");
            postService.write(member1, "제목3", "내용3");
            postService.write(member2, "제목4", "내용4");
            postService.write(member2, "제목5", "내용5");
            postService.write(member3, "제목6", "내용6");
        };
    }
}
