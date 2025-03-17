package br.edu.iff.ccc.bsi.webdev.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
