package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import entidades.Caixa;
import entidades.Gerente;
import entidades.ItemVenda;
import entidades.Venda;
import enumerados.StatusVenda;
import enumerados.TipoPagamento;
import interfaces.MasterCard;
import interfaces.Visa;

public class Programa {

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat sdfh = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm:ss");
    static int matricula = 1;
    static String nome = "";
    static double salario = 0.0;
    static double proventos = 0.0;
    static double descontos = 0.0;
    static double comissao = 0.0;
    static String log = "";

    public static void main(String[] args) throws ParseException {
        int op = 0;
        do {
            op = menu();
            if (op < 1 || op > 5) {
                JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
            if (op == 1) {
                log(">>> Acessou a opção 1 (Realizar Venda)");
                StatusVenda statusVenda = StatusVenda.valueOf("INICIANDO");
                log("ESTADO >> INICIANDO VENDA");
                int numeroVenda = 0;
                numeroVenda = Integer.parseInt(JOptionPane.showInputDialog("Número da venda:"));
                Date data = sdf.parse(JOptionPane.showInputDialog("Data da venda:"));

                TipoPagamento tipoPagamento;
                int tipo = Integer.parseInt(JOptionPane.showInputDialog("Tipo Pagamento\n"
                        + "\n(1) DINHEIRO"
                        + "\n(2) À VISTA"
                        + "\n(3) CRÉDITO"
                        + "\n(4) DÉBITO"
                        + "\n(5) CHEQUE"));
                while (true) {
                    if (tipo == 1) {
                        tipoPagamento = TipoPagamento.valueOf("DINHEIRO");
                        break;
                    } else if (tipo == 2) {
                        tipoPagamento = TipoPagamento.valueOf("VISTA");
                        break;
                    } else if (tipo == 3) {
                        tipoPagamento = TipoPagamento.valueOf("CREDITO");
                        break;
                    } else if (tipo == 4) {
                        tipoPagamento = TipoPagamento.valueOf("DEBITO");
                        break;
                    } else if (tipo == 5) {
                        tipoPagamento = TipoPagamento.valueOf("CHEQUE");
                        break;
                    } else {
                        System.out.println("Tipo Inválido.");
                        continue;
                    }
                }
                log("Informou tipo de pagamento " + tipoPagamento);

                Venda venda = new Venda(numeroVenda, data, statusVenda, tipoPagamento);

                int numeroItem = 1;
                while (true) {
                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe os itens da venda:"
                            + "\n\nProduto número: " + numeroItem
                            + "\nQuantidade (-1 finaliza):"));

                    if (quantidade == -1) {
                        break;
                    }

                    double precoUnitario = Double.parseDouble(JOptionPane.showInputDialog("Preço unitário: "));

                    String nome = JOptionPane.showInputDialog("Nome do produto: ");
                    
                    ItemVenda itemVenda = new ItemVenda(numeroItem, nome, quantidade, precoUnitario);
                    venda.adicionarItem(itemVenda);

                    statusVenda = StatusVenda.valueOf("PROCESSANDO");
                    venda.setStatusVenda(statusVenda);
                    log("ESTADO >> PROCESSANDO VENDA");

                    numeroItem++;
                }

                JOptionPane.showMessageDialog(null, venda.toString(), "Dados da Venda", 1);
                statusVenda = StatusVenda.valueOf("IMPRIMINDO");
                venda.setStatusVenda(statusVenda);
                log("ESTADO >> IMPRIMINDO A VENDA");
                System.out.println(venda.toString());
                statusVenda = StatusVenda.valueOf("FINALIZANDO");
                log("ESTADO >> FINALIZANDO A VENDA");
                venda.setStatusVenda(statusVenda);
            }
            if (op == 2) {
                log(">>> Acessou a opção 2 (Mostrar Salários dos funcionários)");
                
                matricula = Integer.parseInt(JOptionPane.showInputDialog("Informe a matrícula do Caixa:"));
                nome = JOptionPane.showInputDialog("Informe o nome do Caixa:");
                salario = Double.parseDouble(JOptionPane.showInputDialog("Informe o salário base do Caixa:"));

                Caixa cx = new Caixa(matricula, nome, salario);

                proventos = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor dos Proventos para o Caixa:"));
                descontos = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor dos Descontos para o Caixa:"));

                JOptionPane.showMessageDialog(null, cx.toString() + String.format("\nSalário final: R$ %.2f", cx.calcularSalario(proventos, descontos)), "Informações do Caixa", 1);
                log("Mostrou dados do salário do Caixa " + nome);

                matricula = Integer.parseInt(JOptionPane.showInputDialog("Informe a matrícula do Gerente:"));
                nome = JOptionPane.showInputDialog("Informe o nome do Gerente:");
                salario = Double.parseDouble(JOptionPane.showInputDialog("Informe o salário base do Gerente:"));

                Gerente ge = new Gerente(matricula, nome, salario);

                proventos = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor dos Proventos para o Gerente:"));
                descontos = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor dos Descontos para o Gerente:"));
                comissao = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor da Comissão para o Gerente:"));

                JOptionPane.showMessageDialog(null, ge.toString() + String.format("\nSalário final: R$ %.2f", ge.calcularSalario(proventos, comissao, descontos)), "Informações do Gerente", 1);
                log("Mostrou dados do salário do Gerente " + nome);
            }
            if (op == 3) {
                log(">>> Acessou a opção 3 (Verificar Bandeira do Cartão)");
                while (true) {
                    char opcao;
                    opcao = JOptionPane.showInputDialog("Informe o tipo de cartão: (v) Visa, (m) MasterCard, (f) Finalizar:").toLowerCase().charAt(0);
                    if (opcao == 'v') {
                        Visa vi = new Visa();
                        vi.setSetor(Integer.parseInt(JOptionPane.showInputDialog("Informe o setor para o cartão VISA: ")));
                        JOptionPane.showMessageDialog(null, vi.verificarBandeira(vi.getSetor()));
                        log("Verificou cartão VISA");
                    } else if (opcao == 'm') {
                        MasterCard ma = new MasterCard();
                        ma.setSetor(Integer.parseInt(JOptionPane.showInputDialog("Informe o setor para o cartão MasterCard: ")));
                        JOptionPane.showMessageDialog(null, ma.verificarBandeira(ma.getSetor()));
                        log("Verificou cartão MasterCard");
                    } else {
                        break;
                    }
                }
            }
            if (op == 4) {
                log(">>> Acessou a opção 4 (Informações sobre o Programa)");
                JOptionPane.showMessageDialog(null, sobre(), "Informações", 1);
            }

        } while (op != 5);
        JOptionPane.showMessageDialog(null, "*** PROGRAMA FINALIZADO ***", "Opção 5", 1);
        log("Finalizou Programa.");
        JOptionPane.showMessageDialog(null, log, "log", 1);
    }

    private static int menu() {
        String menu = "\n** MENU DE OPÇÕES **\nDigite o número da opção escolhida:\n"
                + "\n1 - Realizar Venda, emitir cupom fical "
                + "\n2 - Mostrar salários dos funcionários"
                + "\n3 - Verificar Bandeira do Cartão"
                + "\n4 - Sobre o Programa"
                + "\n5 - Sair";
        return Integer.parseInt(JOptionPane.showInputDialog(menu));
    }

    public static void tipoPagamento() {
    }

    //@Override
    public static String sobre() {
        String sobre = "                                                               SOBRE O PROGRAMA\n\n" +
"        BYTE BUG LTDA                                                                                   \n" +
"        CLIENTE SUPERMERCADOS BIG                                                                       \n" +
"        VERSÃO 1.0                                                                                      \n" +
"        GERENTE DE PROJETOS: Ana Thereza Oliveira Vasconcellos Motta - 321118508                        \n" +
"        ANALISTA DE SISTEMAS: Esther Gonçalves de Paula - 32114144                                      \n" +
"        PROGRAMADORES: Francielle Lorrane Silva Santos - 321125778 e Lucas Candelário Santos - 321118473\n" +
"        TESTES: Maria Vitória Bezerra de Vasconcelos - 32115032                                         \n";
        return sobre;
    }

    private static void log(String texto) {
        Date date = java.util.Calendar.getInstance().getTime();
        log = log + (sdfh.format(date) + " : " + texto + "\n");
    }

}
