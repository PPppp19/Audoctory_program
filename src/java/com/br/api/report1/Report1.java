/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.report1;

import com.br.api.connect.ConnectDB2;
import com.br.api.data.CRS692;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.br.api.data.CRS692;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Report1", urlPatterns = {"/Report1"})
@MultipartConfig
public class Report1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>
     * /code> and <code>POST</code> methods.
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Report</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Report at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        
        if (request.getParameter("REPORT").equalsIgnoreCase("GetReport")) {
            
            JasperDesign JPD;
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "BM_NEW.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/BM_NEW.jasper"));
                
                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();
                
                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";
                
                if (type.equalsIgnoreCase("Normal")) {
                    
                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";
                    
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    
                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_RESERVE";
                } else {
                    
                    parameterA = "%";
                    parameterB = "%";
                    
                }
                
                if (location.equalsIgnoreCase("LS")) {
                    FGLOCATION = "L/S";
                    RV = "RVC-TRN";
                } else if (location.equalsIgnoreCase("DC_CM")) {
                    FGLOCATION = "DC/CM";
                    RV = "RVF-TRN";
                } else if (location.equalsIgnoreCase("WTF")) {
                    FGLOCATION = "WTF";
//                    RV = "RVF-TRN";
                } else if (location.equalsIgnoreCase("ACOOUNT")) {
                    FGLOCATION = "ACOOUNT";
//                    RV = "RVF-TRN";
                } else if (location.equalsIgnoreCase("OTHER")) {
                    FGLOCATION = "OTHER";
                    RV = "RVG-TRN";
                } else if (location.equalsIgnoreCase("HC_PCB")) {
                    FGLOCATION = "FARMER";
                    RV = "RVE-TRN";
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                } else if (location.equalsIgnoreCase("FM")) {
                    FGLOCATION = "FARMER";
                    RV = "RVH-TRN";
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                }
                else if (location.equalsIgnoreCase("OTHER_FM")) {
                    FGLOCATION = "OTHER_FM";
                    RV = "RVH-TRN";
                }
                
                Map parameterss = new HashMap();
                parameterss.put("mono", "10");
                parameterss.put("com", "10");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);
                
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameterss, conn);
                response.setContentType("application/pdf");
                
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (request.getParameter("REPORT").equalsIgnoreCase("RP04_EXCEL")) {
            
            String path = getServletContext().getRealPath("/Report/");
            int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
            String DIVI = session.getAttribute("divi").toString().trim();
            String fdate = request.getParameter("fdate").replaceAll("-", "");
            String tdate = request.getParameter("tdate").replaceAll("-", "");
            
            String locations = request.getParameter("location").trim();
            Map parameterss = new HashMap();
            
            parameterss.put("cono", CONO);
            parameterss.put("divi", DIVI);
            parameterss.put("fromdate", Integer.parseInt(fdate));
            parameterss.put("todate", Integer.parseInt(tdate));
            parameterss.put("locations", locations);
            
            JasperDesign jasperDesign;
            try {
                jasperDesign = JRXmlLoader.load(path + "RC_004_XLSX.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                Connection connaaa = null;
                try {
                    connaaa = ConnectDB2.ConnectionDB();
                } catch (Exception ex) {
                    Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
                }
                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, connaaa);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "RC_004.xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("REPORT").equalsIgnoreCase("sPost")) {
            
            System.out.println("ccccccccccccccccccccccccccccccccccccccccccccc");
            
        } else if (request.getParameter("REPORT").equalsIgnoreCase("RP04_PDF")) {
            try {
                File reportFile = new File(getServletContext().getRealPath("Report/RC_004_XLSX.jasper"));
                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String fdate = request.getParameter("fdate").replaceAll("-", "");
                String tdate = request.getParameter("tdate").replaceAll("-", "");
                String locations = request.getParameter("location").trim();
                
                Map parameterss = new HashMap();
                parameterss.put("cono", CONO);
                parameterss.put("divi", DIVI);
                parameterss.put("fromdate", Integer.parseInt(fdate));
                parameterss.put("todate", Integer.parseInt(tdate));
                parameterss.put("locations", locations);
                
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("REPORT").equalsIgnoreCase("GetReport_WTFold")) {
            
            JasperDesign JPD;
            
            System.out.println("GetReportold(WTF)");
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "BM_WTF.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/BM_WTF.jasper"));
                
                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();
                
                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";
                
                if (type.equalsIgnoreCase("Normal")) {
                    
                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";
                    
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    
                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_RESERVE";
                } else {
                    
                    parameterA = "%";
                    parameterB = "%";
                    
                }
                
                if (location.equalsIgnoreCase("WTF")) {
                    FGLOCATION = "WTF";
                    RV = "RVA-TRN";
                }
                
                Map parameterss = new HashMap();
                parameterss.put("mono", "600");
                parameterss.put("com", "600");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);
                
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameterss, conn);
                response.setContentType("application/pdf");
                
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (request.getParameter("REPORT").equalsIgnoreCase("GetReport_WTF_EXCELold")) {
            
            JasperDesign JPD;
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "BM_WTF.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/BM_WTF.jasper"));
                System.out.println(getServletContext().getRealPath("jasper/BM_WTF.jasper"));
                
                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();
                
                String CONO = "";
                String DIVI = "";
                
                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";
                
                if (type.equalsIgnoreCase("Normal")) {
                    
                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";
                    
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    
                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_RESERVE";
                } else {
                    
                    parameterA = "%";
                    parameterB = "%";
                    
                }
                
                if (location.equalsIgnoreCase("WTF")) {
                    FGLOCATION = "WTF";
                    RV = "RVA-TRN";
                    CONO = "600";
                    DIVI = "600";
                }
                
                Map parameterss = new HashMap();
                parameterss.put("mono", "10");
                parameterss.put("com", "10");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);
                
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                
                Connection conn = ConnectDB2.ConnectionDB();
                
                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, conn);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "BANK_MAPPING_Excel" + ".xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (request.getParameter("REPORT").equalsIgnoreCase("GetReport_FM_EXCELold")) {
            
            JasperDesign JPD;
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "BM_FM.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/BM_FM.jasper"));
                System.out.println(getServletContext().getRealPath("jasper/BM_WTF.jasper"));
                
                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();
                
                String CONO = "";
                String DIVI = "";
                
                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";
                
                if (type.equalsIgnoreCase("Normal")) {
                    
                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";
                    
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    
                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_RESERVE";
                } else {
                    
                    parameterA = "%";
                    parameterB = "%";
                    
                }
                
                if (location.equalsIgnoreCase("FM")) {
                    FGLOCATION = "FM";
                    RV = "RVH-TRN";
                    CONO = "10";
                    DIVI = "101";
                }
                
                Map parameterss = new HashMap();
                parameterss.put("mono", "10");
                parameterss.put("com", "10");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);
                
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                
                Connection conn = ConnectDB2.ConnectionDB();
                
                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, conn);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "BANK_MAPPING_Excel" + ".xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("REPORT").equalsIgnoreCase("GetReport_FMold")) {
            
            JasperDesign JPD;
            
            System.out.println("GetReportold(FM)");
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "BM_FM.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/BM_FM.jasper"));
                
                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();
                
                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";
                
                if (type.equalsIgnoreCase("Normal")) {
                    
                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";
                    
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    
                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_RESERVE";
                } else {
                    
                    parameterA = "%";
                    parameterB = "%";
                    
                }
                
                if (location.equalsIgnoreCase("FM")) {
                    FGLOCATION = "FM";
                    RV = "RVH-TRN";
                    
                }
                
                Map parameterss = new HashMap();
                parameterss.put("mono", "600");
                parameterss.put("com", "600");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);
                
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameterss, conn);
                response.setContentType("application/pdf");
                
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (request.getParameter("REPORT").equalsIgnoreCase("GetReportold")) {
            
            JasperDesign JPD;
            
            System.out.println("GetReportold()");
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "BM.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/BM.jasper"));
                
                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();
                
                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";
                
                if (type.equalsIgnoreCase("Normal")) {
                    
                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";
                    
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    
                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_RESERVE";
                } else {
                    
                    parameterA = "%";
                    parameterB = "%";
                    
                }
                
                if (location.equalsIgnoreCase("LS")) {
                    FGLOCATION = "L/S";
                    RV = "RVC-TRN";
                } else if (location.equalsIgnoreCase("DC_CM")) {
                    FGLOCATION = "DC/CM";
                    RV = "RVF-TRN";
                } else if (location.equalsIgnoreCase("WTF")) {
                    FGLOCATION = "WTF";
                    RV = "RVA-TRN";
                } else if (location.equalsIgnoreCase("ACOOUNT")) {
                    FGLOCATION = "ACOOUNT";
//                    RV = "RVF-TRN";
                } else if (location.equalsIgnoreCase("OTHER")) {
                    FGLOCATION = "OTHER";
                    RV = "RVG-TRN";
                } else if (location.equalsIgnoreCase("HC_PCB")) {
                    FGLOCATION = "FARMER";
                    RV = "RVE-TRN";
                }
                
                Map parameterss = new HashMap();
                parameterss.put("mono", "10");
                parameterss.put("com", "10");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);
                
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameterss, conn);
                response.setContentType("application/pdf");
                
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (request.getParameter("REPORT").equalsIgnoreCase("RC001")) {
            
            JasperDesign JPD;
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "DC001.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/DC001.jasper"));
                
                String fdate = request.getParameter("vFROM").toString().trim();
                String tdate = request.getParameter("vTO").toString().trim();
                
                fdate = fdate + " 00:00:00.000";
                tdate = tdate + " 00:00:00.000";
                System.out.println(fdate);
                System.out.println(tdate);
                
                Map parameterss = new HashMap();
