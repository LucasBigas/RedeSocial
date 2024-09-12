package Telas;

import javax.swing.*;

import DAO.PostagemDAO;
import Entity.Postagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    private JTextArea areaPostagens;
    private JTextField txtPostagem;
    private PostagemDAO postagemDAO;

    public TelaPrincipal() {
        postagemDAO = new PostagemDAO();

        setTitle("Rede Social - Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Área para mostrar postagens
        areaPostagens = new JTextArea(10, 40);
        areaPostagens.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaPostagens);
        panel.add(scrollPane);

        // Campo para adicionar nova postagem
        txtPostagem = new JTextField(30);
        panel.add(txtPostagem);

        // Botão de Postar
        JButton btnPostar = new JButton("Postar");
        panel.add(btnPostar);

        btnPostar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String conteudo = txtPostagem.getText();
                if (!conteudo.isEmpty()) {
                    // Cria a postagem e adiciona ao DAO
                    Postagem postagem = new Postagem(conteudo, null); // Usuário atual seria passado aqui
                    postagemDAO.adicionarPostagem(postagem);
                    areaPostagens.append("Você: " + conteudo + "\n");
                    txtPostagem.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "A postagem não pode estar vazia.");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}