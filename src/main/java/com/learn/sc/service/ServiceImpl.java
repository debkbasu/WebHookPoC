package com.learn.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.learn.sc.repo.GitDetails;

@Service
public class ServiceImpl {
	
	@Autowired
    private RepositoryImpl repoImpl;

    // Save operation
    public GitDetails saveRepoDetails(GitDetails gitDet)
    {
        return repoImpl.save(gitDet);
    }
    
    // fetch operation
    public GitDetails fetchRepoDetails(String cloneURL)
    {
        return repoImpl.findByCloneUrl(cloneURL);
    }
    
    // fetch all operation
    public Optional<GitDetails[]> fetchAllRepoDetailsWhichAreNotProcessed( String processedIndicator)
    {
        return repoImpl.findByProcessed(processedIndicator);
    }

}
