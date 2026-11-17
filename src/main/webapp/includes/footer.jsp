<%@ page import="java.util.Calendar,java.util.GregorianCalendar" %>
<%
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
%>
<p>&copy; Copyright <%= currentYear %> Mike Murach &amp; Associates</p>
</body>
</html>
