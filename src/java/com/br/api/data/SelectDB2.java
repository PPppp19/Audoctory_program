/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author Wattana
 */
public class SelectDB2 {

    public static String DBPRD = GBVAR.DBPRD;
    public static String M3DB = GBVAR.M3DB;

    public static JSONArray GETREQCUSCHK() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "  SELECT CONO,DIVI,REQ_USER,BRANCH_NAME,CUSTOMER_NO,STATUS,REQ_DATE ,OTHER, BANK FROM BRLDTA0100.REQCUSCHK  ";
                System.out.println("SelectGETREQCUSCHK\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CONO", mRes.getString(1).trim());
                    mMap.put("DIVI", mRes.getString(2).trim());
                    mMap.put("REQ_USER", mRes.getString(3).trim());
                    mMap.put("BRANCH_NAME", mRes.getString(4).trim());
                    mMap.put("CUSTOMER_NO", mRes.getString(5).trim());
                    mMap.put("STATUS", mRes.getString(6).trim());
                    mMap.put("REQ_DATE", mRes.getString(7).trim());
                    mMap.put("OTHER", mRes.getString(8).trim());
                      mMap.put("BANK", mRes.getString(9).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray Location(String CONO, String DIVI) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT RL_CONO, RL_DIVI,RL_LCCODE, RL_LCDESC\n"
                        + " FROM " + DBPRD + ".RECEIPT_LOCAFNC\n"
                        + " WHERE RL_CONO = '" + CONO.trim() + "'\n"
                        + " AND RL_DIVI ='" + DIVI.trim() + "'\n"
                        + " AND RL_STS = '20'";
                System.out.println("SelectCompany\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RL_CONO", mRes.getString(1).trim());
                    mMap.put("RL_DIVI", mRes.getString(2).trim());
                    mMap.put("RL_LCCODE", mRes.getString(3).trim());
                    mMap.put("RL_LCDESC", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static boolean CHECKEXP(String CONO, String DIVI, String ID) throws Exception {

        boolean chksts = false;
        String sts = "0";
        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  H_TYPE  , H_RCNO  FROM " + DBPRD + ".HEAD_RECIPT\n"
                        + "WHERE H_RNNO  = '" + ID.trim() + "' AND H_CONO  = '" + CONO.trim() + "' AND H_DIVI  = '" + DIVI.trim() + "' ORDER BY  H_RCNO  DESC";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    sts = mRes.getString("H_TYPE");

                }

                if (sts.equalsIgnoreCase("TRANSFER_EXP")) {

                    chksts = true;
                } else if (sts.equalsIgnoreCase("CHECK_EXP")) {

                    chksts = true;
                } else if (sts.equalsIgnoreCase("CASH_EXP")) {

                    chksts = true;
                }

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

        return chksts;

    }

    public static boolean CHKISBM(String CONO, String DIVI, String ID) throws Exception {

        int ccountbm = 0;
        boolean isBM = false;
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  COUNT(BM_FNNO) AS CCBANK_MAPPING  FROM " + DBPRD + ".BANK_MAPPING bm  \n"
                        + "WHERE  BM_FNNO  = '" + ID.trim() + "' AND BM_CONO = '" + CONO.trim() + "' AND BM_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    ccountbm = mRes.getInt("CCBANK_MAPPING");
                }

                if (ccountbm > 0) {
                    isBM = true;
                }

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

        return isBM;

    }

    public static boolean CHECKSTS(String CONO, String DIVI, String ID) throws Exception {

        boolean chksts = false;
        String sts = "0";
        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  COALESCE(H_STS,'1') AS H_STS  , H_RCNO FROM " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_RNNO  = '" + ID.trim() + "' AND H_CONO = '" + CONO.trim() + "' AND H_DIVI = '" + DIVI.trim() + "' ORDER BY H_RCNO DESC";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    sts = mRes.getString("H_STS");

                }

                if (!sts.equalsIgnoreCase("3")) {

                    chksts = true;
                }

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

        return chksts;

    }

    public static JSONArray CALLCODE() throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT BM_ACCODE , BM_BANK , BM_LCODE  FROM  BRLDTA0100.BANK_MAPPING bm \n"
                        + "WHERE  BM_LCODE  IS NOT NULL  AND BM_LCODE  != ''\n"
                        + "AND BM_LCODE  != '-'\n"
                        + "AND BM_LCODE  != 'cc' ";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("BM_ACCODE", mRes.getString(1).trim());
                    mMap.put("BM_BANK", mRes.getString(2).trim());
                    mMap.put("BM_LCODE", mRes.getString(3).trim());

                    mJSonArr.put(mMap);
