/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB2;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

/**
 *
 * @author Jilasak
 */
public class InsertDB2 {

    public static String DBPRD = GBVAR.DBPRD;
    public static String M3DB = GBVAR.M3DB;

    public static void INSERTBM(
            String BM_ID, String BM_CONO, String BM_DIVI, String BM_BANK, String BM_ACCODE,
            String BM_DATE, String BM_TIME, String BM_CHQNO, String BM_TRANSAC, String BM_CUNO,
            String BM_CHANEL, String BM_BRANCH, String BM_AMOUNT, String BM_DESC,
            String BM_RCNO, String BM_TIME1, String BM_USER, String BM_BKCHARGE, String BM_OVPAY,
            String BM_CNDN, String BM_STATUS, String REF2, String REF3, String SENDER,
            String BM_REFCU, String REF4, String BM_REFCU1, String BM_CUNA, String BM_FNNO, String BM_LCODE
    ) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".BANK_MAPPING \n"
                        + "(BM_CONO,  BM_DIVI,  BM_BANK,  BM_ACCODE,\n"
                        + "             BM_DATE,  BM_TIME,  BM_CHQNO,  BM_TRANSAC,  BM_CUNO,\n"
                        + "             BM_CHANEL,  BM_BRANCH,  BM_AMOUNT,  BM_DESC,\n"
                        + "             BM_RCNO,  BM_TIME1,  BM_USER,  BM_BKCHARGE,  BM_OVPAY,\n"
                        + "             BM_CNDN,  BM_STATUS,  REF2,  REF3,  SENDER,\n"
                        + "             BM_REFCU,  REF4,  BM_REFCU1,  BM_CUNA,  BM_FNNO,  BM_LCODE)\n"
                        + "VALUES \n"
                        + "('" + BM_CONO.trim() + "',  '" + BM_DIVI.trim() + "',  '" + BM_BANK.trim() + "',  '" + BM_ACCODE.trim() + "',\n"
                        + "            '" + BM_DATE.trim() + "',  '" + BM_TIME.trim() + "',  '" + BM_CHQNO.trim() + "',  '" + BM_TRANSAC.trim() + "',  '" + BM_CUNO.trim() + "',\n"
                        + "             '" + BM_CHANEL.trim() + "',  '" + BM_BRANCH.trim() + "',  '" + BM_AMOUNT.trim() + "',  '" + BM_DESC.trim() + "',\n"
                        + "             '" + BM_RCNO.trim() + "',  '" + timestamp + "',  '" + BM_USER.trim() + "',  '" + BM_BKCHARGE.trim() + "',  '" + BM_OVPAY.trim() + "',\n"
                        + "             '" + BM_CNDN.trim() + "',  '" + BM_STATUS.trim() + "',  '" + REF2.trim() + "',  '" + REF3.trim() + "',  '" + SENDER.trim() + "',\n"
                        + "             '" + BM_REFCU.trim() + "',  '" + REF4.trim() + "',  '" + BM_REFCU1.trim() + "',  '" + BM_CUNA.trim() + "',  '" + BM_FNNO.trim() + "',  '" + BM_LCODE.trim() + "')";
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

