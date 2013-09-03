/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

 * NewJFrame.java
 *
 * Created on Jan 15, 2009, 1:47:11 PM

 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package mtechproject.dbfrontend;

import java.text.DateFormat;
import java.util.Date;

import net.sourceforge.jcalendarbutton.JCalendarButton;
import net.sourceforge.jcalendarbutton.JTimeButton;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarPopup;

/**
 *
 * @author don
 */
public class NewJFrame extends javax.swing.JFrame {

    /** Creates new form NewJFrame */
    public NewJFrame() {
        initComponents();
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jCalendarButton1 = new JCalendarButton();
        jTimeButton1 = new JTimeButton();
        jCalendarButton2 = new JCalendarButton();
        jTimeButton2 = new JTimeButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name:");

        jLabel2.setText("Date:");

        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateFocusLost(evt);
            }
        });

        jLabel3.setText("Time:");

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                timeFocusLost(evt);
            }
        });

        jLabel4.setText("Date and Time:");

        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateTimeFocusLost(evt);
            }
        });

        jCalendarButton1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateOnlyPopupChanged(evt);
            }
        });

        jTimeButton1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                timeOnlyPopupChanged(evt);
            }
        });

        jCalendarButton2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePopupChanged(evt);
            }
        });

        jTimeButton2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                timePopupChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jTextField3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendarButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTimeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCalendarButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTimeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTimeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCalendarButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jCalendarButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jTimeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateTimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateTimeFocusLost
        String dateTime = jTextField4.getText();
        setDateTime(dateTime);
    }//GEN-LAST:event_dateTimeFocusLost

    private void timeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timeFocusLost
        String time = jTextField3.getText();
        setTime(time);
    }//GEN-LAST:event_timeFocusLost

    private void dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFocusLost
        String date = jTextField2.getText();
        setDate(date);
    }//GEN-LAST:event_dateFocusLost

    private void dateOnlyPopupChanged(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateOnlyPopupChanged
        if (evt.getNewValue() instanceof Date)
            setDate((Date)evt.getNewValue());
    }//GEN-LAST:event_dateOnlyPopupChanged

    private void timeOnlyPopupChanged(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_timeOnlyPopupChanged
        if (evt.getNewValue() instanceof Date)
            setTime((Date)evt.getNewValue());
    }//GEN-LAST:event_timeOnlyPopupChanged

    private void datePopupChanged(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePopupChanged
        if (evt.getNewValue() instanceof Date)
            setDateTime((Date)evt.getNewValue());
    }//GEN-LAST:event_datePopupChanged

    private void timePopupChanged(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_timePopupChanged
        if (evt.getNewValue() instanceof Date)
            setDateTime((Date)evt.getNewValue());
    }//GEN-LAST:event_timePopupChanged

    /**
     * Validate and set the datetime field on the screen given a datetime string.
     * @param dateTime The datetime string
     */
    public void setDate(String dateString)
    {
		Date date = null;
		try	{
            if ((dateString != null) && (dateString.length() > 0))
                date = dateFormat.parse(dateString);
		} catch (Exception e)	{
            date = null;
		}
        this.setDate(date);
    }
    /**
     * Validate and set the datetime field on the screen given a date.
     * @param dateTime The datetime object
     */
    public void setDate(Date date)
    {
        String dateString = "";
        if (date != null)
    		dateString = dateFormat.format(date);
        jTextField2.setText(dateString);
        jCalendarButton1.setTargetDate(date);
    }
    /**
     * Validate and set the datetime field on the screen given a datetime string.
     * @param dateTime The datetime string
     */
    public void setTime(String timeString)
    {
		Date time = null;
		try	{
            if ((timeString != null) && (timeString.length() > 0))
                time = timeFormat.parse(timeString);
		} catch (Exception e)	{
            time = null;
		}
        this.setTime(time);
    }
    /**
     * Validate and set the datetime field on the screen given a date.
     * @param dateTime The datetime object
     */
    public void setTime(Date time)
    {
        String timeString = "";
        if (time != null)
    		timeString = timeFormat.format(time);
        jTextField3.setText(timeString);
        jTimeButton1.setTargetDate(time);
    }
    /**
     * Validate and set the datetime field on the screen given a datetime string.
     * @param dateTime The datetime string
     */
    public void setDateTime(String dateTimeString)
    {
		Date dateTime = null;
		try	{
            if ((dateTimeString != null) && (dateTimeString.length() > 0))
                dateTime = dateTimeFormat.parse(dateTimeString);
		} catch (Exception e)	{
            dateTime = null;
		}
        this.setDateTime(dateTime);
    }
    /**
     * Validate and set the datetime field on the screen given a date.
     * @param dateTime The datetime object
     */
    public void setDateTime(Date dateTime)
    {
        String dateTimeString = "";
        if (dateTime != null)
    		dateTimeString = dateTimeFormat.format(dateTime);
        jTextField4.setText(dateTimeString);
        jCalendarButton2.setTargetDate(dateTime);
        jTimeButton2.setTargetDate(dateTime);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton jCalendarButton1;
    private org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton jCalendarButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private org.jbundle.thin.base.screen.jcalendarbutton.JTimeButton jTimeButton1;
    private org.jbundle.thin.base.screen.jcalendarbutton.JTimeButton jTimeButton2;
    // End of variables declaration//GEN-END:variables

    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    public static DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
	public static DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);

}
