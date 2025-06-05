package app.controller;

import app.dao.*;
import app.model.*;
import java.time.LocalDate;

public class TransacaoController {
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();

    public void registrarTransacao(double valor, String descricao, String tipo, LocalDate data, Usuario usuario, Categoria categoria) {
        Transacao t = new Transacao();
        t.setValor(valor);
        t.setDescricao(descricao);
        t.setTipo(tipo);
        t.setData(data);
        t.setUsuario(usuario);
        t.setCategoria(categoria);
        transacaoDAO.salvar(t);
    }
}
