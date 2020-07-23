import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.usuario.Aluno;
import model.bean.usuario.Avaliador;
import model.bean.usuario.Turma;
import model.dao.GenericDAO;

/**
 *
 * @author eddunic
 */
public class PreencheBanco extends HttpServlet {

	private static final long serialVersionUID = -3756526333135569327L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		GenericDAO<Turma, Long> DAOTurma = new GenericDAO<>(Turma.class);
		GenericDAO<Aluno, Long> DAOAluno = new GenericDAO<>(Aluno.class);
        GenericDAO<Avaliador, Long> DAOAvaliador = new GenericDAO<>(Avaliador.class);

	
        Turma t = new Turma("IINF - 31B");
        Aluno a = new Aluno("2018323990", "FelipeMuAmorim@gmail,com", "Felipe M. Amorim", "aluno", t);
        Avaliador v = new Avaliador("6402939", "Tijolodecimento@gmail.com","Professor", "professor");

        DAOTurma.save(t);
        DAOAluno.save(a);
        DAOAvaliador.save(v);
    }
}
