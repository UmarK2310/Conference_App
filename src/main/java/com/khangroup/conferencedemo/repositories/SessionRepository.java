package com.khangroup.conferencedemo.repositories;

import com.khangroup.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
