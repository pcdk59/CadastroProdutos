import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import db.ProdutosDB;
import db.UsuariosDB;
import models.Admin;
import models.Cliente;
import models.Produto;
import models.Usuario;

public class Main {
    static ProdutosDB produtosDB = new ProdutosDB();
    static UsuariosDB usuariosDB = new UsuariosDB();

    public static void main(String[] args) throws Exception {
       System.out.println("--- PEDIDOS DE VENDAS ---");
        System.out.println(" ");

       int option;

       do {
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos cadastrados");
            System.out.println("3 - Cadastrar usuário ADMINISTRADOR");
            System.out.println("4 - Cadastrar usuário CLIENTE");
            System.out.println("5 - Listar todos os usuários");
            System.out.println("0 - Sair");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Escolha uma das opções: ");
            option = scanner.nextInt();

            process(option);
       } while (option != 0);
    }

    public static void process(int option) throws Exception {
        switch (option) {
            case 1: {
                Scanner scanner = new Scanner(System.in);

                System.out.println(" ");
                System.out.print("Descrição do produto: ");
                String descricao = scanner.nextLine();

                System.out.println(" ");
                System.out.print("Preço do produto: ");
                double preco = scanner.nextDouble();

                System.out.println(" ");
                System.out.print("Data de validade do produto: ");
                String dataString = scanner.next();

                Date dataValidade = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

                Produto novoProduto = new Produto(descricao, preco, dataValidade);

                produtosDB.addNovoProduto(novoProduto);

                break;
            }

            case 2: {
                List<Produto> listaDeProdutos = produtosDB.getProdutosList();

                for(Produto produto : listaDeProdutos) {
                    System.out.println("------------------------------------------------");
                    System.out.println("--- DESCRIÇÃO: " +produto.getDescricao());
                    System.out.println("--- ID: " +produto.getId());
                    System.out.println("--- Preço: " + produto.getPreco());
                    System.out.println("--- Data validade: " + produto.getDataValidade());
                    System.out.println("------------------------------------------------");
                    System.out.println(" ");
                }

                break;
            }

            case 3: {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Digite o nome do usuário ADMINISTRADOR: ");
                String nome = scanner.nextLine();

                Admin novoAdmin = new Admin(nome);
                usuariosDB.addNovoUsuario(novoAdmin);
                break;
            }

            case 4: {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Digite o nome do usuário CLIENTE: ");
                String nome = scanner.nextLine();

                Cliente novoCliente = new Cliente(nome);
                usuariosDB.addNovoUsuario(novoCliente);
                break;
            }

            case 5: {
                System.out.println("----------------------------------------------------------");
                System.out.println("--------------LISTANDO USUÁRIOS CADASTRADOS---------------");
                System.out.println("----------------------------------------------------------");
                for(Usuario usuario : usuariosDB.getUsuarioList()) {
                    System.out.println("ID: " +usuario.getId());
                    System.out.println("NOME: " +usuario.getNome());
                    System.out.println("TIPO: " +usuario.getTipoUsuario());
                    System.out.println("----------------------------------------------------------");
                    break;
                }
            }
        }
    }
}

