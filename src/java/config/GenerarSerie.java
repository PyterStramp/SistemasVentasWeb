
package config;

public class GenerarSerie {
    int dato;
    String numero;

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String NumeroSerie(int dato) {
        this.dato=dato+1;
        if (this.dato>=10000000 && (this.dato<=100000000)) {
            numero =""+this.dato;
        }
        if (this.dato>=1000000 &&(this.dato<=10000000)){
            numero="0"+this.dato;
        }
        if (this.dato>=100000 &&(this.dato<=1000000)){
            numero="00"+this.dato;
        }
        if (this.dato>=10000 &&(this.dato<=100000)){
            numero="000"+this.dato;
        }
        if (this.dato>=1000 &&(this.dato<=10000)){
            numero="0000"+this.dato;
        }
        if (this.dato>=100 &&(this.dato<=1000)){
            numero="00000"+this.dato;
        }
        if (this.dato>=10 &&(this.dato<=100)){
            numero="000000"+this.dato;
        }
        if (this.dato<10) {
            numero="0000000"+this.dato;
        }
        return this.numero;
    }
}
