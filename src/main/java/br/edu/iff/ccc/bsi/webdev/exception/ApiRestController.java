package br.edu.iff.ccc.bsi.webdev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiRestController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
    ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        errorInfo.setTitle("Usuário não encontrado");
        return errorInfo;
    }

    @ExceptionHandler(CommentNotFoundException.class)
    ProblemDetail handleCommentNotFoundException(CommentNotFoundException e) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        errorInfo.setTitle("Comentário não encontrado");
        return errorInfo;
    }

    @ExceptionHandler(CommunityNotFoundException.class)
    ProblemDetail handleCommunityNotFoundException(CommunityNotFoundException e) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        errorInfo.setTitle("Comunidade não encontrada");
        return errorInfo;
    }

    @ExceptionHandler(PostNotFoundException.class)
    ProblemDetail handlePostNotFoundException(PostNotFoundException e) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        errorInfo.setTitle("Post não encontrado");
        return errorInfo;
    }

    @ExceptionHandler(ReplyNotFoundException.class)
    ProblemDetail handleReplyNotFoundException(ReplyNotFoundException e) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        errorInfo.setTitle("Resposta não encontrada");
        return errorInfo;
    }

    @ExceptionHandler(ReportNotFoundException.class)
    ProblemDetail handleReportNotFoundException(ReportNotFoundException e) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        errorInfo.setTitle("Denúncia não encontrada");
        return errorInfo;
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleDefaultException(Exception e) {
        ProblemDetail errorInfo = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        errorInfo.setTitle("Erro interno do servidor");
        return errorInfo;
    }


}