//                parameterss.put("FROM", fdate);
//                parameterss.put("TO", tdate);

//                Date date1=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse("2023-01-01 00:00:00.000");  
//                Date date2=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse("2023-01-01 00:00:00.000");  
//                parameterss.put("FROM", "2023-01-01 00:00:00.000");
//                parameterss.put("TO", "2023-04-01 00:00:00.000");
//String s = "2023-01-01 00:00:00.000";
//String s2 = "2023-04-01 00:00:00.000";
                String s = fdate;
                String s2 = tdate;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp ts = new Timestamp(format.parse(s).getTime());
                Timestamp ts2 = new Timestamp(format.parse(s2).getTime());
                
                parameterss.put("FROM", ts);
                parameterss.put("TO", ts2);
                System.out.println(ts);
                System.out.println(ts2);
                
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameterss, conn);
                response.setContentType("application/pdf");
                
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (request.getParameter("REPORT").equalsIgnoreCase("RC001excel")) {
            
            JasperDesign JPD;
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "DC001.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/DC001.jasper"));
                
                String fdate = request.getParameter("vFROM").toString().trim();
                String tdate = request.getParameter("vTO").toString().trim();
                
                fdate = fdate + " 00:00:00.000";
                tdate = tdate + " 00:00:00.000";
                System.out.println(fdate);
                System.out.println(tdate);
                
                Map parameterss = new HashMap();
