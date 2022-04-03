package repositorios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import negocios.Livro;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author samu
 */
public class RepositorioLivro {
    
    private static int ultimoCodigo = 1;

    private static List<Livro> livros = new ArrayList<>();

    public static void inserir(Livro l){
        l.SetId(ultimoCodigo++);
        livros.add(l);
    }

    public static void alterar(Livro l){
    
        for(Livro livr: livros){
            if(livr.GetId()==l.GetId()){
                livr.SetTitulo(l.GetTitulo());
                livr.SetAutor(l.GetAutor());
                livr.SetEditora(l.GetEditora());
                livr.SetNumPaginas(l.GetNumPaginas());
            }
            return;
        }
    }

    public static Livro ler(int id){
        for(Livro l: livros){
            if(l.GetId()==id){
                return l;
            }
        }
        return null;
    }
    public static void delete(Livro l){
        Livro lAux=null;
        for(Livro livr: livros){
            if(livr.GetId()==l.GetId()){
                lAux=livr;
                break;
            }
        }
        livros.remove(lAux);
    }
    public static List<Livro> lerTudo(){
        return livros;
    }
}
