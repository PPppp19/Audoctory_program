/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class DeleteDB2 {

    public static String DBPRD = GBVAR.DBPRD;
    public static String M3DB = GBVAR.M3DB;

    public static void DELETEBM(String BM_ID, String BM_CONO, String BM_DIVI, String BM_BANK, String BM_DATE, String BM_TIME) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "DELETE " + DBPRD + ".BANK_MAPPING\n"
                        + "WHERE  BM_ID = '" + BM_ID.trim() + "' AND  BM_CONO = '" + BM_CONO.trim() + "' AND  BM_DIVI = '" + BM_DIVI.trim() + "'  AND  BM_BANK = '" + BM_BANK.trim() + "'   AND  BM_DATE = '" + BM_DATE.trim() + "' AND  BM_TIME = '" + BM_TIME.trim() + "'";
                System.out.println(query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DELETELRNOEXP(String LRE_CONO, String LRE_DIVI, String LRE_RCNO, String LRE_CODE, String LRE_ACTCODE, String LRE_AMT, String LRE_REF1, String LRE_REF2, String LRE_REF3, String LRE_VCTXT, String LRE_FNNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "DELETE " + DBPRD + ".LR_LINEEXPEN\n"
                        + "WHERE  LRE_CONO = '" + LRE_CONO.trim() + "' AND  LRE_DIVI = '" + LRE_DIVI.trim() + "' AND  LRE_RCNO = '" + LRE_RCNO.trim() + "'  AND  LRE_CODE = '" + LRE_CODE.trim() + "'   AND  LRE_ACTCODE = '" + LRE_ACTCODE.trim() + "' AND  LRE_AMT = '" + LRE_AMT.trim() + "' AND LRE_FNNO = '" + LRE_FNNO.trim() + "'";
                System.out.println(query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DELETELRNO(String LR_CONO, String LR_DIVI, String LR_RCNO, String LR_INVNO, String LR_INVDT, String LR_INVAMT, String LR_REAMT, String LR_STS) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "DELETE " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE  LR_CONO = '" + LR_CONO.trim() + "' AND  LR_DIVI = '" + LR_DIVI.trim() + "' AND  LR_RCNO = '" + LR_RCNO.trim() + "'  AND  LR_INVNO = '" + LR_INVNO.trim() + "'   AND  LR_INVAMT = '" + LR_INVAMT.trim() + "' AND  LR_REAMT = '" + LR_REAMT.trim() + "'";
                System.out.println(query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DELETERCNO(String CONO, String DIVI, String RCNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "DELETE " + DBPRD + ".HR_RECEIPT\n"
                        + "WHERE  HR_CONO = '" + CONO.trim() + "' AND  HR_DIVI = '" + DIVI.trim() + "' AND  HC_RCNO = '" + RCNO.trim() + "'";
                System.out.println(query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DELETEID(String RNNO, String CONO, String DIVI, String RCNO, String CUNO, String PYNO, String STS, String VCNO, String LOCATION, String TYPE, String user) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String query = "DELETE " + DBPRD + ".HEAD_RECIPT\n"
                        + "WHERE  H_CONO = '" + CONO.trim() + "' AND  H_DIVI = '" + DIVI.trim() + "' AND  H_RCNO = '" + RCNO.trim() + "' AND H_RNNO = '" + RNNO.trim() + "'\n"
                        + "AND H_CUNO = '" + CUNO.trim() + "' AND H_PYNO = '" + PYNO.trim() + "' AND H_STS = '" + STS.trim() + "'";
                System.out.println(query);
                stmt.execute(query);

                Statement stmt1 = conn.createStatement();
                String query1 = "INSERT INTO M3FDBTST.BCKUP_PPP\n"
                        + "(USER_DEL,TIME_DEL,\n"
                        + "COLUMN1,COLUMN2,COLUMN3,COLUMN4,COLUMN5,\n"
                        + "COLUMN6,COLUMN7,COLUMN8,COLUMN9,COLUMN10)\n"
                        + "VALUES \n"
                        + "('" + user + "','" + timestamp + "',\n"
                        + "'" + CONO + "','" + DIVI + "','" + RCNO + "','" + RNNO + "','" + CUNO + "',\n"
                        + "'" + PYNO + "','" + STS + "','" + VCNO + "','" + LOCATION + "','" + TYPE + "')";
                System.out.println(query1);
                stmt1.execute(query1);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void UNLOCKINVOICE(String cono, String divi, String invno) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "DELETE  FROM M3FDBprd.FCR040\n"
                        //                        + "WHERE ACCONO='"+cono+"' \n"
                        //                        + "AND ACDIVI='"+divi+"' \n"
                        + "WHERE ACCINO='" + invno.trim() + "' \n"
                        + "AND ACSTCF = 9 ";
                stmt.executeUpdate(query);
                System.out.println(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DELEBK(String cono, String divi, String pyno, String vcno, String date) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "DELETE BRLDTA0100.EBK_PAYMAL ep  \n"
                        + "WHERE  PPSPYN  = '" + pyno.trim() + "'\n"
                        + "AND PPCONO  = '" + cono.trim() + "'\n"
                        + "AND PPDIVI  = '" + divi.trim() + "'\n"
                        + "AND PPVONO  = '" + vcno.trim() + "'\n"
                        + "AND PPDUDT  = '" + date.trim() + "'";
                System.out.println(query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DeleteLineInvoiceJSON(String CONO, String DIVI, String BNNO, String JSONARRAY) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Double TAX = 0.00;
        try (
                PreparedStatement stmt = conn.prepareStatement(
                        "DELETE FROM BRLDTA0100.LN_BILLNOTE\n"
                        + "WHERE LB_CONO = ? AND LB_DIVI = ? AND LB_BNNO = ? AND LB_INVNO = ? AND  LB_INVYEAR = ? AND LB_INVDT = ?")) {

            JSONArray arr = new JSONArray(JSONARRAY);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                try {

                    stmt.setInt(1, Integer.parseInt(CONO));
                    stmt.setString(2, DIVI);
                    stmt.setString(3, BNNO);
                    stmt.setString(4, obj.getString("LB_INVNO"));
                    stmt.setString(5, obj.getString("LB_INVYEAR"));
                    stmt.setString(6, obj.getString("INVDATE").replaceAll("-", ""));

                    stmt.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e.toString());
                    continue;
//                            break;
                }
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void DELDL(String code, String user, String date, String amt, String fee) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " DELETE FROM BRLDTA0100.DDEPOSIT_IN di \n"
                        + " WHERE  DD_DDNO  = '" + code + "' and  DD_TRDT  =  '" + date + "' and  DD_REAMT  = '" + amt + "' and  DD_FEE = '" + fee + "' and  DD_UKEY  = '" + user + "' ";
                System.out.println(query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DELDLo(String code, String user, String date, String amt, String fee) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " DELETE FROM BRLDTA0100.DDEPOSIT_OUT di \n"
                        + " WHERE  DDO_DDNO  = '" + code + "' and  DDO_TRDT  =  '" + date + "' and  DDO_REAMT  = '" + amt + "' and  DDO_FEE = '" + fee + "' and  DDO_UKEY  = '" + user + "' ";
                System.out.println(query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DeleteLineInvoice(String LB_CONO, String LB_DIVI, String LB_BNNO, String LB_INVNO, String LB_INVDT, String LB_INVYEAR) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {
                Double TAX = 0.00;

                Statement stmt = conn.createStatement();
                String query = "DELETE FROM BRLDTA0100.LN_BILLNOTE\n"
                        + "WHERE LB_CONO = '" + LB_CONO + "' AND LB_DIVI = '" + LB_DIVI.trim() + "' AND LB_BNNO = '" + LB_BNNO.trim() + "' AND LB_INVNO = '" + LB_INVNO.trim() + "' AND  LB_INVYEAR = '" + LB_INVYEAR.trim() + "'";
//                System.out.println(query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

}
