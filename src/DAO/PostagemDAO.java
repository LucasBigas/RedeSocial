package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Postagem;

public class PostagemDAO {
    private List<Postagem> postagens;
    public PostagemDAO() {
        postagens = new ArrayList<>();
    }

    public void adicionarPostagem(Postagem postagem) {
        postagens.add(postagem);
    }

    public List<Postagem> listarPostagens() {
        return postagens;
    }
}
