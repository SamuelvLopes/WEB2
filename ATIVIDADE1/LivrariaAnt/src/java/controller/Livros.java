/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocios.Livro;
import repositorios.RepositorioLivro;
/**
 *
 * @author samu
 */
@WebServlet(name = "Livros", urlPatterns = {"/Livros"})
public class Livros extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String acao = request.getParameter("a");
            if(acao.equals("cadastrar")){
                int numPaginas = Integer.parseInt(request.getParameter("numPaginas"));
                String editora = request.getParameter("editora");
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");
                out.println(numPaginas+" pao "+editora+" pao "+titulo+" pao "+autor);            
                
                Livro l = new Livro(titulo,autor,editora,numPaginas);
                RepositorioLivro.inserir(l);

            }
            if(acao.equals("apresentar")){
                List<Livro> livros = RepositorioLivro.lerTudo();
                out.println("<center>");
                out.println("<h1>Livros cadastrados</h1>");
                out.println("<table border='1'>");
                out.println("<tr><th>Id</th><th>Titulo</th><th>Autor</th><th>Editora</th><th>Numero de Paginas</th><th>Vizualizar</th><th>Alterar</th><th>Deletar</th></tr>");
                for(Livro l: livros){
                
                out.println("<tr>"
                + "<td>"+l.GetId()+"</td>"
                + "<td>"+l.GetTitulo()+"</td>"
                +"<td>"+l.GetAutor()+"</td>"
                + "<td>"+l.GetEditora()+"</td>"
                + "<td>"+l.GetNumPaginas()+"</td>"
                + "<td><a href='Livros?a=vizualizar&id="+l.GetId()+"'>Vizualizar</a></td>"
                + "<td><a href='Livros?a=alterar&id="+l.GetId()+"'>Alterar</a></td>"
                + "<td><a href='Livros?a=deletar&id="+l.GetId()+"'>Deletar</a></td>"
                + "</tr>");

                }
                out.println("</table>");

                out.println("</center>");
            }
            
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
