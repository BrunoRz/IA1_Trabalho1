package ia2_trab1;

import java.util.List;

/**
 *
 * @author Bruno
 */
public class Empresa {
    String nome;
    List<Registro> registro;

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
    
    
}
