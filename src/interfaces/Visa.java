
package interfaces;

public class Visa implements iBandeiraCartao {
    private int setor;
    
    public int getSetor(){
        return setor;
    }
    public void setSetor(int setor){
        this.setor = setor;
    }
    public String verificarBandeira(int setor){
        if (setor == 4)
            return "BANDEIRA CONFERE PARA VISA";
        else
            return "*** BANDEIRA INVÁLIDA PARA VISA ***";
    }
}
