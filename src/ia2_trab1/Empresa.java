package ia2_trab1;

import java.util.List;

/**
 *
 * @author Bruno
 */
public class Empresa {
    String nome;
    List<Registro> registro;
    Float probSubida, probDescida, probCompra, probVenda;

    public Empresa(String nome, List<Registro> registro) {
        this.nome = nome;
        this.registro = registro;
    }

    public void teste(){
        System.out.println("A EMPRESA " + nome + " TEM " + registro.size() + " REGISTROS");
    }

    public String getNome() {
        return nome;
    }

    public Float getProbSubida() {
        return probSubida;
    }

    public void setProbSubida(Float probSubida) {
        this.probSubida = probSubida;
    }

    public Float getProbDescida() {
        return probDescida;
    }

    public void setProbDescida(Float probDescida) {
        this.probDescida = probDescida;
    }

    public Float getProbCompra() {
        return probCompra;
    }

    public void setProbCompra(Float probCompra) {
        this.probCompra = probCompra;
    }

    public Float getProbVenda() {
        return probVenda;
    }

    public void setProbVenda(Float probVenda) {
        this.probVenda = probVenda;
    }    
}
