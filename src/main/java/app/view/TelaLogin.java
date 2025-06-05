package app.view;

import app.controller.UsuarioController;
import app.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton btnLogin;
    private JButton btnCadastrar;
    private UsuarioController usuarioController;

    public TelaLogin() {
        super("Login - Gestão Financeira");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usuarioController = new UsuarioController();

        campoEmail = new JTextField(20);
        campoSenha = new JPasswordField(20);
        btnLogin = new JButton("Entrar");
        btnCadastrar = new JButton("Cadastrar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Email:"));
        panel.add(campoEmail);
        panel.add(new JLabel("Senha:"));
        panel.add(campoSenha);
        panel.add(btnLogin);
        panel.add(btnCadastrar);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText();
                String senha = new String(campoSenha.getPassword());

                try {
                    Usuario usuario = usuarioController.login(email, senha);
                    JOptionPane.showMessageDialog(null, "Bem-vindo, " + usuario.getNome());
                    new TelaPrincipal(usuario).setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Login inválido: " + ex.getMessage());
                }
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText();
                String senha = new String(campoSenha.getPassword());
                String nome = JOptionPane.showInputDialog("Digite seu nome:");

                usuarioController.cadastrar(nome, email, senha);
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}