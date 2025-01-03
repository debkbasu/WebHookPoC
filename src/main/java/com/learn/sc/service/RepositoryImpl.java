package com.learn.sc.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.sc.repo.GitDetails;

@Repository
public interface RepositoryImpl extends CrudRepository<GitDetails, Long> {

	GitDetails findByCloneUrl(String cloneURL);
	Optional<GitDetails[]> findByProcessed(String processIndicator);
}

