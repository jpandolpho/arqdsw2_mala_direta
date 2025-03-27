package br.com.ifsp.maladireta.model;

import java.time.LocalDate;

public class Aniversariante 
{
	private String nome;
    private String email;
    private LocalDate dataNascimento;

    public Aniversariante(String nome, String email, LocalDate dataNascimento) 
    {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // getters e setters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    
    @Override
    public String toString()
    {
    	return nome + ": " + email;
    }
}