    public static String ADDREQCUSCHK(String cono, String divi, String cusno, String branch, String user, String bank) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        String RCNOTXT = "OK";
        try {
            if (conn != null) {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".REQCUSCHK\n"
                        + " ( CONO ,DIVI, REQ_USER, BRANCH_NAME, CUSTOMER_NO, STATUS, REQ_DATE, OTHER ,BANK\n"
                        + " )\n"
                        + " VALUES \n"
                        + " (" + cono.trim() + ",'" + divi.trim() + "',\n"
                        + " '" + user.trim() + "','" + branch.trim() + "','" + cusno.trim() + "','00','" + dtf.format(now) + "' , '-' ,'"+bank.trim()+"')\n"
                        + " ";
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

        return RCNOTXT;

    }

    public static String INSERTRCNO(String CONO, String DIVI, String RCNO, String TRDT, String PYNO, String REAMT, String TYPE, String BANK, String ACCODE, String PYDT, String CHKNO, String USER, String VCNO, String STS, String BKCHG, String LOCATION, String FIXNO, String RNNO
    ) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        String RCNOTXT = "";
        try {
            if (conn != null) {

                Statement stmt0 = conn.createStatement();
                String query0 = "SELECT MAX(CAST(HC_RCNO  AS integer))+1 AS HR_RCNO  FROM " + DBPRD + ".HR_RECEIPT hr";
                ResultSet mRes = stmt0.executeQuery(query0);
                while (mRes.next()) {
                    RCNOTXT = mRes.getString("HR_RCNO");
                }

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                        + " (HR_CONO,HR_DIVI,HC_RCNO,HC_TRDT,\n"
                        + " HC_PYNO,HC_REAMT,HC_TYPE,HC_BANK,\n"
                        + " HC_ACCODE,HC_PYDT,HC_CHKNO,HC_USER,\n"
                        + " HC_VCNO,HC_STS,HR_BKCHG,HC_LOCATION,\n"
                        + " HC_FIXNO,HC_FNNO\n"
                        + " )\n"
                        + " VALUES \n"
                        + " (" + CONO.trim() + ",'" + DIVI.trim() + "'," + RCNOTXT + ",\n"
                        + " " + TRDT.trim() + ",'" + PYNO.trim() + "'," + REAMT.trim() + ",'" + TYPE.trim() + "','" + BANK.trim() + "',\n"
                        + " '" + ACCODE.trim() + "'," + PYDT.trim() + ",'" + CHKNO.trim() + "','" + USER.trim() + "',\n"
                        + " '" + VCNO.trim() + "','" + STS.trim() + "'," + BKCHG.trim() + ",'" + LOCATION.trim() + "','" + FIXNO.trim() + "','" + RNNO.trim() + "')\n"
                        + " ";
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

        return RCNOTXT;

    }

    public static void INSERTLRNOEXP(String LRE_CONO, String LRE_DIVI, String LRE_RCNO, String LRE_CODE, String LRE_ACTCODE, String LRE_AMT, String LRE_REF1, String LRE_REF2, String LRE_REF3, String LRE_VCTXT, String LRE_FNNO) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " INSERT INTO " + DBPRD + ".LR_LINEEXPEN\n"
                        + " (LRE_CONO,LRE_DIVI,LRE_RCNO,LRE_CODE,LRE_ACTCODE,LRE_AMT,LRE_REF1,LRE_REF2,LRE_REF3,LRE_VCTXT,LRE_FNNO)\n"
                        + " VALUES \n"
                        + " ('" + LRE_CONO.trim() + "','" + LRE_DIVI.trim() + "','" + LRE_RCNO.trim() + "','" + LRE_CODE.trim() + "','" + LRE_ACTCODE.trim() + "','" + LRE_AMT.trim() + "','" + LRE_REF1.trim() + "','" + LRE_REF2.trim() + "','" + LRE_REF3.trim() + "','" + LRE_VCTXT.trim() + "','" + LRE_FNNO.trim() + "')\n"
                        + " ";
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

    public static void INSERTLRNO(String LR_CONO, String LR_DIVI, String LR_RCNO, String LR_INVNO, String LR_INVDT, String LR_INVAMT, String LR_REAMT, String LR_STS) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " INSERT INTO " + DBPRD + ".LR_LINERECEIPT\n"
                        + " (LR_CONO,LR_DIVI,LR_RCNO,LR_INVNO,LR_INVDT,LR_INVAMT,LR_REAMT,LR_STS)\n"
                        + " VALUES \n"
                        + " ('" + LR_CONO.trim() + "','" + LR_DIVI.trim() + "','" + LR_RCNO.trim() + "','" + LR_INVNO.trim() + "','" + LR_INVDT.trim() + "','" + LR_INVAMT.trim() + "','" + LR_REAMT.trim() + "','" + LR_STS.trim() + "')\n"
                        + " ";
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

    public static void INSERTID(String RNNO, String CONO, String DIVI, String RCNO, String CUNO, String PYNO, String STS, String VCNO, String LOCATION, String TYPE) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                        + " (H_CONO,H_DIVI,H_RCNO,H_RNNO,H_CUNO,H_PYNO,H_STS,H_VCNO,H_LOCATION,H_TYPE)\n"
                        + " VALUES \n"
                        + " ('" + CONO.trim() + "','" + DIVI.trim() + "','" + RCNO.trim() + "','" + RNNO.trim() + "','" + CUNO.trim() + "','" + PYNO.trim() + "','" + STS.trim() + "','" + VCNO.trim() + "','" + LOCATION.trim() + "','" + TYPE.trim() + "')\n"
                        + " ";
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

    public static void ADDEXP(String cono, String divi, String RE_DESC, String RE_ACCODE1, String RE_ACCODE2, String RE_OPTION) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " INSERT INTO BRLDTA0100.RECEIPT_EXPENSE\n"
                        + "     (RE_CONO , RE_DIVI , RE_CODE,RE_DESC,RE_ACCODE1,RE_ACCODE2,RE_OPTION,RE_STATUS)\n"
                        + "     VALUES (" + cono.trim() + "," + divi.trim() + ", (SELECT MAX(CAST(RE_CODE AS integer))+1 AS CODE  FROM BRLDTA0100.RECEIPT_EXPENSE re) ,'" + RE_DESC + "','" + RE_ACCODE1 + "','" + RE_ACCODE2 + "','" + RE_OPTION + "','10')";
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

    public static String CreateBillingNote(String CONO, String DIVI, String CUNO, String TRDT, String PONO, String USER) throws Exception {
        UpdateDB2 UPDATE = new UpdateDB2();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        String BNNO = "";
        String userc = CUNO;
        if (userc != "" && (userc.indexOf("TH") != 0 && userc.indexOf("th") != 0 && userc.indexOf("Th") != 0 && userc.indexOf("tH") != 0)) {

            userc = "TH" + userc;
        }
        System.out.println(userc);
        try {
            if (conn != null) {

                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
                String YEAR = date.format(formatter);

                String RNNO = Get_RNNO(CONO, DIVI, YEAR);

//                BNNO = "BP" + YEAR + "-" + RNNO;
                if (USER.equalsIgnoreCase("PHONGS_PHO")) {
                    BNNO = "PP" + "2023" + "-" + "0001";

                } else {
                    BNNO = "BP" + YEAR + "-" + RNNO;
                    UPDATE.UpdateRunning(CONO, DIVI, YEAR, RNNO);

                }

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.H_BILLNOTE\n"
                        + "(HB_CONO, HB_DIVI, HB_BNNO, HB_TRDT, HB_CUNO, HB_PO, HB_USER, HB_STS)\n"
                        + "VALUES('" + CONO.trim() + "', '" + DIVI.trim() + "', '" + BNNO.trim() + "', '" + TRDT.replaceAll("-", "").trim() + "', '" + userc.trim() + "', '" + PONO.trim() + "', '" + USER.trim() + "', '10')";
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
        return BNNO;
    }

    public static void InsertLine(String CONO, String DIVI, String BNNO, String INVNO, String INVDT, String INVYEAR, String INVAMT, String WTTAX) throws Exception {
        UpdateDB2 UPDATE = new UpdateDB2();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {
                Double TAX = 0.00;

                if (WTTAX.equalsIgnoreCase("TRUE")) {
                    TAX = Double.parseDouble(INVAMT) * 0.03;
                }

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.LN_BILLNOTE\n"
                        + "(LB_CONO, LB_DIVI, LB_BNNO, LB_INVNO, LB_INVDT, \n"
                        + "LB_INVYEAR, LB_INVAMT, LB_WHTAX, LB_TAXAMT, LB_STS)\n"
                        + "VALUES('" + CONO.trim() + "', '" + DIVI.trim() + "', '" + BNNO.trim() + "', '" + INVNO.trim() + "', '" + INVDT.replaceAll("-", "").trim() + "',\n"
                        + "'" + INVYEAR.trim() + "', '" + INVAMT.trim() + "', '" + WTTAX.trim() + "', '" + TAX + "', '20')";
                System.out.println(query);
                stmt.execute(query);
//                UPDATE.UpdateStatus(CONO, CUNO, Start, Stop, BNNO);

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

    public static void InsertLineJSON(String CONO, String DIVI, String BNNO, String JSONARRAY) throws Exception {

        UpdateDB2 UPDATE = new UpdateDB2();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        Double TAX = 0.00;

        try (
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO BRLDTA0100.LN_BILLNOTE\n"
                        + "(LB_CONO, LB_DIVI, LB_BNNO, LB_INVNO, LB_INVDT, \n"
                        + "LB_INVYEAR, LB_INVAMT, LB_WHTAX, LB_TAXAMT, LB_STS)\n"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?)")) {

            JSONArray arr = new JSONArray(JSONARRAY);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                try {

                    stmt.setInt(1, Integer.parseInt(CONO));
                    stmt.setString(2, DIVI);
                    stmt.setString(3, BNNO);
                    stmt.setString(4, obj.getString("ESCINO"));
                    stmt.setString(5, obj.getString("ESIVDT").replaceAll("-", ""));
                    stmt.setString(6, obj.getString("ESINYR"));
                    stmt.setString(7, obj.getString("ESCUAM"));
                    stmt.setString(8, obj.getString("WHTAX"));
                    stmt.setDouble(9, TAX);
                    stmt.setString(10, "20");
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

    public static String Get_RNNO(String CONO, String DIVI, String YEAR) {
        String RNNO = "0";

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();
            String sql = "SELECT TO_CHAR(max(RN_RNNO)+1,'0000') AS RN_RNNO\n"
                    + "FROM BRLDTA0100.running_BILLNOTE\n"
                    + "WHERE RN_CONO = '" + CONO.trim() + "'\n"
                    + "AND RN_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND RN_YEAR = '" + YEAR.trim() + "'";

            ResultSet mRes = stmt.executeQuery(sql);
            if (mRes.next()) {
                RNNO = mRes.getString("RN_RNNO").trim();
            }

            return RNNO;
        } catch (Exception ex) {
            Logger.getLogger(InsertDB2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return RNNO;
    }
}
