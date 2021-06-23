
package entidades;

public class ItemVenda {
    private Integer numero;
    private String nome;
    private Integer quantidade;
    private Double precoUnitario;
    
    public ItemVenda(){
    }

    public ItemVenda(Integer numero, String nome, Integer quantidade, Double precoUnitario) {
        super();
        this.numero = numero;
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numeroItem) {
        this.numero = numeroItem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeItem) {
        this.nome = nomeItem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    public double subTotal(){
        return (precoUnitario * quantidade);
    }
    
    @Override
    public String toString(){
        return numero +" "+nome+" R$ "+
                String.format("%.2f", precoUnitario) +
                " x " + quantidade + " = R$ "+String.format("%.2f", subTotal());
    }
}
