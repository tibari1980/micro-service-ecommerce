package com.arcesi.micoroserviceecommerce.dtos.responses;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
public class EntityAbstractResponse implements Serializable {

	private static final long serialVersionUID = 1L;

 	private Instant createdAt;
 	private Instant updatedAt;
 	private Boolean isActive;
}
