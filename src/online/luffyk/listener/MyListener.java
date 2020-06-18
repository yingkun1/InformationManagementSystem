package online.luffyk.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.io.*;
import java.util.Enumeration;

public class MyListener implements HttpSessionListener,HttpSessionAttributeListener, ServletContextListener {
    private Logger logger = Logger.getLogger(MyListener.class);
    private int count = 0;

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        logger.debug("监听到用户登录了");
        count++;
        ServletContext servletContext = httpSessionBindingEvent.getSession().getServletContext();
        servletContext.setAttribute("current_users",count);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

//        HttpSession session = httpSessionEvent.getSession();
//        Enumeration<String> attributeNames = session.getAttributeNames();
//        while (attributeNames.hasMoreElements()){
//            String key = attributeNames.nextElement();
//            Object value = session.getAttribute(key);
//            logger.debug("key:"+key+",value:"+value);
//        }


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        count--;
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        servletContext.setAttribute("current_users",count);
        logger.debug("监听到用户登出了");
    }


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String realPath = servletContextEvent.getServletContext().getRealPath("/file/visits.txt");
        try {
            FileReader fileReader = new FileReader(realPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            int visits_count = 0;
            line = bufferedReader.readLine();
            if (line != null) {
                visits_count = Integer.parseInt(line);
            }
//            logger.debug("此时的visits_count=" + visits_count);
            servletContextEvent.getServletContext().setAttribute("visits_count",visits_count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Integer visits_count = (Integer) servletContext.getAttribute("visits_count");
        String realPath = servletContext.getRealPath("/file/visits.txt");
//        logger.debug("realPath"+realPath);
//        logger.debug("visits_count:"+visits_count);
        File file = new File(realPath);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(visits_count));
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
