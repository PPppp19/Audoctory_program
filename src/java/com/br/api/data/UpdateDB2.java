/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB2;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UpdateDB2 {

    public static String DBPRD = GBVAR.DBPRD;
    public static String M3DB = GBVAR.M3DB;
    
    
            
            
             public static void POSTREQCUSCHK(String cono, String divi, String cusno, String branch, String user, String date) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String state = "OK";

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " UPDATE " + DBPRD + ".REQCUSCHK fm  \n"
                        + " SET STATUS  = '22', OTHER  = '"+user+"'\n"
                        + " WHERE REQ_DATE  = '" + date+ "'";

                System.out.println("rejectid : \n" + query);
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

     public static void RETURNEXP(String CONO, String DIVI, String ID) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "  UPDATE  " + DBPRD + ".BANK_MAPPING  \n"
                        + "  SET  BM_CUNO = NULL , BM_RCNO = NULL , BM_BKCHARGE = 0 \n"
                        + "  WHERE   BM_FNNO  = '" + ID.trim() + "' AND BM_CONO  = '" + CONO.trim() + "'  AND BM_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query);
                stmt.executeUpdate(query);

                Statement stmt1 = conn.createStatement();
                String query1 = "  UPDATE  " + DBPRD + ".HEAD_RECIPT  \n"
                        + "  SET  H_CUNO = NULL , H_PYNO = NULL , H_STS = 1  , H_TYPE = NULL \n"
                        + "  WHERE   H_RNNO  = '" + ID.trim() + "' AND  H_CONO = '" + CONO.trim() + "'  AND  H_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query1);
                stmt1.executeUpdate(query1);

                Statement stmt2 = conn.createStatement();
                String query2 = "   UPDATE  " + DBPRD + ".HR_RECEIPT  \n"
                        + "  SET  HC_TRDT = NULL , HC_PYNO = NULL ,  HC_REAMT = 0 , HC_TYPE = NULL ,HC_BANK = NULL ,HC_ACCODE = NULL ,HC_PYDT = NULL, HC_USER = NULL,  HC_STS = 2 , HR_BKCHG = 0 , HC_FIXNO = NULL \n"
                        + "  WHERE   HC_FNNO  = '" + ID.trim() + "' AND  HR_CONO = '" + CONO.trim() + "'  AND  HR_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query2);
                stmt2.executeUpdate(query2);

                Statement stmt3 = conn.createStatement();
                String query3 = "DELETE FROM   " + DBPRD + ".LR_LINERECEIPT\n"
                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM " + DBPRD + ".HR_RECEIPT hr WHERE  HC_FNNO = '" + ID.trim() + "'   AND HR_CONO = '"+CONO.trim()+"' AND HR_DIVI  = '"+DIVI.trim()+"') AND LR_CONO  = '" + CONO.trim() + "' AND LR_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query3);
                stmt3.executeUpdate(query3);

                  Statement stmt4 = conn.createStatement();
                String query4 = "DELETE FROM   "+DBPRD+".LR_LINEEXPEN\n"
                        + "        WHERE LRE_RCNO  IN  (SELECT HC_RCNO FROM "+DBPRD+".HR_RECEIPT hr WHERE  HC_FNNO = '"+ID.trim()+"' AND HR_CONO = '"+CONO.trim()+"' AND HR_DIVI  = '"+DIVI.trim()+"') AND LRE_CONO  = '"+CONO.trim()+"' AND LRE_DIVI  = '"+DIVI.trim()+"'";

                System.out.println(query4);
                stmt4.executeUpdate(query4);
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

    
     public static void RETURNRC(String CONO, String DIVI, String ID) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

