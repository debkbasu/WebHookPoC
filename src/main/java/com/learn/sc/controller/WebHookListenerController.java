package com.learn.sc.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.sc.repo.GitDetails;
import com.learn.sc.service.ServiceImpl;
import com.learn.sc.vo.GitRequest;


@RestController
public class WebHookListenerController {
	
	@Autowired
	private ServiceImpl service;
	
	@PostMapping("/hitme")
	public String pushEvent(@RequestBody GitRequest request){
		System.out.println("Inside");
		System.out.println(request.getRepository().getHtml_url());
		
		String repoUrl = request.getRepository().getClone_url();
        String cloneDirectoryPath = "C:\\Users\\001AHY744\\Work\\gitdumps";
        
        GitDetails gitty = new GitDetails();
        gitty.setCloneUrl(repoUrl);
        gitty.setRepoUrl(request.getRepository().getHtml_url());
        gitty.setProcessed("N");
        
        
        service.saveRepoDetails(gitty);
        /*
		try { 
			System.out.println("Cloning repository from " + repoUrl + " to " + cloneDirectoryPath); 
			Git.cloneRepository() .setURI(repoUrl) .setDirectory(new File(cloneDirectoryPath)) .call();
			  System.out.println("Repository cloned successfully."); 
		} catch (GitAPIException e) { 
				e.printStackTrace(); 
		}
		*/
        
		
		return "Details saved successfully.";
	}
	
	@GetMapping("/generatereport")
	public String generate(){
		System.out.println("Called for generation");
		//Get the last entry from H2 DB
		Optional<GitDetails[]> gitties = service.fetchAllRepoDetailsWhichAreNotProcessed("N");
		GitDetails gitty = null;
        if(!gitties.isEmpty()) {
        	gitty = gitties.get()[0];
        }
		String cloneDirectoryPath = "C:\\Users\\001AHY744\\Work\\gitdumps";
        try { 
			System.out.println("Cloning repository from " + gitty.getRepoUrl() + " to " + cloneDirectoryPath); 
			Git.cloneRepository() .setURI(gitty.getCloneUrl()) .setDirectory(new File(cloneDirectoryPath)) .call();
			  System.out.println("Repository cloned successfully."); 
			Git.shutdown();  
		} catch (GitAPIException e) { 
				e.printStackTrace(); 
		}
		System.out.println("Repositories cloned and saved successfully from Git.");
		System.out.println("Scanning for .properties files");
		
		try {
			 List<Path> propertiesFiles = findPropertiesFiles("C:\\Users\\001AHY744\\Work\\gitdumps");
	            propertiesFiles.forEach(x -> sayHi(x));
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		
		return "Report generated successfully.";
	}

	public List<Path> findPropertiesFiles(String directory) throws IOException {
        List<Path> propertiesFiles = new ArrayList<>();
        Files.walk(Paths.get(directory))
             .filter(Files::isRegularFile)
             .filter(path -> path.toString().endsWith(".properties"))
             .forEach(propertiesFiles::add);
        return propertiesFiles;
    }
	
	public void sayHi(Path pathName) {
		System.out.println("Hi " + pathName.toString());
	}

}
