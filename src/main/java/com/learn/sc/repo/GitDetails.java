package com.learn.sc.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GitDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long entryId;
	
	
    private String repoUrl;
    private String cloneUrl;
    private String processed;
}