//                parameterss.put("FROM", fdate);
//                parameterss.put("TO", tdate);

//                Date date1=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse("2023-01-01 00:00:00.000");  
//                Date date2=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse("2023-01-01 00:00:00.000");  
//                parameterss.put("FROM", "2023-01-01 00:00:00.000");
//                parameterss.put("TO", "2023-04-01 00:00:00.000");
                String s = fdate;
                String s2 = tdate;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp ts = new Timestamp(format.parse(s).getTime());
                Timestamp ts2 = new Timestamp(format.parse(s2).getTime());
                
                parameterss.put("FROM", ts);
                parameterss.put("TO", ts2);
                System.out.println(ts);
                System.out.println(ts2);
                
                Connection conn = ConnectDB2.ConnectionDB();
                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, conn);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "BANK_MAPPING_Excel" + ".xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (request.getParameter("REPORT").equalsIgnoreCase("GetReport_EXCELold")) {
            
            JasperDesign JPD;
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "BM.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/BM.jasper"));
                System.out.println(getServletContext().getRealPath("jasper/BM.jasper"));
                
                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();
                
                String CONO = "";
                String DIVI = "";
                
                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";
                
                if (type.equalsIgnoreCase("Normal")) {
                    
                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";
                    
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    
                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_RESERVE";
                } else {
                    
                    parameterA = "%";
                    parameterB = "%";
                    
                }
                
                if (location.equalsIgnoreCase("LS")) {
                    FGLOCATION = "L/S";
                    RV = "RVC-TRN";
                    CONO = "10";
                    DIVI = "101";
                    
                } else if (location.equalsIgnoreCase("DC_CM")) {
                    FGLOCATION = "DC/CM";
                    RV = "RVF-TRN";
                    CONO = "10";
                    DIVI = "101";
                } else if (location.equalsIgnoreCase("FM")) {
                    FGLOCATION = "FM";
                    RV = "RVH-TRN";
                    CONO = "10";
                    DIVI = "101";
                } //                else if (location.equalsIgnoreCase("HC_PCR")) {
                //                    FGLOCATION = "FM";
                //                    RV = "RVH-TRN";
                //                }
                else if (location.equalsIgnoreCase("OTHER")) {
                    FGLOCATION = "OTHER";
                    RV = "RVG-TRN";
                    CONO = "10";
                    DIVI = "101";
                } else if (location.equalsIgnoreCase("WTF")) {
                    FGLOCATION = "WTF";
                    RV = "RVA-TRN";
                    CONO = "600";
                    DIVI = "600";
                }
                
                Map parameterss = new HashMap();
                parameterss.put("mono", "10");
                parameterss.put("com", "10");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);
                
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                
                Connection conn = ConnectDB2.ConnectionDB();
                
                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, conn);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "BANK_MAPPING_Excel" + ".xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
