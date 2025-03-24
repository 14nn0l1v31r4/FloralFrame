package br.edu.iff.ccc.bsi.webdev.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.webdev.entities.Community;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.repository.CommunityRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepo;
	
	public List<UserComum> getList(){
		List<UserComum> communityMembers = new ArrayList<>();
		return communityMembers;
		
	}
	
	public Community findById(Long id){
		Community community = communityRepo.findById(id).orElseThrow(null);
		return community;
	}
	
	public Community save(Community community) {
        return communityRepo.save(community);
    }
	
	public List<Community> findByName(String name){
		List<Community> nameCommunitys = new ArrayList<>();
		return nameCommunitys;
	}
	
	public List<Community> findAll() {
        return communityRepo.findAll();
    }
	
	public Community update(Long id, Community communityDetails) {
        Community community = findById(id);
        community.setName(communityDetails.getName());
        return communityRepo.save(community);
    }
	
	public void deleteById(Long id) {
        if (!communityRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comunidade não encontrada para exclusão");
        }
        communityRepo.deleteById(id);
    }
	
}