//
                }

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

        return mJSonArr;

    }

    public static String CheckINVSTS(String cono, String divi, String invno) throws Exception {

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " SELECT acstcf FROM " + GBVAR.M3DB + ".FCR040 WHERE  ACCINO LIKE '" + invno.trim() + "%'";
//                String query = " SELECT acstcf FROM M3FDBprd.FCR040 WHERE ACCONO ='"+cono+"' AND ACDIVI ='"+divi+"' AND ACCINO LIKE '"+invno.trim()+"%'";
                System.out.println("CheckINVSTS \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    stats = mRes.getString("ACSTCF");
                }

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

        return stats;

    }

    public static JSONArray CALLLRNOEXP(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE( LRE_CONO,'-') AS LRE_CONO, COALESCE(LRE_DIVI,'-') AS LRE_DIVI,\n"
                        + "COALESCE(LRE_RCNO,'-') AS LRE_RCNO ,COALESCE(LRE_CODE,'-') AS LRE_CODE,\n"
                        + "COALESCE(LRE_ACTCODE,'-') AS LRE_ACTCODE,COALESCE(LRE_AMT,'-') AS LRE_AMT ,\n"
                        + "COALESCE(LRE_REF1,'-') AS LRE_REF1, \n"
                        + "COALESCE(LRE_REF2,'-') AS LRE_REF2,COALESCE(LRE_REF3,'-') AS LRE_REF3,COALESCE(LRE_VCTXT,'-') AS LRE_VCTXT,COALESCE(LRE_FNNO,'-') AS  LRE_FNNO\n"
                        + "FROM " + DBPRD + ".LR_LINEEXPEN ll \n"
                        + "WHERE  LRE_RCNO  = '" + RCNO.trim() + "' AND  LRE_CONO  = '" + CONO.trim() + "'AND  LRE_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("LRE_CONO", mRes.getString(1).trim());
                    mMap.put("LRE_DIVI", mRes.getString(2).trim());
                    mMap.put("LRE_RCNO", mRes.getString(3).trim());
                    mMap.put("LRE_CODE", mRes.getString(4).trim());
                    mMap.put("LRE_ACTCODE", mRes.getString(5).trim());
                    mMap.put("LRE_AMT", mRes.getString(6).trim());
                    mMap.put("LRE_REF1", mRes.getString(7).trim());
                    mMap.put("LRE_REF2", mRes.getString(8).trim());
                    mMap.put("LRE_REF3", mRes.getString(9).trim());
                    mMap.put("LRE_VCTXT", mRes.getString(10).trim());
                    mMap.put("LRE_FNNO", mRes.getString(11).trim());

                    mJSonArr.put(mMap);
//
                }

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

        return mJSonArr;

    }

    public static JSONArray CALLRCNO(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(HR_CONO , '-') AS HR_CONO,\n"
                        + " COALESCE(HR_DIVI, '-') AS HR_DIVI,\n"
                        + " COALESCE(HC_RCNO, '-')AS HC_RCNO,\n"
                        + " COALESCE(HC_TRDT, '-')AS HC_TRDT,\n"
                        + " COALESCE(HC_PYNO, '-')AS HC_PYNO,\n"
                        + " COALESCE(HC_REAMT, '-')AS HC_REAMT,\n"
                        + " COALESCE(HC_TYPE, '-')AS HC_TYPE,\n"
                        + " COALESCE(HC_BANK, '-')AS HC_BANK,\n"
                        + " COALESCE(HC_ACCODE, '-')AS HC_ACCODE,\n"
                        + " COALESCE(HC_PYDT, '-')AS HC_PYDT,\n"
                        + " COALESCE(HC_CHKNO, '-')AS HC_CHKNO,\n"
                        + " COALESCE(HC_USER, '-')AS HC_USER,\n"
                        + " COALESCE(HC_VCNO, '-')AS HC_VCNO,\n"
                        + " COALESCE(HC_STS, '-')AS HC_STS,\n"
                        + " COALESCE(HR_BKCHG, '-')AS HR_BKCHG,\n"
                        + " COALESCE(HC_LOCATION, '-')AS HC_LOCATION,\n"
                        + " COALESCE(HC_FIXNO, '-')AS HC_FIXNO,\n"
                        + " COALESCE(HC_FNNO, '-')AS HC_FNNO FROM " + DBPRD + ".HR_RECEIPT  \n"
                        + " WHERE HC_RCNO  = '" + RCNO.trim() + "'\n"
                        + " AND HR_CONO  = '" + CONO.trim() + "'\n"
                        + " AND HR_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_TYPE", mRes.getString(7).trim());
                    mMap.put("HC_BANK", mRes.getString(8).trim());
                    mMap.put("HC_ACCODE", mRes.getString(9).trim());
                    mMap.put("HC_PYDT", mRes.getString(10).trim());
                    mMap.put("HC_CHKNO", mRes.getString(11).trim());
                    mMap.put("HC_USER", mRes.getString(12).trim());
                    mMap.put("HC_VCNO", mRes.getString(13).trim());
                    mMap.put("HC_STS", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("HC_LOCATION", mRes.getString(16).trim());
                    mMap.put("HC_FIXNO", mRes.getString(17).trim());
                    mMap.put("HC_FNNO", mRes.getString(18).trim());

                    mJSonArr.put(mMap);
//
                }

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

        return mJSonArr;

    }

    public static JSONArray CALLLRNO(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE( LR_CONO,'-') AS LR_CONO, COALESCE(LR_DIVI,'-') AS LR_DIVI,\n"
                        + "COALESCE(LR_RCNO,'-') AS LR_RCNO ,COALESCE(LR_INVNO,'-') AS LR_INVNO,\n"
                        + "COALESCE(LR_INVDT,'-') AS LR_INVDT,COALESCE(LR_INVAMT,'-') AS LR_INVAMT,\n"
                        + "COALESCE(LR_REAMT,'-') AS LR_REAMT,COALESCE(LR_STS,'-') AS LR_STS \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT ll \n"
                        + "WHERE  LR_RCNO  = '" + RCNO.trim() + "' AND  LR_CONO  = '" + CONO.trim() + "'AND  LR_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("LR_CONO", mRes.getString(1).trim());
                    mMap.put("LR_DIVI", mRes.getString(2).trim());
                    mMap.put("LR_RCNO", mRes.getString(3).trim());
                    mMap.put("LR_INVNO", mRes.getString(4).trim());
                    mMap.put("LR_INVDT", mRes.getString(5).trim());
                    mMap.put("LR_INVAMT", mRes.getString(6).trim());
                    mMap.put("LR_REAMT", mRes.getString(7).trim());
                    mMap.put("LR_STS", mRes.getString(8).trim());

                    mJSonArr.put(mMap);
//
                }

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

        return mJSonArr;

    }

    public static JSONArray CALLBM(String vCONOBM, String vDIVILBM, String vBANKBM, String vDATEBM, String vTIMEBM, String vAMOUNTBM) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE (BM_ID,'-') AS BM_ID,COALESCE (BM_CONO,'-') AS BM_CONO,COALESCE (BM_DIVI,'-') AS BM_DIVI,\n"
                        + "COALESCE (BM_BANK,'-') AS BM_BANK,COALESCE (BM_ACCODE,'-') AS BM_ACCODE,COALESCE (BM_DATE,'-') AS BM_DATE,\n"
                        + "COALESCE (BM_TIME,'-') AS BM_TIME,COALESCE (BM_CHQNO,'-') AS BM_CHQNO,COALESCE (BM_TRANSAC,'-') AS BM_TRANSAC,\n"
                        + "COALESCE (BM_CUNO,'-') AS BM_CUNO,COALESCE (BM_CHANEL,'-') AS BM_CHANEL,COALESCE (BM_BRANCH,'-') AS BM_BRANCH,\n"
                        + "COALESCE (BM_AMOUNT,'-') AS BM_AMOUNT,COALESCE (BM_DESC,'-') AS BM_DESC,COALESCE (BM_RCNO,'-') AS BM_RCNO,\n"
                        + "BM_TIME1 AS BM_TIME1,COALESCE (BM_USER,'-') AS BM_USER,COALESCE (BM_BKCHARGE,'-') AS BM_BKCHARGE,\n"
                        + "COALESCE (BM_OVPAY,'-') AS BM_OVPAY,COALESCE (BM_CNDN,'-') AS BM_CNDN,COALESCE (BM_STATUS,'-') AS BM_STATUS,\n"
                        + "COALESCE (REF2,'-') AS REF2,COALESCE (REF3,'-') AS REF3,COALESCE (SENDER,'-') AS SENDER,\n"
                        + "COALESCE (BM_REFCU,'-') AS BM_REFCU,COALESCE (REF4,'-') AS REF4,COALESCE (BM_REFCU1,'-') AS BM_REFCU1,\n"
                        + "COALESCE (BM_CUNA,'-') AS BM_CUNA,COALESCE (BM_FNNO,'-') AS BM_FNNO,COALESCE (BM_LCODE,'-') AS BM_LCODE\n"
                        + "FROM " + DBPRD + ".BANK_MAPPING bm  \n"
                        + "WHERE BM_CONO  = '" + vCONOBM.trim() + "'\n"
                        + "AND BM_DIVI  = '" + vDIVILBM.trim() + "'\n"
                        + "AND BM_BANK  = '" + vBANKBM.trim() + "'\n"
                        + "AND BM_DATE  = '" + vDATEBM.trim() + "'\n"
                        + "AND BM_TIME  = '" + vTIMEBM.trim() + "'\n"
                        + "AND  BM_AMOUNT  = '" + vAMOUNTBM.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("BM_ID", mRes.getString(1).trim());
                    mMap.put("BM_CONO", mRes.getString(2).trim());
                    mMap.put("BM_DIVI", mRes.getString(3).trim());
                    mMap.put("BM_BANK", mRes.getString(4).trim());
                    mMap.put("BM_ACCODE", mRes.getString(5).trim());
                    mMap.put("BM_DATE", mRes.getString(6).trim());
                    mMap.put("BM_TIME", mRes.getString(7).trim());
                    mMap.put("BM_CHQNO", mRes.getString(8).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(9).trim());
                    mMap.put("BM_CUNO", mRes.getString(10).trim());

                    mMap.put("BM_CHANEL", mRes.getString(11).trim());
                    mMap.put("BM_BRANCH", mRes.getString(12).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(13).trim());
                    mMap.put("BM_DESC", mRes.getString(14).trim());
                    mMap.put("BM_RCNO", mRes.getString(15).trim());
                    mMap.put("BM_TIME1", mRes.getString(16).trim());
                    mMap.put("BM_USER", mRes.getString(17).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(18).trim());
                    mMap.put("BM_OVPAY", mRes.getString(19).trim());
                    mMap.put("BM_CNDN", mRes.getString(20).trim());

                    mMap.put("BM_STATUS", mRes.getString(21).trim());
                    mMap.put("REF2", mRes.getString(22).trim());
                    mMap.put("REF3", mRes.getString(23).trim());
                    mMap.put("SENDER", mRes.getString(24).trim());
                    mMap.put("BM_REFCU", mRes.getString(25).trim());
                    mMap.put("REF4", mRes.getString(26).trim());
                    mMap.put("BM_REFCU1", mRes.getString(27).trim());
                    mMap.put("BM_CUNA", mRes.getString(28).trim());
                    mMap.put("BM_FNNO", mRes.getString(29).trim());
                    mMap.put("BM_LCODE", mRes.getString(30).trim());

                    mJSonArr.put(mMap);
