package negocios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author samu
 */
public class Livro {
    
private int Id;
private String Titulo;
private String Autor;
private String Editora;
private int NumPaginas;

    public Livro(String Titulo,String Autor,String Editora,int NumPaginas){
        this.Titulo=Titulo;
        this.Autor=Autor;
        this.Editora=Editora;
        this.NumPaginas=NumPaginas; 
    }
    public void SetTitulo(String Titulo){
        this.Titulo=Titulo;
    }
    public String GetTitulo(){
        return Titulo;
    }
    public void SetAutor(String Autor){   
        this.Autor=Autor;
    }
    public String GetAutor(){
        return Autor;
    }
    public void SetEditora(String Editora){
        this.Editora=Editora;
    }
    public String GetEditora(){
        return Editora;
    }
    public void SetNumPaginas(int NumPaginas){
        this.NumPaginas=NumPaginas;
    }
    public int GetNumPaginas(){
        return NumPaginas;
    }
}
