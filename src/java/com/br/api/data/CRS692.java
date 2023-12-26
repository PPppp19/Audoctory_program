/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.data;

import MForms.Utils.MNEHelper;
import MForms.Utils.MNEProtocol;
import com.br.api.connect.ConnectDB2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phongsathon
 */
public class CRS692 {

    public static void AUTO_CRS692() {
        System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");

    }

    public static void AUTO_CRS692(int CONO, String DIVI, String CUSNO, String BRANCH, String BANK) throws Exception {

        CRS692_ADDCHECK(CONO, DIVI, CUSNO, BRANCH, BANK);
    }

    public static void CRS692_ADDCHECK(int CONO, String DIVI, String CUSNO, String BRANCH, String BANK) {
        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:22008/mne/servlet/MvxMCSvt"; //TST
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = 16105;
//        int LoginAppPort = 16305;
        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        
        try {

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(CONO, DIVI, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(CONO, DIVI, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }
            mne.pressKey(MNEProtocol.KeyEnter);

            String CRS692 = mne.runM3Pgm("CRS692");

            if ((CRS692).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(CRS692);
            }

            System.out.println("Running");
            if (mne.getMsg() != null) {

                System.out.println(mne.panel);
                System.out.println(mne.getMsg());
            }

            System.out.println(mne.panel);

            if (mne.panel.equals("CRS692/B")) {
                
                                System.out.println("in 1");


                mne.setField("W1OBKV", "2");
                mne.setField("W2OBKV", CUSNO.trim());
                mne.setField("W3OBKV", BANK.trim());
                mne.setField("W4OBKV", DIVI.trim());
                mne.selectOption("1");
            }

            if (mne.getMsg() != null) {

                System.out.println(mne.panel);
                System.out.println(mne.getMsg());
            }

            System.out.println(mne.panel);

            if (mne.panel.equals("CRS692/E")) {

                   System.out.println("in 2");
                mne.setField("WWCBPY", "1");
                mne.setField("WWBANA", BRANCH.trim());
                mne.setField("WWSTAT", "20");

                mne.pressKey(MNEProtocol.KeyEnter);
                mne.pressKey(MNEProtocol.KeyEnter);
                mne.pressKey(MNEProtocol.KeyEnter);
            }

            if (mne.getMsg() != null) {

                System.out.println(mne.panel);
                System.out.println(mne.getMsg());
            } else {

                mne.closeProgram(CRS692);
            }

        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

}
