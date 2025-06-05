package app.view;

import app.dao.TransacaoDAO;
import app.model.Transacao;
import app.model.Usuario;

import javax.swing.*;
import java.awt.*;

class TelaResumoFinanceiro extends JFrame {
    private final Usuario usuario;
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();

    public TelaResumoFinanceiro(Usuario usuario) {
        super("Resumo Financeiro");
        this.usuario = usuario;

        double receitas = 0;
        double despesas = 0;

        for (Transacao t : transacaoDAO.listarPorUsuario(usuario)) {
            if (t.getTipo().equals("receita")) receitas += t.getValor();
            else despesas += t.getValor();
        }

        double saldo = receitas - despesas;

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.add(new JLabel("Total de Receitas: R$ " + receitas));
        panel.add(new JLabel("Total de Despesas: R$ " + despesas));
        panel.add(new JLabel("Saldo Atual: R$ " + saldo));

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        panel.add(btnFechar);

        add(panel);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

