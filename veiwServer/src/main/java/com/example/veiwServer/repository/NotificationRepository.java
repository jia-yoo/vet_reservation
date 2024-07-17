package com.example.veiwServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
