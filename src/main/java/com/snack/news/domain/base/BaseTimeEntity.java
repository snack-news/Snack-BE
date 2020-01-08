package com.snack.news.domain.base;

import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
	private LocalDateTime createAt;
	@LastModifiedDate
	private LocalDateTime modifiedAt;

	protected void setCreateAt(LocalDateTime createAt) {
		this.createAt = Optional.ofNullable(createAt).orElse(LocalDateTime.now());
	}

}