package org.bank.repository;

import org.bank.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, String> {
}
