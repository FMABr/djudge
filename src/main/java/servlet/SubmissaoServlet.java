package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Privado;
import model.bean.Questao;
import model.bean.QuestaoEntrada;
import model.bean.QuestaoSaidaEsperada;
import model.dao.GenericDAO;
import model.dao.PrivadoDAO;
import model.dao.QuestaoSaidaEsperadaDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.mail.EmailException;
import util.CommonsMail;
import util.JDoodle;
import util.JDoodleOutputFormat;

/**
 *
 * @author eddunic
 */
@MultipartConfig
public class SubmissaoServlet extends HttpServlet {

    private String entradaFormat;
    private String saidaUsuarioFormat;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String value = null; //Compiler value

        //Read archive
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        File uploadedFile = null;

        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();

                    if (!item.isFormField()) {
                        String fileName = item.getName();

                        File path = new File("D:\\home\\eddunic\\NetBeansProjects\\djudge");
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }

                        uploadedFile = new File(path + "/" + fileName);
                        System.out.println(uploadedFile.getAbsolutePath());
                        item.write(uploadedFile);
                    } else {
                        String name = item.getFieldName();
                        value = item.getString();

                        // **************************************************
                        // Process your name and value pairs here! *****
                        // **************************************************
                        System.out.println("Found field " + name + " and value " + value);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JDoodle j = new JDoodle();

        Questao q;
        GenericDAO<Questao> gqDAO = new GenericDAO();
        q = gqDAO.findById(Questao.class, Long.parseLong(request.getParameter("id")));

        QuestaoSaidaEsperada qs;
        GenericDAO<QuestaoSaidaEsperada> gqsDAO = new GenericDAO();
        qs = gqsDAO.findById(QuestaoSaidaEsperada.class, Long.parseLong(request.getParameter("id")));

        QuestaoEntrada qe;
        GenericDAO<QuestaoEntrada> gqeDAO = new GenericDAO();
        qe = gqeDAO.findById(QuestaoEntrada.class, Long.parseLong(request.getParameter("id")));

        String entradaDecode = new String(qe.getEntrada(), "ISO-8859-1");

        String[] proSplit = entradaDecode.split("\n");
        entradaFormat = "";
        for (String str : proSplit) {
            entradaFormat += str.trim() + "\\n";
        }

        JDoodleOutputFormat output = j.post(request, response, uploadedFile, value, entradaFormat);

        QuestaoSaidaEsperadaDAO qesdao = new QuestaoSaidaEsperadaDAO();

        List<QuestaoSaidaEsperada> lista = qesdao.getSaidaByQuestao(request.getParameter("id"));
        String saidaAvaliadorDecode = "";    
        for (QuestaoSaidaEsperada qse : lista) {
            saidaAvaliadorDecode = new String(qse.getSaidaEsperada(), "ISO-8859-1");
        }
        
//        byte[] saidaAvaliador = qs.getSaidaEsperada();
//        String saidaAvaliadorDecode = new String(saidaAvaliador, "ISO-8859-1");

        String[] proSplit2 = output.getCodeOutput().split("\\\\n");
        saidaUsuarioFormat = "";
        for (String str2 : proSplit2) {
            saidaUsuarioFormat += str2 + "\n";
        }

        out.println("Saída do usuário ->\n" + saidaUsuarioFormat);
        out.println("Saída do avaliador ->\n" + saidaAvaliadorDecode);

        String resp = null;
        if (saidaUsuarioFormat.equals(saidaAvaliadorDecode)) {
            resp = "Código CORRETO!";
            response.sendRedirect("../djudge/feedback/subCerto.jsp?user=" + saidaUsuarioFormat + "&ava=" + saidaAvaliadorDecode);
        } else {
            out.println("Questão ERRADA!");
            resp = "Código ERRADO!";
            response.sendRedirect("../djudge/feedback/subErro.jsp?user=" + saidaUsuarioFormat + "&ava=" + saidaAvaliadorDecode);
        }

        Privado p = new Privado();
        p = (Privado) request.getSession().getAttribute("usuario");

        PrivadoDAO pDAO = new PrivadoDAO();
        String turma = pDAO.getTurmaById((p.getId()));
        String matricula = pDAO.getMatriculaById((p.getId()));

        String tituloDecode = new String(q.getTitulo(), "ISO-8859-1");

        try {
            CommonsMail.enviarEmail(q.getAvaliador().getEmail(), uploadedFile, p.getNome(), turma, matricula, tituloDecode,
                    saidaUsuarioFormat, saidaAvaliadorDecode, resp);
        } catch (EmailException ex) {
            Logger.getLogger(SubmissaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SubmissaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
