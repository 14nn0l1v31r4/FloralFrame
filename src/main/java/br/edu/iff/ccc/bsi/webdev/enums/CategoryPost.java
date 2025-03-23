package br.edu.iff.ccc.bsi.webdev.enums;

public enum CategoryPost {
	ORNAMENTAL(1, "Planta usada para fins decorativos."),
    FRUTIFERA(2, "Planta que produz frutos comestíveis."),
    MEDICINAL(3, "Planta utilizada para fins terapêuticos."),
    CARNIVORA(4, "Planta que captura e digere insetos."),
    SUCULENTA(5, "Planta que armazena água em suas partes."),
    AQUATICA(6, "Planta que vive em ambientes aquáticos."),
    TREPADEIRA(7, "Planta que cresce apoiada em estruturas."),
    NATIVA(8, "Planta nativa de um ecossistema específico.");

    private  int code;
    private String description;
    private int type;

    CategoryPost(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    

    public int getCodigo() {
        return code;
    }

    public String getDescricao() {
        return description;
    }

    public static CategoryPost porCodigo(int codigo) {
        for (CategoryPost c : values()) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

}
