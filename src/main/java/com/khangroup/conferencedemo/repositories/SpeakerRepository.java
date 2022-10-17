package com.khangroup.conferencedemo.repositories;

import com.khangroup.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
