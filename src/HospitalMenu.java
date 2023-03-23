import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class HospitalMenu {


    public static void main(String[] args) {
       try {
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root",
                   "root");
           Statement statement = connection.createStatement();

           int userSelection;
           int finalUserSelection;
           Scanner input = new Scanner(System.in);
           userSelection = menu();
           boolean exit = true;

           do {
               exit = false;

               switch (userSelection) {
                   case 1:
                       System.out.println("----- Room Utilization ----- \n" +
                               "1) List the rooms that are occupied, along with the associated patient names and " +
                               "the date the patient was admitted. \n" +
                               "2) List the rooms that are currently unoccupied. \n" +
                               "3) List all rooms in the hospital along with patient names and admission dates for " +
                               "those that are occupied.\n" +
                               "4) Exit");
                       finalUserSelection = input.nextInt();

                       switch (finalUserSelection) {
                           case 1:
                               ResultSet resultSet1 = statement.executeQuery("SELECT r.room_number, p.first_name, " +
                                       "p.last_name, p.admit_date FROM room r INNER JOIN patient p" +
                                       " ON r.patient_id = p.patient_id");
                               String format1 = "| %-12s | %-12s | %-11s | %-1s";
                               System.out.println("List the rooms that are occupied, along with the associated patient"
                                       + "names and the date the patient was admitted.");
                               System.out.println("Room Number" + " --- " + "First Name" + " --- " + "Last Name"
                                       + " --- " + "Admission Date");
                               while (resultSet1.next()) {
                                   String roomNumber = resultSet1.getString(1);
                                   if (resultSet1.wasNull()) { roomNumber = " "; }
                                   String firstName = resultSet1.getString(2);
                                   if (resultSet1.wasNull()) { firstName = " "; }
                                   String lastName = resultSet1.getString(3);
                                   if (resultSet1.wasNull()) { lastName = " "; }
                                   String admitDate = resultSet1.getString(4);
                                   if (resultSet1.wasNull()) { admitDate = " "; }
                                   System.out.format(format1, roomNumber, firstName, lastName, admitDate + "\n");
                               }
                               System.out.println("");
                               break;
                           case 2:
                               ResultSet resultSet2 = statement.executeQuery("SELECT * FROM room WHERE patient_id" +
                                       " IS NULL");
                               String format2 = "| %-1s";
                               System.out.println("List the rooms that are currently unoccupied");
                               System.out.println("Room Number");
                               while (resultSet2.next()) {
                                   String roomNumber = resultSet2.getString(1);
                                   if (resultSet2.wasNull()) { roomNumber = " "; }
                                   System.out.format(format2, roomNumber + "\n");
                               }
                               System.out.println("");
                               break;
                           case 3:
                               ResultSet resultSet3 = statement.executeQuery("SELECT r.room_number, p.first_name," +
                                       " p.last_name, p.admit_date\n" +
                                       "FROM room r\n" +
                                       "LEFT JOIN patient p\n" +
                                       "ON r.patient_id = p.patient_id\n" +
                                       "WHERE p.first_name IS NOT NULL\n");
                               String format3 = "| %-12s | %-12s | %-11s | %-1s";
                               System.out.println("List all rooms in the hospital along with patient names and admission" +
                                       " dates for those that are occupied.");
                               System.out.println("Room Number" + " --- " + "First Name" + " --- " + "Last Name"
                                       + " --- " + "Admission Date");
                               while (resultSet3.next()) {
                                   String roomNumber = resultSet3.getString(1);
                                   if (resultSet3.wasNull()) { roomNumber = " "; }
                                   String firstName = resultSet3.getString(2);
                                   if (resultSet3.wasNull()) { firstName = " "; }
                                   String lastName = resultSet3.getString(3);
                                   if (resultSet3.wasNull()) { lastName = " "; }
                                   String admitDate = resultSet3.getString(4);
                                   if (resultSet3.wasNull()) { admitDate = " "; }
                                   System.out.format(format3, roomNumber, firstName, lastName, admitDate + "\n");
                               }
                               System.out.println("");
                               break;
                           case 4:
                               exit = true;
                               break;
                           default:
                               System.out.println("Not a valid choice! Enter a numerical value matching your selection from " +
                                       "the menu. \n");
                               exit = true;
                               break;
                       }
                       break;
                   case 2:
                       System.out.println("----- Patient Information ----- \n" +
                               "1) List all patients in the database, with full personal information.\n" +
                               "2) List all patients currently admitted to the hospital. List only patient" +
                               " identification number and name.\n" +
                               "3) List all patients who were discharged in a given date range. List only patient " +
                               "identification number and name.\n" +
                               "4) List all patients who were admitted within a given date range. List only patient " +
                               "identification number and name.\n" +
                               "5) For a given patient (either patient identification number or name), list all " +
                               "admissions to the hospital along with the diagnosis for each admission.\n" +
                               "6) For a given patient (either patient identification number or name), list all " +
                               "treatments that were administered. Group treatments by admissions. List admissions " +
                               "in descending chronological order, and list treatments in ascending chronological " +
                               "order within each admission.\n" +
                               "7) List patients who were admitted to the hospital within 30 days of their last " +
                               "discharge date. For each patient list their patient identification number, name, " +
                               "diagnosis, and admitting doctor.\n" +
                               "8) For each patient that has ever been admitted to the hospital, list their total number " +
                               "of admissions, average duration of each admission, longest span between admissions, " +
                               "shortest span between admissions, and average span between admissions.\n" +
                               "9) Exit");
                       finalUserSelection = input.nextInt();

                       switch (finalUserSelection) {
                           case 1:
                               ResultSet resultSet1 = statement.executeQuery("SELECT * FROM patient");
                               String format21 = "| %-11s | %-12s | %-12s | %-10s | %-16s | %-16s | %-1s";
                               System.out.println("List all patients in the database, with full personal information.");
                               System.out.println("Patient ID" + " --- " + "First Name" + " --- " + "Last Name"
                                       + " --- " + "Doctor ID" + " --- " + "Admission Date" +
                                       " --- " + "Discharge Date" + " --- " + "Admission ID");

                               while (resultSet1.next()) {
                                   String patientID = resultSet1.getString(1);
                                   if (resultSet1.wasNull()) { patientID = " "; }
                                   String firstName = resultSet1.getString(2);
                                   if (resultSet1.wasNull()) { firstName = " "; }
                                   String lastName = resultSet1.getString(3);
                                   if (resultSet1.wasNull()) { lastName = " "; }
                                   String doctorID = resultSet1.getString(4);
                                   if (resultSet1.wasNull()) { doctorID = " "; }
                                   String admitDate = resultSet1.getString(5);
                                   if (resultSet1.wasNull()) { admitDate = " "; }
                                   String dischargeDate = resultSet1.getString(6);
                                   if (resultSet1.wasNull()) { dischargeDate = " "; }
                                   String admissionID = resultSet1.getString(7);
                                   if (resultSet1.wasNull()) { admissionID = " "; }
                                   System.out.format(format21, patientID, firstName, lastName, doctorID, admitDate,
                                           dischargeDate, admissionID + "\n");
                               }
                               System.out.println("");
                               break;
                           case 2:
                               ResultSet resultSet2 = statement.executeQuery("SELECT p.patient_id, p.first_name," +
                                       " p.last_name\n" +
                                       "FROM patient p\n" +
                                       "WHERE p.discharge_date IS NOT NULL\n");
                               String format2 = "| %-11s | %-12s | %-1s";
                               System.out.println("List all patients currently admitted to the hospital. List only " +
                                       "patient identification number and name");
                               System.out.println("Patient ID" + " --- " + "First Name" + " --- " + "Last Name");
                               while (resultSet2.next()) {
                                   String patientID = resultSet2.getString(1);
                                   if (resultSet2.wasNull()) { patientID = " "; }
                                   String firstName = resultSet2.getString(2);
                                   if (resultSet2.wasNull()) { firstName = " "; }
                                   String lastName = resultSet2.getString(3);
                                   if (resultSet2.wasNull()) { lastName = " "; }
                                   System.out.format(format2, patientID, firstName, lastName + "\n");
                               }
                               System.out.println("");
                               break;
                           case 3:
                               ResultSet resultSet3 = statement.executeQuery("SELECT p.patient_id, p.first_name," +
                                       " p.last_name FROM patient p\n" +
                                       "WHERE p.discharge_date BETWEEN '2022-03-01' AND '2022-03-10'");
                               String format3 = "| %-11s | %-12s | %-1s";
                               System.out.println("List all patients who were discharged in a given date range. " +
                                       "List only patient identification number and name.");
                               System.out.println("Patient ID" + " --- " + "First Name" + " --- " + "Last Name");

                               while (resultSet3.next()) {
                                   String patientID = resultSet3.getString(1);
                                   if (resultSet3.wasNull()) { patientID = " "; }
                                   String firstName = resultSet3.getString(2);
                                   if (resultSet3.wasNull()) { firstName = " "; }
                                   String lastName = resultSet3.getString(3);
                                   if (resultSet3.wasNull()) { lastName = " "; }
                                   System.out.format(format3, patientID, firstName, lastName + "\n");
                               }
                               System.out.println("");
                               break;
                           case 4:
                               Scanner dateInput = new Scanner(System.in);
                               String dateFrom = "";
                               String dateTo = "";
                               String format4 = "| %-11s | %-12s | %-1s";
                               System.out.println("List all patients who were admitted within a given date range. " +
                                       "List only patient identification number and name.");
                               System.out.print("Enter 'from' date range (format YYYY-MM-DD - " +
                                       "Valid example 2022-03-01): ");
                               dateFrom = dateInput.next();
                               System.out.print("Enter 'to' date range (format YYYY-MM-DD - " +
                                       "Valid example 2022-04-01): ");
                               dateTo = dateInput.next();
                               ResultSet resultSet4 = statement.executeQuery("SELECT p.patient_id, p.first_name," +
                                       " p.last_name FROM patient p\n" +
                                       "WHERE p.admit_date BETWEEN '" + dateFrom + "' AND '" + dateTo + "'");
                               System.out.println("Patient ID" + " --- " + "First Name" + " --- " + "Last Name");

                               while (resultSet4.next()) {
                                   String patientID = resultSet4.getString(1);
                                   if (resultSet4.wasNull()) { patientID = " "; }
                                   String firstName = resultSet4.getString(2);
                                   if (resultSet4.wasNull()) { firstName = " "; }
                                   String lastName = resultSet4.getString(3);
                                   if (resultSet4.wasNull()) { lastName = " "; }
                                   System.out.format(format4, patientID, firstName, lastName + "\n");
                               }
                               System.out.println("");
                               break;
                           case 5:
                               Scanner pidInput = new Scanner(System.in);
                               String given = "";
                               String format5 = "| %-11s | %-11s | %-16s | %-15s";
                               System.out.println("For a given patient (either patient identification number" +
                                       " or name), list all admissions to the hospital along with the diagnosis" +
                                       " for each admission.");
                               System.out.print("Enter requested Patient ID (Valid PIDs are 1-6): ");
                               given = pidInput.next();
                               ResultSet resultSet5 = statement.executeQuery("SELECT p.first_name, p.last_name," +
                                       " p.admit_date, t.diagnosis FROM treatment t INNER JOIN patient p\n" +
                                       "ON t.patient_id = p.patient_id WHERE p.patient_id LIKE '" + given + "' \n");
                               System.out.println("First Name" + " --- " + "Last Name"
                                       + " --- " + "Admission Date" + " --- " + "Diagnosis");

                               while (resultSet5.next()) {
                                   String firstName = resultSet5.getString(1);
                                   if (resultSet5.wasNull()) { firstName = " "; }
                                   String lastName = resultSet5.getString(2);
                                   if (resultSet5.wasNull()) { lastName = " "; }
                                   String admitDate = resultSet5.getString(3);
                                   if (resultSet5.wasNull()) { admitDate = " "; }
                                   String diagnosis = resultSet5.getString(4);
                                   if (resultSet5.wasNull()) { diagnosis = " "; }
                                   System.out.format(format5, firstName, lastName, admitDate, diagnosis + "\n");
                               }
                               System.out.println("");
                               break;
                           case 6:
                               Scanner firstInput = new Scanner(System.in);
                               String givenFirst = "";
                               String format6 = "| %-11s | %-11s | %-12s | %-16s | %-12s | %-12s | %-5s";
                               System.out.println("For a given patient (either patient identification number or " +
                                       "name), list all treatments that were administered. Group treatments by" +
                                       " admissions. List admissions in descending chronological order, and list " +
                                       "treatments in ascending chronological order within each admission");
                               System.out.print("Enter requested First Name (A valid example name is Ben): ");
                               givenFirst = firstInput.next();
                               ResultSet resultSet6 = statement.executeQuery("SELECT p.first_name, p.last_name, " +
                                               "p.patient_id, p.admit_date, t.diagnosis, t.treatment_name, " +
                                               "t.timestamp FROM treatment t INNER JOIN patient p ON t.patient_id " +
                                               "= p.patient_id WHERE p.first_name LIKE '%" + givenFirst + "%' " +
                                       "ORDER BY t.timestamp");
                               System.out.println("First Name" + " --- " + "Last Name"
                                       + " --- " + "Patient ID" + " --- " + "Admission Date" + " --- " + "Diagnosis"
                                       + " --- " + "Treatment" + " --- " + "Timestamp");

                               while (resultSet6.next()) {
                                   String firstName = resultSet6.getString(1);
                                   if (resultSet6.wasNull()) { firstName = " "; }
                                   String lastName = resultSet6.getString(2);
                                   if (resultSet6.wasNull()) { lastName = " "; }
                                   String patientID = resultSet6.getString(3);
                                   if (resultSet6.wasNull()) { patientID = " "; }
                                   String admitDate = resultSet6.getString(4);
                                   if (resultSet6.wasNull()) { admitDate = " "; }
                                   String diagnosis = resultSet6.getString(5);
                                   if (resultSet6.wasNull()) { diagnosis = " "; }
                                   String treatment = resultSet6.getString(6);
                                   if (resultSet6.wasNull()) { treatment = " "; }
                                   String timestamp = resultSet6.getString(7);
                                   if (resultSet6.wasNull()) { timestamp = " "; }
                                   System.out.format(format6, firstName, lastName, patientID, admitDate,
                                           diagnosis, treatment, timestamp + "\n");
                               }
                               System.out.println("");
                               break;
                           case 7:
                               ResultSet resultSet7 = statement.executeQuery("SELECT p.patient_id, p.first_name," +
                                       " p.last_name,  t.diagnosis, d.doctor_id, d.emp_first_name, d.emp_last_name, " +
                                       "p.discharge_date\n" +
                                       "FROM patient p JOIN doctor d\n" +
                                       "ON p.doctor_id = d.doctor_id\n" +
                                       "JOIN treatment t\n" +
                                       "ON t.patient_id = p.patient_id\n" +
                                       "WHERE DATEDIFF(p.discharge_date,p.admit_date) < 30\n" +
                                       "AND t.patient_id = p.patient_id GROUP BY p.patient_id\n");
                               String format7 = "| %-11s | %-12s | %-11s | %-13s | %-12s | %-16s | %-16s | %-1s";
                               System.out.println("List patients who were admitted to the hospital within 30 days of" +
                                       " their last discharge date. For each patient list their patient " +
                                       "identification number, name, diagnosis, and admitting doctor.");
                               System.out.println("Patient ID" + " --- " + "First Name" + " --- " + "Last Name"
                                       + " --- " + "Diagnosis" + " --- " + "Doctor ID" + " --- " + "Doctor First Name" +
                                       " --- " + "Doctor Last Name" + " --- " + "Discharge Date");

                               while (resultSet7.next()) {
                                   String patientID = resultSet7.getString(1);
                                   if (resultSet7.wasNull()) { patientID = " "; }
                                   String firstName = resultSet7.getString(2);
                                   if (resultSet7.wasNull()) { firstName = " "; }
                                   String lastName = resultSet7.getString(3);
                                   if (resultSet7.wasNull()) { lastName = " "; }
                                   String diagnosis = resultSet7.getString(4);
                                   if (resultSet7.wasNull()) { diagnosis = " "; }
                                   String doctorID = resultSet7.getString(5);
                                   if (resultSet7.wasNull()) { doctorID = " "; }
                                   String doctorFirstName = resultSet7.getString(6);
                                   if (resultSet7.wasNull()) { doctorFirstName = " "; }
                                   String doctorLastName = resultSet7.getString(7);
                                   if (resultSet7.wasNull()) { doctorLastName = " "; }
                                   String dischargeDate = resultSet7.getString(8);
                                   if (resultSet7.wasNull()) { dischargeDate = " "; }
                                   System.out.format(format7, patientID, firstName, lastName, diagnosis, doctorID,
                                           doctorFirstName, doctorLastName, dischargeDate + "\n");
                               }
                               System.out.println("");
                               break;
                           case 8:
                               String format81 = "| %-11s | %-12s | %-11s | %-15s | %-1s";
                               System.out.println("For each patient that has ever been admitted to the hospital, " +
                                       "list their total number of admissions,average duration of each admission, " +
                                       "longest span between admissions, shortest span between admissions, and " +
                                       "average span between admissions. \n");
                               System.out.println("Total Number of Admissions");
                               ResultSet resultSet81 = statement.executeQuery("SELECT p.patient_id, p.first_name, " +
                                       "p.last_name, a.admit_id, COUNT(p.first_name) AS 'Number of Admissions'\n" +
                                       "FROM \n" +
                                       "\t\tpatient p LEFT JOIN\n" +
                                       "\t\tadmit a ON p.patient_id = a.patient_id\n" +
                                       "GROUP BY p.first_name\n");
                               System.out.println("Patient ID" + " --- " + "First Name" + " --- " + "Last Name"
                                       + " --- " + "Admission ID" + " --- " + "Number of Admissions");

                               while (resultSet81.next()) {
                                   String patientID = resultSet81.getString(1);
                                   if (resultSet81.wasNull()) { patientID = " "; }
                                   String firstName = resultSet81.getString(2);
                                   if (resultSet81.wasNull()) { firstName = " "; }
                                   String lastName = resultSet81.getString(3);
                                   if (resultSet81.wasNull()) { lastName = " "; }
                                   String admissionID = resultSet81.getString(4);
                                   if (resultSet81.wasNull()) { admissionID = " "; }
                                   String numAdmissions = resultSet81.getString(5);
                                   if (resultSet81.wasNull()) { numAdmissions = " "; }
                                   System.out.format(format81, patientID, firstName, lastName, admissionID,
                                           numAdmissions + "\n");
                               }
                               System.out.println("");
                               System.out.println("Press enter for next table.");
                               Scanner scanner = new Scanner(System.in);
                               scanner.nextLine();

                               String format82 = "| %-11s | %-11s | %-22s | %-18s | %-1s";
                               System.out.println("Average duration of each admission and " +
                                       "Longest span Between admissions");
                               ResultSet resultSet82 = statement.executeQuery("SELECT P.first_name, P.last_name, " +
                                       "COUNT(P.admit_date) as 'Number of Admissions', " +
                                       "AVG(datediff(D.discharge_date, A.admit_date)) AS 'Average', \n" +
                                       "DATEDIFF(MAX(A.admit_date),MIN(A.admit_date)) AS 'Longest'\n" +
                                       "FROM admit as A, discharge as D, patient as P \n" +
                                       "WHERE A.admit_id = D.admit_id \n" +
                                       "AND A.patient_id = P.patient_id GROUP BY P.first_name;\n");
                               System.out.println("First Name" + " --- " + "Last Name"
                                       + " --- " + "Number of Admissions" + " --- " + "Average Duration" + " --- "
                                       + "Longest Span");

                               while (resultSet82.next()) {
                                   String firstName = resultSet82.getString(1);
                                   if (resultSet82.wasNull()) { firstName = " "; }
                                   String lastName = resultSet82.getString(2);
                                   if (resultSet82.wasNull()) { lastName = " "; }
                                   String numAdmissions = resultSet82.getString(3);
                                   if (resultSet82.wasNull()) { numAdmissions = " "; }
                                   String avgDuration = resultSet82.getString(4);
                                   if (resultSet82.wasNull()) { avgDuration = " "; }
                                   String longest = resultSet82.getString(5);
                                   if (resultSet82.wasNull()) { longest = " "; }
                                   System.out.format(format82, firstName, lastName, numAdmissions, avgDuration,
                                           longest + "\n");
                               }
                               System.out.println("");
                               System.out.println("Press enter for next table.");
                               scanner.nextLine();

                               String format83 = "| %-1s";
                               System.out.println("Shortest span between admissions");
                               ResultSet resultSet83 = statement.executeQuery("SELECT P.first_name, P.last_name, " +
                                       "COUNT(P.admit_date) as 'Number of Admissions', " +
                                       "AVG(datediff(D.discharge_date, A.admit_date)) AS 'Average', \n" +
                                       "DATEDIFF(MAX(A.admit_date),MIN(A.admit_date)) AS 'Longest'\n" +
                                       "FROM admit as A, discharge as D, patient as P \n" +
                                       "WHERE A.admit_id = D.admit_id \n" +
                                       "AND A.patient_id = P.patient_id GROUP BY P.first_name;\n");
                               System.out.println("Shortest Span");

                               while (resultSet83.next()) {
                                   String shortest = resultSet83.getString(1);
                                   if (resultSet83.wasNull()) { shortest = " "; }
                                   System.out.format(format83, shortest + "\n");
                               }
                               System.out.println("");
                               break;
                           case 9:
                               exit = true;
                               break;
                           default:
                               System.out.println("Not a valid choice! Enter a numerical value matching " +
                                       "your selection from the menu. \n");
                               exit = true;
                               break;
                       }
                       break;
                   case 3:
                       System.out.println("----- Diagnosis and Treatment Information ----- \n" +
                               "1) List the diagnoses given to patients, in descending order of occurrences. " +
                               "List diagnosis identification number, name, and total occurrences of each " +
                               "diagnosis.\n" +
                               "2) List the diagnoses given to hospital patients, in descending order of " +
                               "occurrences. List diagnosis identification number, name, and total occurrences of " +
                               "each diagnosis.\n" +
                               "3) List the treatments performed on admitted patients, in descending order of " +
                               "occurrences. List treatment identification number, name, and total number of " +
                               "occurrences of each treatment.\n" +
                               "4) List the diagnoses associated with patients who have the highest occurrences of " +
                               "admissions to the hospital, in ascending order or correlation.\n" +
                               "5) For a given treatment occurrence, list the patient name and the doctor who " +
                               "ordered the treatment.\n" +
                               "6) Exit");
                       finalUserSelection = input.nextInt();

                       switch (finalUserSelection) {
                           case 1:
                               ResultSet resultSet1 = statement.executeQuery("SELECT p.first_name, p.last_name,  " +
                                       "t.diagnosis, COUNT(t.diagnosis) AS 'Number of Diagnosis Occurrences'\n" +
                                       "FROM treatment t, patient p\n" +
                                       "WHERE p.patient_id = t.patient_id GROUP BY t.diagnosis\n" +
                                       "ORDER BY COUNT(t.diagnosis) DESC\n");
                               String format1 = "| %-11s | %-11s | %-12s | %-1s";
                               System.out.println("List the diagnoses given to patients, in descending order of " +
                                       "occurrences. List diagnosis identification number, name, and total " +
                                       "occurrences of each diagnosis.");
                               System.out.println("First Name" + " --- " + "Last Name"
                                       + " --- " + "Diagnosis" + " --- " + "Number of Diagnosis Occurrences");

                               while (resultSet1.next()) {
                                   String firstName = resultSet1.getString(1);
                                   if (resultSet1.wasNull()) { firstName = " "; }
                                   String lastName = resultSet1.getString(2);
                                   if (resultSet1.wasNull()) { lastName = " "; }
                                   String diagnosis = resultSet1.getString(3);
                                   if (resultSet1.wasNull()) { diagnosis = " "; }
                                   String diagnosisOcc = resultSet1.getString(4);
                                   if (resultSet1.wasNull()) { diagnosisOcc = " "; }
                                   System.out.format(format1, firstName, lastName, diagnosis, diagnosisOcc + "\n");
                               }
                               System.out.println("");
                               break;
                           case 2:
                               ResultSet resultSet2 = statement.executeQuery("SELECT p.first_name, p.last_name, " +
                                       "t.diagnosis, COUNT(t.diagnosis) AS 'Number of Diagnosis Occurrences'\n" +
                                       "FROM treatment t, patient p\n" +
                                       "WHERE p.discharge_date IS NULL\n" +
                                       "AND p.patient_id = t.patient_id GROUP BY t.diagnosis\n" +
                                       "ORDER BY COUNT(t.diagnosis) DESC\n");
                               String format2 = "| %-11s | %-11s | %-12s | %-1s";
                               System.out.println("List the diagnoses given to hospital patients, in descending " +
                                       "order of occurrences. List diagnosis identification number, name, and " +
                                       "total occurrences of each diagnosis.");
                               System.out.println("First Name" + " --- " + "Last Name"
                                       + " --- " + "Diagnosis" + " --- " + "Number of Diagnosis Occurrences");

                               while (resultSet2.next()) {
                                   String firstName = resultSet2.getString(1);
                                   if (resultSet2.wasNull()) { firstName = " "; }
                                   String lastName = resultSet2.getString(2);
                                   if (resultSet2.wasNull()) { lastName = " "; }
                                   String diagnosis = resultSet2.getString(3);
                                   if (resultSet2.wasNull()) { diagnosis = " "; }
                                   String diagnosisOcc = resultSet2.getString(4);
                                   if (resultSet2.wasNull()) { diagnosisOcc = " "; }
                                   System.out.format(format2, firstName, lastName, diagnosis, diagnosisOcc + "\n");
                               }
                               System.out.println("");
                               break;
                           case 3:
                               ResultSet resultSet3 = statement.executeQuery("SELECT p.first_name, p.last_name, " +
                                       "t.treatment_name, COUNT(t.treatment_name) AS 'Number of Treatment Occurrences'\n" +
                                       "FROM treatment t, patient p\n" +
                                       "WHERE p.discharge_date IS NULL\n" +
                                       "AND p.patient_id = t.patient_id GROUP BY t.diagnosis\n" +
                                       "ORDER BY COUNT(t.diagnosis) DESC\n");
                               String format3 = "| %-11s | %-11s | %-12s | %-1s";
                               System.out.println("List the treatments performed on admitted patients, in " +
                                       "descending order of occurrences. List treatment identification number, " +
                                       "name, and total number of occurrences of each treatment.");
                               System.out.println("First Name" + " --- " + "Last Name"
                                       + " --- " + "Treatment" + " --- " + "Number of Treatment Occurrences");

                               while (resultSet3.next()) {
                                   String firstName = resultSet3.getString(1);
                                   if (resultSet3.wasNull()) { firstName = " "; }
                                   String lastName = resultSet3.getString(2);
                                   if (resultSet3.wasNull()) { lastName = " "; }
                                   String diagnosis = resultSet3.getString(3);
                                   if (resultSet3.wasNull()) { diagnosis = " "; }
                                   String treatmentOcc = resultSet3.getString(4);
                                   if (resultSet3.wasNull()) { treatmentOcc = " "; }
                                   System.out.format(format3, firstName, lastName, diagnosis, treatmentOcc + "\n");
                               }
                               System.out.println("");
                               break;
                           case 4:
                               ResultSet resultSet4 = statement.executeQuery("SELECT p.first_name, p.last_name, " +
                                       "t.diagnosis, COUNT(p.admit_date) AS 'Number of Admits'\n" +
                                       "FROM treatment t, patient p\n" +
                                       "WHERE p.patient_id = t.patient_id GROUP BY p.first_name\n" +
                                       "ORDER BY COUNT(p.admit_date) DESC LIMIT 1\n");
                               String format4 = "| %-11s | %-11s | %-12s | %-1s";
                               System.out.println("List the diagnoses associated with patients who have the " +
                                       "highest occurrences of admissions to the hospital, in ascending " +
                                       "order or correlation.");
                               System.out.println("First Name" + " --- " + "Last Name"
                                       + " --- " + "Diagnosis" + " --- " + "Number of Admissions");

                               while (resultSet4.next()) {
                                   String firstName = resultSet4.getString(1);
                                   if (resultSet4.wasNull()) { firstName = " "; }
                                   String lastName = resultSet4.getString(2);
                                   if (resultSet4.wasNull()) { lastName = " "; }
                                   String diagnosis = resultSet4.getString(3);
                                   if (resultSet4.wasNull()) { diagnosis = " "; }
                                   String numAdmits = resultSet4.getString(4);
                                   if (resultSet4.wasNull()) { numAdmits = " "; }
                                   System.out.format(format4, firstName, lastName, diagnosis, numAdmits + "\n");
                               }
                               System.out.println("");
                               break;
                           case 5:
                               ResultSet resultSet5 = statement.executeQuery("SELECT p.first_name, p.last_name, " +
                                       "t.treatment_name, d.emp_first_name, d.emp_last_name\n" +
                                       "FROM treatment t, patient p, doctor d\n" +
                                       "WHERE p.patient_id = t.patient_id\n" +
                                       "AND p.doctor_id = d.doctor_id\n");
                               String format5 = "| %-11s | %-11s | %-12s | %-12s | %-1s";
                               System.out.println("For a given treatment occurrence, list the patient name and " +
                                       "the doctor who ordered the treatment.");
                               System.out.println("First Name" + " --- " + "Last Name"
                                       + " --- " + "Treatment" + " --- " + "Doctor First Name" + " --- "
                                       + "Doctor Last Name");

                               while (resultSet5.next()) {
                                   String firstName = resultSet5.getString(1);
                                   if (resultSet5.wasNull()) { firstName = " "; }
                                   String lastName = resultSet5.getString(2);
                                   if (resultSet5.wasNull()) { lastName = " "; }
                                   String treatment = resultSet5.getString(3);
                                   if (resultSet5.wasNull()) { treatment = " "; }
                                   String docFirstName = resultSet5.getString(4);
                                   if (resultSet5.wasNull()) { docFirstName = " "; }
                                   String docLastName = resultSet5.getString(5);
                                   if (resultSet5.wasNull()) { docLastName = " "; }
                                   System.out.format(format5, firstName, lastName, treatment, docFirstName,
                                           docLastName + "\n");
                               }
                               System.out.println("");
                               break;
                           default:
                               System.out.println("Not a valid choice! Enter a numerical value matching " +
                                       "your selection from the menu. \n");
                               exit = true;
                               break;
                       }
                       break;
                   case 4:
                       System.out.println("----- Employee Information ----- \n" +
                               "1) List all workers at the hospital, in ascending last name, first name order. For " +
                               "each worker, list their, name, and job category.\n" +
                               "2) List the primary doctors of patients with a high admission rate (at least 4 " +
                               "admissions within a one-year time frame).\n" +
                               "3) For a given doctor, list all associated diagnoses in descending order of " +
                               "occurrence. For each diagnosis, list the total number of occurrences for the " +
                               "given doctor.\n" +
                               "4) For a given doctor, list all treatments that they ordered in descending order " +
                               "of occurrence. For each treatment, list the total number of occurrences for the " +
                               "given doctor.\n" +
                               "5) List employees who have been involved in the treatment of every admitted " +
                               "patient.\n" +
                               "6) Exit");
                       finalUserSelection = input.nextInt();

                       switch (finalUserSelection) {
                           case 1:
                               ResultSet resultSet1 = statement.executeQuery("SELECT job_category as " +
                                       "'Job Category', admin_last_name, admin_first_name as 'First Name'\n" +
                                       "FROM administrator\n" +
                                       "UNION ALL\n" +
                                       "SELECT job_category, emp_first_name, emp_last_name\n" +
                                       "FROM doctor\n" +
                                       "UNION ALL\n" +
                                       "SELECT job_category, nurse_first_name, nurse_last_name\n" +
                                       "FROM nurse\n" +
                                       "UNION ALL\n" +
                                       "SELECT job_category, tech_first_name, tech_last_name\n" +
                                       "FROM technician\n" +
                                       "ORDER BY admin_last_name\n");
                               String format1 = "| %-15s | %-11s | %-1s";
                               System.out.println("List all workers at the hospital, in ascending last name, first " +
                                       "name order. For each worker, list their name, and job category.");
                               System.out.println("Job Category" + " --- " + "Last Name" + " --- " + "First Name");

                               while (resultSet1.next()) {
                                   String jobCategory = resultSet1.getString(1);
                                   if (resultSet1.wasNull()) { jobCategory = " "; }
                                   String firstName = resultSet1.getString(2);
                                   if (resultSet1.wasNull()) { firstName = " "; }
                                   String lastName = resultSet1.getString(3);
                                   if (resultSet1.wasNull()) { lastName = " "; }
                                   System.out.format(format1, jobCategory, firstName, lastName + "\n");
                               }
                               System.out.println("");
                               break;
                           case 2:
                               ResultSet resultSet2 = statement.executeQuery("SELECT p.patient_id, p.first_name, " +
                                       "p.last_name, a.admit_id, (COUNT(p.first_name)) >= 4 AS " +
                                       "'Number of Admissions', d.emp_first_name, d.emp_last_name\n" +
                                       "FROM patient p, admit a, doctor d\n" +
                                       "WHERE a.patient_id = p.patient_id\n" +
                                       "AND d.doctor_id = p.doctor_id\n" +
                                       "GROUP BY p.first_name\n");
                               String format2 = "| %-11s | %-12s | %-11s | %-13s | %-26s | %-16s | %-1s";
                               System.out.println("List the primary doctors of patients with a high admission rate " +
                                       "(at least 4 admissions within a one-year time frame).");
                               System.out.println("Patient ID" + " --- " + "First Name" + " --- " + "Last Name"
                                    + " --- " + "Admission ID" + " --- " + "Number of Admissions 4+" + " --- "
                                    + "Doctor First Name" + " --- " + "Doctor Last Name");

                               while (resultSet2.next()) {
                                   String patientID = resultSet2.getString(1);
                                   if (resultSet2.wasNull()) { patientID = " "; }
                                   String firstName = resultSet2.getString(2);
                                   if (resultSet2.wasNull()) { firstName = " "; }
                                   String lastName = resultSet2.getString(3);
                                   if (resultSet2.wasNull()) { lastName = " "; }
                                   String admitID = resultSet2.getString(4);
                                   if (resultSet2.wasNull()) { admitID = " "; }
                                   String numAdmissions = resultSet2.getString(5);
                                   if (resultSet2.wasNull()) { numAdmissions = " "; }
                                   String docFirstName = resultSet2.getString(6);
                                   if (resultSet2.wasNull()) { docFirstName = " "; }
                                   String docLastName = resultSet2.getString(7);
                                   if (resultSet2.wasNull()) { docLastName = " "; }
                                   System.out.format(format2, patientID, firstName, lastName, admitID, numAdmissions,
                                           docFirstName, docLastName + "\n");
                               }
                               System.out.println("");
                               break;
                           case 3:
                               ResultSet resultSet3 = statement.executeQuery("SELECT p.first_name, p.last_name, " +
                                       "t.diagnosis, COUNT(t.diagnosis) AS 'Number of Diagnosis', d.emp_first_name, " +
                                       "d.emp_last_name\n" +
                                       "FROM treatment t, patient p, doctor d\n" +
                                       "WHERE p.patient_id = t.patient_id \n" +
                                       "AND p.doctor_id = d.doctor_id\n" +
                                       "GROUP BY t.diagnosis\n" +
                                       "ORDER BY COUNT(t.diagnosis) DESC\n");
                               String format3 = "| %-11s | %-12s | %-16s | %-16s | %-16s | %-1s";
                               System.out.println("For a given doctor, list all associated diagnoses in " +
                                       "descending order of occurrence. For each diagnosis, list the total number " +
                                       "of occurrences for the given doctor.");
                               System.out.println("First Name" + " --- " + "Last Name" + " --- " + "Diagnosis"
                                       + " --- " + "Number of Diagnosis" + " --- "
                                       + "Doctor First Name" + " --- " + "Doctor Last Name");

                               while (resultSet3.next()) {
                                   String firstName = resultSet3.getString(1);
                                   if (resultSet3.wasNull()) { firstName = " "; }
                                   String lastName = resultSet3.getString(2);
                                   if (resultSet3.wasNull()) { lastName = " "; }
                                   String diagnosis = resultSet3.getString(3);
                                   if (resultSet3.wasNull()) { diagnosis = " "; }
                                   String numDiagnosis = resultSet3.getString(4);
                                   if (resultSet3.wasNull()) { numDiagnosis = " "; }
                                   String docFirstName = resultSet3.getString(5);
                                   if (resultSet3.wasNull()) { docFirstName = " "; }
                                   String docLastName = resultSet3.getString(6);
                                   if (resultSet3.wasNull()) { docLastName = " "; }
                                   System.out.format(format3, firstName, lastName, diagnosis, numDiagnosis,
                                           docFirstName, docLastName + "\n");
                               }
                               System.out.println("");
                               break;
                           case 4:
                               ResultSet resultSet4 = statement.executeQuery("SELECT p.first_name, " +
                                       "p.last_name, t.treatment_name, COUNT(t.treatment_name) AS 'Number of " +
                                       "Diagnosis', d.emp_first_name, d.emp_last_name\n" +
                                       "FROM treatment t, patient p, doctor d\n" +
                                       "WHERE p.patient_id = t.patient_id \n" +
                                       "AND p.doctor_id = d.doctor_id\n" +
                                       "GROUP BY t.treatment_name\n" +
                                       "ORDER BY COUNT(t.treatment_name) DESC\n");
                               String format4 = "| %-11s | %-12s | %-16s | %-16s | %-16s | %-1s";
                               System.out.println("For a given doctor, list all treatments that they ordered in " +
                                       "descending order of occurrence. For each treatment, list the total number " +
                                       "of occurrences for the given doctor.");
                               System.out.println("First Name" + " --- " + "Last Name" + " --- " + "Treatment"
                                       + " --- " + "Number of Treatments" + " --- "
                                       + "Doctor First Name" + " --- " + "Doctor Last Name");

                               while (resultSet4.next()) {
                                   String firstName = resultSet4.getString(1);
                                   if (resultSet4.wasNull()) { firstName = " "; }
                                   String lastName = resultSet4.getString(2);
                                   if (resultSet4.wasNull()) { lastName = " "; }
                                   String treatment = resultSet4.getString(3);
                                   if (resultSet4.wasNull()) { treatment = " "; }
                                   String numTreatments = resultSet4.getString(4);
                                   if (resultSet4.wasNull()) { numTreatments = " "; }
                                   String docFirstName = resultSet4.getString(5);
                                   if (resultSet4.wasNull()) { docFirstName = " "; }
                                   String docLastName = resultSet4.getString(6);
                                   if (resultSet4.wasNull()) { docLastName = " "; }
                                   System.out.format(format4, firstName, lastName, treatment, numTreatments,
                                           docFirstName, docLastName + "\n");
                               }
                               System.out.println("");
                               break;
                           case 5:
                               ResultSet resultSet5 = statement.executeQuery("SELECT p.patient_id, p.first_name, " +
                                       "p.last_name, d.emp_first_name, d.emp_last_name, n.nurse_first_name, " +
                                       "n.nurse_last_name, c.tech_first_name, c.tech_last_name, a.admin_first_name, " +
                                       "a.admin_last_name\n" +
                                       "FROM patient p JOIN doctor d\n" +
                                       "ON p.doctor_id = d.doctor_id\n" +
                                       "JOIN treatment t\n" +
                                       "ON t.patient_id = p.patient_id\n" +
                                       "JOIN nurse n ON n.nurse_id = p.nurse_id\n" +
                                       "JOIN administrator a ON a.admin_id = p.admin_id\n" +
                                       "JOIN technician c ON c.tech_id = p.tech_id\n" +
                                       "WHERE t.patient_id = p.patient_id \n" +
                                       "GROUP BY p.patient_id\n");
                               String format5 = "| %-11s | %-12s | %-11s | %-19s | %-18s | %-18s | %-17s | %-17s | " +
                                       "%-16s | %-17s | %-1s";
                               System.out.println("List employees who have been involved in the treatment of " +
                                       "every admitted patient.");
                               System.out.println("Patient ID" + " --- " + "First Name" + " --- " + "Last Name"
                                       + " --- " + "Doctor First Name" + " --- "
                                       + "Doctor Last Name" + " --- " + "Nurse First Name" + " --- "
                                       + "Nurse Last Name" + " --- " + "Tech First Name" + " --- " + "Tech Last Name"
                                       + " --- " + "Admin First Name" + " --- " + "Admin Last Name");

                               while (resultSet5.next()) {
                                   String patientID = resultSet5.getString(1);
                                   if (resultSet5.wasNull()) { patientID = " "; }
                                   String firstName = resultSet5.getString(2);
                                   if (resultSet5.wasNull()) { firstName = " "; }
                                   String lastName = resultSet5.getString(3);
                                   if (resultSet5.wasNull()) { lastName = " "; }
                                   String docFirstName = resultSet5.getString(4);
                                   if (resultSet5.wasNull()) { docFirstName = " "; }
                                   String docLastName = resultSet5.getString(5);
                                   if (resultSet5.wasNull()) { docLastName = " "; }
                                   String nurseFirstName = resultSet5.getString(6);
                                   if (resultSet5.wasNull()) { nurseFirstName = " "; }
                                   String nurseLastName = resultSet5.getString(7);
                                   if (resultSet5.wasNull()) { nurseLastName = " "; }
                                   String techFirstName = resultSet5.getString(8);
                                   if (resultSet5.wasNull()) { techFirstName = " "; }
                                   String techLastName = resultSet5.getString(9);
                                   if (resultSet5.wasNull()) { techLastName = " "; }
                                   String adminFirstName = resultSet5.getString(10);
                                   if (resultSet5.wasNull()) { adminFirstName = " "; }
                                   String adminLastName = resultSet5.getString(11);
                                   if (resultSet5.wasNull()) { adminLastName = " "; }
                                   System.out.format(format5, patientID, firstName, lastName, docFirstName,
                                           docLastName, nurseFirstName, nurseLastName, techFirstName,
                                           techLastName, adminFirstName, adminLastName + "\n");
                               }
                               System.out.println("");
                               break;
                           default:
                               System.out.println("Not a valid choice! Enter a numerical value matching " +
                                       "your selection from the menu. \n");
                               exit = true;
                               break;
                       }
                       break;

               }
           } while (!exit);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public static int menu() {
        int selection;
        Scanner input = new Scanner(System.in);

        // Menu Information
        System.out.println("Welcome! \nPlease choose a menu option: ");
        System.out.println("1) Room Utilization \n" +
                "2) Patient Information \n" +
                "3) Diagnosis and Treatment Information \n" +
                "4) Employee Information");

        selection = input.nextInt();
        return selection;

    }


}
