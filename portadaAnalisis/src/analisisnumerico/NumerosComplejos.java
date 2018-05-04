/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisnumerico;


public class NumerosComplejos {
    double entera,imaginaria;

    public NumerosComplejos(double entera, double imaginaria) {
        this.entera = entera;
        this.imaginaria = imaginaria;
    }

    public double getEntera() {
        return entera;
    }

    public void setEntera(double entera) {
        this.entera = entera;
    }

    public double getImaginaria() {
        return imaginaria;
    }

    public void setImaginaria(double imaginaria) {
        this.imaginaria = imaginaria;
    }
    
    public NumerosComplejos add(NumerosComplejos nuevo){
        return new NumerosComplejos(this.entera+nuevo.getEntera(), this.imaginaria+nuevo.imaginaria);
    }
    
    public NumerosComplejos mult(NumerosComplejos nuevo){
        return new NumerosComplejos((this.entera*nuevo.entera)-(this.imaginaria*nuevo.imaginaria), (this.entera*nuevo.imaginaria)+(nuevo.entera*this.imaginaria));
    }
    
    public NumerosComplejos substract(NumerosComplejos nuevo){
        return new NumerosComplejos(this.entera-nuevo.getEntera(), this.imaginaria-nuevo.imaginaria);
    }
    
    @Override
    public String toString(){
        if(this.imaginaria>0){
            return (String.valueOf(this.entera + " + " + this.imaginaria+"i"));
        }
            return (String.valueOf(this.entera + " " + this.imaginaria+"i"));
    }
}
