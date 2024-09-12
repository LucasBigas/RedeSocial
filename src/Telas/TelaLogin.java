package Telas;

import javax.swing.*;

import DAO.UsuarioDAO;
import Entity.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    private UsuarioDAO usuarioDAO;

    public TelaLogin(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        setTitle("Login - Rede Social");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230)); // Cor de fundo azul claro
        panel.setLayout(new BorderLayout()); // Usando BorderLayout para organizar os componentes

        // Cabeçalho com título
        JLabel lblTitulo = new JLabel("Bem-vindo à Rede Social", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 102, 204)); // Azul escuro
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Painel central para login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Layout Grid com espaçamento
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens ao redor
        loginPanel.setBackground(new Color(173, 216, 230)); // Mantém a cor de fundo

        // Campo de email
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        // Botões de Login e Cadastro
        JButton btnLogin = new JButton("Login");
        JButton btnCadastro = new JButton("Sign Up");

        // Personalizando botões
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));

        btnCadastro.setBackground(new Color(255, 165, 0)); // Cor laranja
        btnCadastro.setForeground(Color.WHITE);
        btnCadastro.setFont(new Font("Arial", Font.BOLD, 16));

        // Adicionando componentes ao painel
        loginPanel.add(lblEmail);
        loginPanel.add(txtEmail);
        loginPanel.add(lblSenha);
        loginPanel.add(txtSenha);

        // Painel para botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(173, 216, 230)); // Mesma cor de fundo
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCadastro);

        panel.add(loginPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Ação dos botões
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());
                Usuario usuario = usuarioDAO.buscarPorEmail(email);

                if (usuario != null && usuario.getSenha().equals(senha)) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo, " + usuario.getNome() + "!");
                    new TelaPrincipal().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
                }
            }
        });

        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastroUsuario(usuarioDAO).setVisible(true);
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        new TelaLogin(usuarioDAO);
    }
}

