package br.edu.iff.ccc.bsi.webdev.dto;

public class ReportRequestDTO {
    private Long userId;
    private Long postId;
    private String reason;

    public ReportRequestDTO(Long userId, Long postId, String reason) {
		this.userId = userId;
		this.postId = postId;
		this.reason = reason;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
