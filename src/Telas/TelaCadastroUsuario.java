package Telas;

import javax.swing.*;

import DAO.UsuarioDAO;
import Entity.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroUsuario extends JFrame {
    private UsuarioDAO usuarioDAO;

    public TelaCadastroUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        setTitle("Cadastro de Usuário - Rede Social");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(20);
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        JButton btnCadastrar = new JButton("Cadastrar");

        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(btnCadastrar);

         btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());

                // Verifica se o email já está cadastrado
                if (usuarioDAO.buscarPorEmail(email) != null) {
                    JOptionPane.showMessageDialog(null, "Email já cadastrado.");
                } else {
                    // Cria e adiciona novo usuário
                    Usuario novoUsuario = new Usuario(nome, email, senha);
                    usuarioDAO.adicionarUsuario(novoUsuario);
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    new TelaLogin(usuarioDAO).setVisible(true);
                    dispose();
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        new TelaCadastroUsuario(usuarioDAO);
    }   
}