//            } catch (Exception ex) {
//                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            } catch (Exception ex) {
//                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
//            }

        } else if (request.getParameter("REPORT").equalsIgnoreCase("GetReport_EXCEL")) {
            
            JasperDesign JPD;
            
            try {
                
                String path1 = getServletContext().getRealPath("/Report/");
                
                JPD = JRXmlLoader.load(path1 + "BM_NEW.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
                
                File reportFile = new File(getServletContext().getRealPath("Report/BM_NEW.jasper"));
                System.out.println(getServletContext().getRealPath("jasper/BM_NEW.jasper"));
                
                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();
                
                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";
                
                if (type.equalsIgnoreCase("Normal")) {
                    
                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";
                    
                } else if (type.equalsIgnoreCase("DEPOSIT")) {
                    
                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_DEPOSIT";
                } else {
                    
                    parameterA = "%";
                    parameterB = "%";
                    
                }
                
                if (location.equalsIgnoreCase("LS")) {
                    FGLOCATION = "L/S";
                    RV = "RVC-TRN";
                } else if (location.equalsIgnoreCase("DC_CM")) {
                    FGLOCATION = "DC/CM";
                    RV = "RVF-TRN";
                } else if (location.equalsIgnoreCase("OTHER")) {
                    FGLOCATION = "OTHER";
                    RV = "RVG-TRN";
                    
                } else if (location.equalsIgnoreCase("HC_PCB")) {
                    FGLOCATION = "FARMER";
                    RV = "RVE-TRN";
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                }
                  else if (location.equalsIgnoreCase("OTHER_FM")) {
                    FGLOCATION = "OTHER_FM";
                    RV = "RVH-TRN";
                }
                
                Map parameterss = new HashMap();
                parameterss.put("mono", "10");
                parameterss.put("com", "10");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);
                
                Connection conn = ConnectDB2.ConnectionDB();
                
                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, conn);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "BANK_MAPPING_Excel" + ".xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();
                
            } catch (JRException ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
            }
