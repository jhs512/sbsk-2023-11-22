package com.ll.app20231122.domain.post.post.repository;

import com.ll.app20231122.domain.member.member.entity.Member;
import com.ll.app20231122.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Member author);
}
