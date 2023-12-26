/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Wattana
 */
public class Sync extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Sync</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Sync at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("page").equals("Company")) {
                try {
                    out.print(Utility.getCompany());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DELDL")) {

                try {
                    DeleteDB2.DELDL(request.getParameter("code"), request.getParameter("user"), request.getParameter("date"), request.getParameter("amt"), request.getParameter("fee"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("DELDLo")) {

                try {
                    DeleteDB2.DELDLo(request.getParameter("code"), request.getParameter("user"), request.getParameter("date"), request.getParameter("amt"), request.getParameter("fee"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("Location")) {
                try {
                    out.print(Utility.getLocation(request.getParameter("cono").trim(), request.getParameter("divi").trim()));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
                    
                    else if (request.getParameter("page").equals("GETREQCUSCHK")) {
                try {
                    out.print(SelectDB2.GETREQCUSCHK());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
                else if (request.getParameter("page").equals("POSTREQCUSCHK")) {

                try {

                    UpdateDB2.POSTREQCUSCHK(request.getParameter("cono"), request.getParameter("divi"),
                            request.getParameter("cusno"), request.getParameter("branch"),
                          request.getParameter("user") , request.getParameter("date"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } 
            
            else if (request.getParameter("page").equals("ADDREQCUSCHK")) {

                try {

                    InsertDB2.ADDREQCUSCHK(request.getParameter("cono"), request.getParameter("divi"),
                            request.getParameter("cusno"), request.getParameter("branch"),
                          request.getParameter("user"),   request.getParameter("bank"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("DELEBK")) {

                try {

                    DeleteDB2.DELEBK(request.getParameter("cono"), request.getParameter("divi"),
                            request.getParameter("pyno"), request.getParameter("vcno"),
                            request.getParameter("date"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("SETITEM")) {
                try {
                    UpdateDB2.SETITEM(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("ordid"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("ROLLBACK")) {
                try {
                    UpdateDB2.ROLLBACK(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("ordid"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("getEBK")) {

                try {

                    out.print(SelectDB2.getEBK(request.getParameter("cono"), request.getParameter("divi"),
                            request.getParameter("pyno"), request.getParameter("vcno"),
                            request.getParameter("date")));
                    out.flush();

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("updatedailiylist")) {

                try {
                    UpdateDB2.updatedailiylist(request.getParameter("code"), request.getParameter("user"), request.getParameter("date"), request.getParameter("amt"), request.getParameter("fee"), request.getParameter("uhold"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("updatedailiylisto")) {

                try {
                    UpdateDB2.updatedailiylisto(request.getParameter("code"), request.getParameter("user"), request.getParameter("date"), request.getParameter("amt"), request.getParameter("fee"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("updatedate")) {
                try {
                    UpdateDB2.updatedate(request.getParameter("cono"), request.getParameter("start"), request.getParameter("end"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("getdate")) {
                try {
                    out.print(SelectDB2.getdate(request.getParameter("cono")));
                    out.flush();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("getdailylist")) {
                try {

                    out.print(SelectDB2.getdailylist(request.getParameter("code")));
                    out.flush();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("getdailylisto")) {
                try {

                    out.print(SelectDB2.getdailylisto(request.getParameter("code")));
                    out.flush();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("GridBP")) {
                try {
                    out.print(SelectDB2.GridBP(request.getParameter("user")));
                    out.flush();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("UpdateStatus")) {
                try {
                    UpdateDB2.UpdateStatus(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("BNNO"), request.getParameter("USER"), request.getParameter("STS"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter("page").equals("GetPaymentDate")) {
                try {
                    out.print(SelectDB2.GetPaymentDate(request.getParameter("CONO"), request.getParameter("CUNO")));
                    out.flush();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("CALLTABLE")) {
                try {

                    out.print(SelectDB2.CALLTABLE(request.getParameter("TABLE_SCHEMA"), request.getParameter("TABLE_NAME")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLQUERY")) {
                try {

                    out.print(SelectDB2.CALLQUERY(request.getParameter("vQUERY")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("XQUERY")) {
                try {
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");

                    UpdateDB2.XQUERY(request.getParameter("vQUERY"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DELETERCNO")) {
                try {

                    DeleteDB2.DELETERCNO(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DELETELRNOEXP")) {
                try {

                    DeleteDB2.DELETELRNOEXP(request.getParameter("LRE_CONO"), request.getParameter("LRE_DIVI"),
                            request.getParameter("LRE_RCNO"), request.getParameter("LRE_CODE"),
                            request.getParameter("LRE_ACTCODE"), request.getParameter("LRE_AMT"),
                            request.getParameter("LRE_REF1"), request.getParameter("LRE_REF2"),
                            request.getParameter("LRE_REF3"), request.getParameter("LRE_VCTXT"),
                            request.getParameter("LRE_FNNO"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DELETELRNO")) {
                try {

                    DeleteDB2.DELETELRNO(request.getParameter("LR_CONO"), request.getParameter("LR_DIVI"),
                            request.getParameter("LR_RCNO"), request.getParameter("LR_INVNO"),
                            request.getParameter("LR_INVDT"), request.getParameter("LR_INVAMT"),
                            request.getParameter("LR_REAMT"), request.getParameter("LR_STS"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DELETEID")) {
                try {

                    DeleteDB2.DELETEID(request.getParameter("RNNO"), request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO"), request.getParameter("CUNO"),
                            request.getParameter("PYNO"), request.getParameter("STS"),
                            request.getParameter("VCNO"), request.getParameter("LOCATION"),
                            request.getParameter("TYPE"), request.getParameter("user"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("INSERTRCNO")) {
                try {

                    out.print(InsertDB2.INSERTRCNO(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO"), request.getParameter("TRDT"),
                            request.getParameter("PYNO"), request.getParameter("REAMT"),
                            request.getParameter("TYPE"), request.getParameter("BANK"),
                            request.getParameter("ACCODE"), request.getParameter("PYDT"),
                            request.getParameter("CHKNO"), request.getParameter("USER"),
                            request.getParameter("VCNO"), request.getParameter("STS"),
                            request.getParameter("BKCHG"), request.getParameter("LOCATION"),
                            request.getParameter("FIXNO"), request.getParameter("RNNO")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DELETEBM")) {
                try {

                    DeleteDB2.DELETEBM(request.getParameter("BM_ID"),
                            request.getParameter("BM_CONO"), request.getParameter("BM_DIVI"),
                            request.getParameter("BM_BANK"),
                            request.getParameter("BM_DATE"), request.getParameter("BM_TIME")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("INSERTBM")) {
                try {

                    InsertDB2.INSERTBM(request.getParameter("BM_ID"),
                            request.getParameter("BM_CONO"), request.getParameter("BM_DIVI"),
                            request.getParameter("BM_BANK"), request.getParameter("BM_ACCODE"),
                            request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_CHQNO"), request.getParameter("BM_TRANSAC"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_CHANEL"),
                            request.getParameter("BM_BRANCH"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_DESC"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TIME1"), request.getParameter("BM_USER"),
                            request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"),
                            request.getParameter("BM_CNDN"), request.getParameter("BM_STATUS"),
                            request.getParameter("REF2"), request.getParameter("REF3"),
                            request.getParameter("SENDER"), request.getParameter("BM_REFCU"),
                            request.getParameter("REF4"), request.getParameter("BM_REFCU1"),
                            request.getParameter("BM_CUNA"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_LCODE")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("INSERTLRNOEXP")) {
                try {

                    InsertDB2.INSERTLRNOEXP(request.getParameter("LRE_CONO"), request.getParameter("LRE_DIVI"),
                            request.getParameter("LRE_RCNO"), request.getParameter("LRE_CODE"),
                            request.getParameter("LRE_ACTCODE"), request.getParameter("LRE_AMT"),
                            request.getParameter("LRE_REF1"), request.getParameter("LRE_REF2"),
                            request.getParameter("LRE_REF3"), request.getParameter("LRE_VCTXT"),
                            request.getParameter("LRE_FNNO")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("INSERTLRNO")) {
                try {

                    InsertDB2.INSERTLRNO(request.getParameter("LR_CONO"), request.getParameter("LR_DIVI"),
                            request.getParameter("LR_RCNO"), request.getParameter("LR_INVNO"),
                            request.getParameter("LR_INVDT"), request.getParameter("LR_INVAMT"),
                            request.getParameter("LR_REAMT"), request.getParameter("LR_STS")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("INSERTID")) {
                try {

                    InsertDB2.INSERTID(request.getParameter("RNNO"), request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO"), request.getParameter("CUNO"),
                            request.getParameter("PYNO"), request.getParameter("STS"),
                            request.getParameter("VCNO"), request.getParameter("LOCATION"),
                            request.getParameter("TYPE"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UPDATERCNO")) {
                try {

                    UpdateDB2.UPDATERCNO(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO"), request.getParameter("TRDT"),
                            request.getParameter("PYNO"), request.getParameter("REAMT"),
                            request.getParameter("TYPE"), request.getParameter("BANK"),
                            request.getParameter("ACCODE"), request.getParameter("PYDT"),
                            request.getParameter("CHKNO"), request.getParameter("USER"),
                            request.getParameter("VCNO"), request.getParameter("STS"),
                            request.getParameter("BKCHG"), request.getParameter("LOCATION"),
                            request.getParameter("FIXNO"), request.getParameter("RNNO"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UPDATEBM")) {
                try {

                    UpdateDB2.UPDATEBM(request.getParameter("BM_ID"),
                            request.getParameter("BM_CONO"), request.getParameter("BM_DIVI"),
                            request.getParameter("BM_BANK"), request.getParameter("BM_ACCODE"),
                            request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_CHQNO"), request.getParameter("BM_TRANSAC"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_CHANEL"),
                            request.getParameter("BM_BRANCH"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_DESC"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TIME1"), request.getParameter("BM_USER"),
                            request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"),
                            request.getParameter("BM_CNDN"), request.getParameter("BM_STATUS"),
                            request.getParameter("REF2"), request.getParameter("REF3"),
                            request.getParameter("SENDER"), request.getParameter("BM_REFCU"),
                            request.getParameter("REF4"), request.getParameter("BM_REFCU1"),
                            request.getParameter("BM_CUNA"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_LCODE")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UPDATELRNOEXP")) {
                try {

                    UpdateDB2.UPDATELRNOEXP(request.getParameter("LRE_CONO"), request.getParameter("LRE_DIVI"),
                            request.getParameter("LRE_RCNO"), request.getParameter("LRE_CODE"),
                            request.getParameter("LRE_ACTCODE"), request.getParameter("LRE_AMT"),
                            request.getParameter("LRE_REF1"), request.getParameter("LRE_REF2"),
                            request.getParameter("LRE_REF3"), request.getParameter("LRE_VCTXT"),
                            request.getParameter("LRE_FNNO"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UPDATELRNO")) {
                try {

                    UpdateDB2.UPDATELRNO(request.getParameter("LR_CONO"), request.getParameter("LR_DIVI"),
                            request.getParameter("LR_RCNO"), request.getParameter("LR_INVNO"),
                            request.getParameter("LR_INVDT"), request.getParameter("LR_INVAMT"),
                            request.getParameter("LR_REAMT"), request.getParameter("LR_STS"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UPDATEID")) {
                try {

                    UpdateDB2.UPDATEID(request.getParameter("RNNO"), request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO"), request.getParameter("CUNO"),
                            request.getParameter("PYNO"), request.getParameter("STS"),
                            request.getParameter("VCNO"), request.getParameter("LOCATION"),
                            request.getParameter("TYPE"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckINVSTS")) {
                try {
                    out.print(SelectDB2.CheckINVSTS(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("invno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UNLOCKINVOICE")) {
                try {
                    DeleteDB2.UNLOCKINVOICE(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("invno"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CHECKEXP")) {
                try {

                    out.print(SelectDB2.CHECKEXP(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("ID"))
                    );
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("RETURNEXP")) {
                try {

                    UpdateDB2.RETURNEXP(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("ID")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CHECKSTS")) {
                try {

                    out.print(SelectDB2.CHECKSTS(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("ID"))
                    );
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("RETURNBM")) {

                try {

                    boolean isBM = SelectDB2.CHKISBM(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));

                    if (isBM == true) {

                        UpdateDB2.RETURNBM(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));

                    } else {

                        UpdateDB2.RETURNRC(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));

//                        System.out.println("fuckkkkkk");
//                        System.out.println("CASE NORMAL");
//                        UpdateDB2.RETURNBM(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));
                    }

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLRCNO")) {
                try {
                    out.print(SelectDB2.CALLRCNO(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLLRNOEXP")) {
                try {
                    out.print(SelectDB2.CALLLRNOEXP(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLLRNO")) {
                try {
                    out.print(SelectDB2.CALLLRNO(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("RCNO")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLCODE")) {
                try {
                    out.print(SelectDB2.CALLCODE());
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLBM")) {
                try {
                    out.print(SelectDB2.CALLBM(request.getParameter("vCONOBM"), request.getParameter("vDIVILBM"),
                            request.getParameter("vBANKBM"), request.getParameter("vDATEBM"),
                            request.getParameter("vTIMEBM"), request.getParameter("vAMOUNTBM")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLID")) {
                try {
                    out.print(SelectDB2.CALLID(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("ID")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("ADDEXP")) {
                try {
                    InsertDB2.ADDEXP(request.getParameter("cono"), request.getParameter("divi"),
                            request.getParameter("RE_DESC"), request.getParameter("RE_ACCODE1"),
                            request.getParameter("RE_ACCODE2"), request.getParameter("RE_OPTION"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_ARS200")) {
                try {
                    out.print(SelectDB2.Grid_ARS200(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("type"), request.getParameter("ENDDATE")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_BillingNote")) {
                try {
                    out.print(SelectDB2.Grid_BillingNote(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("BNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Search_BillingNote")) {
                try {
                    out.print(SelectDB2.Search_BillingNote(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("BNNO"), request.getParameter("USER")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Search_BillingNote2")) {
                try {
                    out.print(SelectDB2.Search_BillingNote2(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("BNNO"), request.getParameter("USER")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Monitoring")) {
                try {
                    out.print(SelectDB2.Monitoring(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("USER")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckInvoice")) {
                try {
                    out.print(SelectDB2.CheckInvoice(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("Invoice")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("cono") == null) {
            response.sendRedirect("login.jsp");
        }

        if (request.getParameter("page").equals("CreateBillingNote")) {
            try {
                String BBNO = InsertDB2.CreateBillingNote(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("CUNO"), request.getParameter("TRDT"),
                        request.getParameter("PONO"), request.getParameter("USER"));
                try {
                    out.print(SelectDB2.ShowBillingNO(BBNO));
                    out.flush();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        } else if (request.getParameter("page").equals("checkGrid_ARS200")) {
            try {
                out.print(SelectDB2.checkGrid_ARS200(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("type"), request.getParameter("ENDDATE")));
                out.flush();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        } else if (request.getParameter("page").equals("InsertLine")) {
            try {
                InsertDB2.InsertLine(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("BBNO"), request.getParameter("ESCINO"),
                        request.getParameter("ESIVDT"), request.getParameter("ESINYR"), request.getParameter("ESCUAM"), request.getParameter("WHTAX"));
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        } else if (request.getParameter("page").equals("InsertLineJSON")) {
            try {
                InsertDB2.InsertLineJSON(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("BNNO"), request.getParameter("JSONARRAY"));
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        } else if (request.getParameter("page").equals("UpdateLineInvoice")) {
            try {
                UpdateDB2.UpdateLineInvoice(request.getParameter("LB_CONO"), request.getParameter("LB_DIVI"), request.getParameter("LB_BNNO"), request.getParameter("LB_INVNO"),
                        request.getParameter("LB_INVDT"), request.getParameter("LB_INVYEAR"), request.getParameter("LB_INVAMT"), request.getParameter("LB_TAXAMT"), request.getParameter("LB_WHTAX"), request.getParameter("LB_STS"));
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        } else if (request.getParameter("page").equals("UpdateBillingNote")) {
            try {
                UpdateDB2.UpdateBillingNote(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("BNNO"), request.getParameter("CUNO"),
                        request.getParameter("TRDT"), request.getParameter("PONO"), request.getParameter("USER"));
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        } else if (request.getParameter("page").equals("DeleteLineInvoice")) {
            try {
                DeleteDB2.DeleteLineInvoice(request.getParameter("LB_CONO"), request.getParameter("LB_DIVI"), request.getParameter("LB_BNNO"), request.getParameter("LB_INVNO"),
                        request.getParameter("LB_INVDT"), request.getParameter("LB_INVYEAR"));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else if (request.getParameter("page").equals("DeleteLineInvoiceJSON")) {
            try {
                DeleteDB2.DeleteLineInvoiceJSON(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("BNNO"), request.getParameter("JSONARRAY"));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

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
