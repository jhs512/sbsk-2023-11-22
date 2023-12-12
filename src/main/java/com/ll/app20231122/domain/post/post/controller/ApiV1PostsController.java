package com.ll.app20231122.domain.post.post.controller;

import com.ll.app20231122.domain.member.member.entity.Member;
import com.ll.app20231122.domain.post.post.dto.PostDto;
import com.ll.app20231122.domain.post.post.entity.Post;
import com.ll.app20231122.domain.post.post.service.PostService;
import com.ll.app20231122.global.rq.Rq.Rq;
import com.ll.app20231122.global.rsData.RsData;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/posts", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "ApiV1ArticlesController", description = "게시물 CRUD 컨트롤러")
public class ApiV1PostsController {
    private final Rq rq;
    private final PostService postService;

    @Getter
    public static class GetMineResponseBody {
        private final List<PostDto> items;

        public GetMineResponseBody(List<Post> posts) {
            this.items = posts
                    .stream()
                    .map(PostDto::new)
                    .toList();
        }
    }

    @GetMapping("/mine")
    public RsData<GetMineResponseBody> getMine() {
        Member member = rq.getMember();

        List<Post> posts = postService.findByAuthor(member);

        return RsData.of(
                "200",
                "내 글 가져오기 성공",
                new GetMineResponseBody(posts)
        );
    }
}