//
                }

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

        return mJSonArr;

    }

    public static JSONArray CALLTABLE(String TABLE_SCHEMA, String TABLE_NAME) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COLUMN_NAME,TABLE_NAME,DATA_TYPE,LENGTH,ORDINAL_POSITION\n"
                        + "FROM QSYS2.SYSCOLUMNS\n"
                        + "WHERE TABLE_SCHEMA = '" + TABLE_SCHEMA.trim().toUpperCase() + "'\n"
                        + "AND TABLE_NAME = '" + TABLE_NAME.trim().toUpperCase() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("COLUMN_NAME", mRes.getString(1).trim());
                    mMap.put("TABLE_NAME", mRes.getString(2).trim());
                    mMap.put("DATA_TYPE", mRes.getString(3).trim());
                    mMap.put("LENGTH", mRes.getString(4).trim());
                    mMap.put("ORDINAL_POSITION", mRes.getString(5).trim());

                    mJSonArr.put(mMap);
//
                }

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

        return mJSonArr;

    }

    public static JSONArray CALLID(String CONO, String DIVI, String ID) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  COALESCE (H_CONO,'-') AS H_CONO , COALESCE (H_DIVI,'-') AS H_DIVI,COALESCE (H_RCNO,'-') AS H_RCNO,COALESCE (H_RNNO,'-') AS H_RNNO,COALESCE (H_CUNO,'-') AS H_CUNO,COALESCE (H_PYNO,'-') AS H_PYNO,COALESCE (H_STS,'-') AS H_STS,COALESCE (H_VCNO,'-') AS H_VCNO,COALESCE (H_LOCATION,'-') AS H_LOCATION,COALESCE (H_TYPE,'-') AS H_TYPE  FROM " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_RNNO  = '" + ID.trim() + "' AND h_cono = '" + CONO.trim() + "' and h_divi = '" + DIVI.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("H_CONO", mRes.getString(1).trim());
                    mMap.put("H_DIVI", mRes.getString(2).trim());
                    mMap.put("H_RCNO", mRes.getString(3).trim());
                    mMap.put("H_RNNO", mRes.getString(4).trim());
                    mMap.put("H_CUNO", mRes.getString(5).trim());
                    mMap.put("H_PYNO", mRes.getString(6).trim());
                    mMap.put("H_STS", mRes.getString(7).trim());
                    mMap.put("H_VCNO", mRes.getString(8).trim());
                    mMap.put("H_LOCATION", mRes.getString(9).trim());
                    mMap.put("H_TYPE", mRes.getString(10).trim());

                    mJSonArr.put(mMap);
