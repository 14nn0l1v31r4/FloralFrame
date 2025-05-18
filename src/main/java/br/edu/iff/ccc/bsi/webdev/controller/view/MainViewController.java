	package br.edu.iff.ccc.bsi.webdev.controller.view;
	
	import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.entities.Community;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.Reply;
import br.edu.iff.ccc.bsi.webdev.entities.Report;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.enums.CategoryPost;
import br.edu.iff.ccc.bsi.webdev.services.CommentService;
import br.edu.iff.ccc.bsi.webdev.services.CommunityService;
import br.edu.iff.ccc.bsi.webdev.services.PostService;
import br.edu.iff.ccc.bsi.webdev.services.ReplyService;
import br.edu.iff.ccc.bsi.webdev.services.ReportService;
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
		
		@Autowired
		private ReplyService replyService;
		
		@Autowired
		private ReportService reportService;
	
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
		    model.addAttribute("userComum", usuario);   // <- precisa existir
		    model.addAttribute("posts", postService.findAll());
		    return "dashboard";
		}

	
		
		@GetMapping("/post")
		public String mostrarFormulario(Model model, HttpSession session) {
			UserComum usuario = (UserComum) session.getAttribute("usuarioLogado");
			 if (usuario == null) {
			        return "redirect:/home/login";
			    }
		
			    model.addAttribute("userComum", usuario);
		    model.addAttribute("post", new Post());
		    return "post"; 
		}
		
		@PostMapping("/post")
		public String createPost(@ModelAttribute("Post") Post post, Model model, HttpSession session) {
		    // Supondo que você tenha o id do usuário logado na sessão
		    UserComum user = (UserComum) session.getAttribute("usuarioLogado");
		    if (user != null) {
		        post.setUserID(user.getId());
		        post.setDate(LocalDate.now());
		        post.setAuthor(user);
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
		
		
		@GetMapping("/report")
		public String showReporter(Model model) {
		    List<Report> allReports = reportService.findAll();

		    List<Report> postReports = new ArrayList<>();
		    List<Report> commentReports = new ArrayList<>();
		    List<Report> replyReports = new ArrayList<>();

		    for (Report r : allReports) {
		        if (r.getPost() != null) {
		            postReports.add(r);
		        }
		        if (r.getComment() != null) {
		            commentReports.add(r);
		        }
		        if (r.getReply() != null) {
		            replyReports.add(r);
		        }
		    }

		    model.addAttribute("postReports", postReports);
		    model.addAttribute("commentReports", commentReports);
		    model.addAttribute("replyReports", replyReports);


		    return "reporter";
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
		@GetMapping("/createcommunity")
		public String show(Model model) {
			model.addAttribute("community", new Community());
			return "communityform"; 
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
	
		    List<Post> posts = postService.findByUserId(usuario.getId());
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

		@PostMapping("/perfil/update")
		public String atualizarPerfil(@ModelAttribute("userComum") UserComum userAtualizado, HttpSession session) {
		    UserComum userLogado = (UserComum) session.getAttribute("usuarioLogado");

		    if (userLogado == null) {
		        return "redirect:/home/login";
		    }

		    // Atualiza apenas os campos que vieram do formulário
		    userLogado.setName(userAtualizado.getName());
		    userLogado.setEmail(userAtualizado.getEmail());
		    userLogado.setPhone(userAtualizado.getPhone());

		    userComumService.update(userLogado.getId(), userLogado);
		    session.setAttribute("usuarioLogado", userLogado); // Atualiza a sessão

		    return "redirect:/home/perfil";
		}

	
		@PostMapping("/comment/create")
		public String createComment(@RequestParam Long postId,
		                            @RequestParam String text,
		                            HttpSession session) {

		    UserComum user = (UserComum) session.getAttribute("usuarioLogado");

		    if (user == null) {
		        return "redirect:/home/login";
		    }

		    Post post = postService.findById(postId);

		    Comment comment = new Comment();
		    comment.setAuthor(user);
		    comment.setPost(post);
		    comment.setContent(text);
		    comment.setCreatedAt(LocalDate.now());

		    commentService.save(comment);

		    return "redirect:/home/dashboard";
		}
		
		@PostMapping("/comment/reply")
		public String createReply(@RequestParam Long commentId,
		                          @RequestParam String text,
		                          HttpSession session) {

		    UserComum user = (UserComum) session.getAttribute("usuarioLogado");

		    if (user == null) {
		        return "redirect:/home/login";
		    }

		    Comment comment = commentService.findById(commentId);
		    if (comment == null) {
		        // Pode lançar exceção, ou retornar um erro amigável
		        return "redirect:/home/dashboard";
		    }

		    Reply reply = new Reply();
		    reply.setAuthor(user);
		    reply.setComment(comment);
		    reply.setContent(text);
		    reply.setCreatedAt(LocalDate.now());

		    replyService.save(reply);

		    return "redirect:/home/dashboard";
		}
		
		@PostMapping("/post/report")
		public String createReport(@RequestParam Long postId, 
		                           @RequestParam String reason, 
		                           HttpSession session,
		                           RedirectAttributes redirectAttributes) {
		    UserComum user = (UserComum) session.getAttribute("usuarioLogado");
		    if (user == null) return "redirect:/home/login";

		    Post post = postService.findById(postId);
		    reportService.createReportPost(user, post, reason);

		    // Adiciona o flash attribute para aparecer depois do redirect
		    redirectAttributes.addFlashAttribute("reportSuccess", true);

		    return "redirect:/home/dashboard";
		}
		
		@PostMapping("/comment/report")
	    public String reportComment(@RequestParam Long commentId, @RequestParam String reason, HttpSession session) {
	        UserComum user = (UserComum) session.getAttribute("usuarioLogado");
	        if (user == null) return "redirect:/home/login";

	        Comment comment = commentService.findById(commentId);
	        Post post      = comment.getPost(); 
	        reportService.createReportComment(user, comment, reason, post);

	        return "redirect:/home/dashboard";
	    }

		@PostMapping("/reply/report")
	    public String reportReply(@RequestParam Long replyId, @RequestParam String reason, HttpSession session) {
	        UserComum user = (UserComum) session.getAttribute("usuarioLogado");
	        if (user == null) return "redirect:/home/login";

	        Reply reply = replyService.findById(replyId);
	        Post post = reply.getComment().getPost();
	        Comment comment = reply.getComment();
	        reportService.createReportReply(user, reply, reason, comment, post);

	        return "redirect:/home/dashboard";

	    }
		
		@PostMapping("/post/delete/{id}")
		public String deletePost(@PathVariable Long id) {
		    postService.deleteById(id);           // também removerá denúncias via ON DELETE CASCADE ou lógica própria
		    return "redirect:/home/report";
		}

		@PostMapping("/report/delete/{id}")
		public String deleteReport(@PathVariable Long id) {
		    reportService.deleteById(id);
		    return "redirect:/home/report";
		}
		
		// MainViewController.java
		@GetMapping("/community/{id}")
		public String showCommunityPosts(@PathVariable Long id,
		                                 Model model,
		                                 HttpSession session) {

		    UserComum user = (UserComum) session.getAttribute("usuarioLogado");
		    if (user == null) return "redirect:/home/login";

		    // 1) pega comunidade
		    Community community = communityService.findById(id);
		    if (community == null) return "redirect:/home/communities";

		    // 2) converte id → enum (mesmo valor)
		    CategoryPost category = CategoryPost.porCodigo(id.intValue());

		    // 3) busca posts dessa categoria
		    List<Post> posts = postService.findByCategory(category);

		    // 4) envia para o template
		    model.addAttribute("community", community);
		    model.addAttribute("posts", posts);
		    model.addAttribute("selectedCategory", category);

		    return "communityposts";   // novo HTML
		}



	}
