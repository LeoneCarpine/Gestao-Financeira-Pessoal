package app.view;

import app.model.Usuario;

import javax.swing.*;
import java.awt.*;

class TelaPrincipal extends JFrame {
    private final Usuario usuario;

    public TelaPrincipal(Usuario usuario) {
        super("Painel - " + usuario.getNome());
        this.usuario = usuario;

        JButton btnTransacoes = new JButton("Registrar Transação");
        JButton btnResumo = new JButton("Resumo Financeiro");
        JButton btnCategorias = new JButton("Categorias");
        JButton btnSair = new JButton("Sair");

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        panel.add(btnTransacoes);
        panel.add(btnResumo);
        panel.add(btnCategorias);
        panel.add(btnSair);

        btnTransacoes.addActionListener(e -> new TelaTransacao(usuario).setVisible(true));
        btnResumo.addActionListener(e -> new TelaResumoFinanceiro(usuario).setVisible(true));
        btnCategorias.addActionListener(e -> new TelaCategorias(usuario).setVisible(true));
        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin().setVisible(true);
        });

        add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

