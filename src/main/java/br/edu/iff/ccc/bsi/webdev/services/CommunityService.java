package br.edu.iff.ccc.bsi.webdev.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.webdev.entities.Community;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.exception.CommunityNotFoundException;
import br.edu.iff.ccc.bsi.webdev.repository.CommunityRepository;
import jakarta.annotation.PostConstruct;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepo;
	
	public List<UserComum> getList(){
		List<UserComum> communityMembers = new ArrayList<>();
		return communityMembers;
		
	}
	
	public Community findById(Long id){
		Community community = communityRepo.findById(id).orElseThrow( () -> new CommunityNotFoundException(id));
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
            throw new CommunityNotFoundException(id);
        }
        communityRepo.deleteById(id);
    }
	
	@PostConstruct
	public void init() {
	    if (communityRepo.count() == 0) {
	        Community c1 = new Community();
	        c1.setName("Suculentas Lovers");	
	        c1.setDescription("Comunidade para apaixonados pelas Suculentas.");
	        c1.setMembersQuantity(540);
	        communityRepo.save(c1);

	        Community c2 = new Community();
	        c2.setName("Frutíferas Lovers");
	        c2.setDescription("Tudo sobre plantas Frutíferas.");
	        c2.setMembersQuantity(320);
	        communityRepo.save(c2);
	    }
	}

	
}
