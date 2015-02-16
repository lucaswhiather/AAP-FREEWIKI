package wiki.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import wiki.util.HibernateUtil;

@WebFilter(urlPatterns = "/*")
public class SessionFilter implements Filter {

    private SessionFactory sf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sf = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            sf.getCurrentSession().beginTransaction();
           // HttpServletRequest req = (HttpServletRequest) request;
            //System.out.println("Iniciou Requisição---------------------------------"); 
            chain.doFilter(request, response);
            sf.getCurrentSession().getTransaction().commit();
            sf.getCurrentSession().close();
            //System.out.println("Fechou requisição---------------------------------");

        } catch (StaleObjectStateException staleEx) {
            throw staleEx;
        } catch (Throwable ex) {
            System.out.println("Erro filro de sessão: " + ex.getMessage());
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            // Let others handle it... maybe another interceptor for exceptions?  
            throw new ServletException(ex);
        }
    }

    @Override
    public void destroy() {
    }
}
