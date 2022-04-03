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
            out.println("<style>\n" +
"            a{\n" +
"                text-decoration: none;\n" +
"                font-size:25px;\n" +
"                color:#000;\n" +
"                \n" +
"            }\n" +
"            a:hover{\n" +
"                color:#09f563;\n" +
"            }\n" +
"            #form_cadastro{\n" +
"                display:none;\n" +
"            }            \n" +
"        </style>");
            out.println("<center>");  
            String acao = request.getParameter("a");
            if(acao.equals("cadastrar")){
                int numPaginas = Integer.parseInt(request.getParameter("numPaginas"));
                String editora = request.getParameter("editora");
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");
                   
                
                Livro l = new Livro(titulo,autor,editora,numPaginas);
                RepositorioLivro.inserir(l);

                out.println("<h1>Cadastrado!</h1>");
            }
            if(acao.equals("apresentar")){
                List<Livro> livros = RepositorioLivro.lerTudo();
                
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
                + "<td><a href='Livros?a=alterador&id="+l.GetId()+"'>Alterar</a></td>"
                + "<td><a href='Livros?a=deletar&id="+l.GetId()+"'>Deletar</a></td>"
                + "</tr>");

                }
                out.println("</table>");

            }
            if(acao.equals("vizualizar")){

                int id = Integer.parseInt(request.getParameter("id"));
                Livro l = RepositorioLivro.ler(id);
                
                out.println("<h1>Apresentando Livro</h1>");
                out.println("<p>Titulo: <b>"+l.GetTitulo()+ "</b> </p>");
                out.println("<p>Autor: <b>"+l.GetAutor()+ "</b> </p>");
                out.println("<p>Editora: <b>"+l.GetEditora()+ "</b> </p>");
                out.println("<p>Numero de paginas: <b>"+l.GetNumPaginas()+"</b> </p>");
                
                
               
                             
            }
            if(acao.equals("alterar")){
                int id  = Integer.parseInt(request.getParameter("id"));
                int numPaginas = Integer.parseInt(request.getParameter("numPaginas"));
                String editora = request.getParameter("editora");
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");

                Livro l = new Livro(titulo,autor,editora,numPaginas);
                l.SetId(id);
                RepositorioLivro.alterar(l);
                out.println("<h1>Alterado!</h1>");
            }
            if(acao.equals("alterador")){

                int id = Integer.parseInt(request.getParameter("id"));
                Livro l = RepositorioLivro.ler(id);
                out.println(" <form action='Livros?a=alterar&id="+l.GetId()+"' method='post'>\n" +
                "                      <label for='titulo'>Titulo:</label><br>\n" +
                "                      <input type='text' id='titulo' name='titulo' value='"+l.GetTitulo()+"'><br>\n" +
                "                      <label for='autor'>Autor:</label><br>\n" +
                "                      <input type='text' id='autor' name='autor' value='"+l.GetAutor()+"'><br>\n" +
                "                      <label for='editora'>Editora:</label><br>\n" +
                "                      <input type='text' id='editora' name='editora' value='"+l.GetEditora()+"'><br>\n" +
                "                      <label for='numPaginas'>Numero de paginas:</label><br>\n" +
                "                      <input type='number' id='numPaginas' name='numPaginas' value='"+l.GetNumPaginas()+"'><br>\n" +
                "                      <input type='submit' value='editar'>\n" +
                "                    </form>");

            }
            if(acao.equals("deletar")){

                int id = Integer.parseInt(request.getParameter("id"));
                Livro l = RepositorioLivro.ler(id);
                RepositorioLivro.delete(l);
                out.println("<h1>Deletado!</h1>");

            }
            out.println("<br><br><a href='/'>Home</a>");
            out.println("</center>"); 
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
