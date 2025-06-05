package app.view;

import app.controller.TransacaoController;
import app.dao.CategoriaDAO;
import app.model.Categoria;
import app.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

class TelaTransacao extends JFrame {
    private final Usuario usuario;
    private final TransacaoController transacaoController = new TransacaoController();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public TelaTransacao(Usuario usuario) {
        super("Registrar Transação");
        this.usuario = usuario;

        JTextField campoValor = new JTextField();
        JTextField campoDescricao = new JTextField();
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Receita", "Despesa"});
        JComboBox<Categoria> comboCategoria = new JComboBox<>();

        // Componente para escolher a data
        JSpinner spinnerData = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy");
        spinnerData.setEditor(dateEditor);

        JButton btnSalvar = new JButton("Salvar");

        List<Categoria> categorias = categoriaDAO.listarPorUsuario(usuario);
        categorias.forEach(comboCategoria::addItem);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Valor:"));
        panel.add(campoValor);
        panel.add(new JLabel("Descrição:"));
        panel.add(campoDescricao);
        panel.add(new JLabel("Tipo:"));
        panel.add(comboTipo);
        panel.add(new JLabel("Categoria:"));
        panel.add(comboCategoria);
        panel.add(new JLabel("Data:"));
        panel.add(spinnerData);
        panel.add(new JLabel());
        panel.add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            try {
                String valorStr = campoValor.getText().trim();
                String descricao = campoDescricao.getText().trim();

                if (valorStr.isEmpty() || descricao.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
                    return;
                }

                double valor = Double.parseDouble(valorStr);
                String tipo = comboTipo.getSelectedItem().toString().toLowerCase();
                Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
                Date dataSelecionada = (Date) spinnerData.getValue();
                LocalDate data = dataSelecionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                transacaoController.registrarTransacao(valor, descricao, tipo, data, usuario, categoria);
                JOptionPane.showMessageDialog(this, "Transação registrada com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Informe um valor numérico válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar transação: " + ex.getMessage());
            }
        });

        add(panel);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