//
                }

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

        return mJSonArr;

    }

    public static JSONArray CALLQUERY(String vQUERY) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
//                String query = "SELECT * FROM  M3FDBTST.HR_RECEIPT";
                String query = vQUERY + " LIMIT 100";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                ResultSetMetaData rsmd = mRes.getMetaData();
                int column_count = rsmd.getColumnCount();

                System.out.println(column_count);
                String id = "DD";
                int ccount = 0;

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    System.out.println(ccount++);

                    for (int j = 1; j <= column_count; j++) {

                        if (mRes.getString(j) != null) {
                            System.out.println("ADD " + id + j + " : " + mRes.getString(j).trim());
                            mMap.put(id + j, mRes.getString(j).trim());
                        } else {
                            System.out.println("ADD " + id + j + " : " + "-");
                            mMap.put(id + j, "-");

                        }
                    }

                    mJSonArr.put(mMap);
                }

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

        return mJSonArr;

    }

    public static String getEBK(String cono, String divi, String pyno, String vcno, String date) throws Exception {

        String AMT = "";

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  PPCUAM FROM BRLDTA0100.EBK_PAYMAL ep  \n"
                        + "WHERE  PPSPYN  = '" + pyno.trim() + "'\n"
                        + "AND PPCONO  = '" + cono.trim() + "'\n"
                        + "AND PPDIVI  = '" + divi.trim() + "'\n"
                        + "AND PPVONO  = '" + vcno.trim() + "'\n"
                        + "AND PPDUDT  = '" + date.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    AMT = mRes.getString("PPCUAM");
                }

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
        System.out.println(AMT);
        return AMT;

    }

    public static JSONArray getdailylisto(String code) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = " SELECT COALESCE(DDO_DDNO,'-'),DDO_TRDT,COALESCE(DDO_REAMT,'-'),COALESCE(DDO_UKEY,'-'),COALESCE(DDO_FEE,'-') FROM  BRLDTA0100.DDEPOSIT_out di \n"
                        + " WHERE  DDO_DDNO  = '" + code + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("DDNO", mRes.getString(1).trim());
                    mMap.put("TRDT", mRes.getString(2).trim());
                    mMap.put("AMOUT", mRes.getString(3).trim());
                    mMap.put("USER", mRes.getString(4).trim());
                    mMap.put("FEE", mRes.getString(5).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray getdailylist(String code) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = " SELECT COALESCE(DD_DDNO,'-') ,DD_TRDT ,COALESCE(DD_REAMT,'-') ,COALESCE(DD_UKEY,'-') ,COALESCE(DD_UHOLD,'-') ,COALESCE(DD_FEE,'-')  FROM  BRLDTA0100.DDEPOSIT_IN di \n"
                        + " WHERE  DD_DDNO  = '" + code + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("DDNO", mRes.getString(1).trim());
                    mMap.put("TRDT", mRes.getString(2).trim());
                    mMap.put("AMOUT", mRes.getString(3).trim());
                    mMap.put("USER", mRes.getString(4).trim());
                    mMap.put("UHOLD", mRes.getString(5).trim());
                    mMap.put("FEE", mRes.getString(6).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray getdate(String cono) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT MSSDTE , MSEDTE FROM BRLDTA0100.MMSDTE\n"
                        + "WHERE  MSCONO  = '" + cono + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

//                    mMap.put("ESPYNO", mRes.getString(1).trim());
                    mMap.put("MSSDTE", mRes.getString(1).trim());
                    mMap.put("MSEDTE", mRes.getString(2).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray GridBP(String user) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = " \n"
                        + " \n"
                        + " SELECT DISTINCT  h.HB_BNNO , COALESCE(d.LB_BNNO,'-') AS LB_BNNO , H.HB_CUNO ,\n"
                        + " CASE WHEN d.LB_BNNO IS NULL THEN 'NO INVOICE'\n"
                        + " ELSE 'INVOICE'\n"
                        + " END AS STATUS , HB_TRDT, HB_PO , m.BPM_RINV, h.HB_STS \n"
                        + " FROM (\n"
                        + " SELECT *  FROM BRLDTA0100.H_BILLNOTE \n"
                        + " ) AS h\n"
                        + " LEFT JOIN \n"
                        + " (\n"
                        + " SELECT * FROM  BRLDTA0100.LN_BILLNOTE\n"
                        + " ) AS d\n"
                        + " ON   d.LB_BNNO = h.HB_BNNO\n"
                        + "\n"
                        + " LEFT  JOIN  \n"
                        + " (\n"
                        + " SELECT * FROM BRLDTA0100.BP_MASTER \n"
                        + " ) AS m\n"
                        + " ON  h.hb_CUNO = m.BPM_CUNO\n"
                        + "  WHERE h.HB_USER = '" + user + "'\n"
                        + " AND h.HB_STS != '20' AND  h.HB_STS != '90' ";

//                                String query = " SELECT DISTINCT  h.HB_BNNO , COALESCE(d.LB_BNNO,'-') AS LB_BNNO , H.HB_CUNO ,\n"
//                                        + " CASE WHEN d.LB_BNNO IS NULL THEN 'NOT COMPLETE'\n"
//                                        + " ELSE 'COMPLETE'\n"
//                                        + " END AS STATUS , HB_TRDT, HB_PO\n"
//                                        + " FROM (\n"
//                                        + " SELECT *  FROM BRLDTA0100.H_BILLNOTE \n"
//                                        + " ) AS h\n"
//                                        + " LEFT JOIN \n"
//                                        + " (\n"
//                                        + " SELECT * FROM  BRLDTA0100.LN_BILLNOTE\n"
//                                        + " ) AS d\n"
//                                        + " ON   d.LB_BNNO = h.HB_BNNO\n"
//                                        + " WHERE h.HB_USER = '" + user + "' \n"
//                                        + " AND d.LB_BNNO IS NULL ";
                //                       + " WHERE h.HB_USER = '" + user + "' \n"
                //                        + " AND d.lb_BNNO IS  NULL ";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

//                    mMap.put("ESPYNO", mRes.getString(1).trim());
                    mMap.put("HB_BNNO", mRes.getString(1).trim());
                    mMap.put("LB_BNNO", mRes.getString(2).trim());
                    mMap.put("HB_CUNO", mRes.getString(3).trim());
                    mMap.put("STATUS", mRes.getString(4).trim());
                    mMap.put("HB_TRDT", mRes.getString(5).trim());
                    mMap.put("HB_PO", mRes.getString(6).trim());

                    if (mRes.getString(7) != null) {
                        mMap.put("BPM_RINV", mRes.getString(7).trim());
                    } else {
                        mMap.put("BPM_RINV", "-");
                    }
                    mMap.put("HB_STS", mRes.getString(8).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray checkGrid_ARS200(String cono, String divi, String payer, String type, String ENDDATE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT C.*,SUBSTR(D.ESIVDT,1,4) || '-' || SUBSTR(D.ESIVDT,5,2)|| '-' || SUBSTR(D.ESIVDT,7,2) as ESIVDT,'false' as WHTAX\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT  " + type + ",ESCINO,ESINYR,ESCUCD,SUM(ESCUAM) as ESCUAM\n"
                        + "FROM M3FDBPRD.FSLEDG\n"
                        + "WHERE ESCONO = '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + "AND " + type + " = '" + payer.trim() + "'\n"
                        + "AND ESCINO NOT IN (\n"
                        + "SELECT LB_INVNO\n"
                        + "FROM BRLDTA0100.H_BILLNOTE,BRLDTA0100.LN_BILLNOTE\n"
                        + "WHERE HB_CONO = LB_CONO\n"
                        + "AND HB_DIVI = LB_DIVI\n"
                        + "AND HB_BNNO = LB_BNNO\n"
                        + "AND HB_CONO = '" + cono.trim() + "'\n"
                        + "AND HB_DIVI = '" + divi.trim() + "'\n"
                        + "AND HB_CUNO = '" + payer.trim() + "'\n"
                        //                        + "AND LB_STS = '20' \n"
                        + "AND LB_STS IS NOT NULL \n"
                        + ")\n"
                        + "GROUP BY " + type + ",ESCINO,ESINYR,ESCUCD\n"
                        + "HAVING  SUM(ESCUAM) <> 0\n"
                        + ") C LEFT JOIN (\n"
                        + " SELECT  DISTINCT " + type + ",ESCINO,ESIVDT,ESINYR,ESCUCD\n"
                        + "FROM M3FDBPRD.FSLEDG \n"
                        + "WHERE ESCONO =  '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + ") D ON D." + type + " = C." + type + " AND D.ESCINO = C.ESCINO\n "
                        + "WHERE  ESIVDT  <=  '" + ENDDATE + "' \n"
                        + "ORDER  BY ESCINO ASC,ESIVDT \n";

                System.err.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

//                    mMap.put("ESPYNO", mRes.getString(1).trim());
                    mMap.put("ESCUNO", mRes.getString(1).trim());
                    mMap.put("ESCINO", mRes.getString(2).trim());
                    mMap.put("ESINYR", mRes.getString(3).trim());
                    mMap.put("ESCUCD", mRes.getString(4).trim());
                    mMap.put("ESCUAM", mRes.getString(5).trim());
                    mMap.put("ESIVDT", mRes.getString(6).trim());
                    mMap.put("WHTAX", mRes.getBoolean(7));

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray GetPaymentDate(String CONO, String CUNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " \n"
                        + " SELECT  BPH_BPDT as DATE ,BPH_STDT as START ,  BPH_FNDT as END   FROM BRLDTA0100.BP_HBILL bh \n"
                        + " WHERE BPH_CUNO  = '" + CUNO + "'\n"
                        + " AND BPH_CONO  = '" + CONO + "'";
                ResultSet mRes = stmt.executeQuery(query);

                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("DATE", mRes.getString(1).trim());
                    mMap.put("START", mRes.getString(2).trim());
                    mMap.put("END", mRes.getString(3).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray Company() throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CCCONO,CCDIVI,CCCONM,'\"'|| TRIM(CCCONO) || ' : ' || TRIM(CCDIVI) || ' : ' || TRIM(CCCONM) || '\"' AS COMPANY\n"
                        + "FROM M3FDBPRD.CMNDIV\n"
                        + "WHERE CCDIVI NOT IN  ('','120','130')\n"
                        + "ORDER BY CCCONO";
//                System.out.println("SelectCompany\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CCCONO", mRes.getString(1).trim());
                    mMap.put("CCDIVI", mRes.getString(2).trim());
                    mMap.put("CCCONM", mRes.getString(3).trim());
                    mMap.put("COMPANY", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray SetnamePayer(String cono, String divi, String code) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        String PAYERNAME = "NOT FOUND";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(OKCUNM,'-') AS PAYERNAME FROM M3FDBPRD.OCUSMA WHERE OKCONO = '" + cono.trim() + "'  AND OKCUNO = '" + code.trim() + "'";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    PAYERNAME = mRes.getString(1).trim();
                }
                Map<String, Object> mMap = new HashMap<>();
                mMap.put("PAYERNAME", PAYERNAME);
                mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray Grid_ARS200(String cono, String divi, String payer, String type, String ENDDATE) throws Exception {

        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPP");

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT C.*,SUBSTR(D.ESIVDT,1,4) || '-' || SUBSTR(D.ESIVDT,5,2)|| '-' || SUBSTR(D.ESIVDT,7,2) as ESIVDT,'false' as WHTAX\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT  " + type + ",ESCINO,ESINYR,ESCUCD,SUM(ESCUAM) as ESCUAM\n"
                        + "FROM M3FDBPRD.FSLEDG\n"
                        + "WHERE ESCONO = '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + "AND " + type + " = '" + payer.trim() + "'\n"
                        + "AND ESCINO NOT IN (\n"
                        + "SELECT LB_INVNO\n"
                        + "FROM BRLDTA0100.H_BILLNOTE,BRLDTA0100.LN_BILLNOTE\n"
                        + "WHERE HB_CONO = LB_CONO\n"
                        + "AND HB_DIVI = LB_DIVI\n"
                        + "AND HB_BNNO = LB_BNNO\n"
                        + "AND HB_CONO = '" + cono.trim() + "'\n"
                        + "AND HB_DIVI = '" + divi.trim() + "'\n"
                        + "AND HB_CUNO = '" + payer.trim() + "'\n"
                        //                        + "AND LB_STS = '20' \n"
                        + "AND LB_STS IS NOT NULL \n"
                        + ")\n"
                        + "GROUP BY " + type + ",ESCINO,ESINYR,ESCUCD\n"
                        + "HAVING  SUM(ESCUAM) <> 0\n"
                        + ") C LEFT JOIN (\n"
                        + " SELECT  DISTINCT " + type + ",ESCINO,ESIVDT,ESINYR,ESCUCD\n"
                        + "FROM M3FDBPRD.FSLEDG \n"
                        + "WHERE ESCONO =  '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + ") D ON D." + type + " = C." + type + " AND D.ESCINO = C.ESCINO\n "
                        + "WHERE  ESIVDT  <=  '" + ENDDATE + "' \n"
                        + "AND  c.ESCINO NOT LIKE '85%'  \n"
                        + "ORDER  BY ESCINO ASC,ESIVDT \n";

                System.err.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

//                    mMap.put("ESPYNO", mRes.getString(1).trim());
                    mMap.put("ESCUNO", mRes.getString(1).trim());
                    mMap.put("ESCINO", mRes.getString(2).trim());
                    mMap.put("ESINYR", mRes.getString(3).trim());
                    mMap.put("ESCUCD", mRes.getString(4).trim());
                    mMap.put("ESCUAM", mRes.getString(5).trim());
                    mMap.put("ESIVDT", mRes.getString(6).trim());
                    mMap.put("WHTAX", mRes.getBoolean(7));

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray ShowBillingNO(String BBNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        try {
            Map<String, Object> mMap = new HashMap<>();
            mMap.put("BBNO", BBNO);
            mJSonArr.put(mMap);
        } catch (Exception e) {
            e.printStackTrace();

            throw e;
        }
        return mJSonArr;

    }

    public static JSONArray Search_BillingNote(String CONO, String DIVI, String BNNO, String USER) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT HB_CONO, HB_DIVI, HB_BNNO, SUBSTRING(HB_TRDT,1,4)  || SUBSTRING(HB_TRDT,5,2)  || SUBSTRING(HB_TRDT,7,2) AS HB_TRDT, HB_CUNO, HB_PO, HB_USER, HB_STS\n"
                        + "FROM BRLDTA0100.H_BILLNOTE\n"
                        + "WHERE HB_CONO = '" + CONO.trim() + "'\n"
                        + "AND HB_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND HB_BNNO = '" + BNNO.trim() + "'\n"
                        + "AND HB_USER = '" + USER.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("HB_CONO", mRes.getString(1).trim());
                    mMap.put("HB_DIVI", mRes.getString(2).trim());
                    mMap.put("HB_BNNO", mRes.getString(3).trim());
                    mMap.put("HB_TRDT", mRes.getString(4).trim());
                    mMap.put("HB_CUNO", mRes.getString(5).trim());
                    mMap.put("HB_PO", mRes.getString(6).trim());
                    mMap.put("HB_USER", mRes.getString(7).trim());
                    mMap.put("HB_STS", mRes.getBoolean(8));

                    mJSonArr.put(mMap);

                }
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

        return mJSonArr;

    }

    public static JSONArray Search_BillingNote2(String CONO, String DIVI, String BNNO, String USER) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT HB_CONO, HB_DIVI, HB_BNNO, SUBSTRING(HB_TRDT,1,4)  || SUBSTRING(HB_TRDT,5,2)  || SUBSTRING(HB_TRDT,7,2) AS HB_TRDT, HB_CUNO, HB_PO, HB_USER, HB_STS\n"
                        + "FROM BRLDTA0100.H_BILLNOTE\n"
                        + "WHERE HB_CONO = '" + CONO.trim() + "'\n"
                        + "AND HB_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND HB_BNNO = '" + BNNO.trim() + "'\n"
                        + "AND HB_USER = '" + USER.trim() + "'"
                        + "AND HB_STS = '20'";

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("HB_CONO", mRes.getString(1).trim());
                    mMap.put("HB_DIVI", mRes.getString(2).trim());
                    mMap.put("HB_BNNO", mRes.getString(3).trim());
                    mMap.put("HB_TRDT", mRes.getString(4).trim());
                    mMap.put("HB_CUNO", mRes.getString(5).trim());
                    mMap.put("HB_PO", mRes.getString(6).trim());
                    mMap.put("HB_USER", mRes.getString(7).trim());
                    mMap.put("HB_STS", mRes.getBoolean(8));

                    mJSonArr.put(mMap);

                }
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

        return mJSonArr;

    }

    public static JSONArray Grid_BillingNote(String cono, String divi, String billnote) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT LB_CONO, LB_DIVI, LB_BNNO, LB_INVNO, LB_INVDT, LB_INVYEAR, LB_INVAMT, LB_WHTAX, LB_TAXAMT,"
                        + " CASE WHEN LB_STS = '20' THEN 'true' ELSE 'false' END AS LB_STS, LB_INVAMT - LB_TAXAMT as BL,SUBSTR(LB_INVDT,1,4) || '-' || SUBSTR(LB_INVDT,5,2)|| '-' || SUBSTR(LB_INVDT,7,2) as INVDATE\n"
                        + "FROM BRLDTA0100.LN_BILLNOTE\n"
                        + "WHERE LB_CONO = '" + cono.trim() + "'\n"
                        + "AND LB_DIVI = '" + divi.trim() + "'\n"
                        + "AND LB_BNNO = '" + billnote.trim() + "' \n "
                        + "ORDER BY LB_INVNO asc,INVDATE ";

//                System.err.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("LB_CONO", mRes.getString(1).trim());
                    mMap.put("LB_DIVI", mRes.getString(2).trim());
                    mMap.put("LB_BNNO", mRes.getString(3).trim());
                    mMap.put("LB_INVNO", mRes.getString(4).trim());
                    mMap.put("LB_INVDT", mRes.getString(5).trim());
                    mMap.put("LB_INVYEAR", mRes.getString(6).trim());
                    mMap.put("LB_INVAMT", mRes.getString(7).trim());
                    mMap.put("LB_WHTAX", mRes.getBoolean(8));
                    mMap.put("LB_TAXAMT", mRes.getString(9).trim());
                    mMap.put("LB_STS", mRes.getBoolean(10));
                    mMap.put("BL", mRes.getString(11).trim());
                    mMap.put("INVDATE", mRes.getString(12).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray Monitoring(String cono, String divi, String user) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT HB_CONO, HB_DIVI, HB_BNNO, HB_TRDT, HB_CUNO, HB_PO, HB_USER, HB_STS,SUBSTRING(HB_TRDT,7,2) || '-' || SUBSTRING(HB_TRDT,5,2) || '-' ||SUBSTRING(HB_TRDT,1,4) AS H_DATE \n"
                        + ",HB_CUNO || ' ' || TRIM(OKCUNM) AS ENAME,TRIM(OKCUNM) as OKCUNM \n"
                        + "FROM BRLDTA0100.H_BILLNOTE\n"
                        + "LEFT JOIN M3FDBPRD.OCUSMA ON OKSTAT = '20' AND  HB_CUNO = OKCUNO AND OKCONO = HB_CONO \n"
                        + "WHERE HB_CONO  = '" + cono.trim() + "'\n"
                        + "AND HB_DIVI = '" + divi.trim() + "'\n"
                        + "AND HB_USER = '" + user.trim() + "'\n"
                        + "ORDER  BY HB_BNNO ASC";

                System.err.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("HB_CONO", mRes.getString(1).trim());
                    mMap.put("HB_DIVI", mRes.getString(2).trim());
                    mMap.put("HB_BNNO", mRes.getString(3).trim());
                    mMap.put("HB_TRDT", mRes.getString(4).trim());
                    mMap.put("HB_CUNO", mRes.getString(5).trim());
                    mMap.put("HB_PO", mRes.getString(6).trim());
                    mMap.put("HB_USER", mRes.getString(7).trim());
                    mMap.put("HB_STS", mRes.getString(8));
                    mMap.put("H_DATE", mRes.getString(9).trim());
                    mMap.put("E_NAME", mRes.getString(10).trim());
                    mMap.put("OKCUNM", mRes.getString(11).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray CheckInvoice(String CONO, String DIVI, String Invoice) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String BBNO = "";
        int count = 0;
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT LB_BNNO\n"
                        + "FROM BRLDTA0100.LN_BILLNOTE lb  \n"
                        + "WHERE LB_CONO = '" + CONO.trim() + "'\n"
                        + "AND LB_DIVI  = '" + DIVI.trim() + "'\n"
                        + "AND LB_INVNO = '" + Invoice.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    BBNO += mRes.getString(1).trim() + " ";
                    count++;
                }

                Map<String, Object> mMap = new HashMap<>();
                mMap.put("BBNO", BBNO);
                mMap.put("Count", count);

                mJSonArr.put(mMap);
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

}
