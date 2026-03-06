package com.ti2cc.supermercado.dao;

import com.ti2cc.supermercado.infra.ConnectionFactory;
import com.ti2cc.supermercado.model.Produto;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public List<Produto> listarTodos() {
        String sql = "SELECT id, nome, marca, categoria, preco, estoque, ativo, criado_em FROM produto ORDER BY id";
        List<Produto> lista = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto p = mapear(rs);
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT id, nome, marca, categoria, preco, estoque, ativo, criado_em FROM produto WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer inserir(Produto p) {
        //  pegar o id gerado
        String sql = "INSERT INTO produto (nome, marca, categoria, preco, estoque, ativo) " +
                     "VALUES (?,?,?,?,?,?) RETURNING id";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getMarca());
            ps.setString(3, p.getCategoria());
            ps.setDouble(4, p.getPreco());
            ps.setInt(5, p.getEstoque());
            ps.setBoolean(6, p.isAtivo());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idGerado = rs.getInt("id");
                    p.setId(idGerado);
                    return idGerado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    public boolean atualizar(Produto p) {
        String sql = "UPDATE produto SET nome=?, marca=?, categoria=?, preco=?, estoque=?, ativo=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getMarca());
            ps.setString(3, p.getCategoria());
            ps.setDouble(4, p.getPreco());
            ps.setInt(5, p.getEstoque());
            ps.setBoolean(6, p.isAtivo());
            ps.setInt(7, p.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM produto WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Produto mapear(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setMarca(rs.getString("marca"));
        p.setCategoria(rs.getString("categoria"));
        p.setPreco(rs.getDouble("preco"));
        p.setEstoque(rs.getInt("estoque"));
        p.setAtivo(rs.getBoolean("ativo"));

        Timestamp ts = rs.getTimestamp("criado_em");
        if (ts != null) {
            p.setCriadoEm(ts.toLocalDateTime());
        } else {
            p.setCriadoEm((LocalDateTime) null);
        }
        return p;
    }
}

