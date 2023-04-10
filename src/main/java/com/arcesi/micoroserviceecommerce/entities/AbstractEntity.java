package com.arcesi.micoroserviceecommerce.entities;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@ToString
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED_AT", nullable = true, insertable = true)
	private Instant createdAt;
	@Column(name = "UPDATED_AT", nullable = true, insertable = true)
	private Instant updatedAt;
	@Column(name = "IS_ACTIVE", nullable = true, insertable = true)
	private Boolean isActive;
}