//                Statement stmt = conn.createStatement();
//                String query = "  UPDATE  " + DBPRD + ".BANK_MAPPING  \n"
//                        + "  SET  BM_CUNO = NULL , BM_RCNO = NULL , BM_BKCHARGE = 0 \n"
//                        + "  WHERE   BM_FNNO  = '" + ID.trim() + "' AND BM_CONO  = '" + CONO.trim() + "'  AND BM_DIVI  = '" + DIVI.trim() + "'";
//
//                System.out.println(query);
//                stmt.executeUpdate(query);
                Statement stmt1 = conn.createStatement();
                String query1 = "  UPDATE  " + DBPRD + ".HEAD_RECIPT  \n"
                        + "  SET  H_CUNO = NULL , H_PYNO = NULL , H_STS = 1 , H_TYPE = NULL \n"
                        + "  WHERE   H_RNNO  = '" + ID.trim() + "' AND  H_CONO = '" + CONO.trim() + "'  AND  H_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query1);
                stmt1.executeUpdate(query1);

                Statement stmt2 = conn.createStatement();
                String query2 = "   UPDATE  " + DBPRD + ".HR_RECEIPT  \n"
                        + "  SET  HC_TRDT = NULL , HC_PYNO = NULL ,  HC_REAMT = 0 , HC_TYPE = NULL ,HC_BANK = NULL ,HC_ACCODE = NULL ,HC_PYDT = NULL, HC_USER = NULL, HC_STS = 2 , HR_BKCHG = 0 , HC_FIXNO = NULL, HC_CHKNO = NULL \n"
                        + "  WHERE   HC_FNNO  = '" + ID.trim() + "' AND  HR_CONO = '" + CONO.trim() + "'  AND  HR_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query2);
                stmt2.executeUpdate(query2);

                Statement stmt3 = conn.createStatement();
                String query3 = "DELETE FROM   " + DBPRD + ".LR_LINERECEIPT\n"
                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM " + DBPRD + ".HR_RECEIPT hr WHERE  HC_FNNO = '" + ID.trim() + "' ) AND LR_CONO  = '" + CONO.trim() + "' AND LR_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query3);
                stmt3.executeUpdate(query3);

//                  Statement stmt4 = conn.createStatement();
//                String query4 = "DELETE FROM   M3FDBTST.LR_LINERECEIPT\n"
//                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM M3FDBTST.HR_RECEIPT hr WHERE  HC_FNNO = '"+ID.trim()+"' ) AND LR_CONO  = '"+CONO.trim()+"' AND LR_DIVI  = '"+DIVI.trim()+"'";
//
//                System.out.println(query4);
//                stmt4.executeUpdate(query4);
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

    public static void RETURNBM(String CONO, String DIVI, String ID) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "  UPDATE  " + DBPRD + ".BANK_MAPPING  \n"
                        + "  SET  BM_CUNO = NULL , BM_RCNO = NULL , BM_BKCHARGE = 0 \n"
                        + "  WHERE   BM_FNNO  = '" + ID.trim() + "' AND BM_CONO  = '" + CONO.trim() + "'  AND BM_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query);
                stmt.executeUpdate(query);

                Statement stmt1 = conn.createStatement();
                String query1 = "  UPDATE  " + DBPRD + ".HEAD_RECIPT  \n"
                        + "  SET  H_CUNO = NULL , H_PYNO = NULL , H_STS = 1  , H_TYPE = NULL \n"
                        + "  WHERE   H_RNNO  = '" + ID.trim() + "' AND  H_CONO = '" + CONO.trim() + "'  AND  H_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query1);
                stmt1.executeUpdate(query1);

                Statement stmt2 = conn.createStatement();
                String query2 = "   UPDATE  " + DBPRD + ".HR_RECEIPT  \n"
                        + "  SET  HC_TRDT = NULL , HC_PYNO = NULL ,  HC_REAMT = 0 , HC_TYPE = NULL ,HC_BANK = NULL ,HC_ACCODE = NULL ,HC_PYDT = NULL, HC_USER = NULL,  HC_STS = 2 , HR_BKCHG = 0 , HC_FIXNO = NULL \n"
                        + "  WHERE   HC_FNNO  = '" + ID.trim() + "' AND  HR_CONO = '" + CONO.trim() + "'  AND  HR_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query2);
                stmt2.executeUpdate(query2);

                Statement stmt3 = conn.createStatement();
                String query3 = "DELETE FROM   " + DBPRD + ".LR_LINERECEIPT\n"
                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM " + DBPRD + ".HR_RECEIPT hr WHERE  HC_FNNO = '" + ID.trim() + "' ) AND LR_CONO  = '" + CONO.trim() + "' AND LR_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query3);
                stmt3.executeUpdate(query3);

