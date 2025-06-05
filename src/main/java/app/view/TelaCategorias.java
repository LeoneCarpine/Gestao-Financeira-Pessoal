package app.view;


import app.dao.CategoriaDAO;
import app.model.Categoria;
import app.model.Usuario;

import javax.swing.*;
import java.awt.*;


class TelaCategorias extends JFrame {
    private final Usuario usuario;
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final DefaultListModel<Categoria> modeloLista = new DefaultListModel<>();
    private final JList<Categoria> listaCategorias = new JList<>(modeloLista);

    public TelaCategorias(Usuario usuario) {
        super("Gerenciar Categorias");
        this.usuario = usuario;

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        JPanel botoes = new JPanel();
        botoes.add(btnAdicionar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);

        add(new JScrollPane(listaCategorias), BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        carregarCategorias();

        btnAdicionar.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nova categoria:");
            if (nome != null && !nome.isEmpty()) {
                Categoria c = new Categoria();
                c.setNome(nome);
                c.setUsuario(usuario);
                categoriaDAO.salvar(c);
                carregarCategorias();
            }
        });

        btnEditar.addActionListener(e -> {
            Categoria selecionada = listaCategorias.getSelectedValue();
            if (selecionada != null) {
                String novoNome = JOptionPane.showInputDialog("Editar categoria:", selecionada.getNome());
                if (novoNome != null && !novoNome.isEmpty()) {
                    selecionada.setNome(novoNome);
                    categoriaDAO.atualizar(selecionada);
                    carregarCategorias();
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            Categoria selecionada = listaCategorias.getSelectedValue();
            if (selecionada != null && JOptionPane.showConfirmDialog(this, "Deseja excluir esta categoria?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                categoriaDAO.remover(selecionada);
                carregarCategorias();
            }
        });

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void carregarCategorias() {
        modeloLista.clear();
        categoriaDAO.listarPorUsuario(usuario).forEach(modeloLista::addElement);
    }
}
