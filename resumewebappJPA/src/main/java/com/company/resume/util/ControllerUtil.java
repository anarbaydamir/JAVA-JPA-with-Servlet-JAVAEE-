package com.company.resume.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerUtil {
    public static void errorPage (HttpServletResponse response, Exception ex){
        try {
            ex.printStackTrace();
            response.sendRedirect("error?msg="+ex.getMessage());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
