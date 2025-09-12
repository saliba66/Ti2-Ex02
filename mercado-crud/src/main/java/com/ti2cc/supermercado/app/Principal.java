package com.ti2cc.supermercado.app;

import com.ti2cc.supermercado.dao.ProdutoDAO;
import com.ti2cc.supermercado.model.Produto;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final Scanner in = new Scanner(System.in);
    private static final ProdutoDAO dao = new ProdutoDAO();

    public static void main(String[] args) {
        int op;
        do {
            menu();
            op = lerInt("Opção: ");
            switch (op) {
                case 1:
                    listar();
                    break;
                case 2:
                    inserir();
                    break;
                case 3:
                    atualizar();
                    break;
                case 4:
                    excluir();
                    break;
                case 5:
                    buscarPorId();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 0);

        in.close();
    }

    private static void menu() {
        System.out.println();
        System.out.println("====== MENU PRODUTOS ======");
        System.out.println("1) Listar");
        System.out.println("2) Inserir");
        System.out.println("3) Atualizar");
        System.out.println("4) Excluir");
        System.out.println("5) Buscar por ID");
        System.out.println("0) Sair");
    }

    private static void listar() {
        List<Produto> lista = dao.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void inserir() {
        String nome = lerStr("Nome: ");
        String marca = lerStr("Marca: ");
        String cat = lerStr("Categoria: ");
        double preco = lerDouble("Preço: ");
        int estoque = lerInt("Estoque: ");
        boolean ativo = lerBool("Ativo? (true/false): ");

        Produto p = new Produto(nome, marca, cat, preco, estoque, ativo);
        Integer id = dao.inserir(p);
        System.out.println(id != null ? "Inserido com ID " + id : "Falha ao inserir.");
    }

    private static void atualizar() {
        int id = lerInt("ID do produto a atualizar: ");
        Produto existente = dao.buscarPorId(id);
        if (existente == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        String nome = lerStr("Nome (" + existente.getNome() + "): ");
        String marca = lerStr("Marca (" + existente.getMarca() + "): ");
        String cat = lerStr("Categoria (" + existente.getCategoria() + "): ");
        double preco = lerDouble("Preço (" + existente.getPreco() + "): ");
        int estoque = lerInt("Estoque (" + existente.getEstoque() + "): ");
        boolean ativo = lerBool("Ativo (" + existente.isAtivo() + "): ");

        existente.setNome(nome);
        existente.setMarca(marca);
        existente.setCategoria(cat);
        existente.setPreco(preco);
        existente.setEstoque(estoque);
        existente.setAtivo(ativo);

        boolean ok = dao.atualizar(existente);
        System.out.println(ok ? "Atualizado." : "Falha ao atualizar.");
    }

    private static void excluir() {
        int id = lerInt("ID do produto a excluir: ");
        boolean ok = dao.excluir(id);
        System.out.println(ok ? "Excluído." : "Falha ao excluir.");
    }

    private static void buscarPorId() {
        int id = lerInt("ID: ");
        Produto p = dao.buscarPorId(id);
        System.out.println(p != null ? p : "Não encontrado.");
    }

    // utilitários
    private static String lerStr(String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }

    private static int lerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um inteiro válido.");
            }
        }
    }

    private static double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private static boolean lerBool(String msg) {
        while (true) {
            System.out.print(msg);
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("true") || s.equals("t") || s.equals("1")) return true;
            if (s.equals("false") || s.equals("f") || s.equals("0")) return false;
            System.out.println("Digite true/false.");
        }
    }
}