//                  Statement stmt4 = conn.createStatement();
//                String query4 = "DELETE FROM   M3FDBTST.LR_LINERECEIPT\n"
//                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM M3FDBTST.HR_RECEIPT hr WHERE  HC_FNNO = '"+ID.trim()+"' ) AND LR_CONO  = '"+CONO.trim()+"' AND LR_DIVI  = '"+DIVI.trim()+"'";
//
//                System.out.println(query4);
//                stmt4.executeUpdate(query4);
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
    public static void XQUERY(String vQUERY) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = vQUERY;

                System.out.println("XQUERY : \n" + query);
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

    public static void ROLLBACK(String cono, String divi, String ordid) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String state = "";

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " UPDATE " + DBPRD + ".FAR_MTRREQ04 fm  \n"
                        + " SET ORD_STAT  = '11', ORD_ISSB  = '-', ISB_DATE  = '-'\n"
                        + " WHERE ORD_ID  = '" + ordid.trim() + "'";

                System.out.println("rejectid : \n" + query);
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

    public static void SETITEM(String cono, String divi, String ordid) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String state = "";

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " UPDATE " + DBPRD + ".FAR_ITMTLB04 fm  \n"
                        + " SET ONH_STAT  = 'YES'\n"
                        + " WHERE ORD_ID  = '" + ordid.trim() + "'";

                System.out.println("rejectid : \n" + query);
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

    static void UPDATELRNO(String LR_CONO, String LR_DIVI, String LR_RCNO, String LR_INVNO, String LR_INVDT, String LR_INVAMT, String LR_REAMT, String LR_STS) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = "UPDATE  " + DBPRD + ".LR_LINERECEIPT \n"
                    + "SET  LR_CONO  = '" + LR_CONO.trim() + "', LR_DIVI  = '" + LR_DIVI.trim() + "', LR_INVNO  = '" + LR_INVNO.trim() + "', LR_INVDT  = '" + LR_INVDT.trim() + "', LR_INVAMT = '" + LR_INVAMT.trim() + "', LR_REAMT ='" + LR_REAMT.trim() + "', LR_STS = '" + LR_STS.trim() + "'\n"
                    + "WHERE LR_RCNO  = '" + LR_RCNO.trim() + "' AND LR_CONO  = '" + LR_CONO.trim() + "' and LR_DIVI = '" + LR_DIVI.trim() + "'";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void UPDATEBM(
            String BM_ID, String BM_CONO, String BM_DIVI, String BM_BANK, String BM_ACCODE,
            String BM_DATE, String BM_TIME, String BM_CHQNO, String BM_TRANSAC, String BM_CUNO,
            String BM_CHANEL, String BM_BRANCH, String BM_AMOUNT, String BM_DESC,
            String BM_RCNO, String BM_TIME1, String BM_USER, String BM_BKCHARGE, String BM_OVPAY,
            String BM_CNDN, String BM_STATUS, String REF2, String REF3, String SENDER,
            String BM_REFCU, String REF4, String BM_REFCU1, String BM_CUNA, String BM_FNNO, String BM_LCODE
    ) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = "UPDATE  " + DBPRD + ".BANK_MAPPING \n"
                    + "SET   \n"
                    + "BM_CONO = '" + BM_CONO.trim() + "',  BM_DIVI = '" + BM_DIVI.trim() + "', BM_BANK = '" + BM_BANK.trim() + "', BM_ACCODE= '" + BM_ACCODE.trim() + "', BM_DATE = '" + BM_DATE.trim() + "' ,\n"
                    + "BM_TIME ='" + BM_TIME.trim() + "',BM_CHQNO = '" + BM_CHQNO.trim() + "',BM_TRANSAC = '" + BM_TRANSAC.trim() + "',BM_CUNO = '" + BM_CUNO.trim() + "',BM_CHANEL = '" + BM_CHANEL.trim() + "',\n"
                    + "BM_BRANCH ='" + BM_BRANCH.trim() + "', BM_AMOUNT= '" + BM_AMOUNT.trim() + "', BM_DESC= '" + BM_DESC.trim() + "', BM_RCNO= '" + BM_RCNO.trim() + "', BM_TIME1= '" + BM_TIME1.trim() + "',\n"
                    + "BM_USER ='" + BM_USER.trim() + "', BM_BKCHARGE= '" + BM_BKCHARGE.trim() + "', BM_OVPAY= '" + BM_OVPAY.trim() + "',BM_CNDN = '" + BM_CNDN.trim() + "',BM_STATUS = '" + BM_STATUS.trim() + "',\n"
                    + "REF2 ='" + REF2.trim() + "',REF3 = '" + REF3.trim() + "',SENDER = '" + SENDER.trim() + "',BM_REFCU = '" + BM_REFCU.trim() + "',REF4 = '" + REF4.trim() + "',\n"
                    + "BM_REFCU1 ='" + BM_REFCU1.trim() + "',BM_CUNA = '" + BM_CUNA.trim() + "',BM_FNNO = '" + BM_FNNO.trim() + "',BM_LCODE = '" + BM_LCODE.trim() + "'\n"
                    + "WHERE BM_ID  = '" + BM_ID.trim() + "'";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void UPDATELRNOEXP(String LRE_CONO, String LRE_DIVI, String LRE_RCNO, String LRE_CODE, String LRE_ACTCODE, String LRE_AMT, String LRE_REF1, String LRE_REF2, String LRE_REF3, String LRE_VCTXT, String LRE_FNNO
    ) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = "UPDATE  " + DBPRD + ".LR_LINEEXPEN \n"
                    + "SET  LRE_REF2  = '" + LRE_REF2.trim() + "', LRE_FNNO = '" + LRE_FNNO.trim() + "' , LRE_REF3  = '" + LRE_REF3.trim() + "', LRE_VCTXT  = '" + LRE_VCTXT.trim() + "', LRE_CODE  = '" + LRE_CODE.trim() + "', LRE_ACTCODE = '" + LRE_ACTCODE.trim() + "', LRE_AMT ='" + LRE_AMT.trim() + "', LRE_REF1 = '" + LRE_REF1.trim() + "'\n"
                    + "WHERE LRE_CONO  = '" + LRE_CONO.trim() + "' AND LRE_DIVI  = '" + LRE_DIVI.trim() + "' and LRE_RCNO = '" + LRE_RCNO.trim() + "'";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void UPDATERCNO(String CONO, String DIVI, String RCNO, String TRDT, String PYNO, String REAMT, String TYPE, String BANK, String ACCODE, String PYDT, String CHKNO, String USER, String VCNO, String STS, String BKCHG, String LOCATION, String FIXNO, String RNNO
    ) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = " \n"
                    + " UPDATE  " + DBPRD + ".HR_RECEIPT  \n"
                    + "SET  HR_CONO = '" + CONO.trim() + "' , HR_DIVI ='" + DIVI.trim() + "' ,HC_TRDT= '" + TRDT.trim() + "' ,HC_PYNO= '" + PYNO.trim() + "' ,\n"
                    + "HC_REAMT= '" + REAMT.trim() + "' ,HC_TYPE= '" + TYPE.trim() + "' ,HC_BANK= '" + BANK.trim() + "' ,HC_ACCODE= '" + ACCODE.trim() + "' ,HC_PYDT= '" + PYDT.trim() + "' ,\n"
                    + "HC_CHKNO= '" + CHKNO.trim() + "' ,HC_USER= '" + USER.trim() + "' ,HC_VCNO= '" + VCNO.trim() + "' ,HC_STS= '" + STS.trim() + "' ,HR_BKCHG= '" + BKCHG.trim() + "' ,\n"
                    + "HC_LOCATION= '" + LOCATION.trim() + "' ,HC_FIXNO= '" + FIXNO.trim() + "' ,HC_FNNO= '" + RNNO.trim() + "' \n"
                    + "WHERE HC_RCNO  = '" + RCNO.trim() + "' ";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void UPDATEID(String RNNO, String CONO, String DIVI, String RCNO, String CUNO, String PYNO, String STS, String VCNO, String LOCATION, String TYPE) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = "UPDATE  " + DBPRD + ".HEAD_RECIPT \n"
                    + "SET  H_CONO  = '" + CONO.trim() + "', H_DIVI  = '" + DIVI.trim() + "', H_CUNO = '" + CUNO.trim() + "', H_PYNO  = '" + PYNO.trim() + "', H_STS  = '" + STS.trim() + "', H_VCNO = '" + VCNO.trim() + "', H_LOCATION ='" + LOCATION.trim() + "', H_TYPE = '" + TYPE.trim() + "'\n"
                    + "WHERE H_RCNO  = '" + RCNO.trim() + "' AND H_RNNO  = '" + RNNO.trim() + "'";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void updatedailiylisto(String code, String user, String date, String amt, String fee) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = "UPDATE BRLDTA0100.DDEPOSIT_out\n"
                    + "SET DDO_TRDT  =  '" + date + "'  , DDO_REAMT  = '" + amt + "', DDO_FEE = '" + fee + "',DDO_UKEY  = '" + user + "' \n"
                    + "WHERE   DDO_DDNO = '" + code + "'  AND DD_STS != '99'  ";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void updatedailiylist(String code, String user, String date, String amt, String fee, String uhold) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = "UPDATE BRLDTA0100.DDEPOSIT_IN\n"
                    + "SET DD_UHOLD = '" + uhold + "' ,DD_TRDT  =  '" + date + "'  , DD_REAMT  = '" + amt + "', DD_FEE = '" + fee + "',DD_UKEY  = '" + user + "' \n"
                    + "WHERE   DD_DDNO = '" + code + "'  AND DD_STS != '99'  ";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void updatedate(String cono, String start, String end) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = "UPDATE BRLDTA0100.MMSDTE\n"
                    + "SET  MSSDTE  =  '" + start + "'  , MSEDTE  = '" + end + "'\n"
                    + "WHERE  MSCONO  = '" + cono + "'";
            System.out.println(sql);
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void UpdateBillingNote(String cono, String divi, String bnno, String cuno, String trdt, String pono, String user) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String sql = "UPDATE BRLDTA0100.H_BILLNOTE\n"
                    + "SET HB_TRDT = '" + trdt.replaceAll("-", "").trim() + "', HB_CUNO = '" + cuno.trim() + "', HB_PO = '" + pono.trim() + "' \n"
                    + "WHERE HB_CONO = '" + cono.trim() + "' AND HB_DIVI = '" + divi.trim() + "' AND HB_BNNO = '" + bnno.trim() + "' ";
            stmt.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    static void UpdateStatus(String CONO, String DIVI, String BNNO, String USER, String STS) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();

            String sql = " UPDATE BRLDTA0100.H_BILLNOTE\n"
                    + "                    SET HB_STS = '" + STS + "'\n"
                    + "                    WHERE HB_CONO = '" + CONO + "' AND HB_DIVI = '" + DIVI + "' AND HB_BNNO = '" + BNNO + "' ";

            stmt.execute(sql);

        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void UpdateRunning(String CONO, String DIVI, String YEAR, String RNNO) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {

            Statement stmt = conn.createStatement();
            String chk = RNNO;

            String sql = "UPDATE BRLDTA0100.RUNNING_BILLNOTE\n"
                    + "SET RN_RNNO = INT('" + RNNO.trim() + "') \n"
                    + "WHERE RN_CONO = '" + CONO.trim() + "' AND RN_DIVI = '" + DIVI.trim() + "' AND RN_YEAR = '" + YEAR.trim() + "'  AND RN_RNNO = INT('" + RNNO.trim() + "')-1";

            stmt.execute(sql);

        } catch (Exception ex) {
            Logger.getLogger(UpdateDB2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void UpdateLineInvoice(String LB_CONO, String LB_DIVI, String LB_BNNO, String LB_INVNO, String LB_INVDT, String LB_INVYEAR, String LB_INVAMT, String LB_TAXAMT, String LB_WHTAX, String LB_STS) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        try {
            if (conn != null) {
                Double TAX = 0.00;

                if (LB_WHTAX.equalsIgnoreCase("TRUE")) {
                    TAX = Double.parseDouble(LB_INVAMT) * 0.03;
                } else {
                    TAX = Double.parseDouble(LB_INVAMT) * 0.0;
                }

                if (LB_STS.equalsIgnoreCase("false")) {
                    LB_STS = "10";
                } else {
                    LB_STS = "20";
                }

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.LN_BILLNOTE\n"
                        + "SET LB_WHTAX = '" + LB_WHTAX.trim() + "', LB_TAXAMT = '" + TAX + "', LB_STS = '" + LB_STS.trim() + "'\n"
                        + "WHERE LB_CONO = '" + LB_CONO.trim() + "' AND LB_DIVI = '" + LB_DIVI.trim() + "' AND LB_BNNO = '" + LB_BNNO.trim() + "' AND LB_INVNO = '" + LB_INVNO.trim() + "' and LB_INVYEAR ='" + LB_INVYEAR.trim() + "'";
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