//            } catch (Exception ex) {
//                Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            } catch (Exception ex) {
//                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
//            }

        }
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
//        processRequest(request, response);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        
        System.out.println("aaaaaaaaaaaaaaaaaaa");
        System.out.println(request.getParameter("REPORT"));
        
        if (request.getParameter("REPORT").equalsIgnoreCase("sPost")) {
            
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            
            String date = request.getParameter("vDATE");
            String cusno = request.getParameter("vCusno");
            String branch = request.getParameter("vBranch");
            String bank = request.getParameter("vBank");
            int cono = Integer.parseInt(session.getAttribute("cono").toString());
            String divi = session.getAttribute("divi").toString();
            String user = session.getAttribute("username").toString();
            
//            System.out.println(branch);
             CRS692.CRS692_ADDCHECK(cono, divi, cusno, branch, bank);
        }

//        if (request.getParameter("REPORT").equalsIgnoreCase("BN-01")) {

        /*
        try {
            File reportFile = new File(getServletContext().getRealPath("Report/BN-01.jasper"));
            //  ClassGetReport cgr = new ClassGetReport();
            String CONO = session.getAttribute("cono").toString().trim();
            String DIVI = session.getAttribute("divi").toString().trim();
            String BNNO = request.getParameter("vBNNO").trim();
            Double AMT = Cal_LineAmount(CONO, DIVI, BNNO);

            String THB = WordCurrency(String.format("%.2f", AMT));

            Map parameterss = new HashMap();
            parameterss.put("CONO", CONO);
            parameterss.put("DIVI", DIVI);
            parameterss.put("BNNO", BNNO);
            parameterss.put("THB", THB);
//                parameterss.put("todate", Integer.parseInt(tdate));
//                parameterss.put("locations", locations);

            Connection conn = ConnectDB2.ConnectionDB();
//                conn.setAutoCommit(true);
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();

            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();

        } catch (JRException ex) {
            Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Report1.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
//        }
//        }
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

    public static Double Cal_LineAmount(String CONO, String DIVI, String BNNO) {
        Double Amt = 0.00;
        try {
            Connection conn = ConnectDB2.ConnectionDB();
//            conn.setAutoCommit(true);

            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT SUM(B.LB_INVAMT - B.LB_TAXAMT) AS  Amount\n"
                        + "FROM BRLDTA0100.LN_BILLNOTE B\n"
                        + "WHERE LB_CONO = '" + CONO + "'\n"
                        + "AND LB_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND LB_BNNO = '" + BNNO.trim() + "'";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    Amt += mRes.getDouble(1);
                }
                Amt = Double2digitReturn(Amt);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return Amt;
    }
    
    public static double Double2digitReturn(double number) {
        
        try {
            String numberBeforeconvert = String.valueOf(new DecimalFormat("##.##").format(Math.round(number * 100.0) / 100.0));
            double numberreturn = Double.parseDouble(numberBeforeconvert);
            return numberreturn;
        } catch (Exception e) {
            return 0;
        }
        
    }
    
    public static String WordCurrency(String Money) {
        DecimalFormat df = new DecimalFormat("#.00");
        String bathTxt, n, bathTH = "";
        Double amount;
        
        bathTxt = Money.replaceAll("-", "");
        amount = Double.parseDouble(Money.replaceAll("-", ""));
        
        bathTxt = df.format(amount);
        String[] num = {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ"};
        String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] rank = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"};
        String[] temp = bathTxt.split("[.]");
        String intVal = temp[0];
        String deciVal = temp[1];
        String strTrillion = "";
        Boolean _IsTrillion = intVal.length() > 7;
        int check = 0;
        if (_IsTrillion) {
            strTrillion = intVal.substring(0, intVal.length() - 6);
            bathTH = WordCurrency2(strTrillion) + "ล้าน";
            intVal = intVal.substring(strTrillion.length());
            check++;
        }
        
        if (Double.parseDouble(bathTxt) == 0) {
            bathTH = "ศูนย์บาท";
        } else {
            try {
                for (int i = 0; i < intVal.length(); i++) {
                    n = intVal.substring(i, i + 1);
                    if (Integer.parseInt(n) != 0) {
                        if ((i == (intVal.length() - 1)) && (n.indexOf("1") > -1) && intVal.length() > 1) {
                            bathTH += "เอ็ด";
                        } else if ((i == (intVal.length() - 2)) && (n.indexOf("2") > -1)) {
                            bathTH += "ยี่";
                        } else if ((i == (intVal.length() - 2)) && (n.indexOf("1") > -1)) {
                            bathTH += "";
                        } else {
                            bathTH += num[Integer.parseInt(n)];
                        }
                        bathTH += rank[(intVal.length() - i) - 1];
                    } else if (i == 0) {
                        bathTH += num[Integer.parseInt(n)];
                    }
                    
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
            
            if (deciVal.length() > 0 && Integer.parseInt(deciVal) != 0) {
                bathTH += "บาท";
                for (int i = 0; i < deciVal.length(); i++) {
                    System.out.print(deciVal.substring(0 + i, 1 + i));
                    n = deciVal.substring(i, i + 1);
                    if (Integer.parseInt(n) != 0) {
                        if ((i == (deciVal.length() - 1)) && (n.indexOf("1") > -1) && deciVal.length() > 1) {
                            if (!deciVal.substring(0, 0 + 1).equalsIgnoreCase("0")) {
                                bathTH += "เอ็ด";
                            } else {
                                bathTH += num[Integer.parseInt(n)];
                            }
                        } else if ((i == (deciVal.length() - 2)) && (n.indexOf("2") > -1)) {
                            bathTH += "ยี่";
                        } else if ((i == (deciVal.length() - 2)) && (n.indexOf("1") > -1)) {
                            bathTH += "";
                        } else {
                            bathTH += num[Integer.parseInt(n)];
                        }
                        bathTH += rank[(deciVal.length() - i) - 1];
                    }
                }
                bathTH += "สตางค์";
            } else {
                bathTH += "บาท";
            }
            
        }
        return bathTH;
        
    }
    
    public static String WordCurrency2(String Money) {
        DecimalFormat df = new DecimalFormat("#.00");
        String bathTxt, n, bathTH = "";
        Double amount;
        bathTxt = Money.replaceAll("-", "");
        amount = Double.parseDouble(Money.replaceAll("-", ""));
        
        bathTxt = df.format(amount);
        String[] num = {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ"};
        String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] rank = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"};
        String[] temp = bathTxt.split("[.]");
        String intVal = temp[0];
        String deciVal = temp[1];
        if (Double.parseDouble(bathTxt) == 0) {
            bathTH = "ศูนย์บาท";
        } else {
            try {
                for (int i = 0; i < intVal.length(); i++) {
                    n = intVal.substring(i, i + 1);
                    if (Integer.parseInt(n) != 0) {
                        if ((i == (intVal.length() - 1)) && (n.indexOf("1") > -1) && intVal.length() > 1) {
                            bathTH += "เอ็ด";
                        } else if ((i == (intVal.length() - 2)) && (n.indexOf("2") > -1)) {
                            bathTH += "ยี่";
                        } else if ((i == (intVal.length() - 2)) && (n.indexOf("1") > -1)) {
                            bathTH += "";
                        } else {
                            bathTH += num[Integer.parseInt(n)];
                        }
                        bathTH += rank[(intVal.length() - i) - 1];
                    } else if (i == 0) {
                        bathTH += num[Integer.parseInt(n)];
                    }
                    
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
        return bathTH;
        
    }
    
    private Object Date(String fdate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
