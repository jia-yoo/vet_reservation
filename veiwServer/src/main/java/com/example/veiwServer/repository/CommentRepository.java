package com.example.veiwServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}