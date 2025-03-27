package br.com.ifsp.maladireta.controller;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import br.com.ifsp.maladireta.dao.AniversarianteDAO;
import br.com.ifsp.maladireta.model.Aniversariante;
import br.com.ifsp.maladireta.service.EmailService;

@WebServlet("/email_aniversariantes")
public class AniversarioServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try 
        {
            AniversarianteDAO dao = new AniversarianteDAO();
            List<Aniversariante> aniversariantes = dao.getAniversariantesDoDia();
            resp.getWriter().println("Aniversariantes de hoje:");
            
            EmailService emailService = new EmailService();
            
            for (Aniversariante aniversariante : aniversariantes) 
            {
                emailService.enviarEmailAniversario(aniversariante);
            	resp.getWriter().println(aniversariante);
            }

            resp.getWriter().println("Mala direta enviada aos aniversariantes!");

        } catch (Exception e) {
            throw new ServletException("Erro no envio de e-mails", e);
        }
    }
}

