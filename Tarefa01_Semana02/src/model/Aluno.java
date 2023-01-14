
package model;

public class Aluno extends Pessoa{
    private String matricula;
    private int anoIngresso;
    
    public Aluno(){
        super();
        this.matricula = "00000";
        this.anoIngresso = 1900;    
    }

    public Aluno(String matricula, int anoIngresso, String nome, char sexo, int idade) {
        super(nome, sexo, idade);
        this.matricula = matricula;
        this.anoIngresso = anoIngresso;
    }       
    
    public void copiar(Aluno outro){
        this.nome = outro.getNome();
        this.sexo = outro.getSexo();
        this.idade = outro.getIdade();
        this.matricula = outro.getMatricula();
        this.anoIngresso = outro.getAnoIngresso();                           
    }

    @Override
    public String toString() {
        String txt = "---- Dados do aluno ------\n"
        + super.toString()
        +" Matricula: "+ this.matricula+ "\n"
        +" Ano de ingresso: "+ this.anoIngresso + "\n"
        +"-------------------------------------\n";    
        
        return txt;
    }     
    
    public String cabecalho(){
        return "Nome;sexo;idade;matricula;ano\n";
    }
    
    public String atributoToCSV(){
        String aux = this.nome + ";" + 
                this.sexo +";"+
                this.idade+";"+
                this.matricula+ ";"+
                this.anoIngresso+ "\n";
        return aux;    
    }
    
    /**
     * Preenche os campos do objeto com uma linha CSV
     * @param csv 
     */
    public void CSVToAtributo(String csv){
        String vetor[] = csv.split(";");
        
        this.nome = vetor[0];
        this.sexo = vetor[1].charAt(0);
        this.idade = Integer.parseInt(vetor[2]);
        this.matricula = vetor[3];
        this.anoIngresso = Integer.parseInt(vetor[4]);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }
    
    
}
