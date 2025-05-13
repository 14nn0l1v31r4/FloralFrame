	package br.edu.iff.ccc.bsi.webdev.controller.view;
	
	import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.entities.Community;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.CommentService;
import br.edu.iff.ccc.bsi.webdev.services.CommunityService;
import br.edu.iff.ccc.bsi.webdev.services.PostService;
import br.edu.iff.ccc.bsi.webdev.services.UserComumService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
	
	@Controller
	@RequestMapping(path= "/home")
	public class MainViewController {
		
		@Autowired
		private UserComumService userComumService;
		
		
		@Autowired
		private PostService postService;
		
		@Autowired
		private CommunityService communityService;
		
		@Autowired
		private CommentService commentService;
	
		@GetMapping("/")
		public String gethome() {
			return "index.html";
		}
		
		
		@GetMapping("/dashboard")
		public String showDasboard(Model model, HttpSession session) {
		    UserComum usuario = (UserComum) session.getAttribute("usuarioLogado");
		    if (usuario == null) {
		        return "redirect:/home/login";
		    }
	
		    model.addAttribute("userComum", usuario);
		    model.addAttribute("posts", postService.findAll()); // pega todos os posts
		    return "dashboard"; 
		}
	
		
		@GetMapping("/post")
		public String mostrarFormulario(Model model) {
		    model.addAttribute("post", new Post());
		    return "post"; 
		}
		
		@PostMapping("/post")
		public String createPost(@ModelAttribute("Post") Post post, Model model, HttpSession session) {
		    // Supondo que você tenha o id do usuário logado na sessão
		    UserComum user = (UserComum) session.getAttribute("usuarioLogado");
		    if (user != null) {
		        post.setUser(user.getUser());
		        postService.save(post); // Salve o post com o serviço correspondente
		        return "redirect:/home/dashboard"; // redireciona após salvar o post
		    }
		    model.addAttribute("erroPost", "Usuário não logado.");
		    return "login"; // Volta para a página de login caso o usuário não esteja logado
		}
	
	
		
		@GetMapping("/login")
		public String showLogin(Model model) {
		    model.addAttribute("UserComum", new UserComum());
		    return "login"; 
		}
		
		 @PostMapping("/login")
		    public String login(@ModelAttribute("UserComum") UserComum userForm, Model model, HttpSession session) {
		        UserComum user = userComumService.findByEmail(userForm.getEmail());
	
		        if (user != null && user.getPassword().equals(userForm.getPassword())) {
		            // Login bem-sucedido
		            session.setAttribute("usuarioLogado", user); // cria a sessão
		            return "redirect:/home/dashboard"; // ou qualquer página após login
		        }
	
		        // Falha no login
		        model.addAttribute("erroLogin", "Email ou senha inválidos");
		        return "login"; // volta pra tela de login
		    }
	
		
		@GetMapping("/register")
		public String showRegisterPage(Model model) {
		    model.addAttribute("userComum", new UserComum());
		    return "register"; 
		}
		
		@PostMapping("/register")
		public String save(@Valid @ModelAttribute("userComum") UserComum userComum, BindingResult result, Model model) {
		    if (result.hasErrors()) {
		        model.addAttribute("userComum", userComum);
		        return "register";
		    }
		    userComumService.save(userComum); 
		    return "redirect:/home/login"; // redirecionar para login pode ser mais intuitivo
		}

		
		@GetMapping("/perfil")
		public String showPerfil(Model model, HttpSession session) {
		    UserComum usuario = (UserComum) session.getAttribute("usuarioLogado");
	
		    if (usuario == null) {
		        return "redirect:/home/login";
		    }
	
		    List<Post> posts = postService.findByUser(usuario);
		    model.addAttribute("userComum", usuario);
		    model.addAttribute("posts", posts);
		    return "perfil";
		}
	

		@GetMapping("/communities")
		public String showCommunities(Model model, HttpSession session) {
		    UserComum usuario = (UserComum) session.getAttribute("usuarioLogado");
		    if (usuario == null) {
		        return "redirect:/home/login";
		    }

		    List<Community> communities = communityService.findAll();
		    model.addAttribute("communities", communities);
		    return "communities"; // deve corresponder ao nome do seu HTML
		}

			@PostMapping("/comment")
			public String addComment(@RequestParam Long postId, @RequestParam String content, HttpSession session) {
			    UserComum user = (UserComum) session.getAttribute("usuarioLogado");
			    Post post = postService.findById(postId); // certifique-se que esse método existe
			    
			    if (user != null && post != null) {
			        Comment comment = new Comment();
			        comment.setContent(content);
			        comment.setPost(post);
			        comment.setCreatedAt(LocalDate.now());
			        comment.setUserIdComment(user.getUser()); // ou o objeto UserComum, se preferir
			        commentService.save(comment);
			    }
			    return "redirect:/home/dashboard";
			}

	
	}
