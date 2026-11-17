package murach.survey;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import murach.business.Survey;
import murach.business.SurveyDTO;
import murach.business.User;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GregorianCalendar currentDate = new GregorianCalendar();
        int currentYear = currentDate.get(Calendar.YEAR);
        request.setAttribute("currentYear", currentYear);

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String hearAbout = request.getParameter("hearAbout");
        boolean wantsAnnouncements = "YES".equals(request.getParameter("wantsAnnouncements"));
        String contactVia = request.getParameter("contactVia");

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(null);

        Survey survey = new Survey();
        survey.setHearAbout(hearAbout);
        survey.setWantsAnnouncements(wantsAnnouncements);
        survey.setContactVia(contactVia);

        SurveyDTO surveyDTO = new SurveyDTO(user, survey);
        request.setAttribute("surveyDTO", surveyDTO);

        getServletContext()
                .getRequestDispatcher("/survey.jsp")
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